package claycorp.soggycarpet.entity.shroom;

import claycorp.soggycarpet.utils.Properties;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityShroom extends EntitySlime{

	public EntityShroom(World par1World) {
		super(par1World);
		this.setSize(1.1F, 1.5F);
        this.experienceValue = Properties.shroomxp;
	}
    @Override
    public boolean isAIEnabled() {
        return true;
    }
    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(Properties.bearhealth); // maxHealth
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(Properties.bearfollowrange); // followRange
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(Properties.bearknockbackresistance); // knockbackResistance
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(Properties.bearmovespeed); // move speed
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(Properties.beardamage); // attackDamage
    }
    @Override
    protected void dropFewItems(final boolean playerHit, final int lootingLvl) {
    		if (playerHit == true);
    			this.dropItem(Properties.slimeplayerkillitemdrop, Properties.slimequantityofdropplayerkill);
    		if (playerHit == false);
    			this.dropItem(Properties.bearkillitemdrop, Properties.bearquantityofdropkill);
    }
}
