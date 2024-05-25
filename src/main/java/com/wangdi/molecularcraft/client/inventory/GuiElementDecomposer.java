package com.wangdi.molecularcraft.client.inventory;

import java.util.List;
import java.util.ArrayList;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.block.BlockElementDecomposer;
import com.wangdi.molecularcraft.inventory.ContainerElementDecomposer;
import com.wangdi.molecularcraft.tileentity.TileEntityElementDecomposer;
import com.wangdi.util.Constant;
import com.wangdi.util.MainUtil;
import com.wangdi.util.physics.PhysicsConstant;
import com.wangdi.util.physics.PrefixNumber;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class GuiElementDecomposer extends GuiContainer
{
    private static final ResourceLocation TEXTURES = new ResourceLocation(MolecularCraft.MODID, "textures/gui/container/" + BlockElementDecomposer.NAME + ".png");
    private final InventoryPlayer playerInventory;
    private final TileEntityElementDecomposer tileEntity;

    public GuiElementDecomposer(InventoryPlayer playerInventory, TileEntityElementDecomposer tileEntity)
    {
        super(new ContainerElementDecomposer(playerInventory, tileEntity));
        this.playerInventory = playerInventory;
        this.tileEntity = tileEntity;

        this.xSize = 175;
        this.ySize = 167;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        // show name on Gui
        String s = this.tileEntity.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);

        // display the energy
        if (this.isPointInRegion(35, 22, 10, 10, mouseX, mouseY))
        {
            PrefixNumber currentEnergy = new PrefixNumber(this.tileEntity.getCurrentEnergy());
            PrefixNumber maxEnergy = new PrefixNumber(BlockElementDecomposer.MAX_STORE_ENERGY);
            List<String> tooltip = new ArrayList<>();
            
            tooltip.add(MainUtil.combinedStrings(TextFormatting.WHITE.toString(), I18n.format(Constant.ENERGY_STORAGE_TEXT)));
            tooltip.add(MainUtil.combinedStrings(TextFormatting.GREEN.toString(), I18n.format(Constant.ENERGY_TEXT), ": ", currentEnergy.toSplitString(), PhysicsConstant.STANDARD_ENERGY_UNIT));
            tooltip.add(MainUtil.combinedStrings(TextFormatting.RED.toString(), I18n.format(Constant.MAX_ENERGY_TEXT), ": ", maxEnergy.toSplitString(), PhysicsConstant.STANDARD_ENERGY_UNIT));

            GuiUtils.drawHoveringText(tooltip, mouseX - guiLeft, mouseY - guiTop, width, height, -1, fontRenderer);
        }

        // display the progress
        if (this.isPointInRegion(30, 36, 25, 16, mouseX, mouseY))
        {
            int progress = (int)(this.getDecompositionProgress() * 100);
            List<String> tooltip = new ArrayList<>();

            tooltip.add(MainUtil.combinedStrings(I18n.format(Constant.PROGRESS_TEXT), ": ", Integer.toString(progress), "%"));

            GuiUtils.drawHoveringText(tooltip, mouseX - guiLeft, mouseY - guiTop, width, height, -1, fontRenderer);
        }
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);

        int l1 = this.getDecompositionProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 30, this.guiTop + 36, 176, 0, l1, 16);

        int l2 = this.getEnergyProgressScaled(10);
        this.drawTexturedModalRect(this.guiLeft + 35, this.guiTop + 32 - l2, 176, 26 - l2, 11, l2);
    }

    // get ratio of work that is done
    private double getDecompositionProgress()
    {
        double i = (double)(this.tileEntity.getField(2));
        double j = (double)(this.tileEntity.getField(1));
        return (i != 0.0 && j != 0.0) ? i / j : 0.0;
    }

    // get ratio of energy
    private double getEnergyProgress()
    {
        PrefixNumber prefixNumber = new PrefixNumber(this.tileEntity.getCurrentEnergy());
        double i = prefixNumber.doubleValue();
        double j = BlockElementDecomposer.MAX_STORE_ENERGY;
        return (i != 0.0 && j != 0.0) ? i / j : 0.0;
    }

    private int getDecompositionProgressScaled(int pixels)
    {
        return (int)(this.getDecompositionProgress() * pixels);
    }

    private int getEnergyProgressScaled(int pixels)
    {
        return (int)(this.getEnergyProgress() * pixels);
    }
}