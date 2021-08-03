// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.awt.Color;
import java.awt.Frame;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.src.me.Shxe.Main;
import net.minecraft.src.me.Shxe.LegendMain;
import net.minecraft.src.me.Shxe.LegendGlobal;
import net.minecraft.src.me.Shxe.Module.module;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            Gui, ScaledResolution, EntityRenderer, EntityPlayerSP, 
//            FontRenderer, InventoryPlayer, GameSettings, ItemStack, 
//            Block, RenderEngine, PlayerController, Material, 
//            RenderHelper, MathHelper, StigmaGlobal, World, 
//            EntityPlayer, EntityClientPlayerMP, GuiChat, ChatLine, 
//            Flooder, Tessellator, BlockPortal, RenderItem, 
//            StringTranslate

public class GuiIngame extends Gui {

	public GuiIngame(Minecraft minecraft) {
		chatMessageList = new ArrayList();
		rand = new Random();
		field_933_a = null;
		updateCounter = 0;
		recordPlaying = "";
		recordPlayingUpFor = 0;
		field_22065_l = false;
		prevVignetteBrightness = 1.0F;
		mc = minecraft;
	}

	public void drawSmallString(FontRenderer fontrenderer, String s, int i, int j, int k) {
		GL11.glScalef(0.8F, 0.8F, 0.8F);
		fontrenderer.drawStringWithShadow(s, i * 2, j * 2, k);
		GL11.glScalef(1.25F, 1.25F, 1.25F);
	}

	public static void Dimensions(Minecraft minecraft) {
		ScaledResolution scaledresolution = new ScaledResolution(minecraft.gameSettings, minecraft.displayWidth,
				minecraft.displayHeight);
		width = scaledresolution.getScaledWidth();
		height = scaledresolution.getScaledHeight();
	}

