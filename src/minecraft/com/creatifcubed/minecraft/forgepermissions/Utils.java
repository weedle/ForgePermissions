package com.creatifcubed.minecraft.forgepermissions;

import org.bouncycastle.util.Arrays;

public class Utils {
	private Utils() {
		return;
	}
	
	public static String[] convertStringArray(int[] arr) {
		String[] result = new String[arr.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = String.valueOf(arr[i]);
		}
		return result;
	}
	
	public static String[] convertStringArray(boolean[] arr) {
		String[] result = new String[arr.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = String.valueOf(arr[i]);
		}
		return result;
	}
	
	public static String[] convertStringArray(double[] arr) {
		String[] result = new String[arr.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = String.valueOf(arr[i]);
		}
		return result;
	}
}
