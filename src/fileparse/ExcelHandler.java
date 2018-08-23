/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;


//http://poi.apache.org/overview.html#components
/**
 *
 * @author awaddell
 */
class ExcelHandler<E> extends FileHandler<E> {
    
    
    public ExcelHandler(String fName){
        super(fName);
    }
    public ExcelHandler(String fName, String tDir){
        super(fName, tDir);
        
    }
    @Override
    public void loadFile(){
        Workbook wkbk = null;
        try {
            wkbk = WorkbookFactory.create(new File(this.getPathWFile()));
        } catch (IOException | InvalidFormatException | EncryptedDocumentException ex) {
            Logger.getLogger(ExcelHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        int n = wkbk.getNumberOfSheets();
        for (int i = 0; i < n; i++){
            Sheet sheet = wkbk.getSheetAt(i);
            SheetC temp = new SheetC(sheet.getSheetName());
            sheet.getRow(0).
            DataFormatter dataFormatter = new DataFormatter();
            int len = sheet.getLastRowNum();
            for (int i2 = 1; i < len; i++){
                Row temprow = sheet.getRow(i2);
                
                for (Cell c: temprow){
                    String val = c.getStringCellValue();
                    c.getColumnIndex()
                    
                    
                }
            }
        }
        
        
        
        

    }
    
    
    
    
    
    @Override
    public void exportData(String fNameWPath){
        
    }

    @Override
    public String[] getHeader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap getRow(int rownum) {
        //returns row of sheet in Hashmap form
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importData(ArrayList<HashMap<E, E>> nData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private class SheetC{
        private final String sheetName;
        private ArrayList<HashMap> sheetData = new ArrayList<>();
        public SheetC(String sheetName){
            this.sheetName = sheetName;
        }
        public String getName(){
            return this.sheetName;
        }
        public void addRow(HashMap hashLine){
            sheetData.add(hashLine);
            
        }
        
        
    }
}
