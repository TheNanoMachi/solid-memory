package thenanomachi.weapons.and.tools.expanded;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("UnstableApiUsage")
public class NetheriteSmithingTableEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    public final DefaultedList<ItemStack> items = DefaultedList.ofSize(25, ItemStack.EMPTY);
    public final FluidVariant LAVA = FluidVariant.of(Fluids.LAVA);

    public final SingleVariantStorage<FluidVariant> lavaStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return 82 * FluidConstants.BUCKET;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    public NetheriteSmithingTableEntity(BlockPos pos, BlockState state) {
        super(WeaponsAndTools.NETHERITE_SMITHING_TABLE_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
        lavaStorage.variant = LAVA;
        lavaStorage.amount = nbt.getLong("amount");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, items);
        nbt.put("lava", LAVA.toNbt());
        nbt.putLong("amount", lavaStorage.amount);
        super.writeNbt(nbt);
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new NetheriteSmithingTableScreenHandler(syncId, inv, this);
    }
}
