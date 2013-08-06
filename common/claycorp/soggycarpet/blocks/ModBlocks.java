package claycorp.soggycarpet.blocks;

import claycorp.soggycarpet.utils.Properties;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
    public static BlockSoggyCarpet soggycarpet;

    public static void init()
    {
        soggycarpet = new BlockSoggyCarpet(Properties.soggycarpetID);
        GameRegistry.registerBlock(soggycarpet, "CLAYCORP.SOGGYCARPET.BLOCK");
    }
}
