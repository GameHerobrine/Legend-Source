// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.*;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.src.me.Shxe.LegendGlobal;


public class EntityClientPlayerMP extends EntityPlayerSP
{

    public EntityClientPlayerMP(Minecraft minecraft, World world, Session session, NetClientHandler netclienthandler)
    {
        super(minecraft, world, session, 0);
        field_9380_bx = 0;
        field_21093_bH = false;
        field_9382_bF = false;
        wasSneaking = false;
        field_12242_bI = 0;
        sendQueue = netclienthandler;
    }

    public boolean attackEntityFrom(Entity entity, int i)
    {
        return false;
    }

    public void heal(int i)
    {
    }

    public void onUpdate()
    {
        if(!worldObj.blockExists(MathHelper.floor_double(posX), 64, MathHelper.floor_double(posZ)))
        {
            return;
        } else
        {
            super.onUpdate();
            func_4056_N();
            return;
        }
    }

    public void func_4056_N()
    {
        if(LegendGlobal.freeCam)
        {
            sendQueue.addToSendQueue(new Packet0KeepAlive());
        } else
        {
            if(field_9380_bx++ == 20)
            {
                sendInventoryChanged();
                field_9380_bx = 0;
            }
            boolean flag = LegendGlobal.sneak;
            if(flag != wasSneaking)
            {
                if(flag)
                {
                    sendQueue.addToSendQueue(new Packet19EntityAction(this, 1));
                } else
                {
                    sendQueue.addToSendQueue(new Packet19EntityAction(this, 2));
                }
                wasSneaking = flag;
            }
            double d = posX - oldPosX;
            double d1 = boundingBox.minY - field_9378_bz;
            double d2 = posY - oldPosY;
            double d3 = posZ - oldPosZ;
            double d4 = rotationYaw - oldRotationYaw;
            double d5 = rotationPitch - oldRotationPitch;
            boolean flag1 = d1 != 0.0D || d2 != 0.0D || d != 0.0D || d3 != 0.0D;
            boolean flag2 = d4 != 0.0D || d5 != 0.0D;
            if(LegendGlobal.Fly || LegendGlobal.freeCam)
            {
                onGround = true;
            }
            if(ridingEntity != null)
            {
                if(flag2)
                {
                    sendQueue.addToSendQueue(new Packet11PlayerPosition(motionX, -999D, -999D, motionZ, LegendGlobal.fall ? true : onGround));
                } else
                {
                    sendQueue.addToSendQueue(new Packet13PlayerLookMove(motionX, -999D, -999D, motionZ, rotationYaw, rotationPitch, LegendGlobal.fall ? true : onGround));
                }
                flag1 = false;
            } else
            if(flag1 && flag2)
            {
                sendQueue.addToSendQueue(new Packet13PlayerLookMove(posX, boundingBox.minY, posY, posZ, rotationYaw, rotationPitch, LegendGlobal.fall ? true : onGround));
                field_12242_bI = 0;
            } else
            if(flag1)
            {
                sendQueue.addToSendQueue(new Packet11PlayerPosition(posX, boundingBox.minY, posY, posZ, LegendGlobal.fall ? true : onGround));
                field_12242_bI = 0;
            } else
            if(flag2)
            {
                sendQueue.addToSendQueue(new Packet12PlayerLook(rotationYaw, rotationPitch, LegendGlobal.fall ? true : onGround));
                field_12242_bI = 0;
            } else
            {
                sendQueue.addToSendQueue(new Packet10Flying(LegendGlobal.fall ? true : onGround));
                if(field_9382_bF != onGround || field_12242_bI > 200)
                {
                    field_12242_bI = 0;
                } else
                {
                    field_12242_bI++;
                }
            }
            field_9382_bF = onGround;
            if(flag1)
            {
                oldPosX = posX;
                field_9378_bz = boundingBox.minY;
                oldPosY = posY;
                oldPosZ = posZ;
            }
            if(flag2)
            {
                oldRotationYaw = rotationYaw;
                oldRotationPitch = rotationPitch;
            }
        }
    }

