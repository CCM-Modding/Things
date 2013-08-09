package claycorp.soggycarpet.entity;

import claycorp.soggycarpet.SoggyCarpet;
import claycorp.soggycarpet.utils.Properties;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntity {
	
	private static EntityBear entitybear;

	public static void init() {
		entitybear = new EntityBear(Properties.totalbear);
		EntityRegistry.registerModEntity(EntityBear.class, "EntityBear", 15, SoggyCarpet.instance, 80, 3, true);
	}
}

