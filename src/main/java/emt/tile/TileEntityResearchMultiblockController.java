package emt.tile;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

import java.util.ArrayList;

public class TileEntityResearchMultiblockController extends GT_MetaTileEntity_MultiBlockBase {
    private final int MAX_LENGTH = 13;

    private class Coordinates {
        public int x;
        public int y;
        public int z;
    }

    public TileEntityResearchMultiblockController(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public TileEntityResearchMultiblockController(String aName) {
        super(aName);
    }

    //TODO deplete nodes, stop if no nodes are available
    @Override
    public boolean onRunningTick(ItemStack aStack) {
        return super.onRunningTick(aStack);
    }

    @Override
    public boolean isCorrectMachinePart(ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean checkRecipe(ItemStack itemStack) {
        ArrayList<ItemStack> tInputList = this.getStoredInputs();

        for (ItemStack stack : tInputList) {
            if (GT_Utility.isStackValid(stack) && stack.stackSize > 0) {
                if (stack.getItem() == ConfigItems.itemResearchNotes) {
                    this.mEfficiency = 10000 - (this.getIdealStatus() - this.getRepairStatus()) * 1000;
                    this.mEfficiencyIncrease = 10000;
                    this.calculateOverclockedNessMulti(120, 1200, 1, this.getMaxInputVoltage());
                    if (this.mMaxProgresstime == 2147483646 && this.mEUt == 2147483646) {
                        return false;
                    }
                    if (this.mEUt > 0) {
                        this.mEUt = -this.mEUt;
                    }

                    //Create a completed version of the note to output
                    this.mOutputItems = new ItemStack[]{GT_Utility.copyAmount(1L, stack)};
                    this.mOutputItems[0].stackTagCompound.setBoolean("complete", true);
                    this.mOutputItems[0].setItemDamage(64);
                    stack.stackSize -= 1;
                    this.sendLoopStart((byte)20);
                    this.updateSlots();
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        int xDir = ForgeDirection.getOrientation(iGregTechTileEntity.getBackFacing()).offsetX;
        int zDir = ForgeDirection.getOrientation(iGregTechTileEntity.getBackFacing()).offsetZ;

        int length = findLength(iGregTechTileEntity, xDir, zDir);
        if (length < 2)
            return false;

        //Do structure check
        boolean eastWest = xDir != 0;

        for (int i = 0; i < length; i++) {
            for (int j = -1; j < 2; j++) {
                for (int h = -1; h < 2; h++) {
                    int forwardsOffset = eastWest ? i * xDir : i * zDir;
                    int sidewaysOffset = j;
                    int upwardsOffset = h;

                    if (forwardsOffset == 0 && sidewaysOffset == 0 && upwardsOffset == 0) //Controller
                        continue;

                    Coordinates offsetCoords = calcOffsetCoords(forwardsOffset, sidewaysOffset, upwardsOffset, eastWest);

                    IGregTechTileEntity tTileEntity = iGregTechTileEntity.getIGregTechTileEntityOffset(offsetCoords.x, offsetCoords.y, offsetCoords.z);
                    Block tBlock = iGregTechTileEntity.getBlockOffset(offsetCoords.x, offsetCoords.y, offsetCoords.z);
                    byte tMeta = iGregTechTileEntity.getMetaIDOffset(offsetCoords.x, offsetCoords.y, offsetCoords.z);

                    if (h == 0) {
                        if (sidewaysOffset == 0) { //Check for air or node TODO maybe remove, no need to require specific blocks here
                            //if (!GT_Utility.isBlockAir(getBaseMetaTileEntity().getWorld(), offsetCoords.x, offsetCoords.y, offsetCoords.z))
                        } else { //Check for warded glass
                            //TODO check for new casing once added
                            if (tBlock != ConfigBlocks.blockCosmeticOpaque || tMeta != 2)
                                return false;
                        }
                    } else { //Check for machine casings and buses etc.
                        if (sidewaysOffset != 0 || forwardsOffset == 0 || Math.abs(forwardsOffset) == length - 1) { //Check ring shapes on top and bottom
                            //TODO change casing index to correct for this multi
                            if (!this.addMaintenanceToMachineList(tTileEntity, 16) && !this.addInputToMachineList(tTileEntity, 16) && !this.addOutputToMachineList(tTileEntity, 16) && !this.addEnergyInputToMachineList(tTileEntity, 16)) {
                                if ((tBlock != GregTech_API.sBlockCasings2 || tMeta != 0))
                                    return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    private int findLength(final IGregTechTileEntity iGregTechTileEntity, final int xDir, final int zDir) {
        for (int i = 1; i < MAX_LENGTH; i++) {
            Block tBlock = iGregTechTileEntity.getBlockOffset(xDir * i, 0, zDir * i);
            byte tMeta = iGregTechTileEntity.getMetaIDOffset(xDir * i, 0, zDir * i);
            if (tBlock == ConfigBlocks.blockCosmeticOpaque && tMeta == 2) {
                return i + 1;
            }
        }
        return -1;
    }

    private Coordinates calcOffsetCoords(final int forwardsOffset, final int sidewaysOffset, final int upwardsOffset, final boolean eastWest) {
        Coordinates coordinates = new Coordinates();
        if (eastWest) {
            coordinates.x = forwardsOffset;
            coordinates.z = sidewaysOffset;
        } else {
            coordinates.x = sidewaysOffset;
            coordinates.z = forwardsOffset;
        }
        coordinates.y = upwardsOffset;

        return coordinates;
    }
    
    @Override
    public int getMaxEfficiency(ItemStack itemStack) {
        return 10000;
    }

    @Override
    public int getPollutionPerTick(ItemStack itemStack) {
        return 0;
    }

    @Override
    public int getDamageToComponent(ItemStack itemStack) {
        return 0;
    }

    @Override
    public boolean explodesOnComponentBreak(ItemStack itemStack) {
        return false;
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new TileEntityResearchMultiblockController(this.mName);
    }

    //TODO finish tooltip using tooltip builder
    @Override
    public String[] getDescription() {
        return new String[]{
                "Controller block for the Thaumcraft Research Multiblock.",
                "Completes research notes using EU and Thaumcraft nodes",
        };
    }

    //TODO texture
    @Override
    public ITexture[] getTexture(IGregTechTileEntity iGregTechTileEntity, byte b, byte b1, byte b2, boolean b3, boolean b4) {
        return new ITexture[0];
    }
}
