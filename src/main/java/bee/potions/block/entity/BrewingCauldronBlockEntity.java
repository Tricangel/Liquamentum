package bee.potions.block.entity;

import bee.potions.Liquamentum;
import bee.potions.ingredienttype.IngredientTypes;
import bee.potions.ingredienttype.OreEffects;
import bee.potions.ingredienttype.RockEffects;
import bee.potions.registry.LiquamentumBlockEntities;
import bee.potions.registry.LiquamentumEffects;
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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
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
            // god i am rewriting this again it is way to much code for what is needed
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



    public Holder<MobEffect> getCauldronEffects() {


        return null;
    }

    public ItemStack applyEffects(ItemStack stack) {
        List<MobEffectInstance> effects = new ArrayList<>();

        for (ItemStack ingredient : ingredients) {
            if (!ingredient.isEmpty()) {
                effects.add(new MobEffectInstance(getPositiveEffect(ingredient), 12232323, 0));
                effects.add(new MobEffectInstance(getNegativeEffect(ingredient), 124343243, 0));
            }
        }

        stack.set(DataComponents.POTION_CONTENTS, new PotionContents(Optional.empty(), Optional.empty(), effects, Optional.empty()));

        return stack;
    }













    public static IngredientTypes getIngredientType(ItemStack stack) {
        if (stack.is(LiquamentumTags.ROCK_TYPE)) return IngredientTypes.ROCK;
        if (stack.is(LiquamentumTags.ORE_TYPE)) return IngredientTypes.ORE;
        if (stack.is(LiquamentumTags.FLOWER_TYPE)) return IngredientTypes.NATURE;
        if (stack.is(LiquamentumTags.FIRE_TYPE)) return IngredientTypes.FIRE;
        if (stack.is(LiquamentumTags.WATER_TYPE)) return IngredientTypes.WATER;
        if (stack.is(LiquamentumTags.UNDEAD_TYPE)) return IngredientTypes.UNDEAD;
        if (stack.is(LiquamentumTags.FLESH_TYPE)) return IngredientTypes.FLESH;
        if (stack.is(LiquamentumTags.SKY_TYPE)) return IngredientTypes.SKY;
        if (stack.is(LiquamentumTags.END_TYPE)) return IngredientTypes.END;
        if (stack.is(LiquamentumTags.BOSS_TYPE)) return IngredientTypes.BOSS;
        else return IngredientTypes.MISC;
    }

    public static Holder<MobEffect> getPositiveEffect(ItemStack stack) {
        RockEffects rockEffects = new RockEffects();
        OreEffects oreEffects = new OreEffects();

        /*Map<IngredientTypes,  Holder<MobEffect>> POSITIVE_EFFECT_POOL = Map.of(
                IngredientTypes.ROCK, rockEffects.getPositiveEffect(stack),
                IngredientTypes.ORE, oreEffects.getPositiveEffect(stack),
                IngredientTypes.NATURE, oreEffects.getPositiveEffect(stack),
                IngredientTypes.FIRE, oreEffects.getPositiveEffect(stack),
                IngredientTypes.WATER, oreEffects.getPositiveEffect(stack),
                IngredientTypes.UNDEAD, oreEffects.getPositiveEffect(stack),
                IngredientTypes.FLESH, oreEffects.getPositiveEffect(stack),
                IngredientTypes.SKY, oreEffects.getPositiveEffect(stack),
                IngredientTypes.END, oreEffects.getPositiveEffect(stack),
                IngredientTypes.BOSS, oreEffects.getPositiveEffect(stack)
        );*/

        //return POSITIVE_EFFECT_POOL.get(getIngredientType(stack));
        return LiquamentumEffects.MUTE;
    }

    public static Holder<MobEffect> getNegativeEffect(ItemStack stack) {
        RockEffects rockEffects = new RockEffects();
        OreEffects oreEffects = new OreEffects();

        /*Map<IngredientTypes,  Holder<MobEffect>> NEGATIVE_EFFECT_POOL = Map.of(
                IngredientTypes.ROCK, rockEffects.getPositiveEffect(stack),
                IngredientTypes.ORE, oreEffects.getPositiveEffect(stack),
                IngredientTypes.NATURE, oreEffects.getPositiveEffect(stack),
                IngredientTypes.FIRE, oreEffects.getPositiveEffect(stack),
                IngredientTypes.WATER, oreEffects.getPositiveEffect(stack),
                IngredientTypes.UNDEAD, oreEffects.getPositiveEffect(stack),
                IngredientTypes.FLESH, oreEffects.getPositiveEffect(stack),
                IngredientTypes.SKY, oreEffects.getPositiveEffect(stack),
                IngredientTypes.END, oreEffects.getPositiveEffect(stack),
                IngredientTypes.BOSS, oreEffects.getPositiveEffect(stack)
        );*/

        //return NEGATIVE_EFFECT_POOL.get(getIngredientType(stack));
        return LiquamentumEffects.MUTE;
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
