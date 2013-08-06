package claycorp.soggycarpet.configuration;

import claycorp.soggycarpet.utils.Properties;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public final class Config
{
    public static void load(final FMLPreInitializationEvent event)
    {
        final Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        Properties.soggycarpetID = config.getBlock("SoggyCarpet", 450).getInt();
        Properties.convayorzID = config.getBlock("CarpetConvayorZ", 451).getInt();
        Properties.convayorxID = config.getBlock("CarpetConvayorX", 452).getInt();
        Properties.convayornzID = config.getBlock("CarpetConvayorNZ", 453).getInt();
        Properties.convayornxID = config.getBlock("CarpetConvayorNX", 454).getInt();
        Properties.convayoryID = config.getBlock("CarpetConvayorY", 455).getInt();
        Properties.convayorzyID = config.getBlock("CarpetConvayorZY", 456).getInt();
        Properties.convayorxyID = config.getBlock("CarpetConvayorXY", 457).getInt();
        Properties.convayornzyID = config.getBlock("CarpetConvayorNZY", 458).getInt();
        Properties.convayornxyID = config.getBlock("CarpetConvayorNXY", 459).getInt();

        if (config.hasChanged())
        {
            config.save();
        }
    }
}
