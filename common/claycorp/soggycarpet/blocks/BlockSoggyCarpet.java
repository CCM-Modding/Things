package claycorp.soggycarpet.blocks;

import claycorp.soggycarpet.utils.Archive;

import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockSoggyCarpet extends BlockCarpet
{

    public BlockSoggyCarpet(final int id)
    {
        super(id);
        setCreativeTab(CreativeTabs.tabDecorations);
        setUnlocalizedName(Archive.MOD_ID_BLOCK + "soggycarpet");
        setHardness(10);
        setResistance(1000);
        setStepSound(soundSnowFootstep);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool
     * has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
                                                         final int x,
                                                         final int y,
                                                         final int z)
    {
        final float f = 0.12F;
        return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, (y + 1) - f, z + 1);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z,
     * entity
     */
    @Override
    public void onEntityCollidedWithBlock(final World world,
                                          final int x,
                                          final int y,
                                          final int z,
                                          final Entity entity)
    {
        entity.motionX *= 0.9D;
        entity.motionZ *= 0.9D;
    }
}