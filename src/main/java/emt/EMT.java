package emt;

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
		modid = EMT.MOD_ID,
		name = EMT.NAME,
		version = EMT.VERSION,
		guiFactory = EMT.GUI_FACTORY,
		dependencies = EMT.DEPENDS
)
public class EMT {
	public static final String NAME = "Electro-Magic Tools";
	public static final String MOD_ID = "EMT";
	public static final String VERSION = "1.2.6";
	public static final String TEXTURE_PATH = "emt";
	public static final String GUI_FACTORY = "emt.client.gui.config.EMTGuiFactory";
	public static final String CLIENT_PROXY = "emt.proxy.ClientProxy";
	public static final String COMMON_PROXY = "emt.proxy.CommonProxy";
	public static final String CHANNEL = "EMT";
	public static final String DEPENDS = "required-after:Thaumcraft ; required-after:IC2";
	
	@SidedProxy(
			clientSide = CLIENT_PROXY,
			serverSide = COMMON_PROXY
	)
	public static CommonProxy proxy;
	public static final CreativeTabs TAB = new EMTCreativeTab(MOD_ID + ".creativeTab");
	public static final Logger LOGGER = LogManager.getLogger(NAME);
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);

	@Instance(MOD_ID)
	public static EMT instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LOGGER.info("Starting planning the world domination");
		EMTConfigHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new EMTEventHandler());
		FMLCommonHandler.instance().bus().register(new EMTClientEventHandler());
		Registry.register();
		EMTEssentiasOutputs.addPrimalOutputs();
		EMTEssentiasOutputs.addOutputs();
		registerPackets();
		LOGGER.info("Planning complete");
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		LOGGER.info("Gathering allies");
		LOGGER.info("Loading the proxies");
		proxy.load();
		LOGGER.info("Making mobs drop additional items");
		MinecraftForge.EVENT_BUS.register(new EMTEventHandler());
		MinecraftForge.EVENT_BUS.register(new EMTClientEventHandler());
		LOGGER.info("Adding dungeon loot");
		EMTDungeonChestGenerator.generateLoot();
		LOGGER.info("Registering entities");
		EMTEntities.registerEMTEntities();
		LOGGER.info("Registering the GUI Handler");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
		LOGGER.info("Allies gathered.");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LOGGER.info("Starting the world takeover");
		Registry.registerLate();
		EMTResearches.register();
		LOGGER.info("World takeover complete. Enjoy!");
	}

	@EventHandler
	public void onFMLServerStart(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandOutputs());
	}

	public void registerPackets() {
		INSTANCE.registerMessage(PacketEMTKeys.class, PacketEMTKeys.class, 0, Side.SERVER);
	}
}
