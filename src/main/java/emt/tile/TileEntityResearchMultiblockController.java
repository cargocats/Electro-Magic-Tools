package emt.tile;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ResearchNoteData;
import thaumcraft.common.tiles.TileNode;

import java.util.ArrayList;

import static gregtech.api.enums.Textures.BlockIcons.*;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_IMPLOSION_COMPRESSOR_GLOW;

public class TileEntityResearchMultiblockController extends GT_MetaTileEntity_MultiBlockBase {
    private final int MAX_LENGTH = 13;

    private int length;
    private int recipeAspectCost;
    private int aspectsAbsorbed;

    private class Coordinates {
        public Coordinates() {}
        public Coordinates(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
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

    @Override
    public boolean onRunningTick(ItemStack aStack) {
        float progressAmount = ((float) this.mProgresstime) / this.mMaxProgresstime;
        int requiredVis = (int)Math.ceil(progressAmount * recipeAspectCost - aspectsAbsorbed);

        //Loop through node spaces and drain them from front to back
        IGregTechTileEntity aBaseMetaTileEntity = this.getBaseMetaTileEntity();
        int xDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetX;
        int zDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetZ;
        int i = 1;
        while (i < this.length - 1 && requiredVis > 0) {
            Coordinates nodeCoordinates = new Coordinates(aBaseMetaTileEntity.getXCoord() + xDir * i, aBaseMetaTileEntity.getYCoord(), aBaseMetaTileEntity.getZCoord() + zDir * i);
            TileEntity tileEntity = aBaseMetaTileEntity.getWorld().getTileEntity(nodeCoordinates.x, nodeCoordinates.y, nodeCoordinates.z);
            if (tileEntity instanceof TileNode) {
                TileNode aNode = (TileNode)tileEntity;
                AspectList aspectsBase = aNode.getAspectsBase();

                for (Aspect aspect : aspectsBase.getAspects()) {
                    int aspectAmount = aspectsBase.getAmount(aspect);
                    int drainAmount = Math.min(requiredVis, aspectAmount);
                    aNode.setNodeVisBase(aspect, (short) (aspectAmount - drainAmount));
                    aNode.takeFromContainer(aspect, drainAmount);
                    requiredVis -= drainAmount;
                    aspectsAbsorbed += drainAmount;
                    if (requiredVis <= 0)
                        break;
                }

                if (aspectsBase.visSize() <= 0)
                    aBaseMetaTileEntity.getWorld().setBlockToAir(nodeCoordinates.x, nodeCoordinates.y, nodeCoordinates.z);

                aNode.markDirty();
                aBaseMetaTileEntity.getWorld().markBlockForUpdate(nodeCoordinates.x, nodeCoordinates.y, nodeCoordinates.z);
            }
            i++;
        }

        if (requiredVis > 0)
            this.criticalStopMachine();

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
                if (stack.getItem() == ConfigItems.itemResearchNotes && !stack.stackTagCompound.getBoolean("complete")) {
                    ResearchNoteData noteData = ResearchManager.getData(stack);
                    if (noteData == null)
                        continue;
                    ResearchItem researchItem = ResearchCategories.getResearch(noteData.key);
                    if (researchItem == null)
                        continue;

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
                    this.aspectsAbsorbed = 0;
                    this.recipeAspectCost = researchItem.tags.visSize();

                    this.sendLoopStart((byte) 20);
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
        int casingAmount = 0;
        this.length = findLength(iGregTechTileEntity, xDir, zDir);
        if (this.length < 2)
            return false;

        boolean eastWest = xDir != 0;

        for (int i = 0; i < this.length; i++) {
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
                        if (sidewaysOffset != 0) { //Check for warded glass
                            if (tBlock != ConfigBlocks.blockCosmeticOpaque || tMeta != 2)
                                return false;
                        }
                    } else { //Check for machine casings and buses etc.
                        if (sidewaysOffset != 0 || forwardsOffset == 0 || Math.abs(forwardsOffset) == this.length - 1) { //Check ring shapes on top and bottom
                            //TODO change casing index to correct for this multi
                            if (!this.addMaintenanceToMachineList(tTileEntity, 182) && !this.addInputToMachineList(tTileEntity, 182) && !this.addOutputToMachineList(tTileEntity, 182) && !this.addEnergyInputToMachineList(tTileEntity, 182)) {
                                if ((tBlock != GregTech_API.sBlockCasings8 || tMeta != 6))
                                    return false;
                                else
                                    casingAmount++;
                            }
                        }
                    }
                }
            }
        }

        return casingAmount >= length * 3;
    }

    private int findLength(final IGregTechTileEntity iGregTechTileEntity, final int xDir, final int zDir) {
        for (int i = 1; i < this.MAX_LENGTH; i++) {
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

    @Override
    public String[] getDescription() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Research Completer")
                .addInfo("Controller block for the Research Completer")
                .addInfo("Completes Thaumcraft research notes using EU and Thaumcraft nodes")
                .addInfo("Place nodes in the center row")
                .addSeparator()
                .beginVariableStructureBlock(3, 3, 3, 3, 3, MAX_LENGTH, true)
                .addController("Front center")
                .addOtherStructurePart("Magical machine casing", "Top and bottom layers outside. 3 x L minimum")
                .addOtherStructurePart("Warded glass", "Middle layer outside")
                .addEnergyHatch("Any casing")
                .addMaintenanceHatch("Any casing")
                .addInputBus("Any casing")
                .addOutputBus("Any casing")
                .toolTipFinisher("Electro-Magic Tools");
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            return tt.getStructureInformation();
        } else {
            return tt.getInformation();
        }
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            if (aActive) return new ITexture[]{
                    Textures.BlockIcons.casingTexturePages[1][54],
                    TextureFactory.of(OVERLAY_FRONT_RESEARCH_COMPLETER_ACTIVE),
                    TextureFactory.builder().addIcon(OVERLAY_FRONT_RESEARCH_COMPLETER_ACTIVE_GLOW).extFacing().glow().build()};
            return new ITexture[]{
                    Textures.BlockIcons.casingTexturePages[1][54],
                    TextureFactory.of(OVERLAY_FRONT_RESEARCH_COMPLETER),
                    TextureFactory.builder().addIcon(OVERLAY_FRONT_RESEARCH_COMPLETER_GLOW).extFacing().glow().build()};
        }
        return new ITexture[]{Textures.BlockIcons.casingTexturePages[1][54]};
    }
}
