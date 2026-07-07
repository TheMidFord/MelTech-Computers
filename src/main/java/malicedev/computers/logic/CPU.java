package malicedev.computers.logic;

import malicedev.computers.tileentities.TileEntityComputer;

public class CPU {
	private CPU(){}
	public static final int INTERRUPT = 0x00;
	public static final int LOAD_X = 0x01;
	public static final int LOAD_Y = 0x02;
	public static final int LOAD_A = 0x03;
	public static final int STORE_X = 0x04;
	public static final int STORE_Y = 0x05;
	public static final int STORE_A = 0x06;
	public static final int MULTIPLY_X = 0x07;
	public static final int MULTIPLY_Y = 0x08;
	public static final int SHIFT_X = 0x09;
	public static final int SHIFT_Y = 0x0A;
	public static final int XORX = 0x0B;
	public static final int XORY = 0x0C;
	public static final int ANDX = 0x0D;
	public static final int ANDY = 0x0E;
	public static final int ORX = 0x0F;
	public static final int ORY = 0x11;
	public static final int JUMP = 0x12;
	public static final int CONDITIONAL_JUMP = 0x13;


	public static void step(TileEntityComputer tE){
		byte opCode = tE.getByte(tE.REGPC);
		switch (opCode) {
			case LOAD_X -> {
				byte addr1 = tE.getByte(tE.REGPC + 1);
				byte addr2 = tE.getByte(tE.REGPC + 2);
				byte addr3 = tE.getByte(tE.REGPC + 3);
				byte addr4 = tE.getByte(tE.REGPC + 4);
				tE.REGX = tE.getByte((addr1<<24) | (addr2<<16)|(addr3 << 8)|(addr4));
				tE.REGPC += 5;

			}
			case LOAD_Y -> {
				byte addr1 = tE.getByte(tE.REGPC + 1);
				byte addr2 = tE.getByte(tE.REGPC + 2);
				byte addr3 = tE.getByte(tE.REGPC + 3);
				byte addr4 = tE.getByte(tE.REGPC + 4);
				tE.REGY = tE.getByte((addr1<<24) | (addr2<<16)|(addr3 << 8)|(addr4));
				tE.REGPC += 5;

			}
			case LOAD_A -> {
				byte addr1 = tE.getByte(tE.REGPC + 1);
				byte addr2 = tE.getByte(tE.REGPC + 2);
				byte addr3 = tE.getByte(tE.REGPC + 3);
				byte addr4 = tE.getByte(tE.REGPC + 4);
				tE.REGA = tE.getByte((addr1<<24) | (addr2<<16)|(addr3 << 8)|(addr4));
				tE.REGPC += 5;
			}

		}
	}







}
