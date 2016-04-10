package emt.block;

import emt.ElectroMagicTools;
import emt.ModInformation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockBase extends Block {
	public final String name;

	public BlockBase(String unlocName, Material material, String textureName, SoundType soundType, float hardness) {
		super(material);
		name = unlocName;
		setBlockName(ModInformation.MODID + ".block." + unlocName);
		if (textureName != null) {
			setBlockTextureName(ModInformation.TEXTURE_PATH + ":" + textureName);
		}
		setCreativeTab(ElectroMagicTools.TAB);
		setStepSound(soundType);
		setHardness(hardness);
	}

	public BlockBase(String unlocName, Material material, SoundType soundType, float hardness) {
		this(unlocName, material, null, soundType, hardness);
	}
}