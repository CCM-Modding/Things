package claycorp.wyldmod.entity.unicorncow;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderUnicornCow extends RenderLiving
{
    private static final ModelUnicornCow model = new ModelUnicornCow();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/unicorncow.png");

    public RenderUnicornCow()
    {
        super(model, 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity entity)
    {
        return texture;
    }
}