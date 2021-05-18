package thenanomachi.weapons.and.tools.expanded;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SteelBlockRecipe implements Recipe<CraftingInventory> {
    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        return false;
    }
    @Override
    public ItemStack craft(CraftingInventory inventory) {
        return null;
    }
    @Override
    public boolean fits(int var1, int var2) {
        return false;
    }
    @Override
    public ItemStack getOutput() {
        return null;
    }
    @Override
    public Identifier getId() {
        return null;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }
    @Override
    public RecipeType<?> getType() {
        return null;
    }
}