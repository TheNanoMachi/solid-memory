package thenanomachi.weapons.and.tools.expanded;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WeaponsAndTools implements ModInitializer {

	public static final Item QUARTERSTAFF = new SwordItem(quarterstaff.INSTANCE, 0, 0F, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static final Item IRON_QUARTERSTAFF = new SwordItem(ToolMaterials.IRON, 2, -1F, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static final Item STEEL_INGOT = new Item(new Item.Settings().group(ItemGroup.MISC).maxCount(64));
	public static final String MOD_ID = "weaponsandtools";
	public static final Identifier AST = new Identifier(MOD_ID, "advanced_smithing_table");
	public static final Identifier NST = new Identifier(MOD_ID, "netherite_smithing_table");
	public static final Block ADVANCED_SMITHING_TABLE;
	public static final BlockItem ADVANCED_SMITHING_TABLE_ITEM;
	public static final Item FORGE_HAMMER = new SwordItem(steel.INSTANCE, -2, -3.0F, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static final Block NETHERITE_SMITHING_TABLE;
	public static final BlockItem NETHERITE_SMITHING_TABLE_ITEM;
	public static BlockEntityType<NetheriteSmithingTableEntity> NETHERITE_SMITHING_TABLE_ENTITY;
	public static final ScreenHandlerType<NetheriteSmithingTableScreenHandler> NETHERITE_SMITHING_TABLE_SCREEN_HANDLER;
	public static final ScreenHandlerType<AdvancedSmithingTableScreenHandler> ADVANCED_SMITHING_TABLE_SCREEN_HANDLER;
	public static final Block LAVA_GENERATOR;
	public static final BlockItem LAVA_GENERATOR_ITEM;
	public static BlockEntityType<LavaGeneratorEntity> LAVA_GENERATOR_ENTITY;
	public static ScreenHandlerType<LavaGeneratorScreenHandler> LAVA_GENERATOR_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(LavaGeneratorScreenHandler::new);
	public static final Identifier LG = new Identifier(MOD_ID, "lava_generator");
	static {
		ADVANCED_SMITHING_TABLE = Registry.register(Registry.BLOCK, AST, new AdvancedSmithingTable(FabricBlockSettings.of(Material.METAL).strength(2.5F, 7.0F).sounds(BlockSoundGroup.STONE)));
		ADVANCED_SMITHING_TABLE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "weaponsandtools:advanced_smithing_table_entity", FabricBlockEntityTypeBuilder.create(AdvancedSmithingTableEntity::new, ADVANCED_SMITHING_TABLE).build(null));
		ADVANCED_SMITHING_TABLE_ITEM = Registry.register(Registry.ITEM, AST, new BlockItem(ADVANCED_SMITHING_TABLE, new Item.Settings().group(ItemGroup.MISC)));
		ADVANCED_SMITHING_TABLE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(AST, AdvancedSmithingTableScreenHandler::new);
		NETHERITE_SMITHING_TABLE = Registry.register(Registry.BLOCK, NST, new NetheriteSmithingTable(FabricBlockSettings.of(Material.METAL).strength(2.5F, 1000000F).sounds(BlockSoundGroup.STONE)));
		NETHERITE_SMITHING_TABLE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "weaponsandtools:netherite_smithing_table_entity", FabricBlockEntityTypeBuilder.create(NetheriteSmithingTableEntity::new, NETHERITE_SMITHING_TABLE).build(null));
		NETHERITE_SMITHING_TABLE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(NST, NetheriteSmithingTableScreenHandler::new);
		NETHERITE_SMITHING_TABLE_ITEM = Registry.register(Registry.ITEM, NST, new BlockItem(NETHERITE_SMITHING_TABLE, new Item.Settings().group(ItemGroup.MISC)));
		LAVA_GENERATOR = Registry.register(Registry.BLOCK, LG, new LavaGenerator(FabricBlockSettings.of(Material.METAL).strength(2.5F, 10F).sounds(BlockSoundGroup.STONE)));
		LAVA_GENERATOR_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, LG, FabricBlockEntityTypeBuilder.create(LavaGeneratorEntity::new, LAVA_GENERATOR).build(null));
		LAVA_GENERATOR_ITEM = Registry.register(Registry.ITEM, LG, new BlockItem(LAVA_GENERATOR, new Item.Settings().group(ItemGroup.MISC)));
		LAVA_GENERATOR_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, LG, LAVA_GENERATOR_SCREEN_HANDLER);
	}
	public static BlockEntityType<AdvancedSmithingTableEntity> ADVANCED_SMITHING_TABLE_ENTITY;
	public static final ToolItem STEEL_SHOVEL = new ShovelItem(steel.INSTANCE, 1F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
	public static final ToolItem STEEL_SWORD = new SwordItem(steel.INSTANCE, 0, -3.0F, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static final ToolItem STEEL_PICKAXE = new SteelPickaxeItem(steel.INSTANCE, 1, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
	public static final ToolItem STEEL_AXE = new SteelAxeItem(steel.INSTANCE, 1, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
	public static final ToolItem STEEL_HOE = new SteelHoeItem(steel.INSTANCE, -7, 0F, new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
	public static final ArmorMaterial SteelArmourMaterial = new SteelArmourMaterial();
	public static final Block STEEL_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(4F, 4F).sounds(BlockSoundGroup.STONE));
	public static final Item STEEL_HELMET = new ArmorItem(SteelArmourMaterial, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static final Item STEEL_CHESTPLATE = new ArmorItem(SteelArmourMaterial, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static final Item STEEL_LEGGINGS = new ArmorItem(SteelArmourMaterial, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static final Item STEEL_BOOTS = new ArmorItem(SteelArmourMaterial, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static Enchantment NECROTIC_FIRE = new NecroticFireEnchantment();
	public static Enchantment CHAOTIC = new ChaoticEnchantment();

	@Override
	public void onInitialize() {
		FluidStorage.SIDED.registerForBlockEntity((n, direction) -> n.lavaStorage, NETHERITE_SMITHING_TABLE_ENTITY);
		Registry.register(Registry.ENCHANTMENT, new Identifier("weaponsandtools", "chaotic"), CHAOTIC);
		Registry.register(Registry.ENCHANTMENT, new Identifier("weaponsandtools", "necrotic_fire"), NECROTIC_FIRE);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "quarterstaff"), QUARTERSTAFF);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "iron_quarterstaff"), IRON_QUARTERSTAFF);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_ingot"), STEEL_INGOT);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_shovel"), STEEL_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "steel_sword"), STEEL_SWORD);
		Registry.register(Registry.ITEM, new Identifier("weaponsandtools", "forge_hammer"), FORGE_HAMMER);
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
