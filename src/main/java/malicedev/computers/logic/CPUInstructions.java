package malicedev.computers.logic;

import malicedev.computers.tileentities.TileEntityMonitor;
import static malicedev.computers.tileentities.TileEntityMonitor.resolution_width;
import static malicedev.computers.tileentities.TileEntityMonitor.resolution_height;
public class CPUInstructions {
	private CPUInstructions(){}

	public static void Execute(TileEntityMonitor tileEntityMonitor){
		if (tileEntityMonitor.instruction == 0x01){
			copyTile(tileEntityMonitor,0,0);
			tileEntityMonitor.instruction = 0x00;
		}

	}
	public static void copyTile(TileEntityMonitor tileEntityMonitor,int column, int row){
		for (int i = 0; i<8;i++){
			tileEntityMonitor.VRAM[column + ((row + i) * resolution_width)] = CharacterROM.charB[i];
		}
	}





}
