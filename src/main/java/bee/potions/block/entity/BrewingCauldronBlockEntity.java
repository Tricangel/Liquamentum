package bee.potions.block.entity;

import bee.potions.Liquamentum;
import bee.potions.ingredienttype.IngredientTypes;
import bee.potions.ingredienttype.OreEffects;
import bee.potions.ingredienttype.RockEffects;
import bee.potions.registry.LiquamentumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrewingCauldronBlockEntity extends BlockEntity implements Clearable {
    private final NonNullList<ItemStack> ingredients;
    public BrewingCauldronBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(LiquamentumBlockEntities.BREWING_CAULDRON_BLOCK_ENTITY, blockPos, blockState);
        this.ingredients = NonNullList.withSize(4, ItemStack.EMPTY);
    }

    public NonNullList<ItemStack> getIngredients() {
        return ingredients;
    }

    public List<Holder<MobEffect>> drinkFromCauldron(Player player, Level level) {
        List<Holder<MobEffect>> effectsToApply = new ArrayList<>();
        if (!level.isClientSide()) {
            for (ItemStack stack : ingredients) {
                if (!stack.isEmpty()) {
                    effectsToApply.add(getEffect(stack));
                }
            }

            return effectsToApply;
        }
        return effectsToApply;
    }

    public void addIngredient(ItemStack stack, Player player) {
        if (!stack.isEmpty()) {
            for (int i = 0; i < ingredients.size(); i++) {
                if (ingredients.get(i).isEmpty()) {
                    ingredients.set(i, stack.consumeAndReturn(1, player));
                    setChanged();
                    break;
                }
            }
        }
    }

    public static IngredientTypes getIngredientType(ItemStack stack) {
        if (stack.is(ItemTags.STONE_CRAFTING_MATERIALS)) return IngredientTypes.ROCK;
        else return IngredientTypes.END;
    }

    public static Holder<MobEffect> getEffect(ItemStack stack) {
        RockEffects rockEffects = new RockEffects();
        OreEffects oreEffects = new OreEffects();

        Map<IngredientTypes,  Holder<MobEffect>> POSITIVE_EFFECT_POOL = Map.of(
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
                );



        return POSITIVE_EFFECT_POOL.get(getIngredientType(stack));

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
