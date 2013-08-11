package claycorp.soggycarpet.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntity
{
    private static int entityID = 0;

    public static void init()
    {
        registerEntity(EntityBunny.class, "EntityRabbit", 0xff86d3, 0x571b60);
        registerEntity(EntityBear.class, "EntityBear", 0xff86d3, 0x571b60);
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
    static void registerEntity(final Class<? extends Entity> entityClass,
                               final String entityName,
                               final int fgColor,
                               final int bgColor)
    {
        final int id = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);

        EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bgColor, fgColor));
    }
}