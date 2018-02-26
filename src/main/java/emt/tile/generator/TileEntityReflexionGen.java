package emt.tile.generator;

import emt.util.EMTEssentiasOutputs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.aspects.Aspect;

public class TileEntityReflexionGen
{
  public static List L = new ArrayList(EMTEssentiasOutputs.outputs.keySet());
  public static Class<? extends TileEntity>[] GensS = new Class[L.size()];
  public static TileEntityBaseGenerator[] Gens = new TileEntityBaseGenerator[L.size()];
  
  public TileEntityReflexionGen()
  {
    for (int i = 0; i < L.size(); i++)
    {
      GensS[i] = TileEntityBaseGenerator.class;
      Gens[i] = new TileEntityBaseGenerator((Aspect)Aspect.aspects.get(L.get(i)));
    }
  }
}
