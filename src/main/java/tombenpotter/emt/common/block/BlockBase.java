package tombenpotter.emt.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.ModInformation;

public abstract class BlockBase extends Block {
	public final String name;
	
	public BlockBase(String unlocName, Material material, String textureName, SoundType soundType, float hardness) {
		super(material);
		name = unlocName;
		setBlockName(ModInformation.modid + ".block." + unlocName);
		if (textureName != null) {
			setBlockTextureName(ModInformation.texturePath + ":" + textureName);
		}
		setCreativeTab(ElectroMagicTools.tabEMT);
		setStepSound(soundType);
		setHardness(hardness);
	}

	public BlockBase(String unlocName, Material material, SoundType soundType, float hardness) {
		this(unlocName, material, null, soundType, hardness);
	}
}