package malicedev.computers.screens;

import malicedev.computers.tileentities.TileEntityMonitor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Screen;
import net.minecraft.core.util.helper.MathHelper;
import org.lwjgl.opengl.GL11;


import static malicedev.computers.tileentities.TileEntityMonitor.resolution_height;
import static malicedev.computers.tileentities.TileEntityMonitor.resolution_width;


public class ScreenMonitor extends Screen {
	public ScreenMonitor (TileEntityMonitor tileEntityMonitor){
		super ();
		for (int i = 0; i < VRAMBuffer.length; i++) {
			if (tileEntityMonitor.getVRAMBit(i) == true)
				VRAMBuffer[i] = 0xFFFFFFFF;
			else{
				VRAMBuffer[i] = 0xFF000000;
			}
		}
	}

	private int[] VRAMBuffer = new int[resolution_width * resolution_height];
	private int BufferTexture = Minecraft.getMinecraft().textureManager.createTexture(resolution_width,resolution_height);


	@Override
	public void render(int mx, int my, float partialTick) {
		super.render(mx, my, partialTick);
		this.mc.textureManager.updateTextureData(VRAMBuffer, resolution_width, resolution_height, BufferTexture);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, BufferTexture);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (this.width - resolution_width) / 2;
		int y = (this.height - resolution_height) / 2;
		this.drawTexturedModalRect(0, 0, 0, 0, resolution_width/4, resolution_height/4);
	}
}
