package claycorp.soggycarpet.blocks;

import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import claycorp.soggycarpet.utils.Archive;

public class CarpetConvayorY extends BlockCarpet {

    protected CarpetConvayorY(final int par1) {
        super(par1);
        this.setCreativeTab(CreativeTabs.tabTransport);
        this.setUnlocalizedName(Archive.MOD_ID_BLOCK + "convayory");
        this.setHardness(5);
        this.setResistance(1000);
        this.setStepSound(soundMetalFootstep);
    }

    @Override
    public void onEntityCollidedWithBlock(final World par1World, final int par2, final int par3, final int par4, final Entity moveEntity) {
        moveEntity.motionY = 1D;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world, final int x, final int y, final int z) {
        return AxisAlignedBB.getAABBPool().getAABB(x + this.minX, y + this.minY, z + this.minZ, x - 1F, y - 0.25F, z - 1F);
    }
}