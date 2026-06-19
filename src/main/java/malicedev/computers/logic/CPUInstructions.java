package malicedev.computers.logic;

import malicedev.computers.tileentities.TileEntityMonitor;

import static malicedev.computers.tileentities.TileEntityMonitor.*;

	public class CPUInstructions {
	private CPUInstructions(){}
	public static final int LDX = 0x01;
	public static final int LDY = 0x02;

	public static void step(TileEntityMonitor tE){
		byte opCode = tE.getByte(tE.REGPC);
		switch (opCode) {
			case LDX: {
				byte addr1 = tE.getByte((int)(tE.REGPC + 1));
				byte addr2 = tE.getByte((int)(tE.REGPC + 2));
				byte addr3 = tE.getByte((int)(tE.REGPC + 3));
				byte addr4 = tE.getByte((int)(tE.REGPC + 4));
				tE.REGX = tE.getByte((int)((addr1<<24) | (addr2<<16)|(addr3 << 8)|(addr4)));
				tE.REGPC += 5;
				break;
			}
			case LDY:{
				byte addr1 = tE.getByte((int)(tE.REGPC + 1));
				byte addr2 = tE.getByte((int)(tE.REGPC + 2));
				byte addr3 = tE.getByte((int)(tE.REGPC + 3));
				byte addr4 = tE.getByte((int)(tE.REGPC + 4));
				tE.REGY = tE.getByte((int)((addr1<<24) | (addr2<<16)|(addr3 << 8)|(addr4)));
				tE.REGPC += 5;
				break;
			}

		}
	}







}
