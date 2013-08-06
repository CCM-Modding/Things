package claycorp.soggycarpet.blocks;

import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import claycorp.soggycarpet.utils.Archive;

public class CarpetConvayorNZ extends BlockCarpet {

	protected CarpetConvayorNZ(int par1) {
		super(par1);
        setCreativeTab(CreativeTabs.tabTransport);
        setUnlocalizedName(Archive.MOD_ID_BLOCK + "convayornz");
        setHardness(5);
        setResistance(1000);
        setStepSound(soundMetalFootstep);
	}
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity moveEntity)
    {
        moveEntity.motionZ = -1D;
    }
}