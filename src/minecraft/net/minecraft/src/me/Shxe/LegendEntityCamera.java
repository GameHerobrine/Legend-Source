// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src.me.Shxe;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.World;

// Referenced classes of package net.minecraft.src:
//            EntityLiving, World, Entity

public class LegendEntityCamera extends EntityLiving
{

    public LegendEntityCamera(World world)
    {
        super(world);
    }

    public boolean canBePushed()
    {
        return false;
    }

    public void onEntityUpdate()
    {
    }

    public void onUpdate()
    {
    }

    public void onDeath(Entity entity)
    {
    }

    public boolean isEntityAlive()
    {
        return true;
    }

    public void setCamera(double d, double d1, double d2, float f, 
            float f1)
    {
        lastTickPosX = posX;
        lastTickPosY = posY;
        lastTickPosZ = posZ;
        posX += d;
        posY += d1;
        posZ += d2;
        prevRotationYaw = rotationYaw;
        prevRotationPitch = rotationPitch;
        rotationYaw = f;
        rotationPitch = f1;
    }
}
