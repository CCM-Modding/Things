package claycorp.soggycarpet.entity.shroom;

import claycorp.soggycarpet.utils.Properties;
import net.minecraft.command.IEntitySelector;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeInstance;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.UUID;

public class EntityShroom extends EntityMob {
    public static final double RADIUS = 2;
    public static NonMushroomEntitySelector nonMushrooms = new NonMushroomEntitySelector();
    private static final UUID angrySpeedBoostUUID = UUID.fromString("48356db2-99df-4c3b-b6b9-0be3b8b1f99b");
    private static final AttributeModifier angrySpeedBoost = new AttributeModifier(angrySpeedBoostUUID, "Angry speed boost", 1.0D, 1);

    public EntityShroom(World par1World) {
        this(par1World, -1, false);
    }

    public EntityShroom(World par1World, int size, boolean spawnAngry) {
        super(par1World);

        this.getNavigator().setAvoidsWater(false);
        this.getNavigator().setCanSwim(false);

        if (size <= 0) {
            int size_roll = rand.nextInt(7);
            if (size_roll < 1) {
                // 1 in 7
                size = 4;
            } else if (size_roll < 3) {
                // 2 in 7
                size = 2;
            } else {
                // 4 in 7
                size = 1;
            }
        }

        setSlimelikeSize(size);
        setAngry(spawnAngry);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)1));
        this.dataWatcher.addObject(17, new Byte((byte)1));
    }

    public void setAngry(boolean angry) {
        if (angry) {
            this.dataWatcher.updateObject(17, new Byte((byte)1));
        } else {
            this.dataWatcher.updateObject(17, new Byte((byte)0));
        }

        AttributeInstance movementSpeed = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
        movementSpeed.func_111124_b(angrySpeedBoost);
        if (angry) {
            movementSpeed.func_111121_a(angrySpeedBoost);
        }



        this.tasks.taskEntries.clear();
        if (angry) {
            this.tasks.addTask(0, new SpreadTarget(this));
            this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
            this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLiving.class, 1.0D, true));
            this.tasks.addTask(2, new EntityAILookIdle(this));
            this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
            this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this,
                                                EntityPlayer.class,
                                                0, false));
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
                                                EntityLiving.class,
                                                0,
                                                true, false, nonMushrooms));
        } else {
            this.tasks.addTask(0, new FeignInnocence(this));
            this.tasks.addTask(0, new SpreadTarget(this));
            this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
            this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLiving.class, 1.0D, true));
            EntityAILookIdle forcedIdle = new EntityAILookIdle(this);
            forcedIdle.setMutexBits(1);
            this.tasks.addTask(3, forcedIdle);
            this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 5.0F));
            this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
            this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this,
                                                EntityPlayer.class,
                                                Properties.shroom_deviousness,
                                                true));
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
                                                EntityLiving.class,
                                                0,
                                                true, false, nonMushrooms));
        }
    }

    public boolean isAngry() {
        return this.dataWatcher.getWatchableObjectByte(17) != 0;
    }

    public void setSlimelikeSize(int size) {
        this.dataWatcher.updateObject(16, new Byte((byte)size));

        this.setSize(0.3F * (float)size, 0.3F * (float)size);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)(size * size));
        this.setEntityHealth(this.func_110138_aP());
        this.experienceValue = Properties.shroomxp;

        int damage = (Properties.shroomdamage * size) / 2;
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(damage); // attackDamage
    }

    public int getSlimelikeSize() {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Size", this.getSlimelikeSize() - 1);
        par1NBTTagCompound.setBoolean("Angry", this.isAngry());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setSlimelikeSize(par1NBTTagCompound.getInteger("Size") + 1);
        this.setAngry(par1NBTTagCompound.getBoolean("Angry"));
    }


    @Override
    public boolean isAIEnabled() {
        return true;
    }
    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(Properties.shroomhealth); // maxHealth
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(Properties.shroomfollowrange); // followRange
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(Properties.shroomknockbackresistance); // knockbackResistance
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(Properties.shroommovespeed); // move speed
    }
    @Override
    protected void dropFewItems(final boolean playerHit, final int lootingLvl) {
    		if (playerHit == true)
    			this.dropItem(Properties.shroomplayerkillitemdrop, Properties.shroomquantityofdropplayerkill);
    		if (playerHit == false)
    			this.dropItem(Properties.shroomkillitemdrop, Properties.shroomquantityofdropkill);
    }

    public void spawnKids(int bonus, boolean angry)
    {
        int i = this.getSlimelikeSize();

        if (!this.worldObj.isRemote && i > 1)
        {
            int j = bonus + this.rand.nextInt(3);

            for (int k = 0; k < j; ++k)
            {
                float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
                float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
                EntityShroom babyshroom = new EntityShroom(this.worldObj, i / 2, angry);
                babyshroom.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(babyshroom);
            }
        }
    }

    /**
     * Will get destroyed next tick.
     */
    public void setDead()
    {
        if (this.func_110143_aJ() <= 0.0F) {
            spawnKids(1, true);
        }

        super.setDead();
    }

    public boolean attackEntityAsMob(Entity target)
    {
        // Store the entity's motion info.
        boolean wasAirBorne = target.isAirBorne;
        double oldMotionX   = target.motionX;
        double oldMotionY   = target.motionY;
        double oldMotionZ   = target.motionZ;

        // Do the actual attack
        boolean successful = super.attackEntityAsMob(target);

        // Restore the saved motion info, undoing knockback.
        target.isAirBorne = wasAirBorne;
        target.motionX    = oldMotionX;
        target.motionY    = oldMotionY;
        target.motionZ    = oldMotionZ;

        boolean dead = false;

        if (target instanceof EntityLivingBase) {
            dead = ((EntityLivingBase)target).func_110143_aJ() <= 0.0F;
        }

        if (successful && dead && (target instanceof EntityPlayer || this.rand.nextInt(2) > 0)) {
            int size = this.getSlimelikeSize();

            if (size == 1) {
                this.setSlimelikeSize(size * 2);
            } else if (size == 2 && this.rand.nextInt(3) > 0) {
                this.setSlimelikeSize(size * 2);
            } else {
                spawnKids(2, false);
                this.isDead = true;
            }
        }

        return successful;
    }

    // EntityLivingBase.isOnSameTeam
    public boolean func_142014_c(EntityLivingBase otherEntity) {
        return otherEntity instanceof EntityShroom;
    }

    // Shrooms spawn in a circle around brown and red mushrooms.
    public boolean getCanSpawnHere() {
        int x = MathHelper.floor_double(this.posX);
        int y = MathHelper.floor_double(this.posY);
        int z = MathHelper.floor_double(this.posZ);

        boolean foundMushroom = false;
        for (int dir=0; dir<4; dir++) {
            int xOffset = 0;
            int zOffset = 0;

            switch (dir) {
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

            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                int id = this.worldObj.getBlockId(x+xOffset, y+yOffset, z+zOffset);
                if (id == Block.mushroomBrown.blockID
                 || id == Block.mushroomRed.blockID
                 || id == Block.mushroomCapBrown.blockID
                 || id == Block.mushroomCapRed.blockID) {
                    foundMushroom = true;
                    this.setLocationAndAngles(x + xOffset + 0.5D,
                                              y + yOffset,
                                              z + zOffset + 0.5D,
                                              this.rand.nextFloat() * 360.0F, 0.0F);
                    break;
                }
            }
        }

        return foundMushroom;
    }

    public boolean basicSpawnCheck() {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);

        return this.worldObj.isBlockNormalCube(i, j-1, k)
            && this.worldObj.checkNoEntityCollision(this.boundingBox, this)
            && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()
            // We'll allow the Shrooms to spawn in water.
            //&& !this.worldObj.isAnyLiquid(this.boundingBox);
            // ... but not lava.
            && !this.worldObj.isBoundingBoxBurning(this.boundingBox);
    }

    public boolean placeNear(double x, double y, double z) {
        for (int yOffset = 1; yOffset >= -1; yOffset--) {
            this.setLocationAndAngles(x, y + yOffset, z, this.rand.nextFloat() * 360.0F, 0.0F);

            if (this.basicSpawnCheck()) {
                return true;
            }
        }

        return false;
    }

    // Shared spawning routine
    // The entity already exists in world, but this gives us a chance to
    // modify it right as it spawns.
    // In this case, we turn a single entity into a ring of 5 Shrooms.
    public EntityLivingData func_110161_a(EntityLivingData data) {
        double originX = this.posX;
        double originY = this.posY;
        double originZ = this.posZ;

        boolean spawnOK = false;
        double baseAngle = this.rand.nextFloat() * 2 * Math.PI;
        double angle;
        for (angle = baseAngle; angle < baseAngle + 2 * Math.PI; angle += Math.PI / 8) {
            if (this.placeNear(originX + RADIUS * Math.sin(angle),
                               originY,
                               originZ + RADIUS * Math.cos(angle))) {
                spawnOK = true;
                break;
            }
        }

        //System.out.println("spawnOK " + spawnOK + " at (" + originX + ", " + originY + ", " + originZ + ")");

        baseAngle = angle;

        if (spawnOK) {
            // Because this entity has spawned and moved in the same tick,
            // the client won't display it in the correct place.
            // To fix this, we remove this entity entirely and let the
            // circle-placement loop recreate it.
            // Yes, this is a dirty hack.
            this.worldObj.removeEntity(this);
        } else {
            // Can't find anywhere to place a mushroom on the circle.
            // Bail out, but leave this one behind, since here is OK.
            // (This avoids the above-mentioned bug, because it's
            //  back where it started.)
            this.setLocationAndAngles(originX, originY, originZ, this.rand.nextFloat() * 360.0F, 0.0F);
            return data;
        }

        EntityShroom othershroom = null;
        for (int i=0; i<5; i++) {
            if (othershroom == null) {
                othershroom = new EntityShroom(this.worldObj);
            }

            angle = baseAngle + i * (2 * Math.PI) / 5;
            if (othershroom.placeNear(originX + RADIUS * Math.sin(angle),
                                      originY,
                                      originZ + RADIUS * Math.cos(angle))) {
                this.worldObj.spawnEntityInWorld(othershroom);
                othershroom = null;
            }
        }

        return data;
    }

    public boolean canBreatheUnderwater() {
        // No need to breathe at all.
        return true;
    }

    public static class NonMushroomEntitySelector implements IEntitySelector {
        /**
         * Return whether the specified entity is applicable to this filter.
         */
        public boolean isEntityApplicable(Entity entity) {
            return !(entity instanceof EntityShroom);
        }
    }
}
