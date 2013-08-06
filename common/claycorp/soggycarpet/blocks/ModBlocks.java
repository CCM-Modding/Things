package claycorp.soggycarpet.blocks;

import claycorp.soggycarpet.utils.Properties;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
    public static BlockSoggyCarpet soggycarpet;

    public static void init()
    {
        soggycarpet = new BlockSoggyCarpet(Properties.soggycarpetID);
        GameRegistry.registerBlock(soggycarpet, "CLAYCORP.SOGGYCARPET.BLOCK");

        final int carpetID = Block.field_111031_cC.blockID;
        Block.blocksList[carpetID] = null;
        Block.blocksList[carpetID] = new BlockCarpetModified(carpetID);

    }
}
