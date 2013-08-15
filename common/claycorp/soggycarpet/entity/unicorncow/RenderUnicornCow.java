package claycorp.soggycarpet.entity.unicorncow;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


public class RenderUnicornCow extends RenderLiving {
	private static final ModelUnicornCow model = new ModelUnicornCow();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/unicorncow.png");

	public RenderUnicornCow()
	{
		super(model, 0.5F);
	}

	protected ResourceLocation func_110832_a(EntityUnicornCow par1EntityCow)
	{
		return texture;
	}

	protected ResourceLocation func_110775_a(Entity par1Entity)
	{
		return this.func_110832_a((EntityUnicornCow)par1Entity);
	}
}
