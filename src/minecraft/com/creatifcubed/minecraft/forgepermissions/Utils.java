package com.creatifcubed.minecraft.forgepermissions;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

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
	public static void filePutContents(File f, String contents) throws IOException {
		FileWriter out = new FileWriter(f);
		out.write(contents);
		out.close();
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
	public static String[] convertStringArray(Object[] arr) {
		List<String> result = new LinkedList<String>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				result.add(arr[i].toString());
			}
		}
		return result.toArray(new String[result.size()]);
	}
	
	public static Number parseNumber(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			try {
				return Double.parseDouble(str);
			} catch (NumberFormatException ex2) {
				return null;
			}
		}
	}
}
