package claycorp.soggycarpet.entity;

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
import claycorp.soggycarpet.utils.Properties;

public class EntityBunny extends EntityPigZombie {

    public EntityBunny(final World par1World) {
        super(par1World);
        this.setSize(0.3F, 0.3F);
        this.getNavigator().setBreakDoors(true);
        this.experienceValue = Properties.bearxp;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 5, true));
        this.tasks.addTask(3, new EntityAIWander(this, 0.5D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void attackEntity(final Entity par1Entity, final float par2) {
        if ((this.attackTime <= 0) && (par2 < 2.0F) && (par1Entity.boundingBox.maxY > this.boundingBox.minY) && (par1Entity.boundingBox.minY < this.boundingBox.maxY)) {
            this.attackTime = 1;
            this.attackEntityAsMob(par1Entity);
        }
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1); // maxHealth
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50); // followRange
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0); // knockbackResistance
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.001); // movementSpeed
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.001); // attackDamage
        
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.worldObj.isRemote && (this.worldObj.difficultySetting == 0)) {
            this.setDead();
        }
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return (this.worldObj.difficultySetting > 0) && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }
    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return "mob.cat.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return "mob.cat.hitt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return "mob.villager.death";
    }
    
    
    protected float getSoundPitch()
    {
		return 5.0F;
    	
    }
}
