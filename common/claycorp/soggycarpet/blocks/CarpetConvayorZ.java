package claycorp.soggycarpet.blocks;

import claycorp.soggycarpet.utils.Archive;

import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class CarpetConvayorZ extends BlockCarpet
{

    protected CarpetConvayorZ(final int par1)
    {
        super(par1);
        setCreativeTab(CreativeTabs.tabTransport);
        setUnlocalizedName(Archive.MOD_ID_BLOCK + "convayorz");
        setHardness(5);
        setResistance(1000);
        setStepSound(soundMetalFootstep);
    }

    @Override
    public void onEntityCollidedWithBlock(final World par1World,
                                          final int par2,
                                          final int par3,
                                          final int par4,
                                          final Entity moveEntity)
    {
        moveEntity.motionZ = 1D;
    }
}