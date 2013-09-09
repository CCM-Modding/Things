package claycorp.wyldmod.entity.bear;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBear extends RenderLiving
{
    private static final ModelBear model = new ModelBear();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/bear.png");

    public RenderBear()
    {
        super(model, 1.05F);
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity entity)
    {
        return texture;
    }
}