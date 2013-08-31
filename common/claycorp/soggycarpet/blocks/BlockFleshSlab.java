package claycorp.soggycarpet.blocks;

import claycorp.soggycarpet.utils.Archive;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockFleshSlab extends BlockHalfSlab{

	public BlockFleshSlab(int par1, boolean par2, Material par3Material) {
		super(par1, par2, par3Material);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("fleshslab");
        this.func_111022_d(Archive.MOD_ID + ":fleshslab");
        this.setHardness(0);
        this.setResistance(1);
        this.setStepSound(soundPowderFootstep);
	}

	@Override
	public String getFullSlabName(int i) {

		return "Flesh Slabs";
	}

}
