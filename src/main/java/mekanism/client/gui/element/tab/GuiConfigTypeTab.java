package mekanism.client.gui.element.tab;

import mekanism.api.transmitters.TransmissionType;
import mekanism.client.gui.GuiSideConfiguration;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.GuiElement;
import mekanism.client.sound.SoundHandler;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import mekanism.common.util.text.TextComponentUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiConfigTypeTab extends GuiElement {

    private final TransmissionType transmission;
    private boolean visible;
    private boolean left;
    private int yPos;

    public GuiConfigTypeTab(IGuiWrapper gui, TransmissionType type, ResourceLocation def) {
        super(getResource(type), gui, def);
        transmission = type;
    }

    private static ResourceLocation getResource(TransmissionType t) {
        return MekanismUtils.getResource(ResourceType.GUI_ELEMENT, t.getTransmission() + ".png");
    }

    public void setY(int y) {
        yPos = y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public TransmissionType getTransmissionType() {
        return transmission;
    }

    @Override
    public Rectangle4i getBounds(int guiWidth, int guiHeight) {
        return new Rectangle4i(guiWidth + getLeftBound(false) - 4, guiHeight + yPos, 26, 26);
    }

    @Override
    protected boolean inBounds(double xAxis, double yAxis) {
        return xAxis >= getLeftBound(true) && xAxis <= getRightBound(true) && yAxis >= yPos + 4 && yAxis <= yPos + 22;
    }

    @Override
    public void renderBackground(int xAxis, int yAxis, int guiWidth, int guiHeight) {
        if (visible) {
            minecraft.textureManager.bindTexture(RESOURCE);
            guiObj.drawTexturedRect(guiWidth + getLeftBound(false) - 4, guiHeight + yPos, 0, left ? 0 : 26, 26, 26);
            guiObj.drawTexturedRect(guiWidth + getLeftBound(true), guiHeight + yPos + 4, 26, inBounds(xAxis, yAxis) ? 0 : 18, 18, 18);
            minecraft.textureManager.bindTexture(defaultLocation);
        }
    }

    @Override
    public void renderForeground(int xAxis, int yAxis) {
        if (visible) {
            minecraft.textureManager.bindTexture(RESOURCE);
            if (inBounds(xAxis, yAxis)) {
                displayTooltip(TextComponentUtil.build(transmission), xAxis, yAxis);
            }
            minecraft.textureManager.bindTexture(defaultLocation);
        }
    }

    public int getLeftBound(boolean adjust) {
        return left ? -21 + (adjust ? 1 : 0) : 179 - (adjust ? 1 : 0);
    }

    public int getRightBound(boolean adjust) {
        return left ? -3 + (adjust ? 1 : 0) : 197 - (adjust ? 1 : 0);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (visible && button == 0 && inBounds(mouseX, mouseY)) {
            ((GuiSideConfiguration) guiObj).setCurrentType(transmission);
            ((GuiSideConfiguration) guiObj).updateTabs();
            SoundHandler.playSound(SoundEvents.UI_BUTTON_CLICK);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}