package claycorp.soggycarpet.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBear extends Entity{

	public EntityBear(World world) {
		super(world);
	}
	public int getMaxSpawnedInChunk()
	{
		return 3;
	}
	public EntityBear(World world, double x, double y, double z)
	{
	this(world);
	posX = x;
	posY = y;
	posZ = z;
	}
	@Override
	protected void entityInit() {
		
	}
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		posX = nbttagcompound.getShort("X");
		posY = nbttagcompound.getShort("Y");
		posZ = nbttagcompound.getShort("Z");
	}
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setShort("X", (short)posX);
		nbttagcompound.setShort("Y", (short)posY);
		nbttagcompound.setShort("Z", (short)posZ);
		
	}
}
