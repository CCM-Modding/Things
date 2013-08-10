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

        String cat = "Block.Id";
        
        Properties.soggycarpetID = config.getBlock(cat, "SoggyCarpet", 450).getInt();
        Properties.convayorzID = config.getBlock(cat, "CarpetConvayorZ", 451).getInt();
        Properties.convayorxID = config.getBlock(cat, "CarpetConvayorX", 452).getInt();
        Properties.convayornzID = config.getBlock(cat, "CarpetConvayorNZ", 453).getInt();
        Properties.convayornxID = config.getBlock(cat, "CarpetConvayorNX", 454).getInt();
        Properties.convayoryID = config.getBlock(cat, "CarpetConvayorY", 455).getInt();
        Properties.convayorzyID = config.getBlock(cat, "CarpetConvayorZY", 456).getInt();
        Properties.convayorxyID = config.getBlock(cat, "CarpetConvayorXY", 457).getInt();
        Properties.convayornzyID = config.getBlock(cat, "CarpetConvayorNZY", 458).getInt();
        Properties.convayornxyID = config.getBlock(cat, "CarpetConvayorNXY", 459).getInt();
        Properties.trapdoorID = config.getBlock(cat, "CarpetTrapDoor", 460).getInt();
        Properties.windowdoorID = config.getBlock(cat, "WindowTrapDoor", 461).getInt();
        
        cat = "Entity.Spawns";
        //Properties.bearID = config.get(cat, "ID_Of_Entity", 0).getInt();
        Properties.totalbear = config.get(cat, "Max_Bears_Per_Chunk", 3).getInt();
        Properties.bearhealth = config.get(cat, "Health_Of_Bears", 100).getInt();
        Properties.xp = config.get(cat, "How_Much_XP_Bears_Drop", 100).getInt();
        Properties.beartracking = config.get(cat, "Distance_Bears_Will_Not_Enjoy_You", 50).getInt();
        Properties.totalbear = config.get(cat, "Max_Bears_Per_Chunk", 3).getInt();
        Properties.bearmovespeed = config.get(cat, "Move_Speed", 1).getInt();
        Properties.bearattackspeed = config.get(cat, "Speed_Of_Attack", 30).getInt();
        Properties.playerdamage = config.get(cat, "Damage_To_Player", 5).getInt();
        Properties.villagerdamage = config.get(cat, "Damage_To_Villager", 1000).getInt();
        Properties.passivedamage = config.get(cat, "Damage_To_Passives", 10).getInt();
        Properties.hostiledamage = config.get(cat, "Damage_To_Hostiles", 10).getInt();
        Properties.beardrop = config.get(cat, "ItemID_Bears_Drop_Guaranteed", 349).getInt();
        Properties.beardrop2 = config.get(cat, "ItemID_Bears_Drop_From_Player_Kill", 388).getInt();
        Properties.dropquantity = config.get(cat, "Quanity_Of_Player_Kill", 2).getInt();
        

        if (config.hasChanged())
        {
            config.save();
        }
    }
}
