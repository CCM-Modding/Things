package claycorp.wyldmod.entity.shroom;

import java.util.UUID;

import claycorp.wyldmod.utils.Properties;

import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeInstance;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityShroom extends EntityMob
{
    public static final double RADIUS = 2;
    public static NonMushroomEntitySelector nonMushrooms = new NonMushroomEntitySelector();
    private static final UUID angrySpeedBoostUUID = UUID.fromString("48356db2-99df-4c3b-b6b9-0be3b8b1f99b");
    private static final AttributeModifier angrySpeedBoost = new AttributeModifier(angrySpeedBoostUUID, "Angry speed boost", 1.0D, 1);

    public EntityShroom(final World par1World)
    {
        this(par1World, -1, false);
    }

    public EntityShroom(final World par1World, int size, final boolean spawnAngry)
    {
        super(par1World);

        getNavigator().setAvoidsWater(false);
        getNavigator().setCanSwim(false);

        if (size <= 0)
        {
            int size_roll = rand.nextInt(7);
            if (size_roll < 1)
            {
                // 1 in 7
                size = 4;
            } else if (size_roll < 3)
            {
                // 2 in 7
                size = 2;
            } else
            {
                // 4 in 7
                size = 1;
            }
        }

        setSlimelikeSize(size);
        setAngry(spawnAngry);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, new Byte((byte) 1));
        dataWatcher.addObject(17, new Byte((byte) 1));
    }

    public void setAngry(final boolean angry)
    {
        if (angry)
        {
            dataWatcher.updateObject(17, new Byte((byte) 1));
        } else
        {
            dataWatcher.updateObject(17, new Byte((byte) 0));
        }

        AttributeInstance movementSpeed = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        movementSpeed.removeModifier(angrySpeedBoost);
        if (angry)
        {
            movementSpeed.applyModifier(angrySpeedBoost);
        }

        tasks.taskEntries.clear();
        if (angry)
        {
            tasks.addTask(0, new SpreadTarget(this));
            tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
            tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLiving.class, 1.0D, true));
            tasks.addTask(2, new EntityAILookIdle(this));
            targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
            targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
            targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, nonMushrooms));
        } else
        {
            tasks.addTask(0, new FeignInnocence(this));
            tasks.addTask(0, new SpreadTarget(this));
            tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
            tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLiving.class, 1.0D, true));
            EntityAILookIdle forcedIdle = new EntityAILookIdle(this);
            forcedIdle.setMutexBits(1);
            tasks.addTask(3, forcedIdle);
            tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 5.0F));
            targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
            targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, Properties.shroom_deviousness, true));
            targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, nonMushrooms));
        }
    }

    public boolean isAngry()
    {
        return dataWatcher.getWatchableObjectByte(17) != 0;
    }

    public void setSlimelikeSize(final int size)
    {
        dataWatcher.updateObject(16, new Byte((byte) size));

        setSize(0.3F * size, 0.3F * size);
        setPosition(posX, posY, posZ);
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(size * size);
        this.setEntityHealth(getMaxHealth());
        experienceValue = Properties.shroomxp;

        int damage = (Properties.shroomdamage * size) / 2;
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(damage); // attackDamage
    }

    public int getSlimelikeSize()
    {
        return dataWatcher.getWatchableObjectByte(16);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Size", getSlimelikeSize() - 1);
        par1NBTTagCompound.setBoolean("Angry", isAngry());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        setSlimelikeSize(par1NBTTagCompound.getInteger("Size") + 1);
        setAngry(par1NBTTagCompound.getBoolean("Angry"));
    }

    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(Properties.shroomhealth); // maxHealth
        getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(Properties.shroomfollowrange); // followRange
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(Properties.shroomknockbackresistance); // knockbackResistance
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(Properties.shroommovespeed); // move speed
    }

    @Override
    protected void dropFewItems(final boolean playerHit, final int lootingLvl)
    {
        if (playerHit == true)
        {
            dropItem(Properties.shroomplayerkillitemdrop, Properties.shroomquantityofdropplayerkill);
        }
        if (playerHit == false)
        {
            dropItem(Properties.shroomkillitemdrop, Properties.shroomquantityofdropkill);
        }
    }

    public void spawnKids(final int bonus, final boolean angry)
    {
        int i = getSlimelikeSize();

        if (!worldObj.isRemote && (i > 1))
        {
            int j = bonus + rand.nextInt(3);

            for (int k = 0; k < j; ++k)
            {
                float f = (((k % 2) - 0.5F) * i) / 4.0F;
                float f1 = (((k / 2) - 0.5F) * i) / 4.0F;
                EntityShroom babyshroom = new EntityShroom(worldObj, i / 2, angry);
                babyshroom.setLocationAndAngles(posX + f, posY + 0.5D, posZ + f1, rand.nextFloat() * 360.0F, 0.0F);
                worldObj.spawnEntityInWorld(babyshroom);
            }
        }
    }

    /**
     * Will get destroyed next tick.
     */
    @Override
    public void setDead()
    {
        if (getHealth() <= 0.0F)
        {
            spawnKids(1, true);
        }

        super.setDead();
    }

    @Override
    public boolean attackEntityAsMob(final Entity target)
    {
        // Store the entity's motion info.
        boolean wasAirBorne = target.isAirBorne;
        double oldMotionX = target.motionX;
        double oldMotionY = target.motionY;
        double oldMotionZ = target.motionZ;

        // Do the actual attack
        boolean successful = super.attackEntityAsMob(target);

        // Restore the saved motion info, undoing knockback.
        target.isAirBorne = wasAirBorne;
        target.motionX = oldMotionX;
        target.motionY = oldMotionY;
        target.motionZ = oldMotionZ;

        boolean dead = false;

        if (target instanceof EntityLivingBase)
        {
            dead = ((EntityLivingBase) target).getHealth() <= 0.0F;
        }

        if (successful && dead && ((target instanceof EntityPlayer) || (rand.nextInt(2) > 0)))
        {
            int size = getSlimelikeSize();

            if (size == 1)
            {
                setSlimelikeSize(size * 2);
            } else if ((size == 2) && (rand.nextInt(3) > 0))
            {
                setSlimelikeSize(size * 2);
            } else
            {
                spawnKids(2, false);
                isDead = true;
            }
        }

        return successful;
    }

    // EntityLivingBase.isOnSameTeam
    public boolean func_142014_c(final EntityLivingBase otherEntity)
    {
        return otherEntity instanceof EntityShroom;
    }

    // Shrooms spawn in a circle around brown and red mushrooms.
    @Override
    public boolean getCanSpawnHere()
    {
        int x = MathHelper.floor_double(posX);
        int y = MathHelper.floor_double(posY);
        int z = MathHelper.floor_double(posZ);

        boolean foundMushroom = false;
        for (int dir = 0; dir < 4; dir++)
        {
            int xOffset = 0;
            int zOffset = 0;

            switch (dir)
            {
                case 0:
                    xOffset = -1;
                    break;
                case 1:
                    xOffset = 1;
                    break;
                case 2:
                    zOffset = -1;
                    break;
                case 3:
                    zOffset = 1;
                    break;
            }

            for (int yOffset = -1; yOffset <= 1; yOffset++)
            {
                int id = worldObj.getBlockId(x + xOffset, y + yOffset, z + zOffset);
                if ((id == Block.mushroomBrown.blockID) || (id == Block.mushroomRed.blockID) || (id == Block.mushroomCapBrown.blockID) || (id == Block.mushroomCapRed.blockID))
                {
                    foundMushroom = true;
                    setLocationAndAngles(x + xOffset + 0.5D, y + yOffset, z + zOffset + 0.5D, rand.nextFloat() * 360.0F, 0.0F);
                    break;
                }
            }
        }

        return foundMushroom;
    }

    public boolean basicSpawnCheck()
    {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(boundingBox.minY);
        int k = MathHelper.floor_double(posZ);

        return worldObj.isBlockNormalCube(i, j - 1, k) && worldObj.checkNoEntityCollision(boundingBox, this) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()
        // We'll allow the Shrooms to spawn in water.
        // && !this.worldObj.isAnyLiquid(this.boundingBox);
        // ... but not lava.
                && !worldObj.isBoundingBoxBurning(boundingBox);
    }

    public boolean placeNear(final double x, final double y, final double z)
    {
        for (int yOffset = 1; yOffset >= -1; yOffset--)
        {
            setLocationAndAngles(x, y + yOffset, z, rand.nextFloat() * 360.0F, 0.0F);

            if (basicSpawnCheck())
            {
                return true;
            }
        }

        return false;
    }

    // Shared spawning routine
    // The entity already exists in world, but this gives us a chance to
    // modify it right as it spawns.
    // In this case, we turn a single entity into a ring of 5 Shrooms.
    public EntityLivingData func_110161_a(final EntityLivingData data)
    {
        double originX = posX;
        double originY = posY;
        double originZ = posZ;

        boolean spawnOK = false;
        double baseAngle = rand.nextFloat() * 2 * Math.PI;
        double angle;
        for (angle = baseAngle; angle < (baseAngle + (2 * Math.PI)); angle += Math.PI / 8)
        {
            if (placeNear(originX + (RADIUS * Math.sin(angle)), originY, originZ + (RADIUS * Math.cos(angle))))
            {
                spawnOK = true;
                break;
            }
        }

        // System.out.println("spawnOK " + spawnOK + " at (" + originX + ", " + originY + ", " + originZ + ")");

        baseAngle = angle;

        if (spawnOK)
        {
            // Because this entity has spawned and moved in the same tick,
            // the client won't display it in the correct place.
            // To fix this, we remove this entity entirely and let the
            // circle-placement loop recreate it.
            // Yes, this is a dirty hack.
            worldObj.removeEntity(this);
        } else
        {
            // Can't find anywhere to place a mushroom on the circle.
            // Bail out, but leave this one behind, since here is OK.
            // (This avoids the above-mentioned bug, because it's
            // back where it started.)
            setLocationAndAngles(originX, originY, originZ, rand.nextFloat() * 360.0F, 0.0F);
            return data;
        }

        EntityShroom othershroom = null;
        for (int i = 0; i < 5; i++)
        {
            if (othershroom == null)
            {
                othershroom = new EntityShroom(worldObj);
            }

            angle = baseAngle + ((i * (2 * Math.PI)) / 5);
            if (othershroom.placeNear(originX + (RADIUS * Math.sin(angle)), originY, originZ + (RADIUS * Math.cos(angle))))
            {
                worldObj.spawnEntityInWorld(othershroom);
                othershroom = null;
            }
        }

        return data;
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        // No need to breathe at all.
        return true;
    }

    public static class NonMushroomEntitySelector implements IEntitySelector
    {
        /**
         * Return whether the specified entity is applicable to this filter.
         */
        @Override
        public boolean isEntityApplicable(final Entity entity)
        {
            return !(entity instanceof EntityShroom);
        }
    }
}
