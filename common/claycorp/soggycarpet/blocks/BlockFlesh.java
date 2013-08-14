package claycorp.soggycarpet.blocks;

import claycorp.soggycarpet.utils.Archive;
import claycorp.soggycarpet.utils.Materials;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockFlesh extends Block{

	public BlockFlesh(int par1, Material par2Material) {
		super(par1, Materials.flesh);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("fleshblock");
        this.func_111022_d(Archive.MOD_ID + ":fleshblock");
        this.setHardness(0);
        this.setResistance(1);
        this.setStepSound(soundPowderFootstep);
        
	}

}
