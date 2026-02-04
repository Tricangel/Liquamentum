package bee.potions.item;

import bee.potions.block.entity.BrewingCauldronBlockEntity;
import bee.potions.registry.LiquamentumComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.AbstractThrownPotion;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownLingeringPotion;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class PotionVialItem extends Item {
    public PotionVialItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 35;
    }


    @Override
    public boolean overrideOtherStackedOnMe(ItemStack itemStack, ItemStack itemStack2, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess) {
        if (clickAction == ClickAction.SECONDARY && itemStack.get(DataComponents.POTION_CONTENTS) != null) {
            itemStack.set(LiquamentumComponents.THROWABLE, !itemStack.getOrDefault(LiquamentumComponents.THROWABLE, false));
            if (player.level().isClientSide()) {
                player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 1, 0.25f);
            }
            return true;
        }
        return super.overrideOtherStackedOnMe(itemStack, itemStack2, slot, clickAction, player, slotAccess);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();
        Player player = useOnContext.getPlayer();
        ItemStack stack = player.getItemInHand(useOnContext.getHand());

        if (stack.get(DataComponents.POTION_CONTENTS) == null) {

            if (level.getBlockEntity(useOnContext.getClickedPos()) instanceof BrewingCauldronBlockEntity brewingCauldronBlockEntity) {
                stack = brewingCauldronBlockEntity.applyEffects(stack);
                return InteractionResult.SUCCESS;
            }
        }

        return super.useOn(useOnContext);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        if (stack.get(DataComponents.POTION_CONTENTS) == null) return InteractionResult.FAIL;
        if (stack.get(LiquamentumComponents.THROWABLE) == null) return InteractionResult.FAIL;
        if (!stack.get(LiquamentumComponents.THROWABLE)) {

            player.startUsingItem(interactionHand);
            return InteractionResult.FAIL;
        }

        if (level.isClientSide()) return InteractionResult.FAIL;
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SPLASH_POTION_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        Projectile.spawnProjectileFromRotation(this::createPotion, (ServerLevel) level, stack, player, -20.0f, 0.75f, 1.0f);
        player.awardStat(Stats.ITEM_USED.get(this));
        stack.consume(1, player);
        return InteractionResult.SUCCESS;

    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int i) {
        super.onUseTick(level, livingEntity, itemStack, i);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide()) {
            if (itemStack.get(DataComponents.POTION_CONTENTS) != null) {
                itemStack.get(DataComponents.POTION_CONTENTS).applyToLivingEntity(livingEntity, 12);
                itemStack.hurtAndBreak(1, livingEntity, livingEntity.getUsedItemHand());
            }

        }
        return super.finishUsingItem(itemStack, level, livingEntity);
    }


    protected AbstractThrownPotion createPotion(ServerLevel serverLevel, LivingEntity livingEntity, ItemStack itemStack) {
        return new ThrownLingeringPotion(serverLevel, livingEntity, itemStack);
    }

    @Override
    public boolean allowComponentsUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
        return super.allowComponentsUpdateAnimation(player, hand, oldStack, newStack);
    }
}
