package thenanomachi.weapons.and.tools.expanded;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class NecroticFireEnchantment extends Enchantment {
    protected NecroticFireEnchantment() {
        super(Rarity.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return true;
    }

    @Override
    public int getMinPower(int level) {
        return 6*level;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity) {
            // on hit apply wither to target and set user on fire for 4 * level.
            // counts as a curse and burns for longer as level increases.
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 20 * 5 * level, level - 1));
            user.setOnFireFor(4 * level);
        }
    }
}
