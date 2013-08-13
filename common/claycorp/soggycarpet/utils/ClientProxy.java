package claycorp.soggycarpet.utils;

import claycorp.soggycarpet.entity.EntityBear;
import claycorp.soggycarpet.entity.EntityBunny;
import claycorp.soggycarpet.entity.RenderBear;
import claycorp.soggycarpet.entity.RenderBunny;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void initSounds() {

    }

    @Override
    public void initRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, new RenderBear());
        RenderingRegistry.registerEntityRenderingHandler(EntityBunny.class, new RenderBunny());
    }
}
