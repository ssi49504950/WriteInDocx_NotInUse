package com.app.test.roughTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.testng.annotations.Test;

import com.app.utils.Utils;

public class RoughTest {
	
	
	@Test(enabled = false)
	public void m1() {
		
		// Step 1: Creating a blank document
		XWPFDocument doc = new XWPFDocument();
	
		// Step 2: Creating a Paragraph using
        // createParagraph() method
		XWPFParagraph paragraph = doc.createParagraph();
		XWPFRun tempRun = paragraph.createRun();
		
		
		// Step 3: Creating a file input stream of image by
        // specifying its path
		String imagePath = System.getProperty("user.dir") + "/xpress_ltd_3.png";
        File image = null;
        FileInputStream imageData = null;
        try {
        	image = new File(imagePath);
			imageData = new FileInputStream(image);
		} catch (FileNotFoundException e2) {
			System.out.println("Invalid image Path Provided");
		}
        
        
        // Step 4: Retrieving the image file name and image
        // type
        int imageType = XWPFDocument.PICTURE_TYPE_PNG;
        String imageFileName = image.getName();
       
        // Step 6: Setting the width and height of the image
        // in pixels.
        int width = 450;
        int height = 400;
        
        // Step 7: Adding the picture using the addPicture()
        try {
			tempRun.addPicture(imageData, imageType, imageFileName,
			               Units.toEMU(width),
			               Units.toEMU(height));
		} catch (InvalidFormatException e2) {
			System.out.println("Adding Picture - Invalid Format Exception");
		} catch (IOException e2) {
			System.out.println("Adding Pictiure - IOException");
		}
		
		tempRun.addBreak();
		tempRun.setText("Adding a Sentence from JAVA");
		
		
//		// Step 8: CREATING FOLDER
//		String folderName = System.getProperty("user.dir") + "/docx/";
//		
//		File folder = null;
//		try {
//			folder = new File(folderName);
//			folder.mkdir();
//			System.out.println("Created Folder : " + folderName);
//			
//		}catch (Exception e) {
//			System.out.println("Invalid Folder Path provided");
//		}

		
		
		// Step 9: Creating a File output stream of word
        // document at the required location
		String fileName = "newFile.docx";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File("" + fileName));
			doc.write(fos);
			doc.close();
			fos.close();
		} catch (Exception e) {
			System.out.println("Invalid File Path provided");
			try {
				doc.close();
				fos.close();
			} catch (IOException e1) {
				System.out.println("Error closing XWPFDocument doc");
			}
		}
		
	}
	
	
	@Test(enabled = true)
	public void m2() {
		// C:\Users\ssi49\Desktop\test
		// C:\Users\ssi49\Desktop\test\Step4_1_Selected_A_Confirmation.png
		
		String dirPath = "C:\\Users\\ssi49\\Desktop\\test/";
		int lastIndex = dirPath.length() - 1;
		char ch = dirPath.charAt(lastIndex);
		if(ch == '/' || ch == '\\') {
			dirPath = dirPath.substring(0, lastIndex);
		}
		
		System.out.println(dirPath);
		
	}
	

}
