package bee.potions.block;

import bee.potions.block.entity.BrewingCauldronBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class BrewingCauldronBlock extends BaseEntityBlock {
    private static final VoxelShape SHAPE_INSIDE = Block.column((double)12.0F, (double)4.0F, (double)16.0F);
    protected static final VoxelShape SHAPE = (VoxelShape) Util.make(() -> {
        int i = 4;
        int j = 3;
        int k = 2;
        return Shapes.join(Shapes.block(), Shapes.or(Block.column((double)16.0F, (double)8.0F, (double)0.0F, (double)3.0F), new VoxelShape[]{Block.column((double)8.0F, (double)16.0F, (double)0.0F, (double)3.0F), Block.column((double)12.0F, (double)0.0F, (double)3.0F), SHAPE_INSIDE}), BooleanOp.ONLY_FIRST);
    });
    public BrewingCauldronBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getInteractionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return SHAPE_INSIDE;
    }

    @Override
    protected boolean isCollisionShapeFullBlock(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }



    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (level.getBlockEntity(blockPos) instanceof BrewingCauldronBlockEntity brewingCauldronBlockEntity) {
            if (brewingCauldronBlockEntity.addIngredient(itemStack, player)) {
                return InteractionResult.SUCCESS;
            }
            }

        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (level.getBlockEntity(blockPos) instanceof BrewingCauldronBlockEntity brewingCauldronBlockEntity && player.isCrouching()) {

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