	public void renderGameOverlay(float f, boolean flag, int i, int j) {
		ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		int k = scaledresolution.getScaledWidth();
		int l = scaledresolution.getScaledHeight();
		FontRenderer fontrenderer = mc.fontRenderer;
		mc.entityRenderer.func_905_b();
		GL11.glEnable(3042 /* GL_BLEND */);
		if (Minecraft.isFancyGraphicsEnabled()) {
			renderVignette(mc.thePlayer.getEntityBrightness(f), k, l);
		}
		ItemStack itemstack = mc.thePlayer.inventory.armorItemInSlot(3);
		if (!mc.gameSettings.thirdPersonView && itemstack != null && itemstack.itemID == Block.pumpkin.blockID) {
			renderPumpkinBlur(k, l);
		}
		float f1 = mc.thePlayer.prevTimeInPortal + (mc.thePlayer.timeInPortal - mc.thePlayer.prevTimeInPortal) * f;
		if (f1 > 0.0F) {
			renderPortalOverlay(f1, k, l);
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBindTexture(3553 /* GL_TEXTURE_2D */, mc.renderEngine.getTexture("/gui/gui.png"));
		InventoryPlayer inventoryplayer = mc.thePlayer.inventory;
		zLevel = -90F;
		drawTexturedModalRect(k / 2 - 91, l - 22, 0, 0, 182, 22);
		drawTexturedModalRect((k / 2 - 91 - 1) + inventoryplayer.currentItem * 20, l - 22 - 1, 0, 22, 24, 22);
		GL11.glBindTexture(3553 /* GL_TEXTURE_2D */, mc.renderEngine.getTexture("/gui/icons.png"));
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glBlendFunc(775, 769);
		drawTexturedModalRect(k / 2 - 7, l / 2 - 7, 0, 0, 16, 16);
		GL11.glDisable(3042 /* GL_BLEND */);
		boolean flag1 = (mc.thePlayer.heartsLife / 3) % 2 == 1;
		if (mc.thePlayer.heartsLife < 10) {
			flag1 = false;
		}
		int i1 = mc.thePlayer.health;
		int j1 = mc.thePlayer.prevHealth;
		rand.setSeed(updateCounter * 0x4c627);
		if (mc.playerController.shouldDrawHUD()) {
			int k1 = mc.thePlayer.getPlayerArmorValue();
			for (int i3 = 0; i3 < 10; i3++) {
				int l4 = l - 32;
				if (k1 > 0) {
					int i7 = (k / 2 + 91) - i3 * 8 - 9;
					if (i3 * 2 + 1 < k1) {
						drawTexturedModalRect(i7, l4, 34, 9, 9, 9);
					}
					if (i3 * 2 + 1 == k1) {
						drawTexturedModalRect(i7, l4, 25, 9, 9, 9);
					}
					if (i3 * 2 + 1 > k1) {
						drawTexturedModalRect(i7, l4, 16, 9, 9, 9);
					}
				}
				int j7 = 0;
				if (flag1) {
					j7 = 1;
				}
				int j8 = (k / 2 - 91) + i3 * 8;
				if (i1 <= 4) {
					l4 += rand.nextInt(2);
				}
				drawTexturedModalRect(j8, l4, 16 + j7 * 9, 0, 9, 9);
				if (flag1) {
					if (i3 * 2 + 1 < j1) {
						drawTexturedModalRect(j8, l4, 70, 0, 9, 9);
					}
					if (i3 * 2 + 1 == j1) {
						drawTexturedModalRect(j8, l4, 79, 0, 9, 9);
					}
				}
				if (i3 * 2 + 1 < i1) {
					drawTexturedModalRect(j8, l4, 52, 0, 9, 9);
				}
				if (i3 * 2 + 1 == i1) {
					drawTexturedModalRect(j8, l4, 61, 0, 9, 9);
				}
			}

			if (mc.thePlayer.isInsideOfMaterial(Material.water)) {
				int j3 = (int) Math.ceil(((double) (mc.thePlayer.air - 2) * 10D) / 300D);
				int i5 = (int) Math.ceil(((double) mc.thePlayer.air * 10D) / 300D) - j3;
				for (int k7 = 0; k7 < j3 + i5; k7++) {
					if (k7 < j3) {
						drawTexturedModalRect((k / 2 - 91) + k7 * 8, l - 32 - 9, 16, 18, 9, 9);
					} else {
						drawTexturedModalRect((k / 2 - 91) + k7 * 8, l - 32 - 9, 25, 18, 9, 9);
					}
				}

			}
		}
		GL11.glDisable(3042 /* GL_BLEND */);
		GL11.glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
		GL11.glPushMatrix();
		GL11.glRotatef(120F, 1.0F, 0.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
		for (int l1 = 0; l1 < 9; l1++) {
			int k3 = (k / 2 - 90) + l1 * 20 + 2;
			int j5 = l - 16 - 3;
			renderInventorySlot(l1, k3, j5, f);
		}

		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
		if (mc.thePlayer.func_22060_M() > 0) {
			GL11.glDisable(2929 /* GL_DEPTH_TEST */);
			GL11.glDisable(3008 /* GL_ALPHA_TEST */);
			int i2 = mc.thePlayer.func_22060_M();
			float f3 = (float) i2 / 100F;
			if (f3 > 1.0F) {
				f3 = 1.0F - (float) (i2 - 100) / 10F;
			}
			int k5 = (int) (220F * f3) << 24 | 0x101020;
			drawRect(0, 0, k, l, k5);
			GL11.glEnable(3008 /* GL_ALPHA_TEST */);
			GL11.glEnable(2929 /* GL_DEPTH_TEST */);
		}
		if (mc.gameSettings.showDebugInfo) {
			GL11.glPushMatrix();
			if (Minecraft.hasPaidCheckTime > 0L) {
				GL11.glTranslatef(0.0F, 32F, 0.0F);
			}
			fontrenderer.drawStringWithShadow(
					(new StringBuilder()).append("Skeet (").append("0.1 ( Beta 1.7.3 )").append(")").toString(), 2, 2,
					0xffffff);
			fontrenderer.drawStringWithShadow(
					(new StringBuilder()).append("Made By ").append("Shae").append("").toString(), 2, 500, 0xffffff);
			fontrenderer.drawStringWithShadow(mc.func_6241_m(), 2, 12, 0xffffff);
			fontrenderer.drawStringWithShadow(mc.func_6262_n(), 2, 22, 0xffffff);
			fontrenderer.drawStringWithShadow(mc.func_6245_o(), 2, 32, 0xffffff);
			fontrenderer.drawStringWithShadow(mc.func_21002_o(), 2, 42, 0xffffff);
			long l2 = Runtime.getRuntime().maxMemory();
			long l5 = Runtime.getRuntime().totalMemory();
			long l8 = Runtime.getRuntime().freeMemory();
			long l9 = l5 - l8;
			String s4 = (new StringBuilder()).append("Used memory: ").append((l9 * 100L) / l2).append("% (")
					.append(l9 / 1024L / 1024L).append("MB) of ").append(l2 / 1024L / 1024L).append("MB").toString();
			drawString(fontrenderer, s4, k - fontrenderer.getStringWidth(s4) - 2, 2, 0xe0e0e0);
			s4 = (new StringBuilder()).append("Alocated memory: ").append((l5 * 100L) / l2).append("% (")
					.append(l5 / 1024L / 1024L).append("MB)").toString();
			drawString(fontrenderer, s4, k - fontrenderer.getStringWidth(s4) - 2, 12, 0xe0e0e0);
			drawString(fontrenderer, (new StringBuilder()).append("x: ").append(mc.thePlayer.posX).toString(), 2, 64,
					0xe0e0e0);
			drawString(fontrenderer, (new StringBuilder()).append("y: ").append(mc.thePlayer.posY).toString(), 2, 72,
					0xe0e0e0);
			drawString(fontrenderer, (new StringBuilder()).append("z: ").append(mc.thePlayer.posZ).toString(), 2, 80,
					0xe0e0e0);
			drawString(fontrenderer,
					(new StringBuilder()).append("f: ").append(
							MathHelper.floor_double((double) ((mc.thePlayer.rotationYaw * 4F) / 360F) + 0.5D) & 3)
							.toString(),
					2, 88, 0xe0e0e0);
			GL11.glPopMatrix();
		} else {
			Collections.sort(LegendMain.modules, new moduleComparator());
			
			float seconds = 4;
			float hue = (System.currentTimeMillis() % (int) (seconds * 1000) / (float) (seconds * 1000));
			long l2 = Runtime.getRuntime().maxMemory();
			long l5 = Runtime.getRuntime().totalMemory();
			long l8 = Runtime.getRuntime().freeMemory();
			long l9 = l5 - l8;

			int color = Color.HSBtoRGB(hue, 0.8f, 1);

			int red = 0xffff0000,
				green = 0xff00ff00,
				blue = 0xff0000ff,
				white = 0xffffffff,
				black = 0xff000000,
				grey = 0xff3f3f3f,
				BasicRGB = Color.HSBtoRGB(hue, 0.8f, 1);
			
			int count = 0;  
            
			if (LegendGlobal.WaterMark) {
				drawRect(10, 4, 103, 5, BasicRGB);
				drawRect(10, 5, 103, 17, black);
				drawString(fontrenderer, Main.NAME + " + [ " + Main.VERSION + " ]", 12, 7, white);
			}
			
			if (!LegendGlobal.WaterMark) {
			drawSmallString(fontrenderer, "-> Rightarrow for gui",10 ,4 , white);
			}
			
			if (LegendGlobal.Click) {
				
				/*
				 * Comment by Shxe : Yes, this is how basic the gui really is only 9 drawrect's and 17 drawSmallString's
				 *                   I got lazy halfway threw and just did drawSmallString to display the keybinds but they
				 *                   can also be seen with the command ".keys"
				 */
				
				// drawRect
				drawRect(174, 49, 476, 201, grey);
				drawRect(175, 51, 475, 50, BasicRGB);
				drawRect(175, 51, 475, 200, black);
				drawRect(175, 60, 475, 61, grey);
				drawRect(180, 65, 220, 195, 0xff5f5f5f);
				drawRect(231, 65, 470, 195, 0xff5f5f5f);
				drawRect(181, 66, 219, 194, grey);
				drawRect(232, 66, 469, 194, grey);
				drawRect(226, 60, 225, 200, grey);
				
				// drawString
				drawSmallString(fontrenderer, "Legend", 110, 33, white);
				drawSmallString(fontrenderer, "+", 129, 33, green);
				drawString(fontrenderer, "x", 466, 51, grey);
				drawSmallString(fontrenderer, "-", 283, 33, grey);
				drawSmallString(fontrenderer, "#", 287, 33, grey);
				
				drawSmallString(fontrenderer, "- AutoHit [ R ]     ", 149, 45, white);
				drawSmallString(fontrenderer, "- Flight [ TAB ]    ", 149, 50, white);
				drawSmallString(fontrenderer, "- FullBright [ C ]  ", 149, 55, white);
				drawSmallString(fontrenderer, "- Xray [ X ]        ", 149, 60, white);
				drawSmallString(fontrenderer, "- ESP(p) [ INSERT ] ", 149, 65, white);
				drawSmallString(fontrenderer, "- NoSwing [ N ]     ", 149, 70, white);
				drawSmallString(fontrenderer, "- NoClip [ K ]      ", 149, 75, white);
				drawSmallString(fontrenderer, "- NoFall [ I ]      ", 149, 80, white);
				drawSmallString(fontrenderer, "- NoChat [ '[' ]    ", 149, 85, white);
				drawSmallString(fontrenderer, "- NoWeather [ P ]   ", 149, 90, white);
				drawSmallString(fontrenderer, "- Reach [ H ]       ", 149, 95, white);
				drawSmallString(fontrenderer, "- Instant [ , ]     ", 149, 100, white);

				
				}			

			if (LegendGlobal.Modules) {
				
				for(module m : LegendMain.modules) {
	        		if(!m.toggled)
	        			continue;
	        		
	        		/*
	        		 *  Comment by Shxe : The arraylist is half broken, the bar doesn't stop and goes to  y0 and couldn't be bothered to 
	        		 *                    fix it.
	        		 */
	        		
	        		drawString(fontrenderer, m.name, scaledresolution.getScaledWidth() - fontrenderer.getStringWidth(m.name) - 4, 3 + count* (13), white);
	        		//drawRect(scaledresolution.getScaledWidth() - fontrenderer.getStringWidth(m.name) - 8, 100,13  + count* (13), 0x90000000, grey);
	        		drawRect(scaledresolution.getScaledWidth() + 123, 0, 638, 13  + count* (13), BasicRGB);
	        		//drawRect(478, 14, 480, count* (20), 0xffff0000);
	        		
	        		count++;
	        	}
			}

			if (LegendGlobal.Coordinate) {
				int j2 = (int) Math.rint(mc.thePlayer.posX);
				int l3 = (int) Math.rint(mc.thePlayer.posY);
				int i6 = (int) Math.rint(mc.thePlayer.posZ);
				// Coords
				drawString(fontrenderer, (new StringBuilder()).append("x: \u00A77").append(j2).toString(), 125, 7,
						green);
				drawString(fontrenderer, (new StringBuilder()).append("y: \u00A77").append(l3).toString(), 125, 17,
						green);
				drawString(fontrenderer, (new StringBuilder()).append("z: \u00A77").append(i6).toString(), 125, 27,
						green);
			}
			if (LegendGlobal.clockspeed) {
				fontrenderer.drawStringWithShadow((new StringBuilder()).append("ClkSpeed x").append(LegendGlobal.clk)
						.append(" is On!").toString(), 2, 250, color);
			}
			if (LegendGlobal.autoBreak) {
				int k2 = 0xff0000;
				if (LegendGlobal.autoBreak && LegendGlobal.autoBreakID == 0) {
					k2 = 0x999999;
				} else if (LegendGlobal.autoBreak) {
					k2 = LegendGlobal.autoBreakSlow ? 0xff0000 : 255;
				}
				fontrenderer.drawStringWithShadow(
						(new StringBuilder()).append("Anni: ").append(LegendGlobal.autoBreakID).toString(), 2, 144, k2);
			}
			if (LegendGlobal.locate) {
				Iterator iterator = mc.theWorld.playerEntities.iterator();
				int i4 = 0;
				do {
					if (!iterator.hasNext()) {
						break;
					}
					EntityPlayer entityplayer = (EntityPlayer) iterator.next();
					if (entityplayer.username != mc.thePlayer.username) {
						int l7 = (int) Math
								.sqrt((mc.thePlayer.posX - entityplayer.posX) * (mc.thePlayer.posX - entityplayer.posX)
										+ (mc.thePlayer.posY - entityplayer.posY)
												* (mc.thePlayer.posY - entityplayer.posY)
										+ (mc.thePlayer.posZ - entityplayer.posZ)
												* (mc.thePlayer.posZ - entityplayer.posZ));
						String s2 = String.format("%s %d", new Object[] { entityplayer.username, Integer.valueOf(l7) });
						int i9 = fontrenderer.getStringWidth(s2);
						if (NameList.contains(entityplayer.username)) {
							namecolour = (new StringBuilder()).append("\247f").append(entityplayer.username).toString();
						} else {
							namecolour = entityplayer.username;
						}
						int entX = (int) entityplayer.posX;
						int entY = (int) entityplayer.posY;
						int entZ = (int) entityplayer.posZ;
						String s3 = (new StringBuilder()).append(namecolour).append(" \247f-\2476 ").append(l7)
								.append(" X: ").append(entX).append(" Y: ").append(entY).append(" Z: ").append(entZ)
								.toString();
						if ((double) l7 <= 100D && (double) l7 > 50D) {
							s3 = (new StringBuilder()).append(namecolour).append(" \247a<\2476 ").append(l7)
									.append(" X: ").append(entX).append(" Y: ").append(entY).append(" Z: ").append(entZ)
									.toString();
						}
						if ((double) l7 <= 50D && (double) l7 > 10D) {
							s3 = (new StringBuilder()).append(namecolour).append(" \2476<< ").append(l7).append(" X: ")
									.append(entX).append(" Y: ").append(entY).append(" Z: ").append(entZ).toString();
						}
						if ((double) l7 <= 10D && (double) l7 > 0.0D) {
							s3 = (new StringBuilder()).append(namecolour).append(" \247c<<<\2476 ").append(l7)
									.append(" X: ").append(entX).append(" Y: ").append(entY).append(" Z: ").append(entZ)
									.toString();
						}
						fontrenderer.drawStringWithShadow(s3, k - fontrenderer.getStringWidth(s3) - 10, 2 + 8 * i4,
								red);
						i4++;
					}
				} while (true);
			}
			if (!LegendGlobal.enabled) {
				fontrenderer.drawStringWithShadow("\2474============= Stigma =============", 120, 29, 0xffffff);
				fontrenderer.drawStringWithShadow("Client: \2474DISABLED", 180, 38, 0xffffff);
				fontrenderer.drawStringWithShadow("\2474 You are not logged in.", 130, 47, 0xffffff);
				fontrenderer.drawStringWithShadow("\2474 Type .help for more information.", 130, 56, 0xffffff);
			}

		}
		if (!LegendGlobal.hideChat) {
			if (recordPlayingUpFor > 0) {
				float f2 = (float) recordPlayingUpFor - f;
				int k4 = (int) ((f2 * 256F) / 20F);
				if (k4 > 255) {
					k4 = 255;
				}
				if (k4 > 0) {
					GL11.glPushMatrix();
					GL11.glTranslatef(k / 2, l - 48, 0.0F);
					GL11.glEnable(3042 /* GL_BLEND */);
					GL11.glBlendFunc(770, 771);
					int k6 = 0xffffff;
					if (field_22065_l) {
						k6 = java.awt.Color.HSBtoRGB(f2 / 50F, 0.7F, 0.6F) & 0xffffff;
					}
					fontrenderer.drawString(recordPlaying, -fontrenderer.getStringWidth(recordPlaying) / 2, -4,
							k6 + (k4 << 24));
					GL11.glDisable(3042 /* GL_BLEND */);
					GL11.glPopMatrix();
				}
			}
			byte byte0 = 10;
			boolean flag2 = false;
			if (mc.currentScreen instanceof GuiChat) {
				byte0 = 20;
				flag2 = true;
			}
			GL11.glEnable(3042 /* GL_BLEND */);
			GL11.glBlendFunc(770, 771);
			GL11.glDisable(3008 /* GL_ALPHA_TEST */);
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, l - 48, 0.0F);
			for (int l6 = 0; l6 < chatMessageList.size() && l6 < byte0; l6++) {
				if (((ChatLine) chatMessageList.get(l6)).updateCounter >= 200 && !flag2) {
					continue;
				}
				double d = (double) ((ChatLine) chatMessageList.get(l6)).updateCounter / 200D;
				d = 1.0D - d;
				d *= 10D;
				if (d < 0.0D) {
					d = 0.0D;
				}
				if (d > 1.0D) {
					d = 1.0D;
				}
				d *= d;
				int j9 = (int) (255D * d);
				if (flag2) {
					j9 = 255;
				}
				if (j9 > 0) {
					byte byte1 = 2;
					int k9 = -l6 * 9;
					String s5 = ((ChatLine) chatMessageList.get(l6)).message;
					drawRect(byte1, k9 - 1, byte1 + 320, k9 + 8, j9 / 2 << 24);
					GL11.glEnable(3042 /* GL_BLEND */);
					fontrenderer.drawStringWithShadow(s5, byte1, k9, 0xffffff + (j9 << 24));
				}
			}

			GL11.glPopMatrix();
			GL11.glEnable(3008 /* GL_ALPHA_TEST */);
			GL11.glDisable(3042 /* GL_BLEND */);
		}
		if (LegendGlobal.spamChat) {
			field_27547_flood = new Flooder(mc);
			field_27548_floodThread = new Thread(field_27547_flood);
			field_27548_floodThread.start();
		}
	}

	private void Display(List slots) {
		// TODO Auto-generated method stub

	}

	private void Display(ItemStack[] mainInventory) {
		// TODO Auto-generated method stub

	}

	private boolean checkKey(int keyUp) {
		// TODO Auto-generated method stub
		return false;
	}

	private void renderPumpkinBlur(int i, int j) {
		GL11.glDisable(2929 /* GL_DEPTH_TEST */);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(3008 /* GL_ALPHA_TEST */);
		GL11.glBindTexture(3553 /* GL_TEXTURE_2D */, mc.renderEngine.getTexture("%blur%/misc/pumpkinblur.png"));
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0D, j, -90D, 0.0D, 1.0D);
		tessellator.addVertexWithUV(i, j, -90D, 1.0D, 1.0D);
		tessellator.addVertexWithUV(i, 0.0D, -90D, 1.0D, 0.0D);
		tessellator.addVertexWithUV(0.0D, 0.0D, -90D, 0.0D, 0.0D);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929 /* GL_DEPTH_TEST */);
		GL11.glEnable(3008 /* GL_ALPHA_TEST */);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderVignette(float f, int i, int j) {
		f = 1.0F - f;
		if (f < 0.0F) {
			f = 0.0F;
		}
		if (f > 1.0F) {
			f = 1.0F;
		}
		prevVignetteBrightness += (double) (f - prevVignetteBrightness) * 0.01D;
		GL11.glDisable(2929 /* GL_DEPTH_TEST */);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(0, 769);
		GL11.glColor4f(prevVignetteBrightness, prevVignetteBrightness, prevVignetteBrightness, 1.0F);
		GL11.glBindTexture(3553 /* GL_TEXTURE_2D */, mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0D, j, -90D, 0.0D, 1.0D);
		tessellator.addVertexWithUV(i, j, -90D, 1.0D, 1.0D);
		tessellator.addVertexWithUV(i, 0.0D, -90D, 1.0D, 0.0D);
		tessellator.addVertexWithUV(0.0D, 0.0D, -90D, 0.0D, 0.0D);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929 /* GL_DEPTH_TEST */);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBlendFunc(770, 771);
	}

	private void renderPortalOverlay(float f, int i, int j) {
		if (f < 1.0F) {
			f *= f;
			f *= f;
			f = f * 0.8F + 0.2F;
		}
		GL11.glDisable(3008 /* GL_ALPHA_TEST */);
		GL11.glDisable(2929 /* GL_DEPTH_TEST */);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
		GL11.glBindTexture(3553 /* GL_TEXTURE_2D */, mc.renderEngine.getTexture("/terrain.png"));
		float f1 = (float) (Block.portal.blockIndexInTexture % 16) / 16F;
		float f2 = (float) (Block.portal.blockIndexInTexture / 16) / 16F;
		float f3 = (float) (Block.portal.blockIndexInTexture % 16 + 1) / 16F;
		float f4 = (float) (Block.portal.blockIndexInTexture / 16 + 1) / 16F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0D, j, -90D, f1, f4);
		tessellator.addVertexWithUV(i, j, -90D, f3, f4);
		tessellator.addVertexWithUV(i, 0.0D, -90D, f3, f2);
		tessellator.addVertexWithUV(0.0D, 0.0D, -90D, f1, f2);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929 /* GL_DEPTH_TEST */);
		GL11.glEnable(3008 /* GL_ALPHA_TEST */);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderInventorySlot(int i, int j, int k, float f) {
		ItemStack itemstack = mc.thePlayer.inventory.mainInventory[i];
		if (itemstack == null) {
			return;
		}
		float f1 = (float) itemstack.animationsToGo - f;
		if (f1 > 0.0F) {
			GL11.glPushMatrix();
			float f2 = 1.0F + f1 / 5F;
			GL11.glTranslatef(j + 8, k + 12, 0.0F);
			GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
			GL11.glTranslatef(-(j + 8), -(k + 12), 0.0F);
		}
		itemRenderer.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, itemstack, j, k);
		if (f1 > 0.0F) {
			GL11.glPopMatrix();
		}
		itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, itemstack, j, k);
	}

	public void updateTick() {
		if (recordPlayingUpFor > 0) {
			recordPlayingUpFor--;
		}
		updateCounter++;
		for (int i = 0; i < chatMessageList.size(); i++) {
			((ChatLine) chatMessageList.get(i)).updateCounter++;
		}

	}

	public void clearChatMessages() {
		chatMessageList.clear();
	}

	public void addChatMessage(String s) {
		int i;
		for (; mc.fontRenderer.getStringWidth(s) > 320; s = s.substring(i)) {
			for (i = 1; i < s.length() && mc.fontRenderer.getStringWidth(s.substring(0, i + 1)) <= 320; i++) {
			}
			addChatMessage(s.substring(0, i));
		}

		chatMessageList.add(0, new ChatLine(s));
		for (; chatMessageList.size() > 50; chatMessageList.remove(chatMessageList.size() - 1)) {
		}
	}

	public void setRecordPlayingMessage(String s) {
		recordPlaying = (new StringBuilder()).append("Now playing: ").append(s).toString();
		recordPlayingUpFor = 60;
		field_22065_l = true;
	}

	public void addChatMessageTranslate(String s) {
		StringTranslate stringtranslate = StringTranslate.getInstance();
		String s1 = stringtranslate.translateKey(s);
		addChatMessage(s1);
	}

	private static RenderItem itemRenderer = new RenderItem();
	private List chatMessageList;
	private Random rand;
	private Minecraft mc;
	public String field_933_a;
	private int updateCounter;
	private String recordPlaying;
	private int recordPlayingUpFor;
	private boolean field_22065_l;
	public float damageGuiPartialTime;
	float prevVignetteBrightness;
	private static Flooder field_27547_flood;
	public static Thread field_27548_floodThread;
	public static int width;
	public static int height;
	public static ArrayList NameList = new ArrayList();
	private List GuiList;
	private String namecolour;
	
	public class moduleComparator implements Comparator<module>{
		public int compare(module arg0, module arg1) {
			if(mc.fontRenderer.getStringWidth(arg0.name) > mc.fontRenderer.getStringWidth(arg1.name)) {
				return -1;
			}
			if(mc.fontRenderer.getStringWidth(arg0.name) < mc.fontRenderer.getStringWidth(arg1.name)) {
				return 1;
			}
			return 0;
		}
		
	}

}
