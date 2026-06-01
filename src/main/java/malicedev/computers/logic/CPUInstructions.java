package malicedev.computers.logic;

import malicedev.computers.tileentities.TileEntityMonitor;
import static malicedev.computers.tileentities.TileEntityMonitor.resolution_width;

public class CPUInstructions {
	public static final int INSTRUCTION_COPY_TILE = 0x01;

	private CPUInstructions(){}

	public static void execute(TileEntityMonitor tileEntityMonitor){
		if (tileEntityMonitor.instruction == INSTRUCTION_COPY_TILE){
			copyTile(tileEntityMonitor,2,2);
			tileEntityMonitor.instruction = 0x00;
		}

	}
	public static void copyTile(TileEntityMonitor tileEntityMonitor,int column, int row){
		for (int i = 0; i<8;i++){
			tileEntityMonitor.VRAM[column + (((row << 3) + i) * (resolution_width >> 3))] = CharacterROM.charC[i];
		}
	}





}
