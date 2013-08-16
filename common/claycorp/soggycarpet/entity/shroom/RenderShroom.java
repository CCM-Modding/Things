package claycorp.soggycarpet.entity.shroom;

import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


public class RenderShroom extends RenderSlime {
	private static final ModelShroom model = new ModelShroom();
    private static final ResourceLocation texture = new ResourceLocation("soggycarpet", "textures/entity/shroom.png");

	public RenderShroom()
	{
		super(model, model, 0.25F);
	}

	protected ResourceLocation func_110832_a(EntityShroom shroom)
	{
		return texture;
	}

	protected ResourceLocation func_110775_a(Entity shroom)
	{
		return this.func_110832_a((EntityShroom)shroom);
	}

}
