package claycorp.wyldmod.blocks;

import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import claycorp.wyldmod.utils.Archive;

public class BlockSoggyCarpet extends BlockCarpet {

    public BlockSoggyCarpet(final int id) {
        super(id);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setUnlocalizedName(Archive.MOD_ID_BLOCK + "soggycarpet");
        this.setHardness(5);
        this.setResistance(1000);
        this.setStepSound(soundSnowFootstep);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this
     * box can change after the pool has been cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world, final int x, final int y, final int z) {
        return AxisAlignedBB.getAABBPool().getAABB(x + this.minX, y + this.minY, z + this.minZ, x + 1F, y + 0.25F, z + 1F);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the
     * block). Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock(final World worldID, final int X, final int Y, final int Z, final Entity slowEntity) {
        slowEntity.motionX *= 0.05D;
        slowEntity.motionZ *= 0.05D;
    }

}