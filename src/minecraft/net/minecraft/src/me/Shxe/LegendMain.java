package net.minecraft.src.me.Shxe;

import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.src.me.Shxe.Event.*;
import net.minecraft.src.me.Shxe.Module.module;
import net.minecraft.src.me.Shxe.Module.COMBAT.*;
import net.minecraft.src.me.Shxe.Module.HUD.*;
import net.minecraft.src.me.Shxe.Module.MISC.*;
import net.minecraft.src.me.Shxe.Module.MOVEMENT.*;
import net.minecraft.src.me.Shxe.Module.PLAYER.*;
import net.minecraft.src.me.Shxe.Module.RENDER.*;

/**
* Comment by Shxe : This was pasted from "Osiris-beta" another client coded by me but since that will never see the light of day
*                   I decided to keep this here. This is only for the arraylist since I was lazy :) 
 */

public class LegendMain {

	public static CopyOnWriteArrayList<module> modules = new CopyOnWriteArrayList<module>();
	
	// StartUp ( For adding modules (( This is for the arraylist only! )) )
	public static void startup() {
		// COMBAT
		
		modules.add(new AutoHit());
		modules.add(new Reach());
		
		// HUD
		
		modules.add(new ClickGui()); // This is not interactive
		
		// MISC
		
		modules.add(new Timer());
		
		// MOVEMENT
		
		modules.add(new AW());
		modules.add(new STEP());
		modules.add(new FlightNew());
		modules.add(new NF());
		
		// PLAYER
		
		modules.add(new Freecam());
		modules.add(new NC());
		modules.add(new AW());
		
		// RENDER
		
		modules.add(new NS());
		modules.add(new FB());
		modules.add(new XR());
		modules.add(new NW());
		modules.add(new NCI());
		modules.add(new PlayerESP());
		modules.add(new MobESP()); // This was never finished in this version
		
		}
	
	public static void onEvent(event e) {
		for(module m : modules) {
			if(!m.toggled)
				continue;
			m.onEvent(e);
		}
	}
	
	public static void keyPress(int key) {
		for(module m : modules) {
			if(m.getKey() == key) {
				m.toggle();
			}
		}
	}
}
