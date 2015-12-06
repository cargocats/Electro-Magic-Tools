package tombenpotter.emt.common.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.research.ResearchPage.PageType;

public class EMTResearchItem extends ResearchItem {

	public EMTResearchItem(String par1, String par2) {
		super(par1, par2);
	}

	public EMTResearchItem(String par1, String par2, AspectList tags, int par3, int par4, int par5, ItemStack icon) {
		super(par1, par2, tags, par3, par4, par5, icon);
	}

	public EMTResearchItem(String par1, String par2, AspectList tags, int par3, int par4, int par5, ResourceLocation icon) {
		super(par1, par2, tags, par3, par4, par5, icon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getName() {
		return TextHelper.localize("emt.rsname." + key);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getText() {
		return "[EMT] " + TextHelper.localize("emt.tag." + key);
	}

	@Override
	public ResearchItem setPages(ResearchPage... rp) {
		for (ResearchPage page : rp) {
			if (page.type == PageType.TEXT)
				page.text = "emt.text." + key;

			if (page.type == PageType.INFUSION_CRAFTING) {
				if (parentsHidden == null || parentsHidden.length == 0)
					parentsHidden = new String[] { "INFUSION" };
				else {
					String[] newParents = new String[parentsHidden.length + 1];
					newParents[0] = "INFUSION";
					for (int i = 0; i < parentsHidden.length; i++)
						newParents[i + 1] = parentsHidden[i];
					parentsHidden = newParents;
				}
			}
		}

		return super.setPages(rp);
	}
}
