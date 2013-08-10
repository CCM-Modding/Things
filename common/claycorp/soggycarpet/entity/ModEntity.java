package claycorp.soggycarpet.entity;

import net.minecraft.world.World;
import claycorp.soggycarpet.SoggyCarpet;
import claycorp.soggycarpet.utils.Properties;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntity {
	
	private static EntityBear entitybear;
	private static World world;

	public static void init() {
		System.out.println("DEBUG!!!!!!register");
		EntityBear bear = new EntityBear(world);
		System.out.println("DEBUG!!!!!!register");
		EntityRegistry.registerModEntity(EntityBear.class, "EntityBear", Properties.bearID, SoggyCarpet.instance, Properties.beartracking, 3, false);
	}
}

