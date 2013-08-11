package claycorp.soggycarpet.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBear extends Render
{
    private final ModelBear model;

    public RenderBear()
    {
        model = new ModelBear();
        shadowSize = 0.8F;
    }

    private static final ResourceLocation texture = new ResourceLocation("soggycarpet",
                                                                         "textures/entity/bear.png");

    public void renderBear(final EntityBear bear,
                           final double x,
                           final double y,
                           final double z,
                           final float yaw,
                           final float partialTickTime)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y + bear.height, (float) z - (bear.width / 8));
        GL11.glScalef(-1, -1, 1);
        func_110777_b(bear);

        model.render(bear, 0, 0, 0, 0, 0, 0.0625F);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation func_110775_a(final Entity entity)
    {
        return texture;
    }

    @Override
    public void doRender(final Entity entity,
                         final double x,
                         final double y,
                         final double z,
                         final float yaw,
                         final float partialTickTime)
    {
        renderBear((EntityBear) entity, x, y, z, yaw, partialTickTime);
    }
}
