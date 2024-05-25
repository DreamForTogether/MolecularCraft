package com.wangdi.molecularcraft.potion;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.util.MainUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionBased extends Potion
{
    public static final int SIZE = 18;
    public static final ResourceLocation resource = new ResourceLocation(MolecularCraft.MODID, "textures/gui/container/inventory.png");

    public int iconIndex;
    public PotionType potionType = PotionTypes.MUNDANE;
                    
    public PotionBased(String name, boolean isBadEffectIn, int liquidColorIn, int icon)
    {
        super(isBadEffectIn, liquidColorIn);

        this.iconIndex = icon;

        this.setRegistryName(name);
        this.setPotionName(I18n.format(MainUtil.combinedStrings(MolecularCraft.MODID, ".effect." + name)));
    }

    public PotionType getPotionType()
    {
        return this.potionType;
    }

    @SideOnly(Side.CLIENT)
    protected void render(int x, int y, float alpha)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(resource);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buf = tessellator.getBuffer();
        buf.begin(7, DefaultVertexFormats.POSITION_TEX);
        GlStateManager.color(1, 1, 1, alpha);

        int textureX = (this.iconIndex % 14) * SIZE;
        int textureY = (this.iconIndex / 14) * SIZE;

        buf.pos(x, y + SIZE, 0).tex(textureX * 0.00390625, (textureY + SIZE) * 0.00390625).endVertex();
        buf.pos(x + SIZE, y + SIZE, 0).tex((textureX + SIZE) * 0.00390625, (textureY + SIZE) * 0.00390625).endVertex();
        buf.pos(x + SIZE, y, 0).tex((textureX + SIZE) * 0.00390625, textureY * 0.00390625).endVertex();
        buf.pos(x, y, 0).tex(textureX * 0.00390625, textureY * 0.00390625).endVertex();

        tessellator.draw();
    }
}