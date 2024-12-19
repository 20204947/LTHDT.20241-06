package test;

import model.setting.Setting;

public class Test {
	public static void main (String[] args) {
		System.out.println(Setting.getSpeed());
		Setting.setSpeed(1000);
		System.out.println(Setting.getSpeed());
	}
}
