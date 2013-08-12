package claycorp.soggycarpet.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import claycorp.soggycarpet.utils.Properties;

public class EntityBear extends EntityMob {

    public EntityBear(final World world) {
        super(world);
        this.setSize(1.1F, 1.5F);
        this.getNavigator().setBreakDoors(true);
        this.experienceValue = Properties.xp;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, Properties.bearmovespeed, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWander(this, 5.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.worldObj.isRemote && (this.worldObj.difficultySetting == 0)) {
            this.setDead();
        }
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden
     * by each mob to define their attack.
     */
    @Override
    protected void attackEntity(final Entity par1Entity, final float par2) {
        if ((this.attackTime <= 0) && (par2 < 2.0F) && (par1Entity.boundingBox.maxY > this.boundingBox.minY) && (par1Entity.boundingBox.minY < this.boundingBox.maxY)) {
            this.attackTime = Properties.bearattackspeed;
            this.attackEntityAsMob(par1Entity);
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        return (this.worldObj.difficultySetting > 0) && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
//        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(Properties.bearhealth); // Health
//        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(Properties.beartracking); // Detection
//        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(Properties.bearmovespeed); // range
//        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(Properties.bearmovespeed); // Movespeed
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
    }

    @Override
    protected void dropFewItems(final boolean par1, final int par2) {
        final int amount = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

        for (int iter = 0; iter < amount; ++iter) {
            this.dropItem(Properties.beardrop, Properties.dropquantity);
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return "mob.ghast.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return "mob.ghast.say";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound() {
        return "mob.ghast.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void playStepSound(final int par1, final int par2, final int par3, final int par4) {
        this.playSound("mob.cow.step", 0.15F, 1.0F);
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return Properties.totalbear;
    }

}
