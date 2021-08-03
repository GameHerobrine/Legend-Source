package net.minecraft.src.me.Shxe.Module.MOVEMENT;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.me.Shxe.Module.module;
import net.minecraft.src.me.Shxe.Module.module.Category;

public class FlightNew extends module {

	public FlightNew(){
		super("Flight", Keyboard.KEY_TAB, Category.MOVEMENT);
	}
	
}
