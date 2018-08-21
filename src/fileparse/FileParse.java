
package fileparse;

import java.io.IOException;
import java.util.LinkedList;


public class FileParse {


    public static FileHandler importData(String fname) throws IOException {
        String temp = fNameExtract(fname);
        if (!(fname.equals(temp)) && temp.contains(".csv")){
            CSVHandler mInst = new CSVHandler(temp, fname); //wrong needs to be fixed
            mInst.loadFile();
            
            return mInst;
            
        }
        else{
            //used for excel files
            //just has a placeholder for now
            CSVHandler mInst = new CSVHandler(fname);
            mInst.loadFile();
            return mInst;
        }

    }
    public static String fNameExtract(String fNameWPath){
        String[] path;
        //String[] path = fNameWPath.split(System.getProperty("file.separator"));
        if (System.getProperty("os.name").equals("win")){
            path = fNameWPath.split("\\\\");
        }
        else{
            path = fNameWPath.split("/");
            
        }
        return path[path.length-1];
}
    public static String dirExtract(String pathWFname){
        String[] path = pathWFname.split("/");
        String ndir = "";
        for (int i = 0; i < path.length-1; i++){
            ndir += path[i] + System.getProperty("file.separator");
        }
        return ndir;
    }
    public static String sep(boolean split){
        boolean isWin = System.getProperty("os.name").equals("win");
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

