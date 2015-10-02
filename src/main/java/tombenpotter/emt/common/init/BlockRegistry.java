package tombenpotter.emt.common.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.common.blocks.BlockEMTMachines;
import tombenpotter.emt.common.blocks.BlockEMTSolars;
import tombenpotter.emt.common.blocks.BlockEMTSolars2;
import tombenpotter.emt.common.blocks.BlockEssentiaGenerator;
import tombenpotter.emt.common.blocks.BlockEssentiaGenerators;
import tombenpotter.emt.common.blocks.BlockPortableNode;
import tombenpotter.emt.common.blocks.BlockShield;
import tombenpotter.emt.common.items.blocks.ItemBlockEMTMachines;
import tombenpotter.emt.common.items.blocks.ItemBlockEMTSolars;
import tombenpotter.emt.common.items.blocks.ItemBlockEMTSolars2;
import tombenpotter.emt.common.items.blocks.ItemBlockEssentiaGenerator;
import tombenpotter.emt.common.items.blocks.ItemBlockEssentiaGenerators;

public class BlockRegistry {

    public static Block portableNode;
    public static Block essentiaGens;
    public static Block shield;
    public static Block emtSolars;
    public static Block emtMachines;
    public static Block emtSolars2;


    public static void registerBlocks() {
        portableNode = new BlockPortableNode().setBlockName(ModInformation.modid + ".portablenode");
        GameRegistry.registerBlock(portableNode, "PortableNode");
        
        shield = new BlockShield().setBlockName(ModInformation.modid + ".shield");
        GameRegistry.registerBlock(shield, "ShieldBlock");

        emtSolars = new BlockEMTSolars().setBlockName(ModInformation.modid + ".emtsolars");
        GameRegistry.registerBlock(emtSolars, ItemBlockEMTSolars.class, "EMTSolars");

        emtSolars2 = new BlockEMTSolars2().setBlockName(ModInformation.modid + ".emtsolars2");
        GameRegistry.registerBlock(emtSolars2, ItemBlockEMTSolars2.class, "EMTSolars2");

        emtMachines = new BlockEMTMachines().setBlockName(ModInformation.modid + ".emtmachines");
        GameRegistry.registerBlock(emtMachines, ItemBlockEMTMachines.class, "EMTMachines");

        essentiaGens = new BlockEssentiaGenerators().setBlockName(ModInformation.modid + ".essentiaGens");
        GameRegistry.registerBlock(essentiaGens, ItemBlockEssentiaGenerators.class, "EssentiaGenerators");
    }
}
