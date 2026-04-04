package bee.potions.cca;

import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class VoidDraftComponent implements CommonTickingComponent, AutoSyncedComponent {
    private boolean value;
    private final LivingEntity livingEntity;

    public VoidDraftComponent(LivingEntity livingEntity) {this.livingEntity = livingEntity;}

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public void tick() {
        if (getValue()) {
            Level level = livingEntity.level();
            livingEntity.addDeltaMovement(new Vec3(0.0f, -livingEntity.getDeltaMovement().y() + 1.5f * (-livingEntity.getY() / 75.0f), 0.0f));
            livingEntity.hurtMarked = true;

            if ((livingEntity.getY() > 0 && level.dimension() != Level.OVERWORLD) || (livingEntity.getY() > -64 && level.dimension() == Level.OVERWORLD)) {
                setValue(false);
            }
            LiquamentumEntityComponents.VOIDDRAFT.sync(livingEntity);
        }

    }

    @Override
    public void readData(ValueInput valueInput) {
        this.value = valueInput.getBooleanOr("voiddraft", false);
    }

    @Override
    public void writeData(ValueOutput valueOutput) {
        valueOutput.putBoolean("voiddraft", getValue());
    }
}
