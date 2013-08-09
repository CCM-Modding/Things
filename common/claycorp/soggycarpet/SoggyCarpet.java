package claycorp.soggycarpet;

import claycorp.soggycarpet.blocks.ModBlocks;
import claycorp.soggycarpet.configuration.Config;
import claycorp.soggycarpet.entity.ModEntity;
import claycorp.soggycarpet.utils.Archive;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Archive.MOD_ID,
     name = Archive.MOD_NAME,
     version = Archive.MOD_VERSION)
@NetworkMod(clientSideRequired = true,
            serverSideRequired = false)
public class SoggyCarpet
{

    @Instance(Archive.MOD_ID)
    public static SoggyCarpet instance;

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event)
    {
        Config.load(event);
        ModBlocks.init();
        ModEntity.init();
    }

    @EventHandler
    public void init(final FMLInitializationEvent event)
    {}

    @EventHandler
    public void postInit(final FMLPostInitializationEvent event)
    {}
}