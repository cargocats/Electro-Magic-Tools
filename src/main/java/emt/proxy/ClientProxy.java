package emt.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import emt.client.EMTKeys;
import emt.client.renderer.RenderElectroBall;
import emt.client.renderer.RenderLaser;
import emt.client.renderer.RenderShield;
import emt.entity.EntityArcher;
import emt.entity.EntityElectroBall;
import emt.entity.EntityLaser;
import emt.entity.EntityShield;

public class ClientProxy extends CommonProxy {

	@Override
	public void load() {
		registerRenders();
		EMTKeys.registerKeys();
	}

	public void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());
		RenderingRegistry.registerEntityRenderingHandler(EntityArcher.class, new RenderSnowMan());
		RenderingRegistry.registerEntityRenderingHandler(EntityShield.class, new RenderShield());
		RenderingRegistry.registerEntityRenderingHandler(EntityElectroBall.class, new RenderElectroBall());
	}
}
