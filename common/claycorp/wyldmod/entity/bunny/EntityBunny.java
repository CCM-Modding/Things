package claycorp.wyldmod.entity.bunny;

import claycorp.wyldmod.utils.Properties;

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

public class EntityBunny extends EntityPigZombie
{
    /**
     * private int randomSoundDelay; private Entity field_110191_bu; private static final UUID field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718"); private
     * static final AttributeModifier field_110190_br = (new AttributeModifier(field_110189_bq, "Attacking speed boost", 0.45D, 0)).func_111168_a(false);
     **/
    public EntityBunny(final World par1World)
    {
        super(par1World);
        setSize(0.3F, 0.3F);
        getNavigator().setBreakDoors(Properties.bunnydoor);
        experienceValue = Properties.bunnyxp;
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        tasks.addTask(3, new EntityAIWander(this, 0.5D));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        tasks.addTask(4, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void attackEntity(final Entity par1Entity, final float par2)
    {
        if ((attackTime <= 0) && (par2 < 2.0F) && (par1Entity.boundingBox.maxY > boundingBox.minY) && (par1Entity.boundingBox.minY < boundingBox.maxY))
        {
            attackTime = Properties.bunnyattackspeed;
            attackEntityAsMob(par1Entity);
        }
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(Properties.bunnyhealth); // maxHealth
        getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(Properties.bunnyfollowrange); // followRange
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(Properties.bunnyknockbackresistance); // knockbackResistance
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(Properties.bunnymovespeed); // movementSpeed
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(Properties.bunnydamage); // attackDamage

    }

    @Override
    protected void dropFewItems(final boolean playerHit, final int lootingLvl)
    {
        if (playerHit == true)
        {
            dropItem(Properties.rabbitplayerkillitemdrop, Properties.rabbitquantityofdropplayerkill);
        }
        if (playerHit == false)
        {
            dropItem(Properties.rabbitkillitemdrop, Properties.rabbitquantityofdropkill);
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
        return (worldObj.difficultySetting > 0) && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()
                && !worldObj.isAnyLiquid(boundingBox);
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

    @Override
    protected float getSoundPitch()
    {
        return 5.0F;

    }
}
