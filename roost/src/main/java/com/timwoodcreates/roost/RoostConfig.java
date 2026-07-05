package com.timwoodcreates.roost;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Roost.MODID)
public class RoostConfig {

	@Comment("The speed multiplier for the roost. Higher is faster.")
	@LangKey("config.roost.roostSpeed")
	@RangeDouble(min = 0.01d, max = 100d)
	public static double roostSpeed = 1d;

	@Comment("The speed multiplier for the breeder. Higher is faster.")
	@LangKey("config.roost.breederSpeed")
	@RangeDouble(min = 0.01d, max = 100d)
	public static double breederSpeed = 1d;

	@Comment("Minimum number of items chickens can lay at once.")
	@LangKey("config.roost.minRoostItemSize")
	@Config.RangeInt(min = 1, max = 64)
	public static int minRoostItemSize = 1;

	@Comment("Maximum number of items chickens can lay at once. Acts as a hard cap on the gain-based sqrt curve for modded chickens.")
	@LangKey("config.roost.maxRoostItemSize")
	@Config.RangeInt(min = 1, max = 64)
	public static int maxRoostItemSize = 3;

	@Comment("Maximum number of chickens that fit in a single roost slot.")
	@LangKey("config.roost.maxChickensPerRoost")
	@Config.RangeInt(min = 1, max = 16)
	public static int maxChickensPerRoost = 1;

	@Comment("Prevent vanilla chickens from laying eggs. Of interest to modpack makers only.")
	public static boolean disableEggLaying = false;

	public static void sync() {
		ConfigManager.sync(Roost.MODID, Config.Type.INSTANCE);
	}

	@Mod.EventBusSubscriber(modid = Roost.MODID)
	public static class SyncHandler {

		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Roost.MODID)) {
				RoostConfig.sync();
				Roost.LOGGER.info("Configuration has been saved.");
			}
		}
	}
}
