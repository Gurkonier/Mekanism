package mekanism.client.gui.element.tab;

import mekanism.client.gui.IGuiWrapper;
import mekanism.common.Mekanism;
import mekanism.common.network.PacketGuiButtonPress;
import mekanism.common.network.PacketGuiButtonPress.ClickedTileButton;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import mekanism.common.util.text.TextComponentUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiTransporterConfigTab extends GuiTabElement<TileEntity> {

    public GuiTransporterConfigTab(IGuiWrapper gui, int y, TileEntity tile, ResourceLocation def) {
        super(MekanismUtils.getResource(ResourceType.GUI_ELEMENT, "transporter_config.png"), gui, def, tile, y);
    }

    @Override
    public void buttonClicked() {
        Mekanism.packetHandler.sendToServer(new PacketGuiButtonPress(ClickedTileButton.TRANSPORTER_CONFIGURATION, tileEntity.getPos()));
    }

    @Override
    public void displayForegroundTooltip(int xAxis, int yAxis) {
        displayTooltip(TextComponentUtil.translate("gui.configuration.transporter"), xAxis, yAxis);
    }
}