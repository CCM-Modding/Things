package claycorp.soggycarpet.utils.proxy;

import claycorp.soggycarpet.entity.bear.EntityBear;
import claycorp.soggycarpet.entity.bear.RenderBear;
import claycorp.soggycarpet.entity.bunny.EntityBunny;
import claycorp.soggycarpet.entity.bunny.RenderBunny;
import claycorp.soggycarpet.entity.shroom.EntityShroom;
import claycorp.soggycarpet.entity.shroom.RenderShroom;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void initSounds() {

    }

    @Override
    public void initRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, new RenderBear());
        RenderingRegistry.registerEntityRenderingHandler(EntityBunny.class, new RenderBunny());
        RenderingRegistry.registerEntityRenderingHandler(EntityShroom.class, new RenderShroom());
    }
}
