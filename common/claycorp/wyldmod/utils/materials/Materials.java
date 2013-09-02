package claycorp.wyldmod.utils.materials;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class Materials extends Material {
    public Materials(final MapColor par1MapColor) {
        super(par1MapColor);
    }

    public static final Material carpet = (new CarpetMaterial(MapColor.clothColor));
    public static final Material flesh = (new FleshMaterial(MapColor.dirtColor));

}
