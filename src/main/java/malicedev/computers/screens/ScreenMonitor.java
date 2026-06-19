package malicedev.computers.screens;

import malicedev.computers.tileentities.TileEntityMonitor;
import net.minecraft.client.GLAllocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.util.helper.Color;
import net.minecraft.core.util.helper.MathHelper;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;


public class ScreenMonitor extends Screen {
	private final TileEntityMonitor tE;
	private final int resolution_width;
	private final int resolution_height;

	private final int[] VRAMBuffer;
	private final int BufferTexture;

	public ScreenMonitor (TileEntityMonitor tileEntityMonitor){
		super ();
		this.tE = tileEntityMonitor;
		byte VideoMode = this.tE.VMD;
		switch(VideoMode){
			case 0:{
				this.resolution_width = 384;
				this.resolution_height = 256;
				break;
			}
			case 1:{
				this.resolution_width = 256;
				this.resolution_height = 384;
				break;
			}
			case 2:{
				this.resolution_width = 256;
				this.resolution_height = 224;
				break;
			}
			case 3:{
				this.resolution_width = 280;
				this.resolution_height = 192;
				break;
			}
			case 4:{
				this.resolution_width = 640;
				this.resolution_height = 360	;
				break;
			}
			default: {
				throw new RuntimeException("Invalid video mode '" + VideoMode + "'!");
			}
		}
		this.VRAMBuffer = new int[this.resolution_width * this.resolution_height];
		this.BufferTexture = Minecraft.getMinecraft().textureManager.createTexture(this.resolution_width, this.resolution_height);
	}

	@Override
	public void tick() {
		super.tick();
	}

	public void VRAMtoBuffer(){
		for (int i = 0; i < this.VRAMBuffer.length; i++) {
			if (this.tE.getVRAMBit(i))
				this.VRAMBuffer[i] = Color.byteToIntARGB((byte) 0xFF, tE.VRAM[tE.VRAM.length-6] , tE.VRAM[tE.VRAM.length-5], tE.VRAM[tE.VRAM.length-4]);
			else{
				this.VRAMBuffer[i] = Color.byteToIntARGB((byte) 0xFF, tE.VRAM[tE.VRAM.length-3] , tE.VRAM[tE.VRAM.length-2], tE.VRAM[tE.VRAM.length-1]);
			}
		}
	}
	@Override
	public void render(int mx, int my, float partialTick) {
		super.render(mx, my, partialTick);
		Tessellator tes = Tessellator.instance;
		VRAMtoBuffer();
		this.mc.textureManager.updateTextureData(this.VRAMBuffer, this.resolution_width, this.resolution_height, this.BufferTexture);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.BufferTexture);
		GL11.glColor4f(1, 1, 1,1);

		{
			GL11.glPushMatrix();
			GL11.glTranslatef(this.width / 2f, this.height / 2f, 0);
			GL11.glScalef(1f / this.mc.resolution.getScale(), 1f / this.mc.resolution.getScale(), 1);

			int padding = 100;
			int scale = MathHelper.floor(Math.min(
				(double) this.mc.resolution.getWidthScreenCoords()/(this.resolution_width + padding),
				(double) this.mc.resolution.getHeightScreenCoords()/(this.resolution_height + padding)));

			int monitorWidth = this.resolution_width * scale;
			int monitorHeight = this.resolution_height * scale;

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
	public void removed() {
		super.removed();
		GLAllocation.deleteTexture(this.BufferTexture); // if for some reason this doesn't actually get called, this will lead to a memory leak :)
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
