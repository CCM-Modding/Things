package claycorp.soggycarpet.utils;

import claycorp.soggycarpet.entity.EntityBear;
import claycorp.soggycarpet.entity.RenderBear;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void initSounds()
    {

    }

    @Override
    public void initRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, new RenderBear());
    }
}
