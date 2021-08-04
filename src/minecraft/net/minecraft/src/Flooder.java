// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.me.Shxe.LegendGlobal;

// Referenced classes of package net.minecraft.src:
//            EntityClientPlayerMP, AliasGlobal, Packet3Chat, NetClientHandler

public class Flooder
    implements Runnable
{

    public Flooder(Minecraft minecraft)
    {
        field_27541_f = true;
        field_27540_flood = false;
        field_27548_x = minecraft;
    }

    public void run()
    {
        String s = EntityClientPlayerMP.floodmsg;
        while(field_27541_f) 
        {
            if(!LegendGlobal.spamChat)
            {
                field_27541_f = false;
            } else
            if(s == null)
            {
                LegendGlobal.spamChat = false;
            } else
            {
                field_27548_x.getSendQueue().addToSendQueue(new Packet3Chat(s));
            }
            try
            {
                Thread.sleep(350L);
            }
            catch(InterruptedException interruptedexception)
            {
                throw new RuntimeException(interruptedexception);
            }
        }
    }

    public void stop()
    {
        field_27541_f = false;
    }

    public boolean field_27541_f;
    private Minecraft field_27548_x;
    private boolean field_27540_flood;
}
