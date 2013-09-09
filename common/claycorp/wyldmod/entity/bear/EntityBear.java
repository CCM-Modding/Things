package claycorp.wyldmod.entity.bear;

import claycorp.wyldmod.utils.Properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityBear extends EntityMob
{

    public EntityBear(final World world)
    {
        super(world);
        setSize(1.1F, 1.5F);
        getNavigator().setBreakDoors(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIBreakDoor(this));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, false));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityAnimal.class, 1.0D, false));
        tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        tasks.addTask(6, new EntityAIWander(this, 1.0D));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, false));
        experienceValue = Properties.bearxp;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (!worldObj.isRemote && (worldObj.difficultySetting == 0))
        {
            setDead();
        }
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    @Override
    protected void attackEntity(final Entity par1Entity, final float par2)
    {
        if ((attackTime <= 0) && (par2 < 2.0F) && (par1Entity.boundingBox.maxY > boundingBox.minY) && (par1Entity.boundingBox.minY < boundingBox.maxY))
        {
            attackTime = Properties.bearattackspeed;
            attackEntityAsMob(par1Entity);
        }
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return (worldObj.difficultySetting > 0) && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()
                && !worldObj.isAnyLiquid(boundingBox);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(Properties.bearhealth); // maxHealth
        getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(Properties.bearfollowrange); // followRange
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(Properties.bearknockbackresistance); // knockbackResistance
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(Properties.bearmovespeed); // move speed
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(Properties.beardamage); // attackDamage
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(final boolean playerHit, final int lootingLvl)
    {
        if (playerHit == true)
        {
            dropItem(Properties.bearplayerkillitemdrop, Properties.bearquantityofdropplayerkill);
        }
        if (playerHit == false)
        {
            dropItem(Properties.bearkillitemdrop, Properties.bearquantityofdropkill);
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return "mob.endermen.idle";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return "mob.ghast.scream";
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
    public int getMaxSpawnedInChunk()
    {
        return Properties.beartotal;
    }

    @Override
    protected float getSoundPitch()
    {
        return -10.0F;

    }

}
