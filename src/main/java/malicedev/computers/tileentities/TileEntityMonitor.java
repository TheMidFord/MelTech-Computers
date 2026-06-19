package malicedev.computers.tileentities;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.block.entity.TileEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.UUID;

import static malicedev.computers.logic.CPUInstructions.step;

public class TileEntityMonitor extends TileEntity {
	public TileEntityMonitor (){
		super();

		VRAM[VRAM.length-3] =(byte)0x00; //Background Color R Value
		VRAM[VRAM.length-2] =(byte)0x00; //Background Color G Value
		VRAM[VRAM.length-1] =(byte)0x00; //Background Color B Value

		VRAM[VRAM.length-6] =(byte)0xFF; //Foreground Color R Value
		VRAM[VRAM.length-5] =(byte)0xFF; //Foreground Color G Value
		VRAM[VRAM.length-4] =(byte)0xFF; //Foreground Color B Value

	}


	static UUID ID = null;
	public boolean isON = false;
	public int ramSize = 128;
	public int vramSize = 32;
	public byte[] VRAM = new byte[(vramSize*1024)];
	public byte[] RAM = new byte[ramSize*1024];
	public int REGA = (int)0x00000000;
	public int REGX = (int)0x00000000;
	public int REGY = (int)0x00000000;
	public int REGPC = 0x00000000;
	public int REGPCI = 0x00000000;
	public byte REGI = 0x00;
	public byte VMD = 0;
	/*Video mode register
	0 = Default (384x256)
	1 = Default Vertical (256x384)
	2 = NES/Famicom Compatibility Mode (256x224)
	3 = Apple ][ Compatibility Mode (280x192)
	4 = 16:9 Fullscreen (640x360)
	 */
	byte[] currentmemory;

	public boolean getVRAMBit(int bitaddress){
		return (VRAM[bitaddress>>3] & (0b1 << (0b111 - (bitaddress & 0b111)))) != 0;

	}
	public void setVRAMBit(int bitaddress,boolean value){
		if (value) {
			VRAM[bitaddress >> 3] |= (0b1 << (0b111 - (bitaddress & 0b111)));
		} else {
			VRAM[bitaddress >> 3] &= ~(0b1 << (0b111 - (bitaddress & 0b111)));
		}
	}


	public byte getByte(int address){
		byte value= RAM[address];
		return value;
	}


	public void setByte(short address, byte value) {
	}

	public void getID() {
		if (ID == null) {
			ID = UUID.randomUUID();
		}
	}

	@Override
	public void writeAdditionalData(@NotNull CompoundTag compoundTag) {
		writeAdditionalData(compoundTag);
		compoundTag.putByteArray("SavedVRAM",VRAM);
	}

	@Override
	public void readAdditionalData(CompoundTag compoundTag){
		readAdditionalData(compoundTag);
		VRAM = compoundTag.getByteArray("SavedVRAM");


	}

	public int i = 0;
	Random r = new Random();
	@Override
	public void tick() {
//		for (int j = 0; j < 50; j++) {
//			setVRAMBit(r.nextInt(VRAM.length*8), false);
//			setVRAMBit(r.nextInt(VRAM.length*8), true);
//		}
		if (isON== true) {
			step(this);
		}
	}

}
