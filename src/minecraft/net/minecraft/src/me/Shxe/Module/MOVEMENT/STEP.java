package net.minecraft.src.me.Shxe.Module.MOVEMENT;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.me.Shxe.Module.module;
import net.minecraft.src.me.Shxe.Module.module.Category;

public class STEP extends module{

	public STEP(){
		super("Step", Keyboard.KEY_SLASH, Category.MOVEMENT);
	}
	
}
