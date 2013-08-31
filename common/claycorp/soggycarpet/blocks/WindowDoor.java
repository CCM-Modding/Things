package claycorp.soggycarpet.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import claycorp.soggycarpet.utils.Archive;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
// Single window Flips down
// Anything larger flips to the sides.

public class WindowDoor extends Block {
    public WindowDoor(final int par1, final Material par2Material) {
        super(par1, Material.glass);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setUnlocalizedName("windowdoor");
        this.func_111022_d(Archive.MOD_ID + ":window");
        this.setHardness(1);
        this.setResistance(10);
        this.setStepSound(soundWoodFootstep);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False
     * (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean getBlocksMovement(final IBlockAccess IBlockAccess, final int x, final int y, final int z) {
        return !isTrapdoorOpen(IBlockAccess.getBlockMetadata(x, y, z));
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(final World worldID, final int x, final int y, final int z) {
        this.setBlockBoundsBasedOnState(worldID, x, y, z);
        return super.getSelectedBoundingBoxFromPool(worldID, x, y, z);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this
     * box can change after the pool has been cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World worldID, final int x, final int y, final int z) {
        this.setBlockBoundsBasedOnState(worldID, x, y, z);
        return super.getCollisionBoundingBoxFromPool(worldID, x, y, z);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y,
     * z
     */
    @Override
    public void setBlockBoundsBasedOnState(final IBlockAccess par1IBlockAccess, final int x, final int y, final int z) {
        this.setBlockBoundsForBlockRender(par1IBlockAccess.getBlockMetadata(x, y, z));
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    @Override
    public void setBlockBoundsForItemRender() {

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    public void setBlockBoundsForBlockRender(final int par1) {

        if ((par1 & 8) != 0) { // Top of block
            this.setBlockBounds(0.0F, 1.0F - 0.0625F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else { // Bottom of block
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        }

        if (isTrapdoorOpen(par1)) { // North
            if ((par1 & 3) == 0) {
                this.setBlockBounds(0.0F, 0.0F, 1.0F - 0.0625F, 1.0F, 1.0F, 1.0F);
            }
            // South
            if ((par1 & 3) == 1) {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
            }
            // West
            if ((par1 & 3) == 2) {
                this.setBlockBounds(1.0F - 0.0625F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
            // East
            if ((par1 & 3) == 3) {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0625F, 1.0F, 1.0F);
            }
        }
    }

    boolean doorState(final World worldID, int x, int y, int z) {
        // final int open = 0; NOT USED
        final int closed = 4;

        int meta = worldID.getBlockMetadata(x++, y, z);
        if (meta == closed) {
            return true;
        }
        meta = worldID.getBlockMetadata(x--, y, z);
        if (meta == closed) {
            return true;
        }
        meta = worldID.getBlockMetadata(x, y, z++);
        if (meta == closed) {
            return true;
        }
        meta = worldID.getBlockMetadata(x, y, z--);
        if (meta == closed) {
            return true;
        }
        meta = worldID.getBlockMetadata(x, y++, z);
        if (meta == closed) {
            return true;
        }
        meta = worldID.getBlockMetadata(x, y--, z);
        if (meta == closed) {
            return true;
        }
        return false;
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    @Override
    public void onBlockClicked(final World worldID, final int x, final int y, final int z, final EntityPlayer clickEntityPlayer) {}

    public boolean syncNeighbor(final World worldID, final int x, final int y, final int z, final int blockID, final int flag) {
        if (this.doorState(worldID, x, y, z) == true) {
            ;
        }

        worldID.setBlockMetadataWithNotify(x, y, z, worldID.getBlockMetadata(x, y, z) ^ 4, 3);
        return true;

    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(final World worldID, final int x, final int y, final int z, final EntityPlayer clickEntityPlayer, final int par6, final float par7, final float par8, final float par9) {
        if (this.blockMaterial == Material.iron) {
            return true;
        } else {
            final int i1 = worldID.getBlockMetadata(x, y, z);
            worldID.setBlockMetadataWithNotify(x, y, z, i1 ^ 4, 2);
            worldID.playAuxSFXAtEntity(clickEntityPlayer, 1003, x, y, z, 0);
            return true;
        }
    }

    public void onPoweredBlockChange(final World worldID, final int x, final int y, final int z, final boolean bool) {
        final int l = worldID.getBlockMetadata(x, y, z);
        final boolean flag1 = (l & 4) > 0;

        if (flag1 != bool) {
            worldID.setBlockMetadataWithNotify(x, y, z, l ^ 4, 2);
            worldID.playAuxSFXAtEntity((EntityPlayer) null, 1003, x, y, z, 0);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which
     * neighbor changed (coordinates passed are their own) Args: x, y, z,
     * neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(final World worldID, final int x, final int y, final int z, final int neighborblockID) {
        if (!worldID.isRemote) {
            final int meta = worldID.getBlockMetadata(x, y, z);
            // pastX and pastZ ARE NOT USED
            int pastX = x;
            int pastZ = z;

            if ((meta & 3) == 0) {
                pastZ = z + 1;
            }

            if ((meta & 3) == 1) {
                --pastZ;
            }

            if ((meta & 3) == 2) {
                pastX = x + 1;
            }

            if ((meta & 3) == 3) {
                --pastX;
            }

            final boolean flag = worldID.isBlockIndirectlyGettingPowered(x, y, z);

            if (flag || ((neighborblockID > 0) && Block.blocksList[neighborblockID].canProvidePower())) {
                this.onPoweredBlockChange(worldID, x, y, z, flag);
            }
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector
     * returning a ray trace hit. Args: world, x, y, z, startVec, endVec
     */
    @Override
    public MovingObjectPosition collisionRayTrace(final World worldID, final int par2, final int par3, final int par4, final Vec3 par5Vec3, final Vec3 par6Vec3) {
        this.setBlockBoundsBasedOnState(worldID, par2, par3, par4);
        return super.collisionRayTrace(worldID, par2, par3, par4, par5Vec3, par6Vec3);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z,
     * side, hitX, hitY, hitZ, block metadata
     */
    @Override
    public int onBlockPlaced(final World worldID, final int x, final int y, final int z, final int side, final float hitX, final float hitY, final float hitZ, final int meta) {
        int j1 = 0;

        if (side == 2) {
            j1 = 0;
        }

        if (side == 3) {
            j1 = 1;
        }

        if (side == 4) {
            j1 = 2;
        }

        if (side == 5) {
            j1 = 3;
        }

        if ((side != 1) && (side != 0) && (hitY > 0.5F)) {
            j1 |= 8;
        }

        return j1;
    }

    public static boolean isTrapdoorOpen(final int par0) {
        return (par0 & 4) != 0;
    }
}