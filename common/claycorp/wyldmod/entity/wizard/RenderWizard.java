package claycorp.wyldmod.entity.wizard;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderWizard extends RenderLiving
{
    private static final ModelWizard model = new ModelWizard();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/wizard.png");

    public RenderWizard()
    {
        super(model, 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity entity)
    {
        return texture;
    }
}