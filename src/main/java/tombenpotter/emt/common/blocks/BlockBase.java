package tombenpotter.emt.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.ModInformation;

public abstract class BlockBase extends Block {

    public BlockBase(String unlocName, Material material, String textureName, SoundType soundType, float hardness) {
        super(material);

        setBlockName(ModInformation.modid + ".block." + unlocName);
        setBlockTextureName(ModInformation.texturePath + ":" + textureName);
        setCreativeTab(ElectroMagicTools.tabEMT);
        setStepSound(soundType);
        setHardness(hardness);
    }

    public BlockBase(String unlocName, Material material, SoundType soundType, float hardness) {
        super(material);

        setBlockName(ModInformation.modid + ".block." + unlocName);
        setCreativeTab(ElectroMagicTools.tabEMT);
        setStepSound(soundType);
        setHardness(hardness);
    }
}