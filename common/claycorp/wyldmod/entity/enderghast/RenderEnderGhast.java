package claycorp.wyldmod.entity.enderghast;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEnderGhast extends RenderLiving {
	private static final ModelEnderGhast model = new ModelEnderGhast();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/enderghast.png");

	public RenderEnderGhast()
	{
		super(model, 0.5F);
	}

	protected ResourceLocation func_110832_a(EntityEnderGhast par1EntityCow)
	{
		return texture;
	}

	protected ResourceLocation func_110775_a(Entity par1Entity)
	{
		return this.func_110832_a((EntityEnderGhast)par1Entity);
	}
}
