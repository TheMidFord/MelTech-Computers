package malicedev.computers.tileentities;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.util.helper.MathHelper;

import java.util.Random;

public class TileEntityMonitor extends TileEntity {
	public TileEntityMonitor (){
		super();
	}



	public static int resolution_width = 512;
	public static int resolution_height =768;
	public byte[] VRAM = new byte[MathHelper.ceil((resolution_width * resolution_height)/8f)];


	public boolean getVRAMBit(int bitaddress){
		return (VRAM[bitaddress>>3] & (0b1 << (bitaddress & 0b111))) != 0;

	}
	public void setVRAMBit(int bitaddress,boolean value){
		if (value) {
			VRAM[bitaddress >> 3] |= (0b1 << (bitaddress & 0b111));
		} else {
			VRAM[bitaddress >> 3] &= ~(0b1 << (bitaddress & 0b111));
		}
	}


	public boolean getMemoryAddress(int address){
		return true;
	}

	public void setMemoryAddress(int address, int value) {

	}
}
