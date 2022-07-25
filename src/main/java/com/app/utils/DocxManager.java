package com.app.utils;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocxManager {
	
	
	public void manage(String dirPath, String newDocxName, int imgHeight, int imgWidth){
		
		Utils.dirValidator(dirPath);
		
		XWPFDocument doc = DocxUtils.createDoc();
		
		XWPFParagraph paragraph = DocxUtils.createNewParagraph(doc);
		
		XWPFRun run = DocxUtils.createRun(paragraph);
		
		List<String> list = Utils.getImagePathAsList(dirPath);
		
		for(int i=0; i < list.size(); i++ ) {
			
			String imgPath = list.get(i);
			
			DocxUtils.addImageInDocx(run, imgPath, imgHeight, imgWidth);
			
			String text = Utils.getSentence(imgPath);
			
			DocxUtils.addTextInDocx(run, text);
		}
		
		DocxUtils.writeToDocxAndClose(doc, dirPath, newDocxName);
		
		
	}

}
