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

public class EntityWizard extends EntityMob{

	public EntityWizard(World par1World) {
		super(par1World);
		this.setSize(1.1F, 1.5F);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIBreakDoor(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, false));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, false));
        this.experienceValue = Properties.wizardxp;
	}
    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden
     * by each mob to define their attack.
     */
    @Override
    protected void attackEntity(final Entity wizard, final float par2) {
    	
        }
    
    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.worldObj.isRemote && (this.worldObj.difficultySetting == 0)) {
            this.setDead();
        }
    }
    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(Properties.wizardhealth); // maxHealth
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(Properties.wizardfollowrange); // followRange
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(Properties.wizardknockbackresistance); // knockbackResistance
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(Properties.wizardmovespeed); // move speed
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(Properties.wizarddamage); // attackDamage
    }
    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(final boolean playerHit, final int lootingLvl) {
    	if (playerHit == true)
    		this.dropItem(Properties.wizardplayerkillitemdrop, Properties.wizardquantityofdropplayerkill);
    	if (playerHit == false)
    		this.dropItem(Properties.wizardkillitemdrop, Properties.wizardquantityofdropkill);
    }
    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return Properties.wizardtotal;
    }
    @Override
    public boolean getCanSpawnHere() {
        return (this.worldObj.difficultySetting > 0) && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }
    

}
