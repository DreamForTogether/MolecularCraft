package com.wangdi.molecularcraft.client.inventory;

import java.util.List;
import java.util.ArrayList;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.block.BlockSubstanceDecomposer;
import com.wangdi.molecularcraft.inventory.ContainerSubstanceDecomposer;
import com.wangdi.molecularcraft.tileentity.TileEntitySubstanceDecomposer;
import com.wangdi.util.Constant;
import com.wangdi.util.MainUtil;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class GuiSubstanceDecomposer extends GuiContainer
{
    private static final ResourceLocation TEXTURES = new ResourceLocation(MolecularCraft.MODID, "textures/gui/container/" + BlockSubstanceDecomposer.NAME + ".png");
    private final InventoryPlayer playerInventory;
    private final TileEntitySubstanceDecomposer tileEntity;

    public GuiSubstanceDecomposer(InventoryPlayer playerInventory, TileEntitySubstanceDecomposer tileEntity)
    {
        super(new ContainerSubstanceDecomposer(playerInventory, tileEntity));
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

        // display the progress
        if (this.isPointInRegion(30, 35, 25, 16, mouseX, mouseY))
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
    }

    // get ratio of work that is done
    private double getDecompositionProgress()
    {
        double i = (double)(this.tileEntity.getField(1));
        double j = (double)(this.tileEntity.getField(0));
        return (i != 0.0 && j != 0.0) ? i / j : 0.0;
    }

    private int getDecompositionProgressScaled(int pixels)
    {
        return (int)(this.getDecompositionProgress() * pixels);
    }
}