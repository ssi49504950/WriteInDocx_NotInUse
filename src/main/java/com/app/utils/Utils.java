package com.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Utils {
	
	private Utils() {}
	
	private static boolean dirValidate(String dirPath) {
		
		File dirPathFile = new File(dirPath);
		
		return dirPathFile.isDirectory();
		
	}
	
	public static void dirValidator(String dirPath) {
		if(!Utils.dirValidate(dirPath)) {
			System.out.println("Invalid directory provided. App is Shutting Down...");
			System.exit(1);
		}
	}
	
	public static List<String> getImagePathAsList(String dirPath){
		List<String> list = new ArrayList<String>();
		
		File dirPathFile = new File(dirPath);
		
		// Getting File list from Directory and adding them in list as String 
			File[] listOfFiles = dirPathFile.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
				  list.add(listOfFiles[i].getAbsolutePath());
			  }
			}
			
			list = list.parallelStream()
						.filter(e -> (e.contains(".png") || e.contains(".jpg") || e.contains(".jpeg")))
						.sorted()
						.collect(Collectors.toList());
		
			return list;
	}
	
	public static String getSentence(String path) {
	
		int index = path.lastIndexOf("\\");
		if(index < 0) {
			index = path.lastIndexOf("/");
		}
		
		String sentence = path.substring(index + 1);
		
		if(sentence.contains(".png")) sentence = sentence.replace(".png", "");
		else if (sentence.contains(".jpg")) sentence = sentence.replace(".jpg", "");
		else if (sentence.contains(".jpeg")) sentence = sentence.replace(".jpeg", "");
		
		return sentence;
	}
	
	public static void closeFileInputStream(FileInputStream fis) {
		try {
			fis.close();
		} catch (IOException e) {
			System.out.println("Failed to close FileInputStream");
			System.out.println("Application is Shutting Down");
			System.exit(1);
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				System.out.println("Failed to close FileInputStream");
				System.out.println("Application is Shutting Down");
				System.exit(1);
			}
		}
	}
	
	public static void closeFileOutputStream(FileOutputStream fos) {
		try {
			fos.close();
		} catch (IOException e) {
			System.out.println("Failed to close FileOutputStream");
			System.out.println("Application is Shutting Down");
			System.exit(1);
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				System.out.println("Failed to close FileOutputStream");
				System.out.println("Application is Shutting Down");
				System.exit(1);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public static List<FileInputStream> getFileInputStream(String dirPath) {
		List<FileInputStream> fisList = new ArrayList<>();
		
		List<String> fileList = getImagePathAsList(dirPath);
		
		for(int i=0; i< fileList.size(); i++) {
			File image = null;
			FileInputStream imageData = null;
			try {
	        	image = new File(fileList.get(i));
				imageData = new FileInputStream(image);
				fisList.add(imageData);
			} catch (FileNotFoundException e2) {
				System.out.println("Invalid image Path Provided in index " + i + ". Application is Shutting Down...");
				System.exit(1);
			}
		}
		return fisList;		
		
	}
	
	
	public static List<String> getFileNamesAndCreateSentences(String dirPath) {
		
		List<String> list = new ArrayList<String>();
		
		//String dirPath = "C:/Users/ssi49/Desktop/test";
		
		File dirPathFile = new File(dirPath);
		
		// if provided path does not exists, then return an empty List object
		// if(!dirPathFile.isDirectory()) return Collections.emptyList();
		
		
		// Getting File list from Directory and adding them in list as String 
		File[] listOfFiles = dirPathFile.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
			  list.add(listOfFiles[i].getName());
		  }
		}
		
		// Removing files that does not contain (.png or .jpg or .jpeg) 
		// Removing extention from file name
		// Sort the List in Ascending order
		list = list.parallelStream()
			.filter(e -> (e.contains(".png") || e.contains(".jpg") || e.contains(".jpeg")))
			.map( e -> {
						if(e.contains(".png")) return e.replace(".png", "");
						if(e.contains(".jpg")) return e.replace(".jpg", "");
						if(e.contains(".jpeg")) return e.replace(".jpeg", "");
						return e;
					    }
					)	
			.sorted()
			.collect(Collectors.toList());
		
		
		
		return list;
	}

}
