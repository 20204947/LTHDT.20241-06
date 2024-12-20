package model.setting;

public class Setting {
	private static int speed = 500;
	private static int valueOfSmallGem = 1;
	private static int valueOfBigGem = 5;
	
	public static int getSpeed() {
		return speed;
	}
	public static void setSpeed(int speed) {
		Setting.speed = speed;
	}
	public static int getValueOfSmallGem() {
		return valueOfSmallGem;
	}
	public static void setValueOfSmallGem(int valueOfSmallGem) {
		Setting.valueOfSmallGem = valueOfSmallGem;
	}
	public static int getValueOfBigGem() {
		return valueOfBigGem;
	}
	public static void setValueOfBigGem(int valueOfBigGem) {
		Setting.valueOfBigGem = valueOfBigGem;
	}
	
	
}
