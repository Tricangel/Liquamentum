package bee.potions.block.entity.client;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class BrewingCauldronBlockEntityRenderState extends BlockEntityRenderState {
    private NonNullList<ItemStack> ingredients;

    public NonNullList<ItemStack> getIngredients() {
        return ingredients;
    }

    public void setIngredients(NonNullList<ItemStack> ingredients) {
        this.ingredients = ingredients;
    }




}
