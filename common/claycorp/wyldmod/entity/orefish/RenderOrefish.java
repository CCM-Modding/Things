package claycorp.wyldmod.entity.orefish;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import claycorp.wyldmod.entity.orefish.EntityOrefish;
import claycorp.wyldmod.entity.orefish.ModelOrefish;

public class RenderOrefish extends RenderLiving {
	private static final ModelOrefish model = new ModelOrefish();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/orefish.png");

	public RenderOrefish()
	{
		super(model, 0.5F);
	}

	protected ResourceLocation func_110832_a(EntityOrefish par1EntityCow)
	{
		return texture;
	}

	protected ResourceLocation func_110775_a(Entity par1Entity)
	{
		return this.func_110832_a((EntityOrefish)par1Entity);
	}
}