package org.jurassicraft.server.entity.dinosaur;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jurassicraft.server.entity.VenomEntity;
import org.jurassicraft.server.entity.base.DinosaurEntity;

public class DilophosaurusEntity extends DinosaurEntity implements IRangedAttackMob
{
    public DilophosaurusEntity(World world)
    {
        super(world);
        this.target(EntityPlayer.class, EntityAnimal.class, GallimimusEntity.class, ParasaurolophusEntity.class, TriceratopsEntity.class, VelociraptorEntity.class);
        this.tasks.addTask(2, super.getAttackAI());
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distance)
    {
        VenomEntity venom = new VenomEntity(this.worldObj, this);
        double deltaX = target.posX - venom.posX;
        double deltaY = target.posY + (double) target.getEyeHeight() - 1.100000023841858D - venom.posY;
        double deltaZ = target.posZ - venom.posZ;
        float yOffset = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ) * 0.2F;
        venom.setThrowableHeading(deltaX, deltaY + (double) yOffset, deltaZ, 1.5F, 0F);
        this.worldObj.spawnEntityInWorld(venom);
    }

    @Override
    public EntityAIBase getAttackAI()
    {
        return new EntityAIAttackRanged(this, 1.0, 40, 10);
    }
}