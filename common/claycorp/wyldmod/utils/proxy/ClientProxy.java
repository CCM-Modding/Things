package claycorp.wyldmod.utils.proxy;

import claycorp.wyldmod.entity.bear.EntityBear;
import claycorp.wyldmod.entity.bear.RenderBear;
import claycorp.wyldmod.entity.bunny.EntityBunny;
import claycorp.wyldmod.entity.bunny.RenderBunny;
import claycorp.wyldmod.entity.enderghast.EntityEnderGhast;
import claycorp.wyldmod.entity.enderghast.RenderEnderGhast;
import claycorp.wyldmod.entity.orefish.EntityOrefish;
import claycorp.wyldmod.entity.orefish.RenderOrefish;
import claycorp.wyldmod.entity.shroom.EntityShroom;
import claycorp.wyldmod.entity.shroom.RenderShroom;
import claycorp.wyldmod.entity.unicorncow.EntityUnicornCow;
import claycorp.wyldmod.entity.unicorncow.RenderUnicornCow;
import claycorp.wyldmod.entity.wizard.EntityWizard;
import claycorp.wyldmod.entity.wizard.RenderWizard;
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
