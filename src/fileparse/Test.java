/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileparse;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

//for testing only
public class Test {
    
 public static void main(String[] args) throws IOException {
        Path target = Paths.get("C:\\Users\\awaddell\\Documents\\kohlerrep.csv");
        Path target2 = Paths.get("C:\\Users\\awaddell\\Documents\\kohlerrep.xlsx");
        Path target3 = Paths.get("C:\\Users\\awaddell\\Documents\\kohlerrepM.xlsx");

        CSVDoc testobj = FileParse.importCSV(target);
        FileHandler testobj2 = FileParse.importData(target2);
        ExcelWorkbook testobj3 =  FileParse.importExcel(target3);
        //System.out.println("TEST OBJ DIR: " + testobj.Tdir);
        System.out.println(System.getProperty("file.separator").toString());
        System.out.println(Arrays.toString(testobj.getHeader()));
        System.out.println(testobj.getClass());
        //System.out.println("Document has " + testobj.sizeTest() + " lines");
        //testobj.exportData("C:\\Users\\awaddell\\Documents\\kohlerrep3test.csv");
        System.out.println(testobj2.getClass());
        testobj.loadFile();
        System.out.println("HEADER: " + Arrays.toString(testobj.getHeader()));
        //testobj2.loadFile();
        //System.out.println(Arrays.toString(testobj2.getSheetNames().toArray()));
        //HashMap j = testobj2.getRow(3);
        //testobj3.loadFile();
        
        System.out.println(Arrays.toString(testobj3.getSheetNames().toArray()));
        //System.out.println(Arrays.toString(testobj2.getLineData(j)));
        
        
        
        

        
        //testInst.setDir();
        
        

        
        
    }
    
    
}
