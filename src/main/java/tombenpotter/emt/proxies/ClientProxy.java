package tombenpotter.emt.proxies;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import tombenpotter.emt.client.EMTKeys;
import tombenpotter.emt.client.renderer.RenderLaser;
import tombenpotter.emt.common.entities.EntityArcher;
import tombenpotter.emt.common.entities.EntityLaser;

public class ClientProxy extends CommonProxy {

    @Override
    public void load() {
        registerRenders();
    	EMTKeys.registerKeys();
    }

    public void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());
        RenderingRegistry.registerEntityRenderingHandler(EntityArcher.class, new RenderSnowMan());
    }
}
