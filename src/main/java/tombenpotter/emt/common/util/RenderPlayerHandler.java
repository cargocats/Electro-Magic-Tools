package tombenpotter.emt.common.util;

import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class RenderPlayerHandler {
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void PlayerRend(RenderPlayerEvent.Pre event){
    	//System.out.println("vse norm");
    	
    }
}
