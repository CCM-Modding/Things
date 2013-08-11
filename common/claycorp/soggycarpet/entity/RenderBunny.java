package claycorp.soggycarpet.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderBunny extends Render {
    private final ModelBunny model;

    public RenderBunny() {
        this.model = new ModelBunny();
        this.shadowSize = 0.5F;
    }

    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/bunny.png");

    public void renderBunny(final EntityBunny bunny, final double x, final double y, final double z, final float yaw, final float partialTickTime) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y + bunny.height, (float) z - (bunny.width / 8));
        GL11.glScalef(-1, -1, 1);
        this.func_110777_b(bunny);

        this.model.render(bunny, 0, 0, 0, 0, 0, 0.0625F);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation func_110775_a(final Entity entity) {
        return texture;
    }

    @Override
    public void doRender(final Entity entity, final double x, final double y, final double z, final float yaw, final float partialTickTime) {
        this.renderBunny((EntityBunny) entity, x, y, z, yaw, partialTickTime);
    }

}
