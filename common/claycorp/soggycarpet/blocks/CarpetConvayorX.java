package claycorp.soggycarpet.blocks;

import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import claycorp.soggycarpet.utils.Archive;

public class CarpetConvayorX extends BlockCarpet {

    protected CarpetConvayorX(final int par1) {
        super(par1);
        this.setCreativeTab(CreativeTabs.tabTransport);
        this.setUnlocalizedName(Archive.MOD_ID_BLOCK + "convayorx");
        this.setHardness(5);
        this.setResistance(1000);
        this.setStepSound(soundMetalFootstep);
    }

    @Override
    public void onEntityCollidedWithBlock(final World par1World, final int par2, final int par3, final int par4, final Entity moveEntity) {
        moveEntity.motionX = 1D;
    }
}
