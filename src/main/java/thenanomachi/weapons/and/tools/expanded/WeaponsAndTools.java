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
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;



public class WeaponsAndTools implements ModInitializer {

	public static final Item QUARTERSTAFF = new Item(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(4));

	public static final Item STEEL_INGOT = new Item(new Item.Settings().group(ItemGroup.MISC).maxCount(64));

	public static final Block ADVANCED_SMITHING_TABLE = new Block(FabricBlockSettings.of(Material.METAL).strength(2.5F, 7.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());

	public static final ToolItem STEEL_SHOVEL = new ShovelItem(steel.INSTANCE, 1F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));

	public static final ToolItem STEEL_SWORD = new SwordItem(steel.INSTANCE, 10, -3.0F, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));

	public static final ToolItem STEEL_PICKAXE = new SteelPickaxeItem(steel.INSTANCE, 1, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));

	public static final ToolItem STEEL_AXE = new SteelAxeItem(steel.INSTANCE, 15, -6.0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));

	public static final ToolItem STEEL_HOE = new SteelHoeItem(steel.INSTANCE, -1, -1.0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));

	public static final Block STEEL_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(1F, 2F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool());

	@Override
	public void onInitialize() {

		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "quarterstaff"), QUARTERSTAFF);
		Registry.register(Registry.BLOCK, new Identifier("weaponsandtools", "advanced_smithing_table"), ADVANCED_SMITHING_TABLE);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "advanced_smithing_table"), new BlockItem(ADVANCED_SMITHING_TABLE, new Item.Settings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_ingot"), STEEL_INGOT);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_shovel"), STEEL_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_sword"), STEEL_SWORD);
		Registry.register(Registry.BLOCK, new Identifier("weaponsandtools", "steel_ore"), STEEL_ORE);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_ore"), new BlockItem(STEEL_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_pickaxe"), STEEL_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_axe"), STEEL_AXE);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_hoe"), STEEL_HOE);

	}
}
