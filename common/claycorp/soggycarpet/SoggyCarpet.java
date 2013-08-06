package claycorp.soggycarpet;


import claycorp.soggycarpet.blocks.ModBlocks;
import claycorp.soggycarpet.configuration.Config;
import claycorp.soggycarpet.utils.Archive;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;


public class SoggyCarpet {

	@Mod(modid = Archive.MOD_ID,
		     name = Archive.MOD_NAME,
		     version = Archive.MOD_VERSION)
	@NetworkMod(clientSideRequired = true,
				serverSideRequired = false)

		public class TradeStuffs
		{
		    @Instance(Archive.MOD_ID)
		    public  SoggyCarpet  instance;

		    public MinecraftServer     server;

		    @EventHandler
		    public void preInit(final FMLPreInitializationEvent event)
		    {

		        ModBlocks.init();
		        Config.load(event);

		    }

		    @EventHandler
		    public void init(final FMLInitializationEvent event)
		    {
		    }

		    @EventHandler
		    public void postInit(final FMLPostInitializationEvent event)
		    {}

		    @EventHandler
		    public void serverStarting(final FMLServerStartingEvent event)
		    {
		        server = event.getServer();

		    }
		}}