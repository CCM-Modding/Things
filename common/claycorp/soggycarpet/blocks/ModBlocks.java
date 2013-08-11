package claycorp.soggycarpet.blocks;

import claycorp.soggycarpet.utils.Materials;
import claycorp.soggycarpet.utils.Properties;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
    public static BlockSoggyCarpet  soggycarpet;
    public static CarpetConvayorZ   convayorz;
    public static CarpetConvayorX   convayorx;
    public static CarpetConvayorNZ  convayornz;
    public static CarpetConvayorNX  convayornx;
    public static CarpetConvayorY   convayory;
    public static CarpetConvayorXY  convayorxy;
    public static CarpetConvayorZY  convayorzy;
    public static CarpetConvayorNXY convayornxy;
    public static CarpetConvayorNZY convayornzy;
    public static CarpetDoor        trapdoor;
    public static WindowDoor        windowdoor;

    public static void init()
    {
        soggycarpet = new BlockSoggyCarpet(Properties.soggycarpetID);
        convayorz = new CarpetConvayorZ(Properties.convayorzID);
        convayorx = new CarpetConvayorX(Properties.convayorxID);
        convayornz = new CarpetConvayorNZ(Properties.convayornzID);
        convayornx = new CarpetConvayorNX(Properties.convayornxID);
        convayory = new CarpetConvayorY(Properties.convayoryID);
        convayorzy = new CarpetConvayorZY(Properties.convayorzyID);
        convayorxy = new CarpetConvayorXY(Properties.convayorxyID);
        convayornzy = new CarpetConvayorNZY(Properties.convayornzyID);
        convayornxy = new CarpetConvayorNXY(Properties.convayornxyID);
        trapdoor = new CarpetDoor(Properties.trapdoorID, Materials.carpet);
        windowdoor = new WindowDoor(Properties.windowdoorID, Material.wood);

        GameRegistry.registerBlock(soggycarpet, "CLAYCORP.SOGGYCARPET.BLOCK");
        GameRegistry.registerBlock(convayorz, "CLAYCORP.CARPETCONVAYORZ.BLOCK");
        GameRegistry.registerBlock(convayorx, "CLAYCORP.CARPETCONVAYORX.BLOCK");
        GameRegistry.registerBlock(convayornz, "CLAYCORP.CARPETCONVAYORNZ.BLOCK");
        GameRegistry.registerBlock(convayornx, "CLAYCORP.CARPETCONVAYORNX.BLOCK");
        GameRegistry.registerBlock(convayory, "CLAYCORP.CARPETCONVAYORY.BLOCK");
        GameRegistry.registerBlock(convayorzy, "CLAYCORP.CARPETCONVAYORZY.BLOCK");
        GameRegistry.registerBlock(convayorxy, "CLAYCORP.CARPETCONVAYORXY.BLOCK");
        GameRegistry.registerBlock(convayornzy, "CLAYCORP.CARPETCONVAYORNZY.BLOCK");
        GameRegistry.registerBlock(convayornxy, "CLAYCORP.CARPETCONVAYORNXY.BLOCK");
        GameRegistry.registerBlock(trapdoor, "CLAYCORP.CARPETTRAPDOOR.BLOCK");
        GameRegistry.registerBlock(windowdoor, "CLAYCORP.WINDOWTRAPDOOR.BLOCK");

        final int carpetID = Block.field_111031_cC.blockID;
        Block.blocksList[carpetID] = null;
        Block.blocksList[carpetID] = new BlockCarpetModified(carpetID);

    }
}
