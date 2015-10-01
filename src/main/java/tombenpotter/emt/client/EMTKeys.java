package tombenpotter.emt.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import tombenpotter.emt.common.util.TextHelper;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class EMTKeys{
	public static KeyBinding keyUnequip;
	
	public static void registerKeys(){
		keyUnequip = new KeyBinding(TextHelper.localize("gui.EMT.key.unequip"), Keyboard.KEY_Z, "EMT");
		ClientRegistry.registerKeyBinding(keyUnequip);

	}
	
	public static boolean keyUnequipPressed(){
		return keyUnequip.getIsKeyPressed();
	}
}