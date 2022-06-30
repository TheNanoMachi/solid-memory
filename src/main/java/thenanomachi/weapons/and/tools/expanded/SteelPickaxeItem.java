package thenanomachi.weapons.and.tools.expanded;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class SteelPickaxeItem extends PickaxeItem {
    protected SteelPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean isFood() {
        return true;
    }
}
