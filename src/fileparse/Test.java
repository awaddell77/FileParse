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
        Path target = Paths.get("C:\\Users\\awaddell\\Documents\\kohlerrep.csv");
        //Path target = Paths.get("C:\\Users\\awaddell\\Documents\\NetBeansProjects\\FileParse\\src\\fileparse\\kohlerrep.csv");

        FileHandler testobj = FileParse.importData(target);
        //System.out.println("TEST OBJ DIR: " + testobj.Tdir);
        System.out.println(System.getProperty("file.separator").toString());
        System.out.println(Arrays.toString(testobj.getHeader()));
        System.out.println(testobj.getClass());
        
        
        

        
        //testInst.setDir();
        
        

        
        
    }
    
}
