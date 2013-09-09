package claycorp.wyldmod.entity.shroom;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderShroom extends RenderLiving
{
    private static final ModelShroom model = new ModelShroom();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/shroom.png");

    public RenderShroom()
    {
        super(model, 0.25F);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args: entityLiving, partialTickTime
     */
    @Override
    protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2)
    {
        EntityShroom shroom = (EntityShroom) par1EntityLivingBase;

        scaleShroom(shroom, par2);
    }

    /**
     * sets the scale for the shroom based on getSlimelikeSize in EntityShroom
     */
    protected void scaleShroom(final EntityShroom shroom, final float partialTicks)
    {
        float size = shroom.getSlimelikeSize();
        float scale = (size * 0.3F);
        GL11.glScalef(scale, scale, scale);
    }

    /**
     * Returns an ARGB int color back. Args: entityLiving, lightBrightness, partialTickTime
     */
    @Override
    protected int getColorMultiplier(final EntityLivingBase par1EntityLivingBase, final float par2, final float par3)
    {
        EntityShroom shroom = (EntityShroom) par1EntityLivingBase;

        if (shroom.isAngry())
        {
            return 0x40FF0000;
        }

        return 0;
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity entity)
    {
        return texture;
    }
}
