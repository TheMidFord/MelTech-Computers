package malicedev.computers.tileentities;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.util.helper.MathHelper;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static malicedev.computers.logic.CPUInstructions.execute;

public class TileEntityMonitor extends TileEntity {
	public TileEntityMonitor (){
		super();
	}


	static UUID ID = null;
	public static int resolution_width = 384;
	public static int resolution_height =256;
	public static int onebit_fgcolor = 0xFFFFFFFF;
	public static int onebit_bgcolor = 0xFF000000;
	public byte[] VRAM = new byte[MathHelper.ceil((resolution_width * resolution_height)/8f)];
	public byte[] RAM = new byte[1024*128];
	public short REGA = (short)0x0000;
	public short REGX = (short)0x0000;
	public short REGY = (short)0x0000;
	public short REGPC = (short)0x0000;
	public short MEMBANK = (short)0x0000;

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


	public byte getByteFromMemory(short address){
		byte value= RAM[address*MEMBANK];
		return value;
	}

	public void setMemoryAddress(int address, int value) {
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
	@Override
	public void tick() {
		isFilling = false;
		if (isFilling == true) {
			setVRAMBit(i, !getVRAMBit(i));
			if (i < VRAM.length * 8) {
				i++;
			} else if (i == VRAM.length * 8) {
				i = 0;
			}
		}
		execute(this);
	}

}
