package claycorp.soggycarpet.utils;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLogic;

public class CarpetMaterial extends MaterialLogic
{

    public CarpetMaterial(final MapColor par1MapColor)
    {
        super(par1MapColor);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }

    @Override
    public boolean blocksMovement()
    {
        return true;
    }
}