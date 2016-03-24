package emt;

import ic2.core.Ic2Items;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import emt.command.CommandOutputs;
import emt.init.EMTEntities;
import emt.init.Registry;
import emt.init.EMTResearches;
import emt.network.PacketEMTKeys;
import emt.proxy.CommonProxy;
import emt.util.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
		modid = ModInformation.modid,
		name = ModInformation.name,
		version = ModInformation.version,
		guiFactory = ModInformation.guiFactory,
		dependencies = ModInformation.depend)
public class ElectroMagicTools {

	@SidedProxy(
			clientSide = ModInformation.clientProxy,
			serverSide = ModInformation.commonProxy)
	public static CommonProxy proxy;
	public static CreativeTabs tabEMT = new EMTCreativeTab(ModInformation.modid + ".creativeTab");
	public static Logger logger = LogManager.getLogger(ModInformation.name);
	public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInformation.channel);

	@Instance(ModInformation.modid)
	public static ElectroMagicTools instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger.info("Starting planning the world domination");
		EMTConfigHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new EMTEventHandler());
		FMLCommonHandler.instance().bus().register(new EMTClientEventHandler());
		Registry.register();
		EMTEssentiasOutputs.addPrimalOutputs();
		EMTEssentiasOutputs.addOutputs();
		registerPackets();
		logger.info("Planning complete");
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		logger.info("Gathering allies");
		logger.info("Loading the proxies");
		proxy.load();
		logger.info("Making mobs drop additional items");
		MinecraftForge.EVENT_BUS.register(new EMTEventHandler());
		MinecraftForge.EVENT_BUS.register(new EMTClientEventHandler());
		logger.info("Adding dungeon loot");
		EMTDungeonChestGenerator.generateLoot();
		logger.info("Registering entities");
		EMTEntities.registerEMTEntities();
		logger.info("Registering the GUI Handler");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
		logger.info("Allies gathered.");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		logger.info("Starting the world takeover");
		Registry.registerLate();
		EMTResearches.register();
		logger.info("World takeover complete. Enjoy!");
	}

	@EventHandler
	public void onFMLServerStart(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandOutputs());
	}

	public void registerPackets() {
		INSTANCE.registerMessage(PacketEMTKeys.class, PacketEMTKeys.class, 0, Side.SERVER);
	}
}