    public void dropCurrentItem()
    {
        sendQueue.addToSendQueue(new Packet14BlockDig(4, 0, 0, 0, 0));
    }

    private void sendInventoryChanged()
    {
    }

    protected void joinEntityItemWithWorld(EntityItem entityitem)
    {
    }

    public void sendChatMessage(String s)
    {
        if(s.startsWith(".up"))
        {
            try
            {
                boolean flag = false;
                String as4[] = s.split(" ");
                double d = posY + (double)Integer.parseInt(as4[1]);
                for(int k1 = 0; k1 < 2; k1++)
                {
                    setLocationAndAngles(posX, d, posZ, rotationYaw, rotationPitch);
                    sendQueue.addToSendQueue(new Packet11PlayerPosition(posX, d - 1.0D, d, posZ, true));
                }

            }
            catch(Exception exception)
            {
                mc.thePlayer.addChatMessage("Invalid Syntax: .up [Integer]");
            }
        } else
        if(s.startsWith(".teleport"))
        {
            try
            {
                boolean flag1 = false;
                String as5[] = s.split(" ");
                int k = Integer.parseInt(as5[1]);
                int i1 = Integer.parseInt(as5[2]);
                int l1 = Integer.parseInt(as5[3]);
                for(int l2 = 0; l2 < 2; l2++)
                {
                    setLocationAndAngles(k, i1, l1, rotationYaw, rotationPitch);
                    sendQueue.addToSendQueue(new Packet11PlayerPosition(k, i1 - 1, i1, l1, true));
                    mc.thePlayer.addChatMessage("\247a Now teleporting");
                }

            }
            catch(Exception exception1)
            {
                mc.thePlayer.addChatMessage("Invalid Syntax: .teleport [X] [Y] [Z]");
            }
        } else
        if(s.startsWith(".x"))
        {
            try
            {
                boolean flag2 = false;
                String as6[] = s.split(" ");
                double d1 = posX + (double)Integer.parseInt(as6[1]);
                for(int i2 = 0; i2 < 2; i2++)
                {
                    setLocationAndAngles(d1, posY, posZ, rotationYaw, rotationPitch);
                    sendQueue.addToSendQueue(new Packet11PlayerPosition(d1, posY - 1.0D, posY, posZ, true));
                }

            }
            catch(Exception exception2)
            {
                mc.thePlayer.addChatMessage("Invalid Syntax: .x [Integer]");
            }
        } else
        if(s.startsWith(".z"))
        {
            try
            {
                boolean flag3 = false;
                String as7[] = s.split(" ");
                double d2 = posZ + (double)Integer.parseInt(as7[1]);
                for(int j2 = 0; j2 < 2; j2++)
                {
                    setLocationAndAngles(posX, posY, d2, rotationYaw, rotationPitch);
                    sendQueue.addToSendQueue(new Packet11PlayerPosition(posX, posY - 1.0D, posY, d2, true));
                }

            }
            catch(Exception exception3)
            {
                mc.thePlayer.addChatMessage("Invalid Syntax: .z [Integer]");
            }
        } else
        if(s.startsWith(".help"))
        {
            mc.ingameGUI.addChatMessage("\2474=== Legend Client help ===");
            mc.ingameGUI.addChatMessage("\247aSyntax .commands - Dot Commands List");
            mc.ingameGUI.addChatMessage("\247aSyntax .keys - Keybinding List");
            mc.ingameGUI.addChatMessage("\247aSyntax .login <password>");
            mc.ingameGUI.addChatMessage("\247aSyntax .Dev - Dev list for this version");
            mc.ingameGUI.addChatMessage("\2474=========================");
        } else
        if(s.startsWith(".keys"))
        {
            mc.ingameGUI.addChatMessage("\2474=== Legend Client keys ===");
            mc.ingameGUI.addChatMessage("C > FullBright");
            mc.ingameGUI.addChatMessage("N > NoSwing");
            mc.ingameGUI.addChatMessage("M > Message Flooder");
            mc.ingameGUI.addChatMessage("G > NewFreeCam");
            mc.ingameGUI.addChatMessage("H > Reach");
            mc.ingameGUI.addChatMessage("J > HighJump");
            mc.ingameGUI.addChatMessage("L > KillAura");
            mc.ingameGUI.addChatMessage("R > Flight");
            mc.ingameGUI.addChatMessage("I > NoFall");
            mc.ingameGUI.addChatMessage("P > NoWeather");
            mc.ingameGUI.addChatMessage("0 > Disables All Active Hacks.");
            mc.ingameGUI.addChatMessage("Rightarrow > Watermark");
            mc.ingameGUI.addChatMessage("Uparrow > ArrayList");
            mc.ingameGUI.addChatMessage("Downarrow > CoordinateHUD");
            mc.ingameGUI.addChatMessage(", > InstaMine");
            mc.ingameGUI.addChatMessage("/ > TimerStep");
            mc.ingameGUI.addChatMessage("[ > ToggleChat");
            mc.ingameGUI.addChatMessage("\2474=========================");
        } else
        if(s.startsWith(".commands"))
        {
            mc.ingameGUI.addChatMessage("\2474=== Legend Client commands ===");
            mc.ingameGUI.addChatMessage("\247aSyntax .up [Integer] - Move up or down (use neg int)");
            mc.ingameGUI.addChatMessage("\247aSyntax .x [Integer] - Move along x-axis (neg int = reverse)");
            mc.ingameGUI.addChatMessage("\247aSyntax .z [Integer] - Move along z-axis (neg int = reverse)");
            mc.ingameGUI.addChatMessage("\247aSyntax .teleport [x] [y] [z] - Teleport to Co-ordinates");
            mc.ingameGUI.addChatMessage("\247aSyntax .step - Step up shit.");
            mc.ingameGUI.addChatMessage("\247aSyntax .spam=<message>=[Integer] - Set Spam Message and Int");
            mc.ingameGUI.addChatMessage("\247aSyntax .flood=<message> - Set Flood Message");
            mc.ingameGUI.addChatMessage("\247aSyntax .spamfile FILENAME - Run Spam File");
            mc.ingameGUI.addChatMessage("\247aSyntax .clkspeed [Integer] - Set Clock Speed");
            mc.ingameGUI.addChatMessage("\247aSyntax .autobreakid [Block ID] - Set autobreak id");
            mc.ingameGUI.addChatMessage("\247aSyntax .name [add/del] <username> - Add/Del name to list.");
            mc.ingameGUI.addChatMessage("\247aSyntax .list - Name list");
            mc.ingameGUI.addChatMessage("\247aSyntax .clear - Clear Locate list");
            mc.ingameGUI.addChatMessage("\247aSyntax .sign - Sign Edit Toggle");
            mc.ingameGUI.addChatMessage("\247aSyntax .fc - FreeCamOld(for the dupe) Toggle");
            mc.ingameGUI.addChatMessage("\247aSyntax .lock - Lock Toggle");
            mc.ingameGUI.addChatMessage("\2474=========================");
        } else
        if(s.startsWith(".Devs")) 
        {
        	mc.ingameGUI.addChatMessage("\2474=== Stigma Client Dev List ===");
        	mc.ingameGUI.addChatMessage("\247a======== Shae ==========");
        }else
        if(s.startsWith(".name"))
        {
            try
            {
                String as[] = s.split(" ");
                String s1 = as[1];
                String s3 = as[2];
                if(s1.equalsIgnoreCase("add"))
                {
                    if(!GuiIngame.NameList.contains(s3))
                    {
                        GuiIngame.NameList.add(s3);
                        mc.thePlayer.addChatMessage((new StringBuilder()).append(s3).append(" added.").toString());
                        updateCloak();
                        LegendGlobal.saveNameList();
                    } else
                    {
                        mc.thePlayer.addChatMessage((new StringBuilder()).append("Name list already contains \247a").append(s3).toString());
                    }
                } else
                if(s1.equalsIgnoreCase("del"))
                {
                    if(GuiIngame.NameList.contains(s3))
                    {
                        GuiIngame.NameList.remove(s3);
                        mc.thePlayer.addChatMessage((new StringBuilder()).append(s3).append(" deleted.").toString());
                        updateCloak();
                        LegendGlobal.saveNameList();
                    } else
                    {
                        mc.thePlayer.addChatMessage((new StringBuilder()).append("Name list does not contain \247c").append(s3).toString());
                    }
                } else
                {
                    mc.thePlayer.addChatMessage("Syntax: .name [add/del] <username>");
                }
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                mc.thePlayer.addChatMessage("NameList not created.");
            }
            catch(Exception exception4)
            {
                mc.thePlayer.addChatMessage("Error In Handling NameList");
                mc.thePlayer.addChatMessage("Syntax: .name [add/del] <username>");
            }
        } else
        if(s.startsWith(".list"))
        {
            for(int i = 0; i < GuiIngame.NameList.size(); i++)
            {
                String s2 = (new StringBuilder()).append((new StringBuilder()).append("\247a").append(i).toString()).append(" \247f").append((String)GuiIngame.NameList.get(i)).toString();
                mc.thePlayer.addChatMessage((new StringBuilder()).append("").append(s2).toString());
            }

            if(GuiIngame.NameList.isEmpty())
            {
                mc.thePlayer.addChatMessage("No names in list to display.");
            }
        } else
        if(s.startsWith(".clear"))
        {
            Locate.player.clear();
        } else
        if(s.startsWith(".lock"))
        {
            LegendGlobal.lock = !LegendGlobal.lock;
            if(LegendGlobal.lock)
            {
                mc.ingameGUI.addChatMessage("\247a Lock Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("\247c Lock Disabled");
            }
        } else
        if(s.startsWith(".sign"))
        {
            LegendGlobal.sign = !LegendGlobal.sign;
            if(LegendGlobal.sign)
            {
                mc.ingameGUI.addChatMessage("\247a Sign Change Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("\247c Sign Change Disabled");
            }
        } else
        if(s.startsWith(".step"))
        {
            LegendGlobal.step = !LegendGlobal.step;
            if(LegendGlobal.step)
            {
                mc.ingameGUI.addChatMessage("\247a Step Enabled");
            } else
            {
                mc.ingameGUI.addChatMessage("\247c Step Disabled");
            }
        }else
        if(s.startsWith(".clkspeed"))
        {
            try
            {
                String as1[] = s.split(" ");
                Float float1 = new Float(as1[1]);
                LegendGlobal.clk = float1.floatValue();
                mc.ingameGUI.addChatMessage((new StringBuilder()).append("ClkSpeed set to: \247a").append(float1).toString());
            }
            catch(Exception exception5)
            {
                mc.thePlayer.addChatMessage("Invalid Syntax: .clkspeed [Integer]");
            }
        } else 
        	if(s.startsWith(".fov"))
            {
                try
                {
                    String as1[] = s.split(" ");
                    Float float1 = new Float(as1[1]);
                    LegendGlobal.clk = float1.floatValue();
                    mc.ingameGUI.addChatMessage((new StringBuilder()).append("fov set to: \247a").append(float1).toString());
                }
                catch(Exception exception5)
                {
                    mc.thePlayer.addChatMessage("Invalid Syntax: .fov [Integer]");
                }
            } else
        if(s.startsWith(".autobreak"))
        {
            try
            {
                String as2[] = s.split(" ");
                int j = Integer.parseInt(as2[1]);
                LegendGlobal.autoBreakID = j;
                mc.ingameGUI.addChatMessage((new StringBuilder()).append("autoBreakID set to: \247a").append(j).toString());
            }
            catch(Exception exception6)
            {
                mc.thePlayer.addChatMessage("Invalid Syntax: .autobreak [Block ID]");
            }
        } else
        if(s.startsWith(".fc"))
        {
            LegendGlobal.freeCam = !LegendGlobal.freeCam;
            if(!LegendGlobal.freeCam)
            {
                LegendGlobal.noClip = false;
            } else
            {
                LegendGlobal.noClip = true;
            }
        } else
        if(s.startsWith(".flood"))
        {
            try
            {
                boolean flag4 = false;
                String as8[] = s.split("=");
                floodmsg = as8[1];
                mc.ingameGUI.addChatMessage((new StringBuilder()).append("Flood message set to: \247a").append(as8[1]).toString());
            }
            catch(Exception exception7)
            {
                mc.thePlayer.addChatMessage("Invalid Syntax: .flood=[Message]");
            }
        } else
        if(s.startsWith(".spamfile"))
        {
            String as3[] = s.split(" ");
            try
            {
                InputStream inputstream = getClass().getResourceAsStream(as3[1]);
                DataInputStream datainputstream = new DataInputStream(inputstream);
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(datainputstream));
                String s5;
                while((s5 = bufferedreader.readLine()) != null) 
                {
                    mc.getSendQueue().addToSendQueue(new Packet3Chat(s5));
                }
                datainputstream.close();
            }
            catch(Exception exception9)
            {
                mc.thePlayer.addChatMessage("File does not exisit.");
            }
        } else
        if(s.startsWith(".login"))
        {
            boolean flag5 = false;
            String as9[] = s.split(" ");
            int l = Integer.parseInt(as9[1]);
            char c = '\u07C8';
            if(l == c)
            {
                LegendGlobal.enabled = true;
                mc.ingameGUI.addChatMessage("Login \2472OK");
            } else
            {
                mc.ingameGUI.addChatMessage("Login \2474Bad");
            }
        } else
        if(s.startsWith(".spam"))
        {
            try
            {
                boolean flag6 = false;
                String as10[] = s.split("=");
                String s4 = as10[1];
                int j1 = Integer.parseInt(as10[2]);
                for(int k2 = 0; k2 < j1; k2++)
                {
                    sendQueue.addToSendQueue(new Packet3Chat(s4));
                }

            }
            catch(Exception exception8)
            {
                mc.thePlayer.addChatMessage("Invalid Syntax: .spam=<Message>=[Integer]");
            }
        } else
        {
            if(s.startsWith("."))
            {
                return;
            }
            sendQueue.addToSendQueue(new Packet3Chat(s));
        }
    }

    public void swingItem()
    {
        if(!LegendGlobal.noSwing)
        {
            super.swingItem();
            sendQueue.addToSendQueue(new Packet18Animation(this, 1));
        }
    }

    public void respawnPlayer()
    {
        sendInventoryChanged();
        sendQueue.addToSendQueue(new Packet9Respawn((byte)dimension));
    }

    protected void damageEntity(int i)
    {
        health -= i;
    }

    public void closeScreen()
    {
        sendQueue.addToSendQueue(new Packet101CloseWindow(craftingInventory.windowId));
        inventory.setItemStack(null);
        super.closeScreen();
    }

    public void setHealth(int i)
    {
        if(field_21093_bH)
        {
            if(!LegendGlobal.lock)
            {
                super.setHealth(i);
            }
        } else
        if(!LegendGlobal.lock)
        {
            health = i;
            field_21093_bH = true;
        }
    }

    public void addStat(StatBase statbase, int i)
    {
        if(statbase == null)
        {
            return;
        }
        if(statbase.field_27088_g)
        {
            super.addStat(statbase, i);
        }
    }

    public void func_27027_b(StatBase statbase, int i)
    {
        if(statbase == null)
        {
            return;
        }
        if(!statbase.field_27088_g)
        {
            super.addStat(statbase, i);
        }
    }

    public NetClientHandler sendQueue;
    private int field_9380_bx;
    private boolean field_21093_bH;
    private double oldPosX;
    private double field_9378_bz;
    private double oldPosY;
    private double oldPosZ;
    private float oldRotationYaw;
    private float oldRotationPitch;
    private boolean field_9382_bF;
    private boolean wasSneaking;
    private int field_12242_bI;
    public static String floodmsg = null;

}
