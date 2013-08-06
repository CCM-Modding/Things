package claycorp.soggycarpet.blocks;

import claycorp.soggycarpet.utils.Archive;
import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class CarpetConvayorX extends BlockCarpet {

	protected CarpetConvayorX(int par1) {
		super(par1);
        setCreativeTab(CreativeTabs.tabTransport);
        setUnlocalizedName(Archive.MOD_ID_BLOCK + "convayorx");
        setHardness(5);
        setResistance(1000);
        setStepSound(soundMetalFootstep);
	}
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity moveEntity)
    {
        moveEntity.motionX = 1D;
    }
}
