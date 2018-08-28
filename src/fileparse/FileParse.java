
package fileparse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;


/*
importData method requires Path instead of string because converting from string
to path adds an extra \ to windows paths whereas converting from Path to string does not
*/
public class FileParse{
    public FileParse(){
        
        
    }



    public  static FileHandler importData(Path fname) throws IOException {
        String dirName = null;
        String temp = fNameExtract(fname.toString());
        
        if (!(fname.toString().equals(temp))){
            System.out.println("IT WENT HERE");
            dirName = Paths.get(dirExtract(fname.toString())).toString();
            System.out.println("dirName is: " + dirName);
        }
        if (temp.contains(".csv") && dirName != null){
            
            CSVHandler mInst = new CSVHandler(temp, dirName);
            mInst.loadFile();
            return mInst;
            
        }
        else if (temp.contains(".csv")){
            CSVHandler mInst = new CSVHandler(temp);
            mInst.loadFile();
            return mInst;
        }
        else if(temp.contains(".xls")){
            ExcelHandler mInst = new ExcelHandler(temp, dirName);
            return mInst;
        }
        else{
            //used for excel files
            //just has a placeholder for now
            CSVHandler mInst = new CSVHandler(fname.toString());
            mInst.loadFile();
            return mInst;
        }

    }
    public static CSVDoc importCSV(Path fname){
        String dirName = null;
        String temp = fNameExtract(fname.toString());
        
        if (!(fname.toString().equals(temp))){
            System.out.println("IT WENT HERE");
            dirName = Paths.get(dirExtract(fname.toString())).toString();
            System.out.println("dirName is: " + dirName);
        }
        if (temp.contains(".csv") && dirName != null){
            
            CSVHandler mInst = new CSVHandler(temp, dirName);
            mInst.loadFile();
            return mInst;
            
        }
        else{
            CSVHandler mInst = new CSVHandler(temp);
            mInst.loadFile();
            return mInst;
        }
        
        
    } 
    public  static ExcelWorkbook importExcel(Path fname) throws IOException {
        String dirName = null;
        String temp = fNameExtract(fname.toString());
        
        if (!(fname.toString().equals(temp))){
            System.out.println("IT WENT HERE");
            dirName = Paths.get(dirExtract(fname.toString())).toString();
            System.out.println("dirName is: " + dirName);
        }

        
        if(temp.contains(".xls")){
            ExcelHandler mInst = new ExcelHandler(temp, dirName);
            return mInst;
        }
        else{
            //used for excel files
            //just has a placeholder for now
            ExcelHandler mInst = new ExcelHandler(fname.toString());
            mInst.loadFile();
            return mInst;
        }

    }

    public static String fNameExtract(String fNameWPath){
        String[] path;
        //String[] path = fNameWPath.split(System.getProperty("file.separator"));
        if (System.getProperty("os.name").contains("Win")){
            path = fNameWPath.split("\\\\");
        }
        else{
            path = fNameWPath.split("/");
            
        }
        return path[path.length-1];
}
    public static String dirExtract(String pathWFname){
        String sep = sep(false);
        String[] path = pathWFname.split(sep(true));
        String ndir = "";
        for (int i = 0; i < path.length-1; i++){
            ndir += path[i] + sep;
        }
        return ndir;
    }
    public static String sep(boolean split){
        boolean isWin = System.getProperty("os.name").contains("Windows");
        if (isWin && split){
            return "\\\\";
        }
        else if (isWin){
            return "\\";
        }
        else{
            return "/";
        }
    }
}

