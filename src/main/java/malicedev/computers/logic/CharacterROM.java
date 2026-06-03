package malicedev.computers.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CharacterROM {
	private CharacterROM(){}
	//First 16
	public static final int CHAR_SPACE=0;
	public static final int CHAR_EXCLAMATION=1;
	public static final int CHAR_QUOTES=2;
	public static final int CHAR_HASH=3;
	public static final int CHAR_DOLLAR_SIGN=4;
	public static final int CHAR_PERCENT=5;
	public static final int CHAR_AMPERSAND=6;
	public static final int CHAR_APOSTROPHE=7;
	public static final int CHAR_OPEN_PARENTHESIS=8;
	public static final int CHAR_CLOSED_PARENTHESIS=9;
	public static final int CHAR_ASTERISK=10;
	public static final int CHAR_PLUS=11;
	public static final int CHAR_COMMA=12;
	public static final int CHAR_MINUS=13;
	public static final int CHAR_FULLSTOP=14;
	public static final int CHAR_SLASH=15;
	//Second 16
	public static final int CHAR_ZERO=16;
	public static final int CHAR_ONE=17;
	public static final int CHAR_TWO=18;
	public static final int CHAR_THREE=19;
	public static final int CHAR_FOUR=20;
	public static final int CHAR_FIVE=21;
	public static final int CHAR_SIX=22;
	public static final int CHAR_SEVEN=23;
	public static final int CHAR_EIGHT=24;
	public static final int CHAR_NINE=25;
	public static final int CHAR_COLON=26;
	public static final int CHAR_SEMICOLON=27;
	public static final int CHAR_SMALLER_THAN=28;
	public static final int CHAR_EQUALS=29;
	public static final int CHAR_LARGER_THAN=30;
	public static final int CHAR_QUESTION=31;
	//Third 16
	public static final int CHAR_AT_SYMBOL=32;
	public static final int CHAR_A=33;
	public static final int CHAR_B=34;
	public static final int CHAR_C=35;
	public static final int CHAR_D=36;
	public static final int CHAR_E=37;
	public static final int CHAR_F=38;
	public static final int CHAR_G=39;
	public static final int CHAR_H=40;
	public static final int CHAR_I=41;
	public static final int CHAR_J=42;
	public static final int CHAR_K=43;
	public static final int CHAR_L=44;
	public static final int CHAR_M=45;
	public static final int CHAR_N=46;
	public static final int CHAR_O=47;
	//Fourth 16
	public static final int CHAR_P=48;
	public static final int CHAR_Q=49;
	public static final int CHAR_R=50;
	public static final int CHAR_S=51;
	public static final int CHAR_T=52;
	public static final int CHAR_U=53;
	public static final int CHAR_V=54;
	public static final int CHAR_W=55;
	public static final int CHAR_X=56;
	public static final int CHAR_Y=57;
	public static final int CHAR_Z=58;
	public static final int CHAR_OPEN_BRACKET=59;
	public static final int CHAR_BACKWARDS_SLASH=60;
	public static final int CHAR_CLOSED_BRACKET=61;
	public static final int CHAR_TOPOWEROF=62;
	public static final int CHAR_UNDERSCORE=63;
	//Fifth 16
	public static final int CHAR_GRAVE=64;
	public static final int CHAR_a=65;
	public static final int CHAR_b=66;
	public static final int CHAR_c=67;
	public static final int CHAR_d=68;
	public static final int CHAR_e=69;
	public static final int CHAR_f=70;
	public static final int CHAR_g=71;
	public static final int CHAR_h=72;
	public static final int CHAR_i=73;
	public static final int CHAR_j=74;
	public static final int CHAR_k=75;
	public static final int CHAR_l=76;
	public static final int CHAR_m=77;
	public static final int CHAR_n=78;
	public static final int CHAR_o=79;
	//Sixth 16
	public static final int CHAR_p=80;
	public static final int CHAR_q=81;
	public static final int CHAR_r=82;
	public static final int CHAR_s=83;
	public static final int CHAR_t=84;
	public static final int CHAR_u=85;
	public static final int CHAR_v=86;
	public static final int CHAR_w=87;
	public static final int CHAR_x=88;
	public static final int CHAR_y=89;
	public static final int CHAR_z=90;
	public static final int CHAR_OPEN_BRACE=91;
	public static final int CHAR_PIPE=92;
	public static final int CHAR_CLOSED_BRACE=93;
	public static final int CHAR_TILDE=94;
	public static final int CHAR_LOGO=95;

	public static byte[] charSet;
	static {
		try {
			charSet = Files.readAllBytes(Paths.get("C:\\Users\\Miles\\Downloads\\output.bin"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
