package claycorp.soggycarpet.entity.shroom;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


public class RenderShroom extends RenderLiving {
	private static final ModelShroom model = new ModelShroom();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/shroom.png");

	public RenderShroom()
	{
		super(model, 0.25F);
	}

	protected ResourceLocation func_110832_a(EntityShroom par1EntityCow)
	{
		return texture;
	}

	protected ResourceLocation func_110775_a(Entity par1Entity)
	{
		return this.func_110832_a((EntityShroom)par1Entity);
	}

}