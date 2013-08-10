package claycorp.soggycarpet.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBear extends Render{
	private ModelBear model;
	
	public RenderBear() {
		model = new ModelBear();
		shadowSize = 0.5F;
	}

	
	private static final ResourceLocation texture = new ResourceLocation("resources", "claycorp/resources/assets/soggycarpet/textures/entity/bear.png");
	
	public void renderBear(EntityBear bear, double x, double y, double z, float yaw, float partialTickTime) {
		System.out.println("DEBUG!!!!!! 1");
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(-1, -1, 1);
		System.out.println("DEBUG!!!!!! 2");
		func_110777_b(bear);
		
		model.render(bear, 0, 0, 0, 0, 0, 0.0625F);
		GL11.glPopMatrix();
		System.out.println("DEBUG!!!!!! 3");
	}
	


	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return texture;
	}
	
	
	
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
		renderBear((EntityBear)entity, x, y, z, yaw, partialTickTime);
	}
}

