package claycorp.soggycarpet.entity.bunny;

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
import claycorp.soggycarpet.utils.Properties;

public class EntityBunny extends EntityPigZombie {
    /**private int randomSoundDelay;
    private Entity field_110191_bu;
    private static final UUID field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final AttributeModifier field_110190_br = (new AttributeModifier(field_110189_bq, "Attacking speed boost", 0.45D, 0)).func_111168_a(false);
    **/
    public EntityBunny(final World par1World) {
        super(par1World);
        this.setSize(0.3F, 0.3F);
        this.getNavigator().setBreakDoors(Properties.bunnydoor);
        this.experienceValue = Properties.bunnyxp;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        this.tasks.addTask(3, new EntityAIWander(this, 0.5D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void attackEntity(final Entity par1Entity, final float par2) {
        if ((this.attackTime <= 0) && (par2 < 2.0F) && (par1Entity.boundingBox.maxY > this.boundingBox.minY) && (par1Entity.boundingBox.minY < this.boundingBox.maxY)) {
            this.attackTime = Properties.bunnyattackspeed;
            this.attackEntityAsMob(par1Entity);
        }
    }

    
    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(Properties.bunnyhealth); // maxHealth
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(Properties.bunnyfollowrange); // followRange
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(Properties.bunnyknockbackresistance); // knockbackResistance
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(Properties.bunnymovespeed); // movementSpeed
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(Properties.bunnydamage); // attackDamage
        
    }
    @Override
    protected void dropFewItems(final boolean playerHit, final int lootingLvl) {
    	if (playerHit == true);
    		this.dropItem(Properties.rabbitplayerkillitemdrop, Properties.rabbitquantityofdropplayerkill);
    	if (playerHit == false);
    		this.dropItem(Properties.rabbitkillitemdrop, Properties.rabbitquantityofdropkill);
    }

    /**@Override
    public void onUpdate()
    {
        if (this.field_110191_bu != this.entityToAttack && !this.worldObj.isRemote)
        {
            AttributeInstance attributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
            attributeinstance.func_111124_b(field_110190_br);

            if (this.entityToAttack != null)
            {
                attributeinstance.func_111121_a(field_110190_br);
            }
        }

        this.field_110191_bu = this.entityToAttack;

        if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0)
        {
            this.playSound("mob.cat.say", this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.onUpdate();
    }**/

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return (this.worldObj.difficultySetting > 0) && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
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
    
    
    protected float getSoundPitch()
    {
		return 5.0F;
    	
    }
}
