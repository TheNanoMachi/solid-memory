package thenanomachi.weapons.and.tools.expanded;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class LavaGeneratorScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    public LavaGeneratorScreenHandler(int syncId, PlayerInventory inv) {
        this(syncId, inv, new SimpleInventory(1));
    }

    protected LavaGeneratorScreenHandler(int syncID, PlayerInventory inv, Inventory inventory) {
        super(WeaponsAndTools.LAVA_GENERATOR_SCREEN_HANDLER, syncID);
        this.inventory = inventory;
        inventory.onOpen(inv.player);
        int m;
        int l;

        this.addSlot(new Slot(inventory, 0, 0,  0));

        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(inv, l + m * 9 + 9, 8 + l * 18, (84 - 25 + 39) + (m * 18)));
            }
        }
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(inv, m, 8 + m * 18, 142-25+39));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
