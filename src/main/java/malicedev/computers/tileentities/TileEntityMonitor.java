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
		//Background Color B Value
		VRAM[VRAM.length-1] =(byte)0x00;
		//Background Color G Value
		VRAM[VRAM.length-2] =(byte)0x00;
		//Background Color R Value
		VRAM[VRAM.length-3] =(byte)0x00;
		//Foreground Color B Value
		VRAM[VRAM.length-4] =(byte)0xFF;
		//Foreground Color G Value
		VRAM[VRAM.length-5] =(byte)0xFF;
		//Foreground Color R Value
		VRAM[VRAM.length-6] =(byte)0xFF;

	}


	static UUID ID = null;
	public static int resolution_width = 384;
	public static int resolution_height =256;
	public static int[] COLORRAM={0xFF000000,0xFFFFFFFF};
	public static byte[] VRAM = new byte[(32*1024)];
	public byte[] RAM = new byte[128*1024];
	public static short REGA = (short)0x00000000;
	public static short REGX = (short)0x00000000;
	public static short REGY = (short)0x00000000;
	public static short REGPC = 0x0000;
	public static byte VMD = 0;
	public int ramSize = 128;
	public int vramSize = 32;

	public boolean isFilling = false;
	public short instruction = 0x00	;

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


	public byte getByte(short address,byte address_part2){
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
	public void writeToNBT(@NotNull CompoundTag compoundTag) {
		super.writeToNBT(compoundTag);
		compoundTag.putByteArray("SavedVRAM",VRAM);
	}

	@Override
	public void readFromNBT(CompoundTag compoundTag){
		super.readFromNBT(compoundTag);
		VRAM = compoundTag.getByteArray("SavedVRAM");


	}
	public int i = 0;
	Random r = new Random();
	@Override
	public void tick() {
		step(this);
	}

}
