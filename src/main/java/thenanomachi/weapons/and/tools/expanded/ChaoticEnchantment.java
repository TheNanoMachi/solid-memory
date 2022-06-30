package thenanomachi.weapons.and.tools.expanded;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Random;

public class ChaoticEnchantment extends Enchantment {
    Random random = new Random();
    protected ChaoticEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return true;
    }

    @Override
    public int getMinPower(int level) {
        return 3*level;
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity) {
            // on hit apply random status effects.
            // their duration depends on the level.
            // 1. damage to the user (roll 0, scaled by level, scales down as level increases)
            // 2. slowness to the opponent (roll 1)
            // 3. poison to the opponent (roll 2)
            // 4. weakness to the opponent (roll 3)
            // 5. levitates the opponent (roll 4)
            int choice = random.nextInt(5);
            switch(choice) {
                case 0:
                    user.damage(DamageSource.MAGIC, user.getMaxHealth()/(level+1));
                    break;
                case 1:
                    ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20*level, level-1));
                    break;
                case 2:
                    ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20*level, level-1));
                    break;
                case 3:
                    ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20*level, level-1));
                    break;
                case 4:
                    ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 20*level, level-1));
                    break;
            }
        }
    }
}
