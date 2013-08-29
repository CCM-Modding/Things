package claycorp.soggycarpet.utils.proxy;

import claycorp.soggycarpet.entity.bear.EntityBear;
import claycorp.soggycarpet.entity.bear.RenderBear;
import claycorp.soggycarpet.entity.bunny.EntityBunny;
import claycorp.soggycarpet.entity.bunny.RenderBunny;
import claycorp.soggycarpet.entity.enderghast.EntityEnderGhast;
import claycorp.soggycarpet.entity.enderghast.RenderEnderGhast;
import claycorp.soggycarpet.entity.orefish.EntityOrefish;
import claycorp.soggycarpet.entity.orefish.RenderOrefish;
import claycorp.soggycarpet.entity.shroom.EntityShroom;
import claycorp.soggycarpet.entity.shroom.RenderShroom;
import claycorp.soggycarpet.entity.unicorncow.EntityUnicornCow;
import claycorp.soggycarpet.entity.unicorncow.RenderUnicornCow;
import claycorp.soggycarpet.entity.wizard.EntityWizard;
import claycorp.soggycarpet.entity.wizard.RenderWizard;
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
        RenderingRegistry.registerEntityRenderingHandler(EntityUnicornCow.class, new RenderUnicornCow());
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderGhast.class, new RenderEnderGhast());
        RenderingRegistry.registerEntityRenderingHandler(EntityWizard.class, new RenderWizard());
        RenderingRegistry.registerEntityRenderingHandler(EntityOrefish.class, new RenderOrefish());
        
    }
}
