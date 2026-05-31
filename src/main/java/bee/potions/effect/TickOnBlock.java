package bee.potions.effect;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;

public class TickOnBlock extends ShouldTick{
    Block block;
    TagKey<Block> blockTag;

    public TickOnBlock(String name, Block block) {
        super(name);
        this.block = block;
    }

    public TickOnBlock(String name,TagKey<Block> tag) {
        super(name);
        this.blockTag = tag;
    }

    @Override
    public boolean canTick(LivingEntity livingEntity) {
        if (block != null) return livingEntity.level().getBlockState(livingEntity.getOnPos()).is(block) || livingEntity.level().getBlockState(livingEntity.blockPosition()).is(block);
        else return livingEntity.level().getBlockState(livingEntity.getOnPos()).is(blockTag) || livingEntity.level().getBlockState(livingEntity.blockPosition()).is(blockTag);


    }
}
