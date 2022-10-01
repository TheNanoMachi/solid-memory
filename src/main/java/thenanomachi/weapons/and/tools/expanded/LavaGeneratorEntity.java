package thenanomachi.weapons.and.tools.expanded;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

@SuppressWarnings("ALL")
public class LavaGeneratorEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    public final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);
    // outputs
    private static final FluidVariant LAVA = FluidVariant.of(Fluids.LAVA);
    // valid inputs
    private static final ItemVariant COBBLESTONE = ItemVariant.of(Items.COBBLESTONE);
    private static final ItemVariant STONE = ItemVariant.of(Items.STONE);
    private static final ItemVariant NETHERRACK = ItemVariant.of(Items.NETHERRACK);
    private static final ItemVariant MAGMA_BLOCK = ItemVariant.of(Items.MAGMA_BLOCK);
    ItemVariant[] valid = {COBBLESTONE, STONE, NETHERRACK, MAGMA_BLOCK};

    private SingleVariantStorage<FluidVariant> createTank(FluidVariant allowed) {
        return new SingleVariantStorage<>() {
            @Override
            protected FluidVariant getBlankVariant() {
                return FluidVariant.blank();
            }

            @Override
            protected long getCapacity(FluidVariant variant) {
                return 128000 * FluidConstants.BUCKET;
            }


            @Override
            protected boolean canInsert(FluidVariant variant) {
                return variant.equals(allowed);
            }

            @Override
            protected void onFinalCommit() {
                markDirty();
            }
        };
    }

    private final SimpleInventory inventory = new SimpleInventory(1) {
        @Override
        public int getMaxCountPerStack() {
            return 64;
        }

        @Override
        public boolean isValid(int slot, ItemStack stack) {
            return Arrays.asList(valid).contains(ItemVariant.of(stack));
        }

        @Override
        public void markDirty() {
            LavaGeneratorEntity.this.markDirty();
        }
    };

    private final SingleVariantStorage<FluidVariant> tank = createTank(LAVA);
    public LavaGeneratorEntity(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    public LavaGeneratorEntity(BlockPos pos, BlockState state) {
        super(WeaponsAndTools.LAVA_GENERATOR_ENTITY, pos, state);
    }



    @Override
    public Text getDisplayName() {
        return null;

    }


    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.put("variant", tank.variant.toNbt());
        nbt.putLong("storedLava", tank.amount);
        nbt.put("itemStored", inventory.getStack(0).getNbt());
        nbt.putInt("itemAmount", inventory.getStack(0).getCount());
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        tank.variant = FluidVariant.fromNbt(nbt.getCompound("variant"));
        tank.amount = nbt.getLong("storedLava");
        if (ItemVariant.fromNbt(nbt.getCompound("itemStored")) == inventory.getStack(0).getNbt())
            inventory.getStack(0).setCount(nbt.getInt("itemAmount"));
    }

    public void convert() {
        // call every 10 ticks, consuming one cobblestone per use
        try (Transaction transaction = Transaction.openOuter()) {
            long lavaInserted = tank.insert(LAVA, FluidConstants.BUCKET, transaction);
            inventory.getStack(0).decrement(1);
            transaction.commit();
        }

    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new LavaGeneratorScreenHandler(syncId, inv, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        long current = tank.amount;
        buf.writeString(Long.toString(current));
    }

    public static void tick(World world, BlockPos pos, BlockState state, LavaGeneratorEntity be) {
        be.convert();
    }

}
