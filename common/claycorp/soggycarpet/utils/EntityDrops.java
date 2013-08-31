package claycorp.soggycarpet.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class EntityDrops {
    /**
     * All of the registered Drop Handlers
     */
    private static final List<EntityDropHandler> drops = new ArrayList<EntityDropHandler>();

    /**
     * Registers a EntityDrop
     */
    public static void registerDrop(final EntityDropHandler drop) {
        drops.add(drop);
    }

    /**
     * @param modID
     *            ID of the mod adding this drop
     * @param item
     *            The Item to drop
     * @param dropRate
     *            the Rate at which to drop it
     * @param entitys
     *            the entity's that are allowed to drop it
     */
    public static EntityDropHandler registerDrop(final String modID, final ItemStack item, final float dropRate, final int minValue, final int maxValue, final Class<? extends EntityLivingBase> entity) {
        final EntityDropHandler tmp = new EntityDropHandler(modID, item, dropRate, minValue, maxValue, entity);
        registerDrop(tmp);
        return tmp;
    }

    /**
     * @param modID
     *            ID of the mod adding this drop
     * @param item
     *            The Item to drop
     * @param dropRate
     *            the Rate at which to drop it
     * @param entitys
     *            the entity's that are allowed to drop it
     */
    public static EntityDropHandler registerDrop(final String modID, final ItemStack item, final double dropRate, final int minValue, final int maxValue, final Class<? extends EntityLivingBase> entity) {
        return registerDrop(modID, item, (float) dropRate, minValue, maxValue, entity);
    }

    /**
     * @param entity
     *            The entity that is dropping the Item
     * @return true if and ONLY if there is a DropHandler registered to that
     *         entity
     */
    public static boolean isEntityRegistered(final EntityLivingBase entity) {
        boolean registered = false;
        for (final EntityDropHandler drop : drops) {
            if (drop.shouldDrop(entity.getClass())) {
                registered = true;
                break;
            }
        }
        return registered;
    }

    /**
     * @param entity
     *            The entity that is dropping the Item
     */
    public static void dropItem(final EntityLivingBase entity) {
        if (EntityDrops.isEntityRegistered(entity)) {
            for (final EntityDropHandler drop : drops) {
                if (drop.shouldDrop(entity.getClass())) {
                    drop.dropItem(entity);
                }
            }
        }
    }

    /**
     * @return a copy of the drops
     */
    public static List<EntityDropHandler> getDrops() {
        return new ArrayList<EntityDropHandler>(drops);
    }
}
