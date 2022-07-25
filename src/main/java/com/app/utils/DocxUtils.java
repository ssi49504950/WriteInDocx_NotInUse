package com.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public final class DocxUtils {

	private DocxUtils() {}
	
	public static XWPFDocument createDoc() {
		
		return new XWPFDocument();
	}
	
	public static XWPFParagraph createNewParagraph(XWPFDocument doc) {
		return doc.createParagraph();
	}
	
	public static XWPFRun createRun(XWPFParagraph paragraph) {
		return paragraph.createRun();
	}
	
	public static void addImageInDocx(XWPFRun run, String imgPath, int imgHeight, int imgWidth) {
		
		int imageType = -1;
		if(imgPath.contains(".png")) imageType = XWPFDocument.PICTURE_TYPE_PNG;
		else if(imgPath.contains(".jpg") || imgPath.contains(".jpeg")) imageType = XWPFDocument.PICTURE_TYPE_JPEG;
		
		File image = null;
        FileInputStream imageData = null;
        String imageFileName = "";
        try {
        	image = new File(imgPath);
        	imageFileName = image.getName();
			imageData = new FileInputStream(image);
		} catch (FileNotFoundException e2) {
			System.out.println("Invalid image Path Provided : " + imgPath);
			System.out.println("Application is Shutting Down...");
			System.exit(1);
		}
        
		try {
			run.addPicture(imageData, imageType, imageFileName,
			               Units.toEMU(imgWidth),
			               Units.toEMU(imgHeight));
		} catch (InvalidFormatException e2) {
			System.out.println("Adding Picture - Invalid Format Exception");
			System.out.println("Application is Shutting Down...");
			System.exit(1);
		} catch (IOException e2) {
			System.out.println("Adding Pictiure - IOException");
			System.out.println("Application is Shutting Down...");
			System.exit(1);
		}
		
		run.addBreak();
		
		Utils.closeFileInputStream(imageData);
		
	}
	
	public static void addTextInDocx(XWPFRun run, String text) {
		
		run.setText(text);
		run.addBreak();
		
	}
	
	public static void writeToDocxAndClose(XWPFDocument doc, String dirPath, String newDocxFileName) {
		
		String fileName = dirPath + "/" + newDocxFileName;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File("" + fileName));
			doc.write(fos);
		} catch (Exception e) {
			System.out.println("Failed to Write to Docx File");
			System.out.println("Application is Shutting Down...");
			System.exit(1);
		}
		
		try {
			DocxUtils.closeXWPFDocument(doc);
			Utils.closeFileOutputStream(fos);
		} catch (Exception e) {
			System.out.println("Failed to Close XWPFDocument Object Or FileOutputStream Object");
			System.out.println("Application is Shutting Down...");
			System.exit(1);
		}
		
	}
	
	public static void closeXWPFDocument(XWPFDocument doc) {
		try {
			doc.close();
		} catch (IOException e) {
			System.out.println("Got issue while closing XWPFDocument");
		} finally {
			try {
				doc.close();
			} catch (IOException e) {
				System.out.println("Got issue while closing XWPFDocument in finally block");
			}
		}
	}
}
