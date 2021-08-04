package net.minecraft.src.me.Shxe.Module.RENDER;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.me.Shxe.Module.module;
import net.minecraft.src.me.Shxe.Module.module.Category;

public class PlayerESP extends module {

	public PlayerESP(){
		super("ESP [ P ]", Keyboard.KEY_INSERT, Category.RENDER);
	}
	
}
