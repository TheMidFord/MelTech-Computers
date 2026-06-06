package malicedev.computers.screens;

import malicedev.computers.tileentities.TileEntityMonitor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.util.helper.MathHelper;
import org.lwjgl.opengl.GL11;

import static malicedev.computers.tileentities.TileEntityMonitor.*;


public class ScreenMonitor extends Screen {
	public ScreenMonitor (TileEntityMonitor tileEntityMonitor){
		super ();
		this.tE = tileEntityMonitor;
	}

	private final TileEntityMonitor tE;


	private final int[] VRAMBuffer = new int[resolution_width * resolution_height];
	private static final int BufferTexture = Minecraft.getMinecraft().textureManager.createTexture(resolution_width,resolution_height);

	@Override
	public void tick() {
		super.tick();
	}

	public void VRAMtoBuffer(){
		for (int i = 0; i < this.VRAMBuffer.length; i++) {
			if (this.tE.getVRAMBit(i))
				this.VRAMBuffer[i] = (0xFF<<24) | (VRAM[VRAM.length-6] << 16) | (VRAM[VRAM.length-5] << 8) | (VRAM[VRAM.length-4]);
			else{
				this.VRAMBuffer[i] = (0xFF<<24) | (VRAM[VRAM.length-3] << 16) | (VRAM[VRAM.length-2] << 8) | (VRAM[VRAM.length-1]);
			}
		}
	}
	@Override
	public void render(int mx, int my, float partialTick) {
		super.render(mx, my, partialTick);
		Tessellator tes = Tessellator.instance;
		VRAMtoBuffer();
		this.mc.textureManager.updateTextureData(this.VRAMBuffer, resolution_width, resolution_height, BufferTexture);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, BufferTexture);
		GL11.glColor4f(1, 1, 1,1);

		{
			GL11.glPushMatrix();
			GL11.glTranslatef(this.width / 2f, this.height / 2f, 0);
			GL11.glScalef(1f / this.mc.resolution.getScale(), 1f / this.mc.resolution.getScale(), 1);

			int padding = 100;
			int scale = MathHelper.floor(Math.min(
				(double) this.mc.resolution.getWidthScreenCoords()/(resolution_width + padding),
				(double) this.mc.resolution.getHeightScreenCoords()/(resolution_height + padding)));

			int monitorWidth = resolution_width * scale;
			int monitorHeight = resolution_height * scale;

			int minX = -monitorWidth / 2;
			int minY = -monitorHeight / 2;
			int maxX = minX + monitorWidth;
			int maxY = minY + monitorHeight;

			tes.startDrawingQuads();
			tes.addVertexWithUV(minX, minY, 0, 0, 0);
			tes.addVertexWithUV(minX, maxY, 0, 0, 1);
			tes.addVertexWithUV(maxX, maxY, 0, 1, 1);
			tes.addVertexWithUV(maxX, minY, 0, 1, 0);
			tes.draw();

			GL11.glPopMatrix();
		}
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
