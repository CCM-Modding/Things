package claycorp.soggycarpet.entity.shroom;

import java.util.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class SpreadTarget extends EntityAIBase
{
    public EntityLiving me;

    public SpreadTarget(EntityLiving entity)
    {
        this.me = entity;
        this.setMutexBits(0);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @SuppressWarnings("unchecked")
    public boolean shouldExecute()
    {
        return me.getAttackTarget() != null;
    }


    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        EntityLivingBase target = me.getAttackTarget();

        // Nothing to do, we just block the attack task.
        List list = me.worldObj.getEntitiesWithinAABB(me.getClass(),
                     me.boundingBox.expand(5.0D, 5.0D, 5.0D));

        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            EntityLiving friend = (EntityLiving)iterator.next();

            if (me != friend && friend.getAttackTarget() == null && !friend.func_142014_c(target))
            {
                friend.setAttackTarget(target);
            }
        }
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        // Nothing to do, this is a one-shot task.
    }
}
