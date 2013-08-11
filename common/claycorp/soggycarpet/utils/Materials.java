package claycorp.soggycarpet.utils;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class Materials extends Material {
    public Materials(final MapColor par1MapColor) {
        super(par1MapColor);
    }

    public static final Material carpet = (new CarpetMaterial(MapColor.clothColor));

}
