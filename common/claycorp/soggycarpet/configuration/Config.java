package claycorp.soggycarpet.configuration;

import net.minecraftforge.common.Configuration;
import claycorp.soggycarpet.utils.Properties;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public final class Config {
    public static void load(final FMLPreInitializationEvent event) {
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
        Properties.fleshblockID = config.getBlock(cat, "BlockOfFlesh", 462).getInt();
        Properties.fleshslabID = config.getBlock(cat, "SlabOfFlesh", 463).getInt();

        cat = "Entity.Bear";
        config.addCustomCategoryComment(cat, "Values For Bears");
        Properties.beartotal = config.get(cat, "Max_Per_Chunk", 3).getInt();
        Properties.bearhealth = config.get(cat, "Health", 10).getInt();
        Properties.bearxp = config.get(cat, "XP_Drop", 100).getInt();
        Properties.beartracking = config.get(cat, "Distance_Bears_Will_Not_Enjoy_You", 10).getInt();
        Properties.bearmovespeed = config.get(cat, "Move_Speed", 0.341).getDouble(0);
        Properties.bearattackspeed = config.get(cat, "Speed_Of_Attack", 1).getInt();
        Properties.beardamage = config.get(cat, "Damage_Per_Attack", 100).getInt();
        Properties.bearknockbackresistance = config.get(cat, "Resistance_To_Knockback", 10).getInt();
        Properties.bearfollowrange = config.get(cat, "How_Far_Bears_Folow_To_Attack", 50).getInt();
        Properties.bearplayerkillitemdrop = config.get(cat, "ID_Of_Item_Drop_When_A_Player_Kills", 56).getInt();
        Properties.bearquantityofdropplayerkill = config.get(cat, "How_Many_Items_To_Drop_On_Player_Kill", 1).getInt();
        Properties.bearkillitemdrop = config.get(cat, "ID_Of_Item_Drop_On_Death", 300).getInt();
        Properties.bearquantityofdropkill = config.get(cat, "How_Many_Items_To_Drop_On_Death", 10).getInt();
        cat= "Entity.Bunny";
        config.addCustomCategoryComment(cat, "Values For Bunnys");
        Properties.bunnytotal = config.get(cat, "Max_Per_Chunk", 15).getInt();
        Properties.bunnydoor = config.get(cat, "Break_Doors", true).getBoolean(true);
        Properties.bunnyhealth = config.get(cat, "Health", 5).getInt();
        Properties.bunnyxp = config.get(cat, "XP_Drop", 1).getInt();
        Properties.bunnytracking = config.get(cat, "Distance_Bunnys_Will_Not_Enjoy_You", 50).getInt();
        Properties.bunnymovespeed = config.get(cat, "Move_Speed", 0.741).getDouble(0);
        Properties.bunnyattackspeed = config.get(cat, "Speed_Of_Attack", 1).getInt();
        Properties.bunnydamage = config.get(cat, "Damage_Per_Attack", 1).getInt();
        Properties.bunnyknockbackresistance = config.get(cat, "Resistance_To_Knockback", 0).getInt();
        Properties.bunnyfollowrange = config.get(cat, "How_Far_Bunnys_Folow_To_Attack", 50).getInt();
        Properties.rabbitplayerkillitemdrop = config.get(cat, "ID_Of_Item_Drop_When_A_Player_Kills", 50).getInt();
        Properties.rabbitquantityofdropplayerkill = config.get(cat, "How_Many_Items_To_Drop_On_Player_Kill", 1).getInt();
        Properties.rabbitkillitemdrop = config.get(cat, "ID_Of_Item_Drop_On_Death", 380).getInt();
        Properties.rabbitquantityofdropkill = config.get(cat, "How_Many_Items_To_Drop_On_Death", 1).getInt();
        cat= "Entity.Shroom";
        config.addCustomCategoryComment(cat, "Values For Shrooms");
        Properties.shroomtotal = config.get(cat, "Max_Per_Chunk", 15).getInt();
        Properties.shroomhealth = config.get(cat, "Health", 5).getInt();
        Properties.shroomxp = config.get(cat, "XP_Drop", 1).getInt();
        //Properties.bunnytracking = config.get(cat, "Distance_Bunnys_Will_Not_Enjoy_You", 50).getInt();
        Properties.shroommovespeed = config.get(cat, "Move_Speed", 0.741).getDouble(0);
        //Properties.bunnyattackspeed = config.get(cat, "Speed_Of_Attack", 1).getInt();
        Properties.shroomdamage = config.get(cat, "Damage_Per_Attack", 1).getInt();
        Properties.shroomknockbackresistance = config.get(cat, "Resistance_To_Knockback", 0).getInt();
        Properties.shroomfollowrange = config.get(cat, "How_Far_Bunnys_Folow_To_Attack", 50).getInt();
        Properties.shroomplayerkillitemdrop = config.get(cat, "ID_Of_Item_Drop_When_A_Player_Kills", 50).getInt();
        Properties.shroomquantityofdropplayerkill = config.get(cat, "How_Many_Items_To_Drop_On_Player_Kill", 1).getInt();
        Properties.shroomkillitemdrop = config.get(cat, "ID_Of_Item_Drop_On_Death", 380).getInt();
        Properties.shroomquantityofdropkill = config.get(cat, "How_Many_Items_To_Drop_On_Death", 1).getInt();




        if (config.hasChanged()) {
            config.save();
        }
    }
}
