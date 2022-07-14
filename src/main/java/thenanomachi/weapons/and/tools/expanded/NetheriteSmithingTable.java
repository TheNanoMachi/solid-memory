package thenanomachi.weapons.and.tools.expanded;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;

public class NetheriteSmithingTable extends BlockWithEntity {
    protected NetheriteSmithingTable(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NetheriteSmithingTableEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null) {
                if (!(player.isHolding(WeaponsAndTools.FORGE_HAMMER))) {
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
            if(player.isHolding(WeaponsAndTools.FORGE_HAMMER)) {
                NetheriteSmithingTableEntity currentBlockEntity = (NetheriteSmithingTableEntity) world.getBlockEntity(pos);
                if (currentBlockEntity != null) {
//                    String[] necroRecipe = {"Air", "Wither Skeleton Skull", "Air", "Fire Charge", "Air", "Fire Charge", "Air", "Wither Skeleton Skull", "Air"};
//                    String[] chaoticRecipe = {"Iron Ingot", "Redstone Dust", "Iron Ingot", "Redstone Dust", "Air", "Redstone Dust", "Iron Ingot", "Redstone Dust", "Iron Ingot"};
//                    String[][] recipes = {necroRecipe, chaoticRecipe};
                    String[][] recipes = {{}, {}};
                    String[] copy = new String[9];
                    for (int i = 0; i < 9; ++i) {
                        copy[i] = currentBlockEntity.items.get(i).getName().getString();
                    }
                    int j;
                    for (j = 0; j < recipes.length; ++j) {
                        if (Arrays.equals(recipes[j], copy)) {
                            break;
                        }
                    }
                    switch (j) {
                        case 0 -> craft(world, pos, player, currentBlockEntity, WeaponsAndTools.NECROTIC_FIRE);
                        case 1 -> craft(world, pos, player, currentBlockEntity, WeaponsAndTools.CHAOTIC);
                        default -> System.out.println("invalid recipe");
                    }
                }
            }
            else if (player.isHolding(Items.LAVA_BUCKET)) {
                @Nullable
                Storage<FluidVariant> storage = FluidStorage.SIDED.find(world, pos, Direction.UP);
                FluidVariant LAVA = FluidVariant.of(Fluids.LAVA);
                try (Transaction transaction = Transaction.openOuter()) {
                    long inserted = storage.insert(LAVA, FluidConstants.BUCKET, transaction);
                    if (inserted == FluidConstants.BUCKET) {
                        transaction.commit();
                    }
                }
            }
            else if (player.isHolding(Items.BUCKET)) {
                @Nullable
                Storage<FluidVariant> storage = FluidStorage.SIDED.find(world, pos, Direction.UP);
                FluidVariant LAVA = FluidVariant.of(Fluids.LAVA);
                try (Transaction transaction = Transaction.openOuter()) {
                    long inserted = storage.extract(LAVA, FluidConstants.BUCKET, transaction);
                    if (inserted == FluidConstants.BUCKET) {
                        transaction.commit();
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    private void craft(World world, BlockPos pos, PlayerEntity player, NetheriteSmithingTableEntity currentBlockEntity, Enchantment enchantment) {
        ItemStack result = player.getOffHandStack();
        if (result.getItem() instanceof ToolItem && (EnchantmentHelper.getLevel(enchantment, result) < enchantment.getMaxLevel())) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.fromNbt(result.getEnchantments());
            int lvl = 0;
            if (enchantments.containsKey(enchantment)) {
                lvl = enchantments.get(enchantment);
            }
            enchantments.remove(enchantment);
            EnchantmentHelper.set(enchantments, result);
            result.addEnchantment(enchantment, lvl + 1);
            ItemScatterer.spawn(world, pos.getX(), pos.getY() + 1, pos.getZ(), result);
            for (ItemStack i : currentBlockEntity.items) {
                i.decrement(1);
            }
        }
    }

    @Override
    public float getBlastResistance() {
        return 3000000;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof NetheriteSmithingTableEntity) {
                //ItemScatterer.spawn(world, pos, (NetheriteSmithingTableEntity) blockEntity);
                ItemStack drop = new ItemStack(WeaponsAndTools.NETHERITE_SMITHING_TABLE_ITEM, 1);
                blockEntity.setStackNbt(drop);
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), drop);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
}
