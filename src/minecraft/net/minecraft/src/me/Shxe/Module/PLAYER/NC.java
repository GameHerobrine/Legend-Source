package net.minecraft.src.me.Shxe.Module.PLAYER;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.me.Shxe.Module.module;
import net.minecraft.src.me.Shxe.Module.module.Category;

public class NC extends module{

	public NC(){
		super("No clip [you will die]", Keyboard.KEY_K, Category.PLAYER);
	}
	
}
