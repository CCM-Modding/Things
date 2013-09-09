package claycorp.wyldmod.entity.wizard;

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

public class EntityWizard extends EntityMob
{

    public EntityWizard(final World par1World)
    {
        super(par1World);
        setSize(1.1F, 1.5F);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIBreakDoor(this));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, false));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        tasks.addTask(5, new EntityAIWander(this, 1.0D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, false));
        experienceValue = Properties.wizardxp;
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    @Override
    protected void attackEntity(final Entity wizard, final float par2)
    {

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

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(Properties.wizardhealth); // maxHealth
        getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(Properties.wizardfollowrange); // followRange
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(Properties.wizardknockbackresistance); // knockbackResistance
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(Properties.wizardmovespeed); // move speed
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(Properties.wizarddamage); // attackDamage
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(final boolean playerHit, final int lootingLvl)
    {
        if (playerHit == true)
        {
            dropItem(Properties.wizardplayerkillitemdrop, Properties.wizardquantityofdropplayerkill);
        }
        if (playerHit == false)
        {
            dropItem(Properties.wizardkillitemdrop, Properties.wizardquantityofdropkill);
        }
    }

    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return Properties.wizardtotal;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return (worldObj.difficultySetting > 0) && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()
                && !worldObj.isAnyLiquid(boundingBox);
    }

}
