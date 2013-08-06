package claycorp.soggycarpet.blocks;


import claycorp.soggycarpet.utils.Archive;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class BlockSoggyCarpet extends BlockSoulSand{

	public BlockSoggyCarpet(int par1) {
		super(par1);
	        setCreativeTab(CreativeTabs.tabDecorations);
	        setUnlocalizedName(Archive.MOD_ID_BLOCK + "soggycarpet");
	        setHardness(10);
	        setResistance(1000);
	        setStepSound(soundSnowFootstep);
	    }
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.motionX *= 0.1D;
        par5Entity.motionZ *= 0.1D;
    }
	}


