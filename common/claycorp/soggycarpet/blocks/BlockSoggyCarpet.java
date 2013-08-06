package claycorp.soggycarpet.blocks;

import claycorp.soggycarpet.utils.Archive;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.material.Material;
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
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(World WorldID, int X, int Y, int Z, int par5)
    {
    	super.onNeighborBlockChange(WorldID, X, Y, Z, par5);
    }
    	boolean soggyCheck(World WorldID, int X, int Y, int Z)
    	{
    		if (!this.canBlockStay(WorldID, X, Y, Z))
    		{
				this.isBlockWet(WorldID, X, Y, Z);
				World.setBlock(X, Y, Z, blockID, 0, 0);
    			return false;
        	}
    		else
    		{
           return true;
        }
    }
    

    boolean isBlockWet(World world, int x, int y, int z){
        if(world.getBlockId(x++, y, z) == waterStill.blockID){
        	return true;
        }
        if(world.getBlockId(x--, y, z) == waterStill.blockID){
        	return true;
        }
        if(world.getBlockId(x, y, z++) == waterStill.blockID){
        	return true;
        }
        if(world.getBlockId(x, y, z--) == waterStill.blockID){
        	return true;
        }
        else
        	if(world.getBlockId(x++, y, z) == waterMoving.blockID){
        		return true;
        	}
        	if(world.getBlockId(x--, y, z) == waterMoving.blockID){
        		return true;
        	}
        	if(world.getBlockId(x, y, z++) == waterMoving.blockID){
        		return true;
        	}
        	if(world.getBlockId(x, y, z--) == waterMoving.blockID){
        		return true;
        	}
        return false;
    }
		

	/**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool
     * has been
     * cleared to be reused)
     */
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