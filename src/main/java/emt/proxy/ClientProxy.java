package emt.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import emt.client.EMTKeys;
import emt.client.renderer.RenderLaser;
import emt.client.renderer.RenderShield;
import emt.entity.EntityArcher;
import emt.entity.EntityEnergyBall;
import emt.entity.EntityLaser;
import emt.entity.EntityShield;

public class ClientProxy extends CommonProxy {

	@Override
	public void load() {
		registerRenders();
		EMTKeys.registerKeys();
	}

	@Override
	public void registerRenders() {
		Render empty = new Render() {

			@Override
			public void doRender(Entity e, double x, double y, double z, float f1, float f2) {
			}

			@Override
			protected ResourceLocation getEntityTexture(Entity e) {
				return null;
			}
			
		};
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());
		RenderingRegistry.registerEntityRenderingHandler(EntityArcher.class, new RenderSnowMan());
		RenderingRegistry.registerEntityRenderingHandler(EntityShield.class, new RenderShield());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnergyBall.class, empty);
	}
}
