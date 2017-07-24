package emt.client;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import emt.util.EMTTextHelper;
import net.minecraft.client.settings.KeyBinding;

public class EMTKeys {
	public static KeyBinding keyUnequip;

	public static void registerKeys() {
		keyUnequip = new KeyBinding(EMTTextHelper.localize("gui.EMT.key.unequip"), Keyboard.KEY_Z, "EMT");
		ClientRegistry.registerKeyBinding(keyUnequip);
	}
}