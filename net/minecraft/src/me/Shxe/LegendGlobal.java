// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src.me.Shxe;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.Packet14BlockDig;
import net.minecraft.src.Packet7UseEntity;
import net.minecraft.src.Torch;

import org.lwjgl.input.Keyboard;

/*
 * Comment by Shxe : This is pasted from the client "Alias" not much has changed only really added the modules to it
 *                   credit goes to "GameHerobrine" for hooking me up with the Source to alias
 */

public class LegendGlobal
{

    public LegendGlobal()
    {
    }

    public static void loadNameList()
    {
        try
        {
            int ai[] = new int[512];
            File file = new File(Minecraft.getMinecraftDir(), "Namelist.txt");
            if(file.exists())
            {
                BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
                String s;
                for(int i = 0; (s = bufferedreader.readLine()) != null; i++)
                {
                    GuiIngame.NameList.add(s);
                }

            }
        }
        catch(Exception exception)
        {
            System.err.print(exception.toString());
        }
    }

    public static void saveNameList()
    {
        try
        {
            File file = new File(Minecraft.getMinecraftDir(), "Namelist.txt");
            BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
            for(int i = 0; i < GuiIngame.NameList.size(); i++)
            {
                bufferedwriter.write((new StringBuilder()).append((String)GuiIngame.NameList.get(i)).append("\r\n").toString());
            }

            bufferedwriter.close();
        }
        catch(Exception exception)
        {
            System.err.print(exception.toString());
        }
    }

    public static void freecamNew(EntityPlayerSP entityplayersp)
    {
        if(freecamNew)
        {
            if(mc.renderViewEntity instanceof LegendEntityCamera)
            {
                mc.renderViewEntity = entityplayersp;
                Fly = !Fly;
                freecamNew1 = false;
            } else
            {
                Fly = !Fly;
                Fly = true;
                entityplayersp.motionX = 0.0D;
                entityplayersp.motionY = 0.0D;
                entityplayersp.motionZ = 0.0D;
                mc.renderViewEntity = new LegendEntityCamera(mc.theWorld);
                mc.renderViewEntity.setPositionAndRotation(entityplayersp.posX, entityplayersp.posY, entityplayersp.posZ, entityplayersp.rotationYaw, entityplayersp.rotationPitch);
            }
            freecamNew = false;
        }
    }

