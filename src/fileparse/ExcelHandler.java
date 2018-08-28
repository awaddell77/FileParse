/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


//http://poi.apache.org/overview.html#components
/**
 *
 * @author awaddell
 */
class ExcelHandler<E> extends FileHandler<E> implements ExcelWorkbook<E> {
    private String targetSheet;
    private boolean isMultSheet = false;
    private HashMap<String, SheetC> multSheet = new HashMap<>();
    private ArrayList<String> sheetNames = new ArrayList<>();
    
 
    
    
    public ExcelHandler(String fName){
        super(fName);
    }
    public ExcelHandler(String fName, String tDir){
        super(fName, tDir);
        
        
    }
    

    @Override
    public void loadFile(){
        Workbook wkbk = null;
        System.out.println("PATH: " + this.getPathWFile());
        try {
            wkbk = WorkbookFactory.create(new File(this.getPathWFile()));
        } catch (IOException | InvalidFormatException | EncryptedDocumentException ex) {
            Logger.getLogger(ExcelHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        int n = wkbk.getNumberOfSheets();
        System.out.println("FILE HAS " + n + " SHEETS");
       
        if (n > 1){
            this.isMultSheet = true;
            this.loadFM(wkbk);
            
            
            
        }
        else{
            
            //this.sheetNames.add(wkbk.getSheetName(0));
            this.fData = this.processSheet(wkbk, 0);
        }


    }
    private ArrayList<HashMap<E, E>> processSheet(Workbook wkbk, int shNum){
        this.header = null;
        Sheet sheet = wkbk.getSheetAt(shNum);
        ArrayList<HashMap<E, E>> tempArrLst = new ArrayList<>();
        int shLen = sheet.getLastRowNum();
        Row header = sheet.getRow(0);
        int rLen = header.getLastCellNum();
        String[] tempHead = new String[rLen];
        //String str = sheet.getSheetName();
        //this.sheetNames.add(str);
        //System.out.println("ADDING " + str + " to sheetnames");
        
        for (int i= 0; i < rLen; i++){
            
            Cell cell = header.getCell(i);
            tempHead[i] = cell.getStringCellValue();
                       
        }
        this.header = tempHead;
        //System.out.println("HEADER:");
        //System.out.println(Arrays.toString(this.header));
        
        for (int i = 1; i < shLen; i++){
            Row row = sheet.getRow(i);
            DataFormatter dataFormatter = new DataFormatter();
            //int len = sheet.getLastRowNum();
            HashMap tempHash = new HashMap<>();
            
            for (int i2 = 0; i2 < row.getLastCellNum(); i2++){
                Cell tempCell = row.getCell(i2);
                String val = dataFormatter.formatCellValue(tempCell);
                
                tempHash.put(this.header[i2], val);
            }
            System.out.println(Arrays.toString(this.getLineData(tempHash)));
            tempArrLst.add(tempHash);
            tempHash.clear();
        }
        return tempArrLst;
    }
    private void loadFM(Workbook wkbk){
        int shNum = wkbk.getNumberOfSheets()-1;
        for (int i = 0; i <= shNum; i++){
            //System.out.println("SHEET NAMES : " + wkbk.getSheetName(i));
            //System.out.println("STORED SHEET NAMES : " + Arrays.toString(this.sheetNames.toArray()));
            Sheet sheet = wkbk.getSheetAt(i);
            this.sheetNames.add(sheet.getSheetName());
            ArrayList<HashMap<E, E>> tempArr = this.processSheet(wkbk, i);
            String[] tempHead = this.header;
            SheetC tempSheet = new SheetC(tempArr, sheet.getSheetName(),tempHead);
            this.multSheet.put(tempSheet.getName(),tempSheet);
            
            
            
        }
        
        
    }
    
    @Override
    public ArrayList<String> getSheetNames(){
        return this.sheetNames;
    }
    @Override
    public boolean isMultSheet(){
        return this.isMultSheet;
      
    }
    
    
    @Override
    public void exportData(String fNameWPath){
        FileWriter tFile = null;
        try {
            tFile = new FileWriter(fNameWPath);
        } catch (IOException ex) {
            Logger.getLogger(CSVHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public String[] getHeader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String[] getHeader(String sheetName){
        return this.multSheet.get(sheetName).getHeader();
        
    }
        
    @Override
    public String[] getHeader(int sheetNum){
        String name = this.sheetNames.get(sheetNum);
        return this.multSheet.get(name).getHeader();
        
    }

    @Override
    public HashMap<E,E> getRow(int rownum) {
        //returns row of the first sheet in Hashmap form
        return this.fData.get(rownum);
        
    }
    @Override
    public HashMap<E,E> getRow(int sheetnum, int rownum ) {
        //returns row of the first sheet in Hashmap form
        if (this.isMultSheet){
            return this.multSheet.get(this.sheetNames.get(sheetnum)).getRow(rownum);
        }
        return fData.get(rownum);
        
        
        
    }

    @Override
    public void importData(ArrayList<HashMap<E, E>> nData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportData(String fNameWPath, String sheetName) {
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper(); //helps with formatting 
        int len = this.sheetNames.size();
        for (int i= 0; i < len; i++) {
           createSheet(this.multSheet.get(this.sheetNames.get(i)));
        }
        
    
        
    }
    private Sheet createSheet(SheetC shData){
        return null;
        
        
    }
    private class SheetC{
        private final String sheetName;
        private ArrayList<HashMap<E, E>> sheetData = new ArrayList<>();
        private String[] header;
        public int length = 0;
        public SheetC(String sheetName, String[] header){
            this.sheetName = sheetName;
            this.header = header;
        }
       public SheetC(ArrayList<HashMap<E, E>> arr, String sheetName, String[] header){
            this.sheetData = arr;
            this.sheetName = sheetName;
            this.header = header;
            this.length = arr.size();
        }
        public String getName(){
            return this.sheetName;
        }
        public String[] getHeader(){
            return this.header;
                    
        }
        public HashMap<E, E> getRow(int rNum){
            return sheetData.get(rNum);
        }

    }
}
