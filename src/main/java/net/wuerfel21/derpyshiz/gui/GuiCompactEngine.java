package net.wuerfel21.derpyshiz.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCompactEngine;

import org.lwjgl.opengl.GL11;

public class GuiCompactEngine extends GuiContainer {

public ResourceLocation theTexture = new ResourceLocation("derpyshiz:textures/gui/burner.png");
	
	public TileEntityCompactEngine engine;
	public InventoryPlayer inventoryPlayer;
	
	public GuiCompactEngine(InventoryPlayer inv, TileEntityCompactEngine te) {
		super(new ContainerCompactEngine(inv, te));
		this.engine = te;
		this.inventoryPlayer = inv;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String engName = engine.getInventoryName();
		String invName = StatCollector.translateToLocal("container.inventory");
		this.fontRendererObj.drawString(engName, this.xSize / 2 - this.fontRendererObj.getStringWidth(engName) / 2, 5, 4210752);
		this.fontRendererObj.drawString(invName, 8, this.ySize - 92, 4210752);
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float gameTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(theTexture);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
		int ticksLeftScaled = this.engine.getTicksLeftScaled(14);
        this.drawTexturedModalRect(x + 80, y + 36 + 13 - ticksLeftScaled, 176, 13 - ticksLeftScaled, 14, ticksLeftScaled);
	}

}
