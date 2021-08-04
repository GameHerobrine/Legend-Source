package net.minecraft.src.me.Shxe.Module;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.me.Shxe.Event.event;
import net.minecraft.src.me.Shxe.Module.module.Category;

public class module {
	
	public String name;
	public boolean toggled;
	public int keyCode;
	public Category category;
	
	public module(String name, int key, Category c) {
		this.name = name;
		this.keyCode = key;
		this.category = c;
		
		//public ExampleModule(){
		//	super("Example", Keyboard.KEY_NONE, Category.EXAMPLE);
		//}
	}
	
	public boolean isEnabled() {
		return toggled;
	}
	
	public int getKey() {
		return keyCode;
	}
	
	public void onEvent(event e) {
		
	}
	
	public void toggle() {
		toggled = !toggled;
		if(toggled) {
			onEnable();
		}else {
			onDisable();
		}
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	// to add more Categories just do "example," or "example;" depends on where you put it
	
	public enum Category {
		COMBAT,
		HUD,
		MISC,
		PLAYER,
		MOVEMENT,
		RENDER;
	}
	
}
