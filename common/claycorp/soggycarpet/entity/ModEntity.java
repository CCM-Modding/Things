package claycorp.soggycarpet.entity;

import claycorp.soggycarpet.entity.bear.EntityBear;
import claycorp.soggycarpet.entity.bunny.EntityBunny;
import claycorp.soggycarpet.entity.enderghast.EntityEnderGhast;
import claycorp.soggycarpet.entity.orefish.EntityOrefish;
import claycorp.soggycarpet.entity.shroom.EntityShroom;
import claycorp.soggycarpet.entity.unicorncow.EntityUnicornCow;
import claycorp.soggycarpet.entity.wizard.EntityWizard;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntity {
    private static int entityID = 0;

    public static void init() {
        registerEntity(EntityBunny.class, "EntityRabbit", 0xff86d3, 0x571b60);
        registerEntity(EntityBear.class, "EntityBear", 0xff86d3, 0x571b60);
        registerEntity(EntityShroom.class, "EntityShroom", 0xe51a1a, 0x88857f);
        registerEntity(EntityUnicornCow.class, "EntityUnicornCow", 0xff86d3, 0x571b60);
        registerEntity(EntityEnderGhast.class, "EntityEnderGhast", 0xff86d3, 0x571b60);
        registerEntity(EntityWizard.class, "EntityWyldWizard", 0xff86d3, 0x571b60);
        registerEntity(EntityOrefish.class, "EntityOrefish", 0xff86d3, 0x571b60);
    }

    public static void addSpawns() {
        // Shrooms can spawn anywhere that small mushrooms exist.
        for (BiomeGenBase biome : BiomeGenBase.biomeList) {
            if (biome != null) {
                EntityRegistry.addSpawn(EntityShroom.class, 10, 1, 1,
                                        EnumCreatureType.monster, biome);
            }
        }
    }

    /**
     * registers an entity
     * 
     * @param entity
     *            The entity
     * @param entityName
     *            Entity Name
     * @param fgColor
     *            Primary foreground egg color
     * @param bgColor
     *            Secondary background egg color
     */
    static void registerEntity(final Class<? extends Entity> entityClass, final String entityName, final int fgColor, final int bgColor) {
        final int id = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);

        EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bgColor, fgColor));
    }
}