package thenanomachi.weapons.and.tools.expanded;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class quarterstaff implements ToolMaterial{

    public static final quarterstaff INSTANCE = new quarterstaff();

    @Override
    public float getAttackDamage() {
        return 2F;
    }

    @Override
    public int getDurability() {
        return 100;
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 1.0F;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.STICK);
    }

    
}
