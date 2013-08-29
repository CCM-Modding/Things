// Date: 8/28/2013 7:05:54 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package claycorp.soggycarpet.entity.orefish;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOrefish extends ModelBase
{
  //fields
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer Tale_Body;
    ModelRenderer Tail_Tip;
    ModelRenderer Right_Horn;
    ModelRenderer Left_Horn;
  
  public ModelOrefish()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Head = new ModelRenderer(this, 2, 17);
      Head.addBox(0F, 0F, 0F, 3, 3, 2);
      Head.setRotationPoint(-1F, 20F, -6F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 22);
      Body.addBox(0F, 0F, 0F, 5, 5, 5);
      Body.setRotationPoint(-2F, 19F, -4F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Tale_Body = new ModelRenderer(this, 20, 24);
      Tale_Body.addBox(0F, 0F, 0F, 3, 3, 5);
      Tale_Body.setRotationPoint(-1F, 21F, 1F);
      Tale_Body.setTextureSize(64, 32);
      Tale_Body.mirror = true;
      setRotation(Tale_Body, 0F, 0F, 0F);
      Tail_Tip = new ModelRenderer(this, 36, 29);
      Tail_Tip.addBox(0F, 0F, 0F, 1, 1, 2);
      Tail_Tip.setRotationPoint(0F, 23F, 6F);
      Tail_Tip.setTextureSize(64, 32);
      Tail_Tip.mirror = true;
      setRotation(Tail_Tip, 0F, 0F, 0F);
      Right_Horn = new ModelRenderer(this, 0, 20);
      Right_Horn.addBox(0F, 0F, 0F, 1, 2, 0);
      Right_Horn.setRotationPoint(-2F, 19F, -6F);
      Right_Horn.setTextureSize(64, 32);
      Right_Horn.mirror = true;
      setRotation(Right_Horn, 0F, 0F, -0.5235988F);
      Left_Horn = new ModelRenderer(this, 0, 20);
      Left_Horn.addBox(0F, 0F, 0F, 1, 2, 0);
      Left_Horn.setRotationPoint(3F, 19F, -6F);
      Left_Horn.setTextureSize(64, 32);
      Left_Horn.mirror = true;
      setRotation(Left_Horn, 0F, -3.141593F, -0.5235988F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Head.render(f5);
    Body.render(f5);
    Tale_Body.render(f5);
    Tail_Tip.render(f5);
    Right_Horn.render(f5);
    Left_Horn.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
