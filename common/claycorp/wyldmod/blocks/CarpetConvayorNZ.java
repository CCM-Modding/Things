package claycorp.wyldmod.blocks;

import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import claycorp.wyldmod.utils.Archive;

public class CarpetConvayorNZ extends BlockCarpet {

    protected CarpetConvayorNZ(final int par1) {
        super(par1);
        this.setCreativeTab(CreativeTabs.tabTransport);
        this.setUnlocalizedName(Archive.MOD_ID_BLOCK + "convayornz");
        this.setHardness(5);
        this.setResistance(1000);
        this.setStepSound(soundMetalFootstep);
    }

    @Override
    public void onEntityCollidedWithBlock(final World par1World, final int par2, final int par3, final int par4, final Entity moveEntity) {
        moveEntity.motionZ = -1D;
    }
}