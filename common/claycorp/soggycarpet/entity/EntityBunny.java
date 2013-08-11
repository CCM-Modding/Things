package claycorp.soggycarpet.entity;

import claycorp.soggycarpet.utils.Properties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityBunny extends EntityPigZombie {

	public EntityBunny(World par1World) {
		super(par1World);
        this.setSize(0.5F, 0.5F);
        this.getNavigator().setBreakDoors(true);
        this.experienceValue = Properties.xp;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 5, true));
        this.tasks.addTask(3, new EntityAIWander(this, 0.5D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
    @Override
    protected void attackEntity(final Entity par1Entity, final float par2)
    {
        if ((attackTime <= 0) && (par2 < 2.0F)
            && (par1Entity.boundingBox.maxY > boundingBox.minY)
            && (par1Entity.boundingBox.minY < boundingBox.maxY))
        {
            attackTime = 10;
            attackEntityAsMob(par1Entity);
        }
    }
    @Override
    protected void func_110147_ax()
    {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10); // Health
        func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50); // Detection
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(20); // range
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5); // Movespeed
    }
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0)
        {
            this.setDead();
        }	
    }
    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return (worldObj.difficultySetting > 0) && worldObj.checkNoEntityCollision(boundingBox)
               && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()
               && !worldObj.isAnyLiquid(boundingBox);
    }
}
