package thenanomachi.weapons.and.tools.expanded;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class SteelArmourMaterial extends WeaponsAndTools implements ArmorMaterial {

    private static final int[] BASE = new int[] {13, 15, 16, 11};
    private static final int[] VALUES = new int[] {2, 6, 7, 3};
    private static final int X = 5;

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE[slot.getEntitySlotId()] * X;
    }

    @Override
    public int getEnchantability() {
        return X;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }

    @Override
    public String getName() {
        return "steel";
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return VALUES[slot.getEntitySlotId()];
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(STEEL_INGOT);
    }

    @Override
    public float getToughness() {
        return 0.2F*X;
    }

}