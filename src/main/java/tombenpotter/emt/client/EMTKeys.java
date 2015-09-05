package tombenpotter.emt.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class EMTKeys{
	private static Map<String, KeyBinding> keys = new HashMap<String, KeyBinding>();
	
	public static void addKey(KeyBinding kb, String name){
		keys.put(name, kb);
	}
	
	public static void registerKeys(){
		addKey(new KeyBinding("UnEquip", Keyboard.KEY_Z, "EMT"), "UnEquip");
		
		for(Entry<String, KeyBinding> entry : keys.entrySet()){
			ClientRegistry.registerKeyBinding(entry.getValue());
		}
	}
	
	public static KeyBinding getKey(String name){

		for(Entry<String, KeyBinding> entry : keys.entrySet()){
			if(name.equals(entry.getKey())){
				return entry.getValue();
			}
		}
		
		return null;
	}
}