package malicedev.computers.logic;

import malicedev.computers.tileentities.TileEntityMonitor;

import static malicedev.computers.logic.CharacterROM.*;
import static malicedev.computers.tileentities.TileEntityMonitor.*;

	public class CPUInstructions {
	private CPUInstructions(){}
	public static final int LDX = 0x01;
	public static int[] thewholecharset = new int[charSet.length/8];

	public static void step(TileEntityMonitor tileEntityMonitor){
		byte opCode = tileEntityMonitor.getByte(REGPC, (byte)0x11);
		switch (opCode) {
			case LDX: {
				byte addr1 = tileEntityMonitor.getByte((short)(REGPC + 1),(byte)0x11 );
				byte addr2 = tileEntityMonitor.getByte((short)(REGPC + 2),(byte)0x11 );
				REGX = tileEntityMonitor.getByte((short)(addr1 | addr2 << 8),(byte)0x11 );
				REGPC += 3;
			}
		}
	}







}
