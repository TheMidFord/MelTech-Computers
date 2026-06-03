package malicedev.computers.logic;

import malicedev.computers.tileentities.TileEntityMonitor;
import static malicedev.computers.tileentities.TileEntityMonitor.resolution_width;

	public class CPUInstructions {
	private CPUInstructions(){}
	public static final int INSTRUCTION_COPY_TILE = 0x01;


	public static void execute(TileEntityMonitor tileEntityMonitor){

		if (tileEntityMonitor.instruction == INSTRUCTION_COPY_TILE){
			copyTile(tileEntityMonitor,7+9,1,0);
			copyTile(tileEntityMonitor,8+9,1,1);

			copyTile(tileEntityMonitor,10+9,1,2);
			copyTile(tileEntityMonitor,11+9,1,3);
			copyTile(tileEntityMonitor,12+9,1,4);
			copyTile(tileEntityMonitor,13+9,1,5);

			copyTile(tileEntityMonitor,14+9,1,6);
			copyTile(tileEntityMonitor,16+9,1,7);
			copyTile(tileEntityMonitor,17+9,1,8);
			copyTile(tileEntityMonitor,18+9,1,8);
			copyTile(tileEntityMonitor,19+9,1,1);
			copyTile(tileEntityMonitor,20+9,1,9);
			copyTile(tileEntityMonitor,21+9,1,10);
			copyTile(tileEntityMonitor,22+9,1,5);
			copyTile(tileEntityMonitor,23+9,1,11);

			copyTile(tileEntityMonitor,14+9,16,16);
			copyTile(tileEntityMonitor,15+9,16,17);
			copyTile(tileEntityMonitor,14+9,17,18);
			copyTile(tileEntityMonitor,15+9,17,19);

			copyTile(tileEntityMonitor,13+9,30,12);
			copyTile(tileEntityMonitor,14+9,30,13);
			copyTile(tileEntityMonitor,15+9,30,14);
			copyTile(tileEntityMonitor,16+9,30,15);



			tileEntityMonitor.instruction = 0x00;
		}

	}
	public static void print(TileEntityMonitor tileEntityMonitor,int[] characters,int startingrow, int startingcolumn){
		int current_character = 0;
		for(int column)


		}
	}
	public static void copyTile(TileEntityMonitor tileEntityMonitor,int column, int row,int charindex){
		for (int i = 0; i<8;i++){
			tileEntityMonitor.VRAM[column + (((row << 3) + i) * (resolution_width >> 3))] = CharacterROM.charSet[i+8*charindex];
		}
	}

	public static void LDX(TileEntityMonitor tileEntityMonitor,short adr){
		tileEntityMonitor.REGX = tileEntityMonitor.getByteFromMemory(adr);


	}






}
