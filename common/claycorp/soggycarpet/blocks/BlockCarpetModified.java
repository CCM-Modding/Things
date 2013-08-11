/**
 * CCM Modding, SoggyCarpet
 */
package claycorp.soggycarpet.blocks;

import net.minecraft.block.BlockCarpet;
import net.minecraft.world.World;
import claycorp.soggycarpet.utils.Properties;

/**
 * BlockCarpet
 * <p>
 * 
 * @author Captain_Shadows
 * @param <setMaterial>
 */
public class BlockCarpetModified extends BlockCarpet {
    public BlockCarpetModified(final int id) {
        super(id);
        this.setHardness(0.1F);
        this.setStepSound(soundClothFootstep);
        this.setUnlocalizedName("woolCarpet");
        this.setLightOpacity(0);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which
     * neighbor changed (coordinates passed are their own) Args: x, y, z,
     * neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final int neighborID) {
        System.out.println("DEBUG!!!!!!");
        System.out.println("WORLD" + world);
        System.out.println("X" + x);
        System.out.println("Y" + y);
        System.out.println("Z" + z);
        System.out.println("neighborID" + neighborID);
        this.soggyCheck(world, x, y, z);
        super.onNeighborBlockChange(world, x, y, z, neighborID);
    }

    boolean soggyCheck(final World world, final int x, final int y, final int z) {
        if (this.isBlockWet(world, x, y, z)) {
            System.out.println("Stuffs");
            this.isBlockWet(world, x, y, z);
            world.setBlock(x, y, z, Properties.soggycarpetID, world.getBlockMetadata(x, y, z), 3);
            return true;
        } else {
            return false;
        }
    }

    boolean removeWater(final World world, final int x, final int y, final int z) {
        if (this.waterAbsorbtion(world, x, y, z)) {
            System.out.println("GG");
            this.waterAbsorbtion(world, x, y, z);
            world.setBlockToAir(x, y, z);
            return true;
        } else {
            return false;
        }
    }

    boolean isBlockWet(final World world, int x, final int y, int z) {
        final int still = waterStill.blockID;
        final int moving = waterMoving.blockID;

        int id = world.getBlockId(x++, y, z);
        if ((id == still) || (id == moving)) {
            return true;
        }
        id = world.getBlockId(x--, y, z);
        if ((id == still) || (id == moving)) {
            return true;
        }
        id = world.getBlockId(x, y, z++);
        if ((id == still) || (id == moving)) {
            return true;
        }
        id = world.getBlockId(x, y, z--);
        if ((id == still) || (id == moving)) {
            return true;
        }
        return false;
    }

    boolean waterAbsorbtion(final World world, int x, final int y, int z) {
        final int still = waterStill.blockID;
        final int moving = waterMoving.blockID;

        int id = world.getBlockId(x++, y, z);
        if ((id == still) || (id == moving)) {
            return true;
        }
        id = world.getBlockId(x--, y, z);
        if ((id == still) || (id == moving)) {
            return true;
        }
        id = world.getBlockId(x, y, z++);
        if ((id == still) || (id == moving)) {
            return true;
        }
        id = world.getBlockId(x, y, z--);
        if ((id == still) || (id == moving)) {
            return true;
        }
        return false;
    }
}