    public static Object checkKeys(int i, boolean flag)
    {
    	if(i == Keyboard.KEY_UP && flag && enabled) {
    		Modules = !Modules;
    		if(Modules)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a Arraylist Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c Arraylist Disabled");
            }
    	}
    	if(i == Keyboard.KEY_RSHIFT && flag && enabled) {
    		Click = !Click;
    		if(Click)
            {} else {}
    	}
    	if(i == Keyboard.KEY_RIGHT && flag && enabled) {
    		WaterMark = !WaterMark;
    		if(WaterMark)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a Watermark Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c Watermark Disabled");
            }
    	}
    	if(i == Keyboard.KEY_INSERT && flag && enabled) {
    		PlayerESP = !PlayerESP;
    		if(PlayerESP)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a PlayerESP test Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c PlayerESP test Disabled");
            }
    	}
        if(i == Keyboard.KEY_TAB && flag && enabled)
        {
            Fly = !Fly;
            if(Fly)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a Flight Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c Flight Disabled");
            }
        }
        if(i == 65 && flag && enabled)
        {
            AutoWalk = !AutoWalk;
            if(AutoWalk)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a AutoWalk Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c AutoWalk Disabled");
            }
        }
        if(i == 36 && flag && enabled)
        {
            highJump = !highJump;
            if(highJump)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a High-Jump Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c High-Jump Disabled");
            }
        }
        if(i == 46 && flag && enabled)
        {
            fullBright = !fullBright;
            rerenderMap();
            if(fullBright)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a Full-Bright Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c Full-Bright Disabled");
            }
        }
        if(i == 34 && flag && enabled)
        {
            freecamNew1 = !freecamNew1;
            freecamNew = true;
            rerenderMap();
            if(freecamNew1)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a New-FreeCam Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c New-FreeCam Disabled");
            }
        }
        if(i == 64 && flag && enabled)
        {
            clockspeed = !clockspeed;
            if(clockspeed)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a Timer Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c Timer Disabled");
            }
        }
        if(i == 49 && flag && enabled)
        {
            noSwing = !noSwing;
            if(noSwing)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a No-Swing Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c No-Swing Disabled");
            }
        }
        if(i == 35 && flag && enabled)
        {
            extendedReach = !extendedReach;
            if(extendedReach)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a Reach Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c Reach Disabled");
            }
        }
        if(i == Keyboard.KEY_R && flag && enabled) {
        	LegendGlobal.autoHit = !LegendGlobal.autoHit;
            if(LegendGlobal.autoHit)
            {
                mc.ingameGUI.addChatMessage("\247a Kill Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("\247c Kill Disabled");
            }
        }
        if(i == Keyboard.KEY_I && flag && enabled) {
        	fall = !fall;
        	if(fall)
        	{
                mc.thePlayer.addChatMessage("\247a Nofall Enabled");
            } else
            {
                mc.thePlayer.addChatMessage("\247c Nofall Disabled");
            }
        }
        if(i == 37 && flag && enabled)
        {
            noClip = !noClip;
            if(noClip)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a No-Clip Enabled - You finna die");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c No-Clip Disabled - You not dead yet?");
            }
        }
        if(i == 45 && flag && enabled)
        {
            xRay = !xRay;
            if(!fullBright1)
            {
                fullBright1 = !fullBright1;
            } else
            {
                fullBright1 = !fullBright1;
            }
            rerenderMap();
            mc.entityRenderer.updateRenderer();
            if(xRay)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a X-Ray Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c X-Ray Disabled");
            }
        }
        if(i == 53 && flag && enabled)
        {
            spider = !spider;
            if(spider)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a Timer-Step Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c Timer-Step Disabled");
            }
        }
        if(i == 50 && flag && enabled)
        {
            if(EntityClientPlayerMP.floodmsg != null)
            {
                spamChat = !spamChat;
                if(spamChat)
                {
                    mc.ingameGUI.addChatMessage("\247a Flood Enabled");
                } else
                {
                    mc.ingameGUI.addChatMessage("\247c Flood Disabled");
                    EntityClientPlayerMP.floodmsg = null;
                }
            } else
            {
                mc.ingameGUI.addChatMessage("Flood message not set!");
            }
        }
        if(i == 26 && flag && enabled)
        {
            hideChat = !hideChat;
            if(hideChat)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a Hide-Chat Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c Hide-Chat Disabled");
            }
        }
        if(i == 25 && flag && enabled)
        {
            weather = !weather;
            if(weather)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a Hide-Weather Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c Hide-Weather Disabled");
            }
        }
        if(i == 44 && flag && enabled)
        {
            sneak = !sneak;
        }
        if(i == 51 && flag && enabled)
        {
            instant = !instant;
            if(instant)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a Instant-Mine Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c Instant-Mine Disabled");
            }
        }
        if(i == Keyboard.KEY_F8 && flag && enabled)
        {
            locate = !locate;
        }
        if(i == 47 && flag && enabled)
        {
            slowMove = !slowMove;
        }
        if(i == Keyboard.KEY_DOWN && flag && enabled)
        {
            Coordinate = !Coordinate;
            if(Coordinate)
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247a Coords Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("[ " + Main.NAME + " ]\247c Coords Disabled");
            }
        }
        if(i == 65 && flag && enabled)
        {
            autowalk = !autowalk;
        }
        if(i == 200 && flag && enabled)
        {
            status = !status;
        }
        if(i == 43 && flag && enabled)
        {
            torch = !torch;
            if(torch)
            {
                mc.ingameGUI.addChatMessage("\247a Torch Anni Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("\247c Torch Anni Disabled");
            }
            if(torch)
            {
                Torch = new Torch(mc);
                TorchThread = new Thread(Torch);
                TorchThread.start();
                System.out.println("STARTED");
            } else
            {
                TorchThread.stop();
            }
        }
        if(i == 48 && flag && enabled)
        {
            if(!autoBreak)
            {
                autoBreak = true;
            } else
            if(autoBreakSlow)
            {
                autoBreak = false;
                autoBreakSlow = false;
                autoBreakID = 0;
            } else
            if(autoBreak)
            {
                autoBreakSlow = true;
            }
        }
        if(i == 11 && flag && enabled)
        {
            autoBreak = false;
            autoBreakID = 0;
            autoBreakSlow = false;
            Fly = false;
            Modules = false;
            AutoWalk = false;
            instant = false;
            noClip = false;
            fov = false;
            sneak = false;
            freeCam = false;
            spider = false;
            xRay = false;
            fullBright = true;
            fullBright1 = false;
            rerenderMap();
            autoHit = false;
            highJump = false;
            noSwing = false;
            hideChat = false;
            locate = false;
            weather = false;
            extendedReach = false;
            clockspeed = false;
            fall = true;
            lock = false;
            sign = false;
            step = false;
            mc.ingameGUI.addChatMessage("\247cPanic - Everything off.");
        }
		return null;
    }

    public static void runFly(EntityPlayerSP entityplayersp)
    {
        if(Fly || freeCam)
        {
            entityplayersp.onGround = false;
            entityplayersp.motionX = 0.0D;
            entityplayersp.motionY = 0.0D;
            entityplayersp.motionZ = 0.0D;
            double d = entityplayersp.rotationPitch + 90F;
            double d1 = entityplayersp.rotationYaw + 90F;
            boolean flag = Keyboard.isKeyDown(17) && mc.inGameHasFocus;
            boolean flag1 = Keyboard.isKeyDown(31) && mc.inGameHasFocus;
            boolean flag2 = Keyboard.isKeyDown(30) && mc.inGameHasFocus;
            boolean flag3 = Keyboard.isKeyDown(32) && mc.inGameHasFocus;
            if(flag)
            {
                if(flag2)
                {
                    d1 -= 45D;
                } else
                if(flag3)
                {
                    d1 += 45D;
                }
            } else
            if(flag1)
            {
                d1 += 180D;
                if(flag2)
                {
                    d1 += 45D;
                } else
                if(flag3)
                {
                    d1 -= 45D;
                }
            } else
            if(flag2)
            {
                d1 -= 90D;
            } else
            if(flag3)
            {
                d1 += 90D;
            }
            if(flag || flag2 || flag1 || flag3)
            {
                entityplayersp.motionX = Math.cos(Math.toRadians(d1));
                entityplayersp.motionZ = Math.sin(Math.toRadians(d1));
            }
            if(Keyboard.isKeyDown(57) && mc.inGameHasFocus)
            {
                entityplayersp.motionY++;
            } else
            if(Keyboard.isKeyDown(42) && mc.inGameHasFocus)
            {
                entityplayersp.motionY--;
            }
            if(slowMove)
            {
                entityplayersp.motionX /= 5D;
                entityplayersp.motionY /= 5D;
                entityplayersp.motionZ /= 5D;
            }
        }
    }

    public static void runAutoBreak()
    {
    	if(autoHit && mc.isMultiplayerWorld())
        {
            EntityPlayer entityplayer = null;
            float f = 10F;
            List list = mc.theWorld.getEntitiesWithinAABBExcludingEntity(mc.thePlayer, AxisAlignedBB.getBoundingBox(mc.thePlayer.posX - 6D, mc.thePlayer.posY - 6D, mc.thePlayer.posZ - 6D, mc.thePlayer.posX + 6D, mc.thePlayer.posY + 6D, mc.thePlayer.posZ + 6D));
            for(int l = 0; l < list.size(); l++)
            {
                System.out.println(list.get(l));
                if(!(list.get(l) instanceof EntityPlayer))
                {
                    continue;
                }
                float f1 = ((EntityPlayer)list.get(l)).getDistanceToEntity(mc.thePlayer);
                if(f1 < f)
                {
                    entityplayer = (EntityPlayer)list.get(l);
                    f = f1;
                }
            }
            if(entityplayer instanceof EntityPlayer)
            {
                mc.getSendQueue().addToSendQueue(new Packet7UseEntity(0, entityplayer.entityId, 1));
            }
        }
        if(autoBreak && autoBreakID != 0 && mc.isMultiplayerWorld())
        {
            int i = (int)mc.thePlayer.posX;
            int j = (int)mc.thePlayer.posY;
            int k = (int)mc.thePlayer.posZ;
            byte byte0 = 4;
label0:
            for(int i1 = (int)(mc.thePlayer.posX - 6D); i1 < i + 4 + 1; i1++)
            {
                int j1 = (int)(mc.thePlayer.posY - 6D);
                do
                {
                    if(j1 >= j + 4 + 1)
                    {
                        continue label0;
                    }
                    for(int k1 = (int)(mc.thePlayer.posZ - 6D); k1 < k + 4 + 1; k1++)
                    {
                        if(mc.theWorld.getBlockId(i1, j1, k1) != autoBreakID)
                        {
                            continue;
                        }
                        if(autoBreakSlow)
                        {
                            double d = ((double)i1 + 0.5D) - mc.thePlayer.posX;
                            double d1 = ((double)j1 + 0.5D) - mc.thePlayer.posY;
                            double d2 = ((double)k1 + 0.5D) - mc.thePlayer.posZ;
                            double d3 = Math.sqrt(d * d + d1 * d1);
                            double d4 = Math.sqrt(d3 * d3 + d2 * d2);
                            if(d4 >= 6D)
                            {
                                continue;
                            }
                            sendFakeBlockDestroy();
                            mc.getSendQueue().addToSendQueue(new Packet14BlockDig(0, i1, j1, k1, 1));
                            mc.getSendQueue().addToSendQueue(new Packet14BlockDig(2, i1, j1, k1, 1));
                            break label0;
                        }
                        mc.getSendQueue().addToSendQueue(new Packet14BlockDig(0, i1, j1, k1, 1));
                        mc.getSendQueue().addToSendQueue(new Packet14BlockDig(2, i1, j1, k1, 1));
                    }

                    j1++;
                } while(true);
            }

        }
    }

    static void rerenderMap()
    {
        int i = (int)mc.thePlayer.posX;
        int j = (int)mc.thePlayer.posZ;
        mc.theWorld.markBlocksDirty(i - 1000, 0, j - 1000, i + 1000, 127, j + 1000);
    }

    public static void sendFakeBlockDestroy()
    {
        mc.getSendQueue().addToSendQueue(new Packet14BlockDig(0, 0x80000000, 0x80000000, 0x80000000, 1));
        mc.getSendQueue().addToSendQueue(new Packet14BlockDig(2, 0x80000000, 0x80000000, 0x80000000, 1));
    }

    public static void getRealUsername()
    {
        username = mc.session.username;
    }

    public static Minecraft mc = null;
    public EntityPlayerSP ep;
    public static boolean sneak;
    public static boolean PlayerESP;
    public static boolean WaterMark;
    public static boolean nothing;
    public static boolean fullBright;
    public static boolean fullBright1;
    public static boolean freeCam;
    public static boolean highJump;
    public static boolean clockspeed;
    public static boolean extendedReach;
    public static boolean fastSneak;
    public static boolean noSwing;
    public static boolean noClip;
    public static boolean fov;
    public static boolean spider;
    public static boolean xRay;
    public static boolean spamChat;
    public static boolean Coordinate;
    public static boolean Fly;
    public static boolean Modules;
    public static boolean AutoWalk;
    public static boolean Speed;
    public static boolean Instant;
    public static boolean torch;
    public static boolean slowMove = true;
    public static boolean hideChat;
    public static boolean fall;
    public static boolean sign;
    public static boolean cavefinder;
    public static boolean Click;
    public static boolean instant;
    public static boolean locate;
    public static boolean weather;
    public static boolean freecamNew = false;
    public static boolean freecamNew1;
    public static boolean status;
    public static boolean step;
    public static boolean autowalk;
    public static boolean enabled = true;
    public static boolean lock;
    public static int autoBreakID = 0;
    public static boolean autoBreak;
    public static boolean autoBreakSlow;
    public static boolean autoHit;
    public static String floodmsg = "~";
    public static boolean block = false;
    public static float clk = 5F;
    public static boolean hack_drawSettings = true;
    public static int blackList[] = {
        1, 7, 13, 87, 8, 9
    };
    public static int blackList1[] = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 
        13, 17, 18, 19, 20, 22, 23, 24, 25, 26, 
        27, 28, 30, 31, 32, 35, 37, 38, 39, 40, 
        41, 42, 43, 44, 45, 47, 49, 50, 51, 53, 
        55, 57, 58, 59, 60, 61, 62, 63, 64, 65, 
        66, 67, 68, 69, 70, 71, 72, 75, 76, 77, 
        78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 
        88, 89, 90, 91, 92, 93, 94, 96
    };
    private static Torch Torch;
    private static Thread TorchThread;
    public static String username = "";
    public static boolean nopaidcheck = false;
    
	public static Object enabled() {
		return LegendGlobal.enabled();
	}

}
