package claycorp.soggycarpet.blocks;

import claycorp.soggycarpet.utils.Archive;

import net.minecraft.block.BlockSoulSand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class BlockSoggyCarpet extends BlockSoulSand
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

    @Override
    public void onEntityCollidedWithBlock(final World world,
                                          final int x,
                                          final int y,
                                          final int z,
                                          final Entity entity)
    {
        entity.motionX *= 0.1D;
        entity.motionZ *= 0.1D;
    }
}
