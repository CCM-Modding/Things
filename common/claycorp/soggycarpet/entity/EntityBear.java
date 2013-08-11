package claycorp.soggycarpet.entity;

import claycorp.soggycarpet.utils.Properties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityBear extends EntityMob
{

    public EntityBear(final World world)
    {
        super(world);
        this.setSize(1.1F, 1.5F);
        this.getNavigator().setBreakDoors(true);
        this.experienceValue = Properties.xp;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, Properties.playerdamage, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, Properties.villagerdamage,true));
        //tasks.addTask(4, new EntityAIAttackOnCollide(this, IAnimals.class, Properties.passivedamage,true));
         //tasks.addTask(5, new EntityAIAttackOnCollide(this, IMob.class, Properties.hostiledamage, true));
        this.tasks.addTask(6, new EntityAIWander(this, 5.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
         //targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, IAnimals.class, 0, false));
         //targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, IMob.class, 0, false));
    }
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0)
        {
            this.setDead();
        }	
    }
    
    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their
     * attack.
     */
    @Override
    protected void attackEntity(final Entity par1Entity, final float par2)
    {
        if ((attackTime <= 0) && (par2 < 2.0F)
            && (par1Entity.boundingBox.maxY > boundingBox.minY)
            && (par1Entity.boundingBox.minY < boundingBox.maxY))
        {
            attackTime = Properties.bearattackspeed;
            attackEntityAsMob(par1Entity);
        }
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return (worldObj.difficultySetting > 0) && worldObj.checkNoEntityCollision(boundingBox)
               && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()
               && !worldObj.isAnyLiquid(boundingBox);
    }

    @Override
    protected void func_110147_ax()
    {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(Properties.bearhealth); // Health
        func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(Properties.beartracking); // Detection
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(Properties.bearmovespeed); // range
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(Properties.bearmovespeed); // Movespeed
    }

    @Override
    protected void dropFewItems(final boolean par1, final int par2)
    {
        final int amount = rand.nextInt(3) + 1 + rand.nextInt(1 + par2);

        for (int iter = 0; iter < amount; ++iter)
        {
            dropItem(Properties.beardrop, Properties.dropquantity);
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return "mob.ghast.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return "mob.ghast.say";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return "mob.ghast.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void playStepSound(final int par1, final int par2, final int par3, final int par4)
    {
        playSound("mob.cow.step", 0.15F, 1.0F);
    }

    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected void updateAITasks()
    {
        super.updateAITasks();
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return Properties.totalbear;
    }

}
