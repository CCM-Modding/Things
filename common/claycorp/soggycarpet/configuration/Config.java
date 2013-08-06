package claycorp.soggycarpet.configuration;

import claycorp.soggycarpet.utils.Properties;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config
{
    public static void load(final FMLPreInitializationEvent event)
    {
        final Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        Properties.soggycarpetID = config.getBlock("SoggyCarpet", 450).getInt();
    }
}
