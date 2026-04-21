package bee.potions.block.entity;

import bee.potions.Liquamentum;
import bee.potions.data.IngredientCategory;
import bee.potions.registry.LiquamentumBlockEntities;
import bee.potions.registry.LiquamentumRegistries;
import bee.potions.registry.LiquamentumTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrewingCauldronBlockEntity extends BlockEntity implements Clearable {
    private final NonNullList<ItemStack> ingredients;
    public BrewingCauldronBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(LiquamentumBlockEntities.BREWING_CAULDRON_BLOCK_ENTITY, blockPos, blockState);
        this.ingredients = NonNullList.withSize(4, ItemStack.EMPTY);
    }


    public Boolean addIngredient(ItemStack itemStack, Player player) {
        BlockPos pos = this.getBlockPos();
        boolean addedIngredient = false;
        if (!itemStack.isEmpty()) {
            // god i am rewriting this again it is way too much code for what is needed
                for (int i = 0; i < ingredients.size(); i++) {
                    if (ingredients.get(i).isEmpty()) {

                        if (ingredients.getFirst().isEmpty()) {
                            if (itemStack.is(LiquamentumTags.BASE_INGREDIENT)) {
                                ingredients.set(i, itemStack.consumeAndReturn(1, player));
                                setChanged();
                                addedIngredient = true;
                            }

                        } else {
                            if (ingredients.getFirst().is(LiquamentumTags.FLOWER_TYPE)) {
                                if (itemStack.is(LiquamentumTags.FLOWER_TYPE)) {
                                    ingredients.set(i, itemStack.consumeAndReturn(1, player));
                                    setChanged();
                                    addedIngredient = true;
                                }
                            } else {
                                ingredients.set(i, itemStack.consumeAndReturn(1, player));
                                setChanged();
                                addedIngredient = true;
                            }

                        }

                        if (addedIngredient) {
                            for (int j = 0; j < 30; j++) {
                                level.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + .5f, pos.getY() + .5f, pos.getZ() + .5f, 0.1, 0.1, 0.1    );
                            }
                            level.playLocalSound(player, SoundEvents.BUBBLE_POP, SoundSource.BLOCKS, 15, 1);
                            setChanged();
                            return true;
                        }

                    }
                }
        }
        return false;
    }



    public ItemStack applyEffects(ItemStack stack, ServerLevel level, Player player) {



            var registry = level.registryAccess().lookupOrThrow(LiquamentumRegistries.INGREDIENT_CATEGORIES);

            for (Holder.Reference<IngredientCategory> holder : registry.listElements().toList()) {
                IngredientCategory ingredientCategory = holder.value();
                List<MobEffectInstance> categoryEffects = new ArrayList<>();
                List<Item> categoryItems = ingredientCategory.getIngredients();
                ingredientCategory.getIngredientEffects().forEach(mobEffectHolder -> categoryEffects.add(new MobEffectInstance(mobEffectHolder, 30, 60)));

                if (stack == null) {
                    player.addEffect(categoryEffects.getFirst());
                    return null;
                }

                for (ItemStack ingredient : ingredients) {
                    if (categoryItems.contains(ingredient.getItem())) {
                        // randomization doesn't work, but getting it data driven is an accomplishment enough for me (about 12+ hours :sob:)
                        PotionContents potionContents = new PotionContents(Optional.empty(), Optional.empty(), categoryEffects, Optional.empty());
                        stack.set(DataComponents.POTION_CONTENTS, potionContents);
                    }
            }
        }


        return stack;
    }


    public NonNullList<ItemStack> getIngredients() {
        return ingredients;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        try (ProblemReporter.ScopedCollector scopedCollector = new ProblemReporter.ScopedCollector(this.problemPath(), Liquamentum.LOGGER)) {
            TagValueOutput tagValueOutput = TagValueOutput.createWithContext(scopedCollector, provider);
            ContainerHelper.saveAllItems(tagValueOutput, this.ingredients, true);
            return tagValueOutput.buildResult();
        }
    }

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        ContainerHelper.loadAllItems(valueInput, this.ingredients);
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        ContainerHelper.saveAllItems(valueOutput, this.ingredients, true);

        super.saveAdditional(valueOutput);
    }

    @Override
    public void clearContent() {
        ingredients.clear();
    }
}
