package claycorp.soggycarpet.blocks;

import claycorp.soggycarpet.utils.Archive;

import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class CarpetConvayorNXY extends BlockCarpet
{

    protected CarpetConvayorNXY(final int par1)
    {
        super(par1);
        setCreativeTab(CreativeTabs.tabTransport);
        setUnlocalizedName(Archive.MOD_ID_BLOCK + "convayornxy");
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
        moveEntity.motionY = 1D;
        moveEntity.motionX = -1D;
    }
}