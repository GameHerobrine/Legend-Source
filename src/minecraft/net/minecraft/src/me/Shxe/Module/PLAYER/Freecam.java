package net.minecraft.src.me.Shxe.Module.PLAYER;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.me.Shxe.Module.module;
import net.minecraft.src.me.Shxe.Module.module.Category;

public class Freecam extends module {

	public Freecam(){
		super("Freecam", Keyboard.KEY_G, Category.PLAYER);
	}
	
}
