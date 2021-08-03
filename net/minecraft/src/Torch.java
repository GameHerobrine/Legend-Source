// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.PrintStream;
import net.minecraft.client.Minecraft;
import net.minecraft.src.me.Shxe.LegendGlobal;

// Referenced classes of package net.minecraft.src:
//            AliasGlobal, EntityPlayerSP, World, Block, 
//            Packet14BlockDig, NetClientHandler

public class Torch
    implements Runnable
{

    public Torch(Minecraft minecraft)
    {
        field_27531_f = true;
        field_27530_smash = true;
        field_27528_x = LegendGlobal.mc;
    }

    public void run()
    {
        field_27529_lpX = (int)field_27528_x.thePlayer.posX;
        field_27533_lpY = (int)field_27528_x.thePlayer.posY;
        field_27532_lpZ = (int)field_27528_x.thePlayer.posZ;
        while(field_27531_f) 
        {
            if(!LegendGlobal.torch)
            {
                field_27531_f = false;
            } else
            if(!field_27530_smash)
            {
                func_27526_Smash();
                field_27530_smash = true;
            } else
            {
                int i = (int)field_27528_x.thePlayer.posX;
                int j = (int)field_27528_x.thePlayer.posY;
                int k = (int)field_27528_x.thePlayer.posZ;
                if(func_27524_getDistance(i, j, k, field_27529_lpX, field_27533_lpY, field_27532_lpZ) >= 16)
                {
                    func_27526_Smash();
                    field_27530_smash = true;
                }
            }
            try
            {
                Thread.sleep(150L);
            }
            catch(InterruptedException interruptedexception)
            {
                throw new RuntimeException(interruptedexception);
            }
        }
    }

    public void func_27527_Stop()
    {
        field_27531_f = false;
    }

    public void func_27526_Smash()
    {
        int i = (int)field_27528_x.thePlayer.posX;
        int j = (int)field_27528_x.thePlayer.posY;
        int k = (int)field_27528_x.thePlayer.posZ;
        int l = i + func_27525_GetBlockReachDistance();
        int i1 = j + func_27525_GetBlockReachDistance();
        int j1 = k + func_27525_GetBlockReachDistance();
        int k1 = i - func_27525_GetBlockReachDistance();
        int l1 = j - func_27525_GetBlockReachDistance();
        int i2 = k - func_27525_GetBlockReachDistance();
        int j2 = func_27525_GetBlockReachDistance() * func_27525_GetBlockReachDistance();
        for(int k2 = k1; k2 < l; k2++)
        {
            for(int l2 = l1; l2 < i1; l2++)
            {
                for(int i3 = i2; i3 < j1; i3++)
                {
                    if(func_27524_getDistance(i, j, k, k2, l2, i3) > j2)
                    {
                        continue;
                    }
                    int j3 = field_27528_x.theWorld.getBlockId(k2, l2, i3);
                    Block block = j3 > 0 ? Block.blocksList[j3] : null;
                    if(block == null || block != Block.torchWood && block != Block.torchRedstoneIdle && block != Block.torchRedstoneActive && block != Block.redstoneWire && block != Block.redstoneRepeaterIdle && block != Block.redstoneRepeaterActive && block != Block.plantYellow && block != Block.plantRed)
                    {
                        continue;
                    }
                    field_27528_x.getSendQueue().addToSendQueue(new Packet14BlockDig(0, k2, l2, i3, 1));
                    System.out.println("WTF");
                    try
                    {
                        Thread.sleep(35L);
                    }
                    catch(InterruptedException interruptedexception)
                    {
                        throw new RuntimeException(interruptedexception);
                    }
                }

            }

        }

    }

    public int func_27524_getDistance(int i, int j, int k, int l, int i1, int j1)
    {
        int k1 = l - i;
        int l1 = i1 - j;
        int i2 = j1 - k;
        return k1 * k1 + l1 * l1 + i2 * i2;
    }

    public static int func_27525_GetBlockReachDistance()
    {
        return 5;
    }

    public boolean field_27531_f;
    private boolean field_27530_smash;
    private int field_27529_lpX;
    private int field_27533_lpY;
    private int field_27532_lpZ;
    private Minecraft field_27528_x;
}
