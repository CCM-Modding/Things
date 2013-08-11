package claycorp.soggycarpet.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import claycorp.soggycarpet.SoggyCarpet;
import claycorp.soggycarpet.utils.Archive;
import claycorp.soggycarpet.utils.Materials;
import claycorp.soggycarpet.utils.Properties;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
//Single window Flips down
//Anything larger flips to the sides.
public class WindowDoor extends Block
{  
	public WindowDoor(int par1, Material par2Material){ 
		super(par1, Materials.wood);
        setCreativeTab(CreativeTabs.tabDecorations);
        setUnlocalizedName(Archive.MOD_ID_BLOCK + "windowdoor");
        setHardness(1);
        setResistance(10);
        setStepSound(soundWoodFootstep);
	}
	/**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(SoggyCarpet.WindowDoor);
    }
    
    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean getBlocksMovement(IBlockAccess IBlockAccess, int x, int y, int z)
    {
        return !isTrapdoorOpen(IBlockAccess.getBlockMetadata(x, y, z));
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World worldID, int x, int y, int z)
    {
        this.setBlockBoundsBasedOnState(worldID, x, y, z);
        return super.getSelectedBoundingBoxFromPool(worldID, x, y, z);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldID, int x, int y, int z)
    {
        this.setBlockBoundsBasedOnState(worldID, x, y, z);
        return super.getCollisionBoundingBoxFromPool(worldID, x, y, z);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z)
    {
        this.setBlockBoundsForBlockRender(par1IBlockAccess.getBlockMetadata(x, y, z));
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    public void setBlockBoundsForBlockRender(int par1)
    {

        if ((par1 & 8) != 0)
        {	//Top of block
            setBlockBounds(0.0F, 1.0F - 0.0625F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else
        {	//Bottom of block
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        }

        if (isTrapdoorOpen(par1))
        {	//North
            if ((par1 & 3) == 0)
            {
                setBlockBounds(0.0F, 0.0F, 1.0F - 0.0625F, 1.0F, 1.0F, 1.0F);
            }
            //South
            if ((par1 & 3) == 1)
            {
                setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
            }
            //West
            if ((par1 & 3) == 2)
            {
                setBlockBounds(1.0F - 0.0625F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
            //East
            if ((par1 & 3) == 3)
            {
                setBlockBounds(0.0F, 0.0F, 0.0F, 0.0625F, 1.0F, 1.0F);
            }
        }
    }

    boolean doorState(final World worldID, int x, int y, int z)
    {
        final int open = 0;
        final int closed = 4;

        int meta = worldID.getBlockMetadata(x++, y, z);
        if (meta == 4)
        {
            return true;
        }
        meta = worldID.getBlockMetadata(x--, y, z);
        if (meta == 4)
        {
            return true;
        }
        meta = worldID.getBlockMetadata(x, y, z++);
        if (meta == 4)
        {
            return true;
        }
        meta = worldID.getBlockMetadata(x, y, z--);
        if (meta == 4)
        {
            return true;
        }
        meta = worldID.getBlockMetadata(x, y++, z);
        if (meta == 4)
        {
            return true;
        }
        meta = worldID.getBlockMetadata(x, y--, z);
        if (meta == 4)
        {
            return true;
        }
        return false;
    }
    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World worldID, int x, int y, int z, EntityPlayer clickEntityPlayer) {}
    

    public boolean syncNeighbor(World worldID, int x, int y, int z, int blockID, int flag)
    {
    	if (doorState(worldID, x, y, z) == true);
    	
    			worldID.setBlockMetadataWithNotify(x, y, z,worldID.getBlockMetadata(x, y, z) ^ 4, 3);
    			return true;

    		
    }
    
    
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World worldID, int x, int y, int z, EntityPlayer clickEntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (this.blockMaterial == Material.iron)
        {
            return true;
        }
        else
        {
            int i1 = worldID.getBlockMetadata(x, y, z);
            worldID.setBlockMetadataWithNotify(x, y, z, i1 ^ 4, 2);
            worldID.playAuxSFXAtEntity(clickEntityPlayer, 1003, x, y, z, 0);
            return true;
        }
    }

    public void onPoweredBlockChange(World worldID, int x, int y, int z, boolean bool)
    {
        int l = worldID.getBlockMetadata(x, y, z);
        boolean flag1 = (l & 4) > 0;

        if (flag1 != bool)
        {
            worldID.setBlockMetadataWithNotify(x, y, z, l ^ 4, 2);
            worldID.playAuxSFXAtEntity((EntityPlayer)null, 1003, x, y, z, 0);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World worldID, int x, int y, int z, int neighborblockID)
    {
        if (!worldID.isRemote)
        {
            int i1 = worldID.getBlockMetadata(x, y, z);
            int j1 = x;
            int k1 = z;

            if ((i1 & 3) == 0)
            {
                k1 = z + 1;
            }

            if ((i1 & 3) == 1)
            {
                --k1;
            }

            if ((i1 & 3) == 2)
            {
                j1 = x + 1;
            }

            if ((i1 & 3) == 3)
            {
                --j1;
            }

            boolean flag = worldID.isBlockIndirectlyGettingPowered(x, y, z);

            if (flag || neighborblockID > 0 && Block.blocksList[neighborblockID].canProvidePower())
            {
                this.onPoweredBlockChange(worldID, x, y, z, flag);
            }
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World worldID, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3)
    {
        this.setBlockBoundsBasedOnState(worldID, par2, par3, par4);
        return super.collisionRayTrace(worldID, par2, par3, par4, par5Vec3, par6Vec3);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World worldID, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        int j1 = 0;

        if (side == 2)
        {
            j1 = 0;
        }

        if (side == 3)
        {
            j1 = 1;
        }

        if (side == 4)
        {
            j1 = 2;
        }

        if (side == 5)
        {
            j1 = 3;
        }

        if (side != 1 && side != 0 && hitY > 0.5F)
        {
            j1 |= 8;
        }

        return j1;
    }
 
    public static boolean isTrapdoorOpen(int par0)
    {
        return (par0 & 4) != 0;
    }
}