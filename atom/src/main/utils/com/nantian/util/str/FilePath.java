package com.nantian.util.str;

/**
 * 格式化文件路径的类
 * @author 猫小游
 *
 */
public class FilePath {
	public static String formatFolderPath(String path){
		path = path.replace("/", "\\");
		
		if (!path.substring(path.length() - 1).equals("\\")) {
			path += "\\";
		}
		
		return path;
	}
	
	public static void main(String[] args){
		System.out.println(formatFolderPath("E:\\path/哈哈哈"));
	}
}
