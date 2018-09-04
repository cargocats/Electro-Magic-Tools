package emt.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.EMT;
import emt.tile.generator.TileEntityBaseGenerator;
import emt.tile.generator.TileEntityReflexionGen;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockReflectedGen
  extends BlockBaseContainer
{
  private int subtypes;
  private static int overalltypes = TileEntityReflexionGen.L.size();
  public static short Set;
  private int[] posInList;
  
  public BlockReflectedGen(String name, short Set)
  {
    super(name, Material.iron, soundTypeMetal, 16, 4.0F);
    this.Set = Set;
    this.subtypes = 16;
    
    if ((Set*16) > overalltypes)
    	subtypes= ((Set-1)*16)+overalltypes-((Set-1)*16);
    
    posInList= new int[subtypes];
    if ((Set*16) < overalltypes)
    for (int i=0; i<subtypes;++i) {
    	posInList[i]=(Set*16)+i;
    }
    else if ((Set*16) > overalltypes)
    	for (int i=0; i<subtypes;++i) {
        	posInList[i]=((Set-1)*16)+overalltypes-((Set-1)*16)+i;
        }
  }
  
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister ir)
  {
    super.registerBlockIcons(ir);
    this.blockIcon = ir.registerIcon("emt:machines/top");
    for (int meta = 0; meta < this.subtypes; meta++)
    {
      this.iconSets[meta].top = ir.registerIcon("emt:essentiagenerator/top");
      this.iconSets[meta].bottom = ir.registerIcon("emt:essentiagenerator/top");
      this.iconSets[meta].side = ir.registerIcon("emt:essentiagenerator/side");
    }
    for (int i = 0; i < this.subtypes; i++) {
      this.iconSets[i].frontOff = ir.registerIcon("emt:essentiagenerator/side");
    }
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item id, CreativeTabs tab, List list)
  {
    for (int i = 0; i < this.subtypes; i++) {
      list.add(new ItemStack(id, 1, i));
    }
  }
  
  public TileEntity createTileEntity(World world, int meta)
  {
    return TileEntityReflexionGen.Gens[posInList[meta]];
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random)
  {
    TileEntityBaseGenerator te = (TileEntityBaseGenerator)world.getTileEntity(x, y, z);
    if (te.isActive)
    {
      int color = te.aspect.getColor();
      float r = (color >> 16 & 0xFF) / 255.0F;
      float g = (color >> 8 & 0xFF) / 255.0F;
      float b = (color & 0xFF) / 255.0F;
      
      EntitySmokeFX fx = new EntitySmokeFX(world, x + 0.5F, y + 1.1D, z + 0.5F, 0.0D, 0.1D, 0.0D, 3.0F);
      fx.setRBGColorF(r, g, b);
      Minecraft.getMinecraft().effectRenderer.addEffect(fx);
    }
  }
  
  public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int s, float f1, float f2, float f3)
  {
    if (player.isSneaking()) {
      return false;
    }
    if (world.isRemote) {
      return true;
    }
    TileEntity tileentity = world.getTileEntity(i, j, k);
    if (tileentity != null) {
      player.openGui(EMT.instance, 1, world, i, j, k);
    }
    return true;
  }
}
