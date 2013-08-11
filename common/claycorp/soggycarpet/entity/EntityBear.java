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
        System.out.println("CREATING A BEAR!!!!");
        setSize(1.6F, 1.8F);
        experienceValue = Properties.xp;
        getNavigator().setBreakDoors(true);
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAILeapAtTarget(this, 1.0F));
        tasks.addTask(2,
                      new EntityAIAttackOnCollide(this, EntityPlayer.class, Properties.playerdamage, false));
        tasks.addTask(3, new EntityAIAttackOnCollide(this,
                                                     EntityVillager.class,
                                                     Properties.villagerdamage,
                                                     true));
        tasks.addTask(4, new EntityAIAttackOnCollide(this, IAnimals.class, Properties.passivedamage, true));
        tasks.addTask(4, new EntityAIAttackOnCollide(this, IMob.class, Properties.hostiledamage, true));
        tasks.addTask(5, new EntityAIWander(this, 5.0D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 50.0F));
        tasks.addTask(6, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, IAnimals.class, 0, false));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, IMob.class, 0, false));
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
        System.out.println("DEBUG!!!!!! ibetryintospawn");
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
                                                                                                      // range
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(Properties.bearmovespeed); // Movespeed
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected int getDropItemId()
    {
        return Properties.beardrop;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a
     * player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(final boolean par1, final int par2)
    {
        final int amount = rand.nextInt(3) + 1 + rand.nextInt(1 + par2);

        for (int iter = 0; iter < amount; ++iter)
        {
            dropItem(Properties.beardrop2, Properties.dropquantity);
        }

        /**
         * amount = this.rand.nextInt(5) + 2 + this.rand.nextInt(1 + par2 * 2);
         * for (int iter = 0; iter < amount; ++iter)
         * {
         * this.entityDropItem(new ItemStack(Properties.beardrop2, 1, 6), 0f);
         * }
         **/
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
