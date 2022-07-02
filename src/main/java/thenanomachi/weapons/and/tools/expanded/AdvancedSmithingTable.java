package thenanomachi.weapons.and.tools.expanded;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class AdvancedSmithingTable extends BlockWithEntity {
    protected AdvancedSmithingTable(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AdvancedSmithingTableEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            // here I need to block the screen from opening when the player is holding a wand item
            // in this case quarterstaff, but later "forge hammer"
            // lastly if nbt meets criteria then remove 1 item from everything
            // and spawn enchant on top (this is how to normally acquire new enchants + better weapons)
            if (screenHandlerFactory != null) {
                if (!(player.isHolding(WeaponsAndTools.FORGE_HAMMER))) {
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
            if(player.isHolding(WeaponsAndTools.FORGE_HAMMER)) {
                // read nbt of the associated entity
                AdvancedSmithingTableEntity currentBlockEntity = (AdvancedSmithingTableEntity) world.getBlockEntity(pos);
                if (currentBlockEntity != null) {
                    String[] recipe = {"Air", "Air", "Steel Block", "Air", "Air", "Air", "Air", "Air", "Air"};
                    String[] copy = new String[9];
                    for (int i = 0; i < 9; ++i) {
                        copy[i] = currentBlockEntity.items.get(i).getName().getString();
                    }
                    if(Arrays.equals(recipe, copy)) {
                        ItemScatterer.spawn(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(WeaponsAndTools.STEEL_SWORD));
                        for (ItemStack i : currentBlockEntity.items) {
                            i.decrement(1);
                        }
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
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
            if (blockEntity instanceof AdvancedSmithingTableEntity) {
                ItemScatterer.spawn(world, pos, (AdvancedSmithingTableEntity) blockEntity);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
}