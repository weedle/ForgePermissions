package com.creatifcubed.minecraft.forgepermissions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.bouncycastle.util.Arrays;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class Utils {
	private Utils() {
		return;
	}
	
	public static String fileGetContents(File f) throws IOException {
		return Files.toString(f, Charsets.UTF_8);
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
