package claycorp.wyldmod.entity.bunny;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBunny extends RenderLiving
{
    private static final ModelBunny model = new ModelBunny();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/bunny.png");

    public RenderBunny()
    {
        super(model, 0.25F);
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity entity)
    {
        return texture;
    }

}