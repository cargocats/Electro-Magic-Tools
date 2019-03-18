package emt.tile.solar;

import emt.init.EMTBlocks;
import emt.util.EMTConfigHandler;
import gregtech.GT_Mod;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;

import java.util.LinkedHashMap;

import static thaumcraft.api.aspects.Aspect.*;

//Solar Factory
public enum Solars {
    //compability to older versions
    Compressed(EMTConfigHandler.compressedSolarOutput, null, 0, 0),
    Double_Compressed(EMTConfigHandler.doubleCompressedSolarOutput, null, 0, 1),
    Triple_Compressed(EMTConfigHandler.tripleCompressedSolarOutput, null, 0, 2),
    Compressed_Aqua(EMTConfigHandler.compressedSolarOutput, WATER, 0, 3),
    Double_Compressed_Aqua(EMTConfigHandler.doubleCompressedSolarOutput, WATER, 0, 4),
    Triple_Compressed_Aqua(EMTConfigHandler.tripleCompressedSolarOutput, WATER, 0, 5),
    Compressed_Perditio(EMTConfigHandler.compressedSolarOutput, ENTROPY, 0, 6),
    Double_Compressed_Perditio(EMTConfigHandler.doubleCompressedSolarOutput, ENTROPY, 0, 7),
    Triple_Compressed_Perditio(EMTConfigHandler.tripleCompressedSolarOutput, ENTROPY, 0, 8),
    Compressed_Ordo(EMTConfigHandler.compressedSolarOutput, ORDER, 0, 9),
    Double_Compressed_Ordo(EMTConfigHandler.doubleCompressedSolarOutput, ORDER, 0, 10),
    Triple_Compressed_Ordo(EMTConfigHandler.tripleCompressedSolarOutput, ORDER, 0, 11),
    Compressed_Ignis(EMTConfigHandler.compressedSolarOutput, FIRE, 0, 12),
    Double_Compressed_Ignis(EMTConfigHandler.doubleCompressedSolarOutput, FIRE, 0, 13),
    Triple_Compressed_Ignis(EMTConfigHandler.tripleCompressedSolarOutput, FIRE, 0, 14),
    Compressed_Aer(EMTConfigHandler.compressedSolarOutput, AIR, 0, 15),
    Double_Compressed_Aer(EMTConfigHandler.doubleCompressedSolarOutput, AIR, 1, 0),
    Triple_Compressed_Aer(EMTConfigHandler.tripleCompressedSolarOutput, AIR, 1, 1),
    Compressed_Terra(EMTConfigHandler.compressedSolarOutput, EARTH, 1, 2),
    Double_Compressed_Terra(EMTConfigHandler.doubleCompressedSolarOutput, EARTH, 1, 3),
    Triple_Compressed_Terra(EMTConfigHandler.tripleCompressedSolarOutput, EARTH, 1, 4),

