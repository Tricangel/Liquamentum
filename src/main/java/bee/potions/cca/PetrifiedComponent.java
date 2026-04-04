package bee.potions.cca;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class PetrifiedComponent implements Component, AutoSyncedComponent {

    private Boolean value = false;

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public void readData(ValueInput valueInput) {
        this.value = valueInput.getBooleanOr("isPetrified", false);
    }

    @Override
    public void writeData(ValueOutput valueOutput) {
        valueOutput.putBoolean("isPetrified", getValue());
    }
}
