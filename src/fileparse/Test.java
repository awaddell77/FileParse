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

//for testing only
public class Test {
    
 public static void main(String[] args) throws IOException {
        Path testp = Paths.get("C:\\Users\\awaddell\\Documents\\NetBeansProjects\\FileParse\\src\\fileparse\\kohlerrep.csv");
        Path testp2 = Paths.get("C:\\Users\\awaddell\\Documents\\NetBeansProjects\\FileParse\\src\\fileparse\\kohlerrep.csv");
        String fname3 = FileParse.fNameExtract("C:\\Users\\awaddell\\Documents\\NetBeansProjects\\FileParse\\src\\fileparse\\kohlerrep.csv");
        String orig = "kohlerrep.csv";
        String fname4 = FileParse.fNameExtract("kohlerrep.csv");
        System.out.println("FNAME4: " + fname4);
        System.out.println(orig.equals(fname4));
        URL res =  FileParse.class.getClassLoader().getResource("kohlerrep.csv");        
        String test5;
        System.out.println(System.getProperty("file.separator").toString());
        String nRes = res.toString().substring(6);
        CSVHandler testInst = new CSVHandler(testp.toString());
        

        System.out.println(nRes);
        System.out.println(testInst.dirExtract());
        testInst.loadFile();
        System.out.println(Arrays.toString(testInst.getHeader()));
        String dir = testInst.dirExtract();
        //testInst.setDir();
        
        
        Path test2 = Paths.get(nRes);
        Path test3 = Paths.get(testInst.dirExtract());
        System.out.println("PATH: " + test2.toString());
        System.out.println("PATH2: " + test3.toString()); 
        
        Reader reader = null;
        try {
            
            reader = Files.newBufferedReader(test2);
           
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return;
        }
    }
    
}
