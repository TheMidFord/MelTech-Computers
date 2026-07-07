package malicedev.computers.tileentities;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.block.entity.TileEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.UUID;

import static malicedev.computers.logic.CPU.step;

public class TileEntityComputer extends TileEntity {
	public TileEntityComputer(){
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
	static final int MAX_ROM_MEMORY_ALLOC_SIZE = 65536;
	static final int MAX_RAM_MEMORY_ALLOC_SIZE = 16777216;
	static final int MAX_VRAM_MEMORY_ALLOC_SIZE = 4194304;
	public int ramSize = 128;
	public int vramSize = 32;
	public byte[] VRAM = new byte[(vramSize*1024)];
	public byte[] RAM = new byte[ramSize*1024];
	public byte[] ROMPlaceholder = new byte[64*1024];
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
		if (address<MAX_ROM_MEMORY_ALLOC_SIZE) {
			byte value = ROMPlaceholder[address];
			return value;
		}
		if (address < MAX_ROM_MEMORY_ALLOC_SIZE + RAM.length){
			byte value =RAM[address- MAX_ROM_MEMORY_ALLOC_SIZE];
			return value;
		}
		if (address < MAX_ROM_MEMORY_ALLOC_SIZE + MAX_RAM_MEMORY_ALLOC_SIZE + VRAM.length){
			byte value = VRAM[address-(MAX_ROM_MEMORY_ALLOC_SIZE+MAX_RAM_MEMORY_ALLOC_SIZE)];
			return value;
		}
		else {
			throw new ArrayIndexOutOfBoundsException("WRONG! [MelTech:Computers Error: Tried to access a memory address that doesn't exist (out of range).]");
		}
	}


	public void setByte(int address, byte value) {
		if (address<MAX_ROM_MEMORY_ALLOC_SIZE) {

		}
		if (address < MAX_ROM_MEMORY_ALLOC_SIZE + RAM.length){
			RAM[address- MAX_ROM_MEMORY_ALLOC_SIZE] = value;
		}
		if (address < MAX_ROM_MEMORY_ALLOC_SIZE + MAX_RAM_MEMORY_ALLOC_SIZE + VRAM.length){
			VRAM[address-(MAX_ROM_MEMORY_ALLOC_SIZE+MAX_RAM_MEMORY_ALLOC_SIZE)]=value;

		}
		else {
			throw new ArrayIndexOutOfBoundsException("WRONG! [MelTech:Computers Error: Tried to access a memory address that doesn't exist (out of range).]");
		}
	}

	public void handleMemoryException(int address) {

	}

	@Override
	public void writeAdditionalData(@NotNull CompoundTag compoundTag) {
		compoundTag.putByteArray("SavedVRAM",VRAM);
	}

	@Override
	public void readAdditionalData(CompoundTag compoundTag){
		VRAM = compoundTag.getByteArray("SavedVRAM");
	}

	public int i = 0;
	Random r = new Random();
	@Override
	public void tick() {
		for (int j = 0; j < 50; j++) {
			setVRAMBit(r.nextInt(VRAM.length*8), false);
			setVRAMBit(r.nextInt(VRAM.length*8), true);
		}
		if (isON== true) {
			step(this);
		}
	}

}
