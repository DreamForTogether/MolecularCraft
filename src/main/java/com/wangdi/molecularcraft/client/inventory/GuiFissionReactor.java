package com.wangdi.molecularcraft.client.inventory;

import java.util.ArrayList;
import java.util.List;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.block.BlockFissionReactor;
import com.wangdi.molecularcraft.inventory.ContainerFissionReactor;
import com.wangdi.molecularcraft.tileentity.TileEntityFissionReactor;
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

public final class GuiFissionReactor extends GuiContainer
{
    private static final ResourceLocation TEXTURES = new ResourceLocation(MolecularCraft.MODID, "textures/gui/container/" + BlockFissionReactor.NAME + ".png");
    private final InventoryPlayer playerInventory;
    private final TileEntityFissionReactor tileEntity;

    public GuiFissionReactor(InventoryPlayer playerInventory, TileEntityFissionReactor tileEntity)
    {
        super(new ContainerFissionReactor(playerInventory, tileEntity));
        this.playerInventory = playerInventory;
        this.tileEntity = tileEntity;

        this.xSize = 176;
        this.ySize = 232;
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
        if (this.isPointInRegion(81, 115, 82, 9, mouseX, mouseY))
        {
            PrefixNumber currentEnergy = new PrefixNumber(this.tileEntity.getCurrentEnergy());
            PrefixNumber maxEnergy = new PrefixNumber(BlockFissionReactor.MAX_STORE_ENERGY);
            List<String> tooltip = new ArrayList<>();
            
            tooltip.add(MainUtil.combinedStrings(TextFormatting.WHITE.toString(), I18n.format(Constant.ENERGY_STORAGE_TEXT)));
            tooltip.add(MainUtil.combinedStrings(TextFormatting.GREEN.toString(), I18n.format(Constant.ENERGY_TEXT), ": ", currentEnergy.toSplitString(), PhysicsConstant.STANDARD_ENERGY_UNIT));
            tooltip.add(MainUtil.combinedStrings(TextFormatting.RED.toString(), I18n.format(Constant.MAX_ENERGY_TEXT), ": ", maxEnergy.toSplitString(), PhysicsConstant.STANDARD_ENERGY_UNIT));

            GuiUtils.drawHoveringText(tooltip, mouseX - guiLeft, mouseY - guiTop, width, height, -1, fontRenderer);
        }

        // display the progress
        if (this.isPointInRegion(52, 88, 5, 9, mouseX, mouseY))
        {
            int progress = (int)(this.getWorkingProgress() * 100);
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
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        // draw control rod
        if (this.tileEntity.isControlled())
        {
            this.drawTexturedModalRect(this.guiLeft + 43, this.guiTop + 17, 176, 17, 5, 65);
            this.drawTexturedModalRect(this.guiLeft + 54, this.guiTop + 17, 176, 17, 5, 65);
            this.drawTexturedModalRect(this.guiLeft + 65, this.guiTop + 17, 176, 17, 5, 65);
        }

        // draw raw material (fuel rod)
        if (this.tileEntity.hasRawMaterialInput())
        {
            this.drawTexturedModalRect(this.guiLeft + 37, this.guiTop + 61, 187, 17, 6, 21);
            this.drawTexturedModalRect(this.guiLeft + 48, this.guiTop + 61, 187, 17, 6, 21);
            this.drawTexturedModalRect(this.guiLeft + 59, this.guiTop + 61, 187, 17, 6, 21);
            this.drawTexturedModalRect(this.guiLeft + 70, this.guiTop + 61, 187, 17, 6, 21);
        }

        // draw coolant (liquid cooling)
        if (this.tileEntity.hasLiquidCooling())
            this.drawTexturedModalRect(this.guiLeft + 78, this.guiTop + 26, 193, 9, 47, 54);

        // draw reactor core (blue background)
        int l1 = this.tileEntity.getField(3);
        this.drawTexturedModalRect(this.guiLeft + 35, this.guiTop + 84 - l1, 176, 139 - l1, 73, l1);
        this.drawTexturedModalRect(this.guiLeft + 35, this.guiTop + 84 - l1, 176, 197 - l1, 73, l1);

        // draw ⚡ icon
        if (this.tileEntity.isWorking() && this.tileEntity.hasGenerator())
            this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 115, 176, 7, 11, 10);

        int l2 = this.getWorkingProgressScaled(9);
        this.drawTexturedModalRect(this.guiLeft + 53, this.guiTop + 88, 231, 0, 7, l2);
        
        int l3 = this.getEnergyProgressScaled(68);
        this.drawTexturedModalRect(this.guiLeft + 93, this.guiTop + 116, 176, 0, l3, 7);
    }

    // get ratio of work that is done
    private double getWorkingProgress()
    {
        double i = (double)(this.tileEntity.getField(1));
        double j = (double)(this.tileEntity.getField(0));
        return (i != 0.0 && j != 0.0) ? i / j : 0.0;
    }

    // get ratio of energy
    private double getEnergyProgress()
    {
        PrefixNumber prefixNumber = new PrefixNumber(this.tileEntity.getCurrentEnergy());
        double i = prefixNumber.doubleValue();
        double j = BlockFissionReactor.MAX_STORE_ENERGY;
        return (i != 0.0 && j != 0.0) ? i / j : 0.0;
    }

    private int getWorkingProgressScaled(int pixels)
    {
        return (int)(this.getWorkingProgress() * pixels);
    }

    private int getEnergyProgressScaled(int pixels)
    {
        return (int)(this.getEnergyProgress() * pixels);
    }
}