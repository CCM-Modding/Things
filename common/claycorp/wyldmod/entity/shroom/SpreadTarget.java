package claycorp.wyldmod.entity.shroom;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class SpreadTarget extends EntityAIBase
{
    public EntityLiving me;

    public SpreadTarget(final EntityLiving entity)
    {
        me = entity;
        setMutexBits(0);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        return me.getAttackTarget() != null;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        EntityLivingBase target = me.getAttackTarget();

        // Nothing to do, we just block the attack task.
        List<?> list = me.worldObj.getEntitiesWithinAABB(me.getClass(), me.boundingBox.expand(5.0D, 5.0D, 5.0D));

        Iterator<?> iterator = list.iterator();

        while (iterator.hasNext())
        {
            EntityLiving friend = (EntityLiving) iterator.next();

            if ((me != friend) && (friend.getAttackTarget() == null) && !friend.isOnSameTeam(target))
            {
                friend.setAttackTarget(target);
            }
        }
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask()
    {
        // Nothing to do, this is a one-shot task.
    }
}
