package tombenpotter.emt;

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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tombenpotter.emt.common.commands.CommandOutputs;
import tombenpotter.emt.common.init.EntityRegistry;
import tombenpotter.emt.common.init.Registry;
import tombenpotter.emt.common.network.PacketEMTKeys;
import tombenpotter.emt.common.util.*;
import tombenpotter.emt.proxies.CommonProxy;

@Mod(modid = ModInformation.modid, name = ModInformation.name, version = ModInformation.version, guiFactory = ModInformation.guiFactory, dependencies = ModInformation.depend)
public class ElectroMagicTools {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("EMT");

    @SidedProxy(clientSide = ModInformation.clientProxy, serverSide = ModInformation.commonProxy)
    public static CommonProxy proxy;

    public static CreativeTabs tabEMT = new CreativeTabEMT(ModInformation.modid + ".creativeTab");
    public static Logger logger = LogManager.getLogger(ModInformation.name);

    @Instance(ModInformation.modid)
    public static ElectroMagicTools instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ElectroMagicTools.logger.info("Starting planning the world domination");

        ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new EventHandlerEMT());
	    Registry.register();
        EssentiasOutputs.addPrimalOutputs();
        EssentiasOutputs.addOutputs();
        registerPackets();

        ElectroMagicTools.logger.info("Planning complete");
    }

    @EventHandler
    public void load(FMLInitializationEvent event) {
        ElectroMagicTools.logger.info("Gathering allies");

        ElectroMagicTools.logger.info("Loading the proxies");
        proxy.load();
        ElectroMagicTools.logger.info("Making mobs drop additional items");
        MinecraftForge.EVENT_BUS.register(new EventHandlerEMT());
        ElectroMagicTools.logger.info("Adding dungeon loot");
        DungeonChestGenerator.generateLoot();

        ElectroMagicTools.logger.info("Registering entities");
        EntityRegistry.registerEMTEntities();
        ElectroMagicTools.logger.info("Registering the GUI Handler");
        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);

        ElectroMagicTools.logger.info("Allies gathered.");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ElectroMagicTools.logger.info("Starting the world takeover");

	    Registry.registerLate();
        RegistryHandler.registerIc2PostRegistrys();

        ElectroMagicTools.logger.info("World takeover complete. Enjoy!");
    }

    @EventHandler
    public void onFMLServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandOutputs());
    }
    
    public void registerPackets(){
    	INSTANCE.registerMessage(PacketEMTKeys.class, PacketEMTKeys.class, 0, Side.SERVER);
    }
}
