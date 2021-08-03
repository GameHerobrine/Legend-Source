package net.minecraft.src.me.Shxe.Module.RENDER;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.me.Shxe.Module.module;
import net.minecraft.src.me.Shxe.Module.MISC.Timer;
import net.minecraft.src.me.Shxe.Module.module.Category;

public class XR extends module{

	public XR(){
		super("X-ray", Keyboard.KEY_X, Category.RENDER);
	}
	
}
