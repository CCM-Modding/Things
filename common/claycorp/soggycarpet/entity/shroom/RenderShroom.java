package claycorp.soggycarpet.entity.shroom;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderShroom extends RenderLiving {
	private static final ModelShroom model = new ModelShroom();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/shroom.png");

	public RenderShroom()
	{
		super(model, 0.25F);
	}

	protected ResourceLocation func_110832_a(EntityShroom shroom)
	{
		return texture;
	}

	protected ResourceLocation func_110775_a(Entity shroom)
	{
		return this.func_110832_a((EntityShroom)shroom);
	}

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        EntityShroom shroom = (EntityShroom)par1EntityLivingBase;

        this.scaleShroom(shroom, par2);
    }

    /**
     * sets the scale for the shroom based on getSlimelikeSize in EntityShroom
     */
    protected void scaleShroom(EntityShroom shroom, float partialTicks) {
        float size = (float)shroom.getSlimelikeSize();
        float scale = (size * 0.3F);
        GL11.glScalef(scale, scale, scale);
    }

    /**
     * Returns an ARGB int color back. Args: entityLiving, lightBrightness, partialTickTime
     */
    @Override
    protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3)
    {
        EntityShroom shroom = (EntityShroom)par1EntityLivingBase;

        if (shroom.isAngry()) {
            return 0x40FF0000;
        }

        return 0;
    }
}
