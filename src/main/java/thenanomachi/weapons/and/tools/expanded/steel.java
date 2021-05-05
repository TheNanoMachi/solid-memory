package thenanomachi.weapons.and.tools.expanded;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class steel implements ToolMaterial{

    public static final steel INSTANCE = new steel();

    @Override
    public float getAttackDamage() {
        // TODO Auto-generated method stub
        return 6.5F;
    }

    @Override
    public int getDurability() {
        // TODO Auto-generated method stub
        return 750;
    }

    @Override
    public int getEnchantability() {
        // TODO Auto-generated method stub
        return 10;
    }

    @Override
    public int getMiningLevel() {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        // TODO Auto-generated method stub
        return 6.0F;
    }

    @Override
    public Ingredient getRepairIngredient() {
        // TODO Auto-generated method stub
        return Ingredient.ofItems(Items.IRON_INGOT);
    }

    
}
