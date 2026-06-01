package malicedev.computers.screens;

import malicedev.computers.tileentities.TileEntityMonitor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.util.helper.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;


import static malicedev.computers.tileentities.TileEntityMonitor.resolution_height;
import static malicedev.computers.tileentities.TileEntityMonitor.resolution_width;


public class ScreenMonitor extends Screen {
	public ScreenMonitor (TileEntityMonitor tileEntityMonitor){
		super ();
		this.tE = tileEntityMonitor;
	}

	private TileEntityMonitor tE;


	private int[] VRAMBuffer = new int[resolution_width * resolution_height];
	private static final int BufferTexture = Minecraft.getMinecraft().textureManager.createTexture(resolution_width,resolution_height);

	@Override
	public void tick() {
		super.tick();
	}

	public void VRAMtoBuffer(){
		for (int i = 0; i < VRAMBuffer.length; i++) {
			if (tE.getVRAMBit(i) == true)
				VRAMBuffer[i] = 0xFFFFFFFF;
			else{
				VRAMBuffer[i] = 0xFF000000;
			}
		}
	}
	@Override
	public void render(int mx, int my, float partialTick) {
		super.render(mx, my, partialTick);
		Tessellator tes = Tessellator.instance;
		VRAMtoBuffer();
		this.mc.textureManager.updateTextureData(VRAMBuffer, resolution_width, resolution_height, BufferTexture);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, BufferTexture);
		GL11.glColor4f(1, 1, 1,1);
		tes.startDrawingQuads();


		final double aspectRatio = ((double) resolution_width / resolution_height);
		final double scale = 0.8;

		final int monitorWidth;
		final int monitorHeight;

		{
			final int screenWidth256x = (this.width / 256) * 256;
			final int screenHeight256x = (this.height / 256) * 256;

			if (screenHeight256x < screenWidth256x) {
				monitorWidth  = (int) (screenWidth256x * scale * aspectRatio);
				monitorHeight = (int) (monitorWidth / aspectRatio);
			}

			else {
				monitorHeight = (int) (screenHeight256x * scale * aspectRatio);
				monitorWidth  = (int) (monitorHeight / aspectRatio);
			}
		}

		tes.drawRectangleWithUV(
			(int) (this.width / 2 - monitorWidth / 2),
			(int) (this.height / 2 - monitorHeight / 2),
			monitorWidth,
			monitorHeight,
			0, 0, 1, 1
		);

		tes.draw();
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
