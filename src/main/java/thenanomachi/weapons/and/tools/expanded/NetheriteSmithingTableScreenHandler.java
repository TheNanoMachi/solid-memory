package thenanomachi.weapons.and.tools.expanded;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class NetheriteSmithingTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    public NetheriteSmithingTableScreenHandler(int syncId, PlayerInventory inv) {
        this(syncId, inv, new SimpleInventory(25));
    }

    public NetheriteSmithingTableScreenHandler(int syncID, PlayerInventory inv, Inventory inventory) {
        super(WeaponsAndTools.NETHERITE_SMITHING_TABLE_SCREEN_HANDLER, syncID);
        this.inventory = inventory;
        inventory.onOpen(inv.player);
        int m;
        int l;
        for(m = 0; m < 5; ++m) {
            for(l = 0; l < 5; ++l) {
                this.addSlot(new Slot(inventory, l + m * 5, 44 + l * 18,  -7 + m * 18));
            }
        }
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
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
