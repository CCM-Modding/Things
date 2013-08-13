package claycorp.soggycarpet.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


public class RenderBunny extends RenderLiving {
	private static final ModelBunny model = new ModelBunny();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/bunny.png");

	public RenderBunny()
	{
		super(model, 0.25F);
	}

	protected ResourceLocation func_110832_a(EntityBunny par1EntityCow)
	{
		return texture;
	}

	protected ResourceLocation func_110775_a(Entity par1Entity)
	{
		return this.func_110832_a((EntityBunny)par1Entity);
	}

}
