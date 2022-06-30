package thenanomachi.weapons.and.tools.expanded;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class WeaponsAndTools implements ModInitializer {

	public static final Item QUARTERSTAFF = new SwordItem(quarterstaff.INSTANCE, 0, 0F, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));

	public static final Item IRON_QUARTERSTAFF = new SwordItem(ToolMaterials.IRON, 2, -1F, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));

	public static final Item STEEL_INGOT = new Item(new Item.Settings().group(ItemGroup.MISC).maxCount(64));

	public static final Block ADVANCED_SMITHING_TABLE = new Block(FabricBlockSettings.of(Material.METAL).strength(2.5F, 7.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());

	public static final ToolItem STEEL_SHOVEL = new ShovelItem(steel.INSTANCE, 1F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));

	public static final ToolItem STEEL_SWORD = new SwordItem(steel.INSTANCE, 0, -3.0F, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));

	public static final ToolItem STEEL_PICKAXE = new SteelPickaxeItem(steel.INSTANCE, 1, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));

	public static final ToolItem STEEL_AXE = new SteelAxeItem(steel.INSTANCE, 1, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));

	public static final ToolItem STEEL_HOE = new SteelHoeItem(steel.INSTANCE, -7, 0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
	public static final ArmorMaterial SteelArmourMaterial = new SteelArmourMaterial();
	public static final Block STEEL_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(4F, 4F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool());
	public static final Item STEEL_HELMET = new ArmorItem(SteelArmourMaterial, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static final Item STEEL_CHESTPLATE = new ArmorItem(SteelArmourMaterial, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static final Item STEEL_LEGGINGS = new ArmorItem(SteelArmourMaterial, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static final Item STEEL_BOOTS = new ArmorItem(SteelArmourMaterial, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static Enchantment NECROTIC_FIRE = new NecroticFireEnchantment();
	public static Enchantment CHAOTIC = new ChaoticEnchantment();

	@Override
	public void onInitialize() {
		Registry.register(Registry.ENCHANTMENT, new Identifier("weaponsandtools", "chaotic"), CHAOTIC);
		Registry.register(Registry.ENCHANTMENT, new Identifier("weaponsandtools", "necrotic_fire"), NECROTIC_FIRE);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "quarterstaff"), QUARTERSTAFF);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "iron_quarterstaff"), IRON_QUARTERSTAFF);
		Registry.register(Registry.BLOCK, new Identifier("weaponsandtools", "advanced_smithing_table"), ADVANCED_SMITHING_TABLE);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "advanced_smithing_table"), new BlockItem(ADVANCED_SMITHING_TABLE, new Item.Settings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_ingot"), STEEL_INGOT);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_shovel"), STEEL_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_sword"), STEEL_SWORD);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_pickaxe"), STEEL_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_axe"), STEEL_AXE);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_hoe"), STEEL_HOE);
		Registry.register(Registry.BLOCK, new Identifier("weaponsandtools", "steel_block"), STEEL_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_block"), new BlockItem(STEEL_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_helmet"), STEEL_HELMET);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_chestplate"), STEEL_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_leggings"), STEEL_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_boots"), STEEL_BOOTS);
	}
}
