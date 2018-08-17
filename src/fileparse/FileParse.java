
package fileparse;
import fileparse.FileHandler;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FileParse {


    public static void main(String[] args) throws IOException {
        //Path test2 = Paths.get("C:\\Users\\awaddell\\Documents\\NetBeansProjects\\FileParse\\src\\fileparse\\kohlerrep.csv");
        URL res =  FileParse.class.getClassLoader().getResource("kohlerrep.csv");
        String test5;
        System.out.println(System.getProperty("file.separator").toString());
        String nRes = res.toString().substring(6);
        TestClass testInst = new TestClass(nRes);
        String dir = testInst.dirExtract();
        System.out.println(nRes);
        System.out.println(testInst.dirExtract());
        testInst.setDir();
        testInst.mkDir("TESTFOLDER", true);
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
        CSVReader f = new CSVReader(reader);
        System.out.println("SUCCESS");
        String[] test = null;
        while((test = f.readNext()) != null){
           // System.out.println(Arrays.toString(test));
            
        }
    }
    
}
class TestClass extends FileHandler{
    public TestClass(String fName){
        super(fName);
        this.fData = "TEST";
        
    }
    @Override
    public void openFile(){
        System.out.println("DONE");
    }
    
}
