package claycorp.soggycarpet.entity.shroom;

import claycorp.soggycarpet.utils.Properties;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeInstance;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.UUID;

public class EntityShroom extends EntityMob {
    public static NonMushroomEntitySelector nonMushrooms = new NonMushroomEntitySelector();
    private static final UUID angrySpeedBoostUUID = UUID.fromString("48356db2-99df-4c3b-b6b9-0be3b8b1f99b");
    private static final AttributeModifier angrySpeedBoost = new AttributeModifier(angrySpeedBoostUUID, "Angry speed boost", 1.0D, 1);

    public EntityShroom(World par1World) {
        this(par1World, false);
    }

    public EntityShroom(World par1World, boolean spawnAngry) {
        super(par1World);

        setSlimelikeSize(1 << rand.nextInt(3));
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
                EntityShroom babyshroom = new EntityShroom(this.worldObj, angry);
                babyshroom.setSlimelikeSize(i / 2);
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
        boolean successful = super.attackEntityAsMob(target);
        boolean dead = false;

        if (target instanceof EntityLiving) {
            dead = ((EntityLiving)target).func_110143_aJ() <= 0.0F;
        }

        if (successful && dead && this.rand.nextInt(2) > 0) {
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

    // Per MCPBot, EntityLivingBase.isOnSameTeam
    public boolean func_142014_c(EntityLivingBase otherEntity) {
        return otherEntity instanceof EntityShroom;
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