    //New Solars
    Quadruple_Compressed(EMTConfigHandler.quadrupleCompressedSolarOutput, null, 2, 0),
    Quadruple_Compressed_Ordo(EMTConfigHandler.quadrupleCompressedSolarOutput, ORDER, 2, 1),
    Quadruple_Compressed_Perditio(EMTConfigHandler.quadrupleCompressedSolarOutput, ENTROPY, 2, 2),
    Quadruple_Compressed_Aer(EMTConfigHandler.quadrupleCompressedSolarOutput, AIR, 2, 3),
    Quadruple_Compressed_Terra(EMTConfigHandler.quadrupleCompressedSolarOutput, EARTH, 2, 4),
    Quadruple_Compressed_Aqua(EMTConfigHandler.quadrupleCompressedSolarOutput, WATER, 2, 5),
    Quadruple_Compressed_Ignis(EMTConfigHandler.quadrupleCompressedSolarOutput, FIRE, 2, 6),
    Quintuple_Compressed(EMTConfigHandler.quintupleCompressedSolarOutput, null, 2, 7),
    Quintuple_Compressed_Ordo(EMTConfigHandler.quintupleCompressedSolarOutput, ORDER, 2, 8),
    Quintuple_Compressed_Perditio(EMTConfigHandler.quintupleCompressedSolarOutput, ENTROPY, 2, 9),
    Quintuple_Compressed_Aer(EMTConfigHandler.quintupleCompressedSolarOutput, AIR, 2, 10),
    Quintuple_Compressed_Terra(EMTConfigHandler.quintupleCompressedSolarOutput, EARTH, 2, 11),
    Quintuple_Compressed_Aqua(EMTConfigHandler.quintupleCompressedSolarOutput, WATER, 2, 12),
    Quintuple_Compressed_Ignis(EMTConfigHandler.quintupleCompressedSolarOutput, FIRE, 2, 13),
    Sextuple_Compressed(EMTConfigHandler.sextupleCompressedSolarOutput, null, 2, 14),
    Sextuple_Compressed_Ordo(EMTConfigHandler.sextupleCompressedSolarOutput, ORDER, 2, 15),
    Sextuple_Compressed_Perditio(EMTConfigHandler.sextupleCompressedSolarOutput, ENTROPY, 3, 0),
    Sextuple_Compressed_Aer(EMTConfigHandler.sextupleCompressedSolarOutput, AIR, 3, 1),
    Sextuple_Compressed_Terra(EMTConfigHandler.sextupleCompressedSolarOutput, EARTH, 3, 2),
    Sextuple_Compressed_Aqua(EMTConfigHandler.sextupleCompressedSolarOutput, WATER, 3, 3),
    Sextuple_Compressed_Ignis(EMTConfigHandler.sextupleCompressedSolarOutput, FIRE, 3, 4),
    Septuple_Compressed(EMTConfigHandler.septupleCompressedSolarOutput, null, 3, 5),
    Septuple_Compressed_Ordo(EMTConfigHandler.septupleCompressedSolarOutput, ORDER, 3, 6),
    Septuple_Compressed_Perditio(EMTConfigHandler.septupleCompressedSolarOutput, ENTROPY, 3, 7),
    Septuple_Compressed_Aer(EMTConfigHandler.septupleCompressedSolarOutput, AIR, 3, 8),
    Septuple_Compressed_Terra(EMTConfigHandler.septupleCompressedSolarOutput, EARTH, 3, 9),
    Septuple_Compressed_Aqua(EMTConfigHandler.septupleCompressedSolarOutput, WATER, 3, 10),
    Septuple_Compressed_Ignis(EMTConfigHandler.septupleCompressedSolarOutput, FIRE, 3, 11),
    Octuple_Compressed(EMTConfigHandler.octupleCompressedSolarOutput, null, 3, 12),
    Octuple_Compressed_Ordo(EMTConfigHandler.octupleCompressedSolarOutput, ORDER, 3, 13),
    Octuple_Compressed_Perditio(EMTConfigHandler.octupleCompressedSolarOutput, ENTROPY, 3, 14),
    Octuple_Compressed_Aer(EMTConfigHandler.octupleCompressedSolarOutput, AIR, 3, 15),
    Octuple_Compressed_Terra(EMTConfigHandler.octupleCompressedSolarOutput, EARTH, 4, 0),
    Octuple_Compressed_Aqua(EMTConfigHandler.octupleCompressedSolarOutput, WATER, 4, 1),
    Octuple_Compressed_Ignis(EMTConfigHandler.octupleCompressedSolarOutput, FIRE, 4, 2),
    ;

    private final static LinkedHashMap<Long, Solars> cachemap = new LinkedHashMap<>();
    int instance, meta;
    Aspect aspect;
    String guiname;
    private double output;

    Solars(double output, Aspect aspect, int instance, int meta) {
        this.aspect = aspect;
        this.output = output;
        this.guiname = this.name().replaceAll("_", " ") + " Solar";
        this.instance = instance;
        this.meta = meta;
    }

    private static Solars getBaseSolar(double output){
        for (Solars s : Solars.values()){
            if (s.output == output && s.aspect == null)
                return s;
        }
        return null;
    }

    public static void registerReverseRecipes(){
        for (Solars s : Solars.values()){
            if (s.aspect != null){
                //ItemStack aInput, FluidStack aBathingFluid, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, int[] aChances, int aDuration, int aEUt
                GT_Values.RA.addChemicalBathRecipe(new ItemStack(EMTBlocks.solars[s.instance],1,s.meta), Materials.Chlorine.getGas(1000),new ItemStack(EMTBlocks.solars[getBaseSolar(s.output).instance],1,getBaseSolar(s.output).meta),null,null,null,30,30);
            }
        }
    }

    public static void populateCache() {
        for (Solars s : Solars.values()) {
            cachemap.put((long) (s.instance << 4 | s.meta), s);
        }
    }

    public static int getCountOfInstances() {
        int ret = 0;
        for (Solars s : Solars.values()) {
            ret = Math.max(s.instance, ret);
        }
        return ret + 1;
    }

    public static int getCountOfMetas(int instance) {
        int ret = 0;
        for (Solars s : Solars.values()) {
            if (s.instance == instance) {
                ret = Math.max(s.meta, ret);
            }
        }
        return ret + 1;
    }

    public static TileEntitySolarBase getTileEntitySolarBase(int instance, int meta) {
        Solars cached = cachemap.get((long) (instance << 4 | meta));
        if (cached != null)
            return new TileEntitySolarBase(cached.output, cached.aspect, cached.guiname, instance, meta);
        for (Solars s : Solars.values()) {
            if (s.instance == instance && s.meta == meta)
                return new TileEntitySolarBase(s.output, s.aspect, s.guiname, instance, meta);
        }
        return null;
    }

    public static String getNameFromSolar(int instance, int meta) {
        Solars cached = cachemap.get((long) (instance << 4 | meta));
        if (cached != null)
            return cached.guiname;
        for (Solars s : Solars.values()) {
            if (s.instance == instance && s.meta == meta)
                return s.guiname;
        }
        return "";
    }
}