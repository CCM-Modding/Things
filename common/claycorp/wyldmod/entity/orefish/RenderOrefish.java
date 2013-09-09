package claycorp.wyldmod.entity.orefish;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderOrefish extends RenderLiving
{
    private static final ModelOrefish model = new ModelOrefish();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/orefish.png");

    public RenderOrefish()
    {
        super(model, 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity entity)
    {
        return texture;
    }
}