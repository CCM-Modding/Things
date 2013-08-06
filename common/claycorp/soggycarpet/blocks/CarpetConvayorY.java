package claycorp.soggycarpet.blocks;

import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import claycorp.soggycarpet.utils.Archive;

public class CarpetConvayorY extends BlockCarpet {

	protected CarpetConvayorY(int par1) {
		super(par1);
        setCreativeTab(CreativeTabs.tabTransport);
        setUnlocalizedName(Archive.MOD_ID_BLOCK + "convayory");
        setHardness(5);
        setResistance(1000);
        setStepSound(soundMetalFootstep);
	}
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity moveEntity)
    {
        moveEntity.motionY = 1D;
    }
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
                                                         final int x,
                                                         final int y,
                                                         final int z)
    {
        return AxisAlignedBB.getAABBPool().getAABB(x + minX, y + minY, z + minZ, x - 1F, y - 0.25F, z - 1F);
    }
}