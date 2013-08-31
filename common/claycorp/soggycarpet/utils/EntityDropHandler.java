package claycorp.soggycarpet.utils;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class EntityDropHandler {

    /**
     * Name of the mod registering this coin
     */
    private final String modID;

    /**
     * Item to drop
     */
    private final ItemStack item;
    /**
     * The drop rate of it
     */
    private final float dropRate;
    /**
     * The Maximum to drop
     */
    private final int maxValue;
    /**
     * The Minimum to drop
     */
    private final int minValue;
    /**
     * The entity that should drop it
     */
    private final Class<? extends EntityLivingBase> entity;

    /**
     * @param modID
     *            ID of the mod adding this drop
     * @param item
     *            The Item to drop
     * @param dropRate
     *            the Rate at which to drop it
     * @param minValue
     *            The Minimum to drop
     * @param maxValue
     *            The Maximum to drop
     * @param entitys
     *            the entity's that are allowed to drop it
     */
    public EntityDropHandler(final String modID, final ItemStack item, final float dropRate, final int minValue, final int maxValue, final Class<? extends EntityLivingBase> entity) {
        this.modID = modID;
        this.item = item;
        this.dropRate = dropRate;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.entity = entity;
    }

    /**
     * @return The ID of the mod that registered this coin
     */
    public String getModID() {
        return this.modID;
    }

    /**
     * @return The item to drop
     */
    public ItemStack getDrop() {
        return this.item;
    }

    /**
     * @return The drop rate
     */
    public float getDropRate() {
        return this.dropRate;
    }

    /**
     * @return The entity that should drop it
     */
    public Class<? extends EntityLivingBase> getEntity() {
        return this.entity;
    }

    /**
     * @param entity
     *            the entity class to check
     * @return true if and ONLY if the entity class is the same as the class
     *         that this Handler "uses"
     */
    public boolean shouldDrop(final Class<? extends EntityLivingBase> entity) {
        return entity.equals(this.entity);
    }

    private static final Random rand = new Random();

    /**
     * @param entity
     *            Drops the Item
     */
    public void dropItem(final EntityLivingBase entity) {
        if (this.shouldDrop(entity.getClass())) {
            final double chance = rand.nextDouble();

            final int amount = this.clamp(rand.nextInt(this.maxValue), this.minValue, this.maxValue);

            if (chance < this.dropRate) {
                entity.entityDropItem(new ItemStack(this.item.itemID, amount, this.item.getItemDamage()), 0.0F);
            }
        }
    }

    int clamp(int val, final int min, final int max) {
        if (val > max) {
            val = max;
        } else if (val < min) {
            val = min;
        }
        return val;
    }
}
