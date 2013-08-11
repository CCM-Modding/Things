package claycorp.soggycarpet;

import claycorp.soggycarpet.blocks.ModBlocks;
import claycorp.soggycarpet.configuration.Config;
import claycorp.soggycarpet.entity.ModEntity;
import claycorp.soggycarpet.utils.Archive;
import claycorp.soggycarpet.utils.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Archive.MOD_ID, name = Archive.MOD_NAME, version = Archive.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SoggyCarpet {
    public static final String WindowDoor = "soggycarpet:window";

    @Instance(Archive.MOD_ID)
    public static SoggyCarpet instance;

    @SidedProxy(clientSide = "claycorp.soggycarpet.utils.ClientProxy", serverSide = "claycorp.soggycarpet.utils.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        Config.load(event);
        ModBlocks.init();
    }

    @EventHandler
    public void init(final FMLInitializationEvent event) {
        proxy.initRenders();

        ModEntity.init();
        // EntityRegistry.addSpawn(EntityBear.class, weightedProb, min, max,
        // spawnList, biomes);
    }

    @EventHandler
    public void postInit(final FMLPostInitializationEvent event) {}

}
