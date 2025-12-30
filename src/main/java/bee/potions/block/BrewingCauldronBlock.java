package bee.potions.block;

import bee.potions.block.entity.BrewingCauldronBlockEntity;
import bee.potions.registry.LiquamentumTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class BrewingCauldronBlock extends BaseEntityBlock {
    public BrewingCauldronBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (level.getBlockEntity(blockPos) instanceof BrewingCauldronBlockEntity brewingCauldronBlockEntity) {
            if (itemStack.is(LiquamentumTags.BREWABLE)) {
                brewingCauldronBlockEntity.addIngredient(itemStack, player);
                return InteractionResult.SUCCESS;
            }
            }

        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (level.getBlockEntity(blockPos) instanceof BrewingCauldronBlockEntity brewingCauldronBlockEntity && player.isCrouching()) {
            List<Holder<MobEffect>> effectsToApply = brewingCauldronBlockEntity.drinkFromCauldron(player, level);
            for (Holder<MobEffect> effect : effectsToApply) {
                player.addEffect(new MobEffectInstance(effect, 30, 30));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(BrewingStandBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BrewingCauldronBlockEntity(blockPos, blockState);
    }


}
