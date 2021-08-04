// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.*;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            World, EntityPlayer, EntityPlayerSP

public class Locate
{

    public Locate()
    {
    }

    public static void players(Minecraft minecraft)
    {
        Iterator iterator = minecraft.theWorld.playerEntities.iterator();
        LocalIter = 0;
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            EntityPlayer entityplayer = (EntityPlayer)iterator.next();
            if(entityplayer != minecraft.thePlayer)
            {
                LocateDistance = (int)Math.sqrt((minecraft.thePlayer.posX - entityplayer.posX) * (minecraft.thePlayer.posX - entityplayer.posX) + (minecraft.thePlayer.posY - entityplayer.posY) * (minecraft.thePlayer.posY - entityplayer.posY) + (minecraft.thePlayer.posZ - entityplayer.posZ) * (minecraft.thePlayer.posZ - entityplayer.posZ));
                if(!player.contains(entityplayer))
                {
                    player.add(LocalIter, entityplayer);
                    LocalIter++;
                }
            }
        } while(true);
        playerclean();
        player.trimToSize();
    }

    public static void playerclean()
    {
        HashSet hashset = new HashSet();
        hashset.addAll(player);
        player.clear();
        player.addAll(hashset);
    }

    private static Minecraft mc;
    public static int LocateDistance;
    public static ArrayList player = new ArrayList();
    public static int LocalIter;

}
