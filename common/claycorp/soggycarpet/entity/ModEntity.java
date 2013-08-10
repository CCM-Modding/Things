package claycorp.soggycarpet.entity;

import claycorp.soggycarpet.SoggyCarpet;
import claycorp.soggycarpet.utils.Properties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntity
{

    private static EntityBear entitybear;
    private static World      world;
    private static int        entityID = 0;

    public static void init()
    {
        System.out.println("DEBUG!!!!!!register");
        entitybear = new EntityBear(world);
        System.out.println("DEBUG!!!!!!register");
        registerEntity(EntityBear.class,
                       "EntityBear",
                       SoggyCarpet.instance,
                       Properties.beartracking,
                       3,
                       false,
                       0xff86d3,
                       0x571b60);
    }

    /**
     * registers an entity
     * 
     * @param entityClass
     *            Entity Class
     * @param entityName
     *            Entity Name
     * @param fgColor
     *            Primary foreground egg color
     * @param bgColor
     *            Secondary background egg color
     */
    static void registerEntity(final Class<? extends Entity> entityClass,
                               final String entityName,
                               final Object mod,
                               final int trackingRange,
                               final int updateFrequency,
                               final boolean sendsVelocityUpdates,
                               final int fgColor,
                               final int bgColor)
    {
        EntityRegistry.registerModEntity(entityClass,
                                         entityName,
                                         entityID++,
                                         mod,
                                         trackingRange,
                                         updateFrequency,
                                         sendsVelocityUpdates);

        final int id = EntityList.getEntityID(entitybear);

        EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bgColor, fgColor));
    }
}