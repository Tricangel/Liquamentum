package bee.potions.cca;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class BooleanComponent implements Component, AutoSyncedComponent {
    String name;
    public BooleanComponent(String name) {
        this.name = name;
    }

    private boolean value;

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;

    }

    @Override
    public void readData(ValueInput valueInput) {
        this.value = valueInput.getBooleanOr(name, false);
    }

    @Override
    public void writeData(ValueOutput valueOutput) {
        valueOutput.putBoolean(name, getValue());
    }
}
