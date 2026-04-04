package bee.potions.cca;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class IsObscuredComponent implements Component, AutoSyncedComponent {
    private boolean isObscured;

    public boolean getValue() {
        return isObscured;
    }

    public void setValue(boolean value) {
        isObscured = value;
    }

    @Override
    public void readData(ValueInput valueInput) {
        this.isObscured = valueInput.getBooleanOr("isObscured", false);
    }

    @Override
    public void writeData(ValueOutput valueOutput) {
        valueOutput.putBoolean("isObscured", getValue());
    }
}
