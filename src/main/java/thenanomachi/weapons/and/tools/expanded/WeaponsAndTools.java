package thenanomachi.weapons.and.tools.expanded;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WeaponsAndTools implements ModInitializer {

	public static final Item Quarterstaff = new Item(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(4));

	public static final Item STEEL_INGOT = new Item(new Item.Settings().group(ItemGroup.MISC).maxCount(64));

	public static final Block ADVANCED_SMITHING_TABLE = new Block(FabricBlockSettings.of(Material.METAL).strength(2.5F, 7.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());

	public static final ToolItem STEEL_SHOVEL = new ShovelItem(steel.INSTANCE, 3F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));

	@Override
	public void onInitialize() {

		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "quarterstaff"), Quarterstaff);
		Registry.register(Registry.BLOCK, new Identifier("weaponsandtools", "advanced_smithing_table"), ADVANCED_SMITHING_TABLE);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "advanced_smithing_table"), new BlockItem(ADVANCED_SMITHING_TABLE, new Item.Settings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_ingot"), STEEL_INGOT);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_shovel"), STEEL_SHOVEL);

	}
}
