package tombenpotter.emt.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelCpecialArmor extends ModelBiped
{
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    
    public ModelRenderer rightWing;
    public ModelRenderer leftWing;
    public ModelRenderer center;
    
    public ModelRenderer jetpack;
    
    public boolean isJumping;
    
    public int type = 0;//0 - nothing, 1 - jetpack, 2 - wings
    public int heldItemLeft;
    public int heldItemRight;
    public boolean isSneak;
    public boolean aimedBow;
    private static final String __OBFID = "CL_00000840";

    public ModelCpecialArmor()
    {
        this(0.0F, 0);
    }

    public ModelCpecialArmor(float p_i1148_1_, int type)
    {
        this(p_i1148_1_, 0.0F, 64, 32, type);
    }

    public ModelCpecialArmor(float p_i1149_1_, float p_i1149_2_, int p_i1149_3_, int p_i1149_4_, int type)
    {
 
        this.type = type;
        switch(this.type){
        	case 1 : 
                this.textureWidth = 128;
                this.textureHeight = 96;
        		this.jetpack = new ModelRenderer(this, 16, 69);
        		this.jetpack.setTextureSize(64, 48);
                this.jetpack.addBox(-4.0F, -12.0F, -2.0F, 8, 12 * 2, 4, p_i1149_1_ * 2);
                this.jetpack.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
                break;
            
        	case 2 :
                this.textureWidth = 64;
                this.textureHeight = 64;
        		rightWing = new ModelRenderer(this, 24, 0 + 32);
                rightWing.addBox(-11F, -1F, 0F, 11, 13, 1);
                rightWing.setRotationPoint(-2F, 0F, 3F);
                rightWing.setTextureSize(64, 32);
                rightWing.mirror = false;
                setRotation(rightWing, 0F, 0.1570796F, 0F);

                leftWing = new ModelRenderer(this, 0, 0 + 32);
                leftWing.addBox(0F, -1F, 0F, 11, 13, 1);
                leftWing.setRotationPoint(2F, 0F, 3F);
                leftWing.setTextureSize(64, 32);
                leftWing.mirror = true;
                setRotation(leftWing, 0F, -0.1570796F, 0F);

                center = new ModelRenderer(this, 0, 14 + 32);
                center.addBox(0F, 0F, 1F, 8, 14, 1);
                center.setRotationPoint(-4F, 0F, 3F);
                center.setTextureSize(64, 32);
                center.mirror = true;
                setRotation(center, 0F, 0F, 0F);
                break;
        }
        
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1149_1_);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i1149_1_);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + p_i1149_2_, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + p_i1149_2_, 0.0F);
    }
    
    private void setRotation (ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

        this.bipedBody.render(p_78088_7_);
        this.bipedRightArm.render(p_78088_7_);
        this.bipedLeftArm.render(p_78088_7_);
        switch(this.type){
        	case 1 :
        		this.jetpack.render(p_78088_7_);
        		break;
        	case 2 :
        		this.center.render(p_78088_7_);
        		this.leftWing.render(p_78088_7_);
        		this.rightWing.render(p_78088_7_);
        }
        
    }

    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity)
    {
        this.bipedHead.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.bipedHead.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.bipedRightArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 2.0F * p_78087_2_ * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 2.0F * p_78087_2_ * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;

        if (entity.isRiding())
        {
            this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
        }
        
        if(isJumping){
        	this.rightWing.rotateAngleY = 0.5f;
    		this.leftWing.rotateAngleY = -0.5f;
        }

        if (this.heldItemLeft != 0)
        {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
        }

        if (this.heldItemRight != 0)
        {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedLeftArm.rotateAngleY = 0.0F;
        float f6;
        float f7;

        if (this.onGround > -9990.0F)
        {
            f6 = this.onGround;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
        }

        if (entity.isSneaking())
        {
            this.bipedBody.rotateAngleX = 0.5F;
            switch(this.type){
            	case 1 :
            		this.jetpack.rotateAngleX = 0.5F;
            		break;
            	case 2 :
            		this.center.offsetY -= 0.1;
            		this.rightWing.offsetY -= 0.1;
            		this.leftWing.offsetY -= 0.1;
            		this.center.rotateAngleX = 0.5F;
            		this.rightWing.rotateAngleX = 0.5F;
            		this.leftWing.rotateAngleX = 0.5F;
            		break;
            }
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
            this.bipedHead.rotationPointY = 1.0F;
        }
        else
        {
            this.bipedBody.rotateAngleX = 0.0F;
            this.bipedHead.rotationPointY = 0.0F;
        }

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;

        if (this.aimedBow)
        {
            f6 = 0.0F;
            f7 = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        }
    }
}