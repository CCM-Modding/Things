package claycorp.wyldmod.entity.shroom;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class FeignInnocence extends EntityAIBase
{
    public EntityLiving me;

    public FeignInnocence(final EntityLiving entity)
    {
        me = entity;
        setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        List<?> list = me.worldObj.getEntitiesWithinAABB(EntityPlayer.class, me.boundingBox.expand(40.0D, 20.0D, 40.0D));

        Iterator<?> iterator = list.iterator();

        while (iterator.hasNext())
        {
            // Method shamelessly stolen from the "looking at an Enderman"
            // code. Returns true if the player is looking anywhere near
            // this mob.
            EntityPlayer player = (EntityPlayer) iterator.next();

            if (player.getHealth() <= 0.0F)
            {
                continue;
            }

            Vec3 playerLook = player.getLook(1.0F).normalize();
            Vec3 myDirection = me.worldObj.getWorldVec3Pool().getVecFromPool(me.posX - player.posX,
                    (me.boundingBox.minY + (me.height / 2.0F)) - (player.posY + player.getEyeHeight()), me.posZ - player.posZ);

            // No need for the distance, here.
            // double distance = myDirection.lengthVector();
            myDirection = myDirection.normalize();

            // Since both vectors are normalized (i.e. length 1), this is
            // cos(angle between vectors)
            // Reminder: cos(same)==1, cos(right angle)==0, cos(opposite)==-1;
            double dot = playerLook.dotProduct(myDirection);

            if ((dot > 0) && player.canEntityBeSeen(me))
            {
                return true;
            }
        }

        // The coast is clear! No need to feign innocence.
        return false;
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
        // Nothing to do, we just block the attack task.
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask()
    {
        // Nothing to do, we just block the attack task.
    }
}
