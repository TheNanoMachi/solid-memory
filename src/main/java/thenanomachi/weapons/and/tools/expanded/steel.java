package thenanomachi.weapons.and.tools.expanded;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class steel extends WeaponsAndTools implements ToolMaterial {

    public static final steel INSTANCE = new steel();

    @Override
    public float getAttackDamage() {
        return 7F;
    }

    @Override
    public int getDurability() {
        return 750;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 6.0F;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(STEEL_INGOT);
    }

    
}
