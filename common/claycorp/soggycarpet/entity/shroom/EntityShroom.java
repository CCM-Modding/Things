package claycorp.soggycarpet.entity.shroom;

import claycorp.soggycarpet.utils.Properties;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;

public class EntityShroom extends EntitySlime{
    public boolean angry = false;

	public EntityShroom(World par1World) {
		super(par1World);
		this.setSize(1.1F, 1.5F);
        this.experienceValue = Properties.shroomxp;
	}
    @Override
    public boolean isAIEnabled() {
        if (this.angry) {
            // Angry mushrooms use the default slime AI, not the fancy
            // new system.
            return false;
        } else {
            // Non-angry mushrooms are downright devious.
            return true;
        }
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

    /**
     * Gets the amount of damage dealt to the player when "attacked" by the slime.
     */
    protected int getAttackStrength()
    {
        return this.getSlimeSize() * Properties.shroomdamage;
    }

    /**
     * Will get destroyed next tick.
     */
    public void setDead()
    {
        int i = this.getSlimeSize();

        if (!this.worldObj.isRemote && i > 1 && this.func_110143_aJ() <= 0.0F)
        {
            int j = 2 + this.rand.nextInt(3);

            for (int k = 0; k < j; ++k)
            {
                float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
                float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
                EntityShroom babyshroom = new EntityShroom(this.worldObj);
                babyshroom.setSlimeSize(i / 2);
                babyshroom.angry = true;
                babyshroom.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(babyshroom);
            }
        }

        // Sadly, we have to go up an extra level to avoid spawning slimes, and
        // Java doesn't allow that.
        //super.setDead();

        // Happily, what's actually done at the next level up (all the way at
        // Entity) is really simple:
        this.isDead = true;
    }
}
