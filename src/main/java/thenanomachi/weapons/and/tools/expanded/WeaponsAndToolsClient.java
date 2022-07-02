package thenanomachi.weapons.and.tools.expanded;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class WeaponsAndToolsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(WeaponsAndTools.ADVANCED_SMITHING_TABLE_SCREEN_HANDLER, AdvancedSmithingTableScreen::new);
    }
}
