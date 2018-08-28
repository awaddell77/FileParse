
package fileparse;
import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author awaddell
 * @param <E>
 */
abstract public class FileHandler<E> implements SpreadsheetDoc<E> {
    public int length = 0;
    protected String fName;
    protected String Tdir;
    protected ArrayList<HashMap<E, E>> fData = new ArrayList<>();
    protected String saveDir;
    protected int headerLoc = 0;
    protected String[] header;
    //protected String delim; 
    
    public FileHandler  (String fName) {
        this.fName = fName;
        this.Tdir = Paths.get("").toString();
        
}
    
    public FileHandler(String fName, String Tdir) {
        this.fName = fName;
        this.Tdir = Tdir;
            
}
    public FileHandler(){
        this.fName = null;
        this.Tdir = Paths.get("").toString();
    }
    public String getFname(){
        return this.fName;
    }
    public ArrayList<HashMap<E, E>> getData(){
        return this.fData;
    
}
    public String fNameExtract(){
        String[] path = this.fName.split(System.getProperty("file.separator"));
        
        return path[path.length-1];
        
    }
    public String getPathWFile(){
        return this.Tdir + System.getProperty("file.separator") + this.fName;
    }
    public String getDir(){
        return this.Tdir;
    }
    public int getHeaderLoc(){
        return this.headerLoc;
        
    }
    protected String dirExtract(){
        String temp = this.fName + this.Tdir;
        String[] path = temp.split("/");
        String ndir = "";
        for (int i = 0; i < path.length-1; i++){
            ndir += path[i] + System.getProperty("file.separator");
        }
        return ndir;
    }
    public void fixNameDir(){
        setDir();
        this.fName = fNameExtract();
    }
    public void setDir(){
        this.Tdir = dirExtract();
        
    }
    /*
    public void setDir(String nDir){
        this.Tdir = nDir;
    }
    public void setDelim(String delim){
        this.delim = delim;
    }*/
    public int sizeTest(){
        return this.fData.size();
    }
    protected boolean checkDir(){
        File checker = new File(this.Tdir);
        return checker.exists();
    }
    
    @Override
    public void setHeaderLoc(int rowNum){
        if (this.length < rowNum){
            throw new java.lang.IndexOutOfBoundsException("File is only " + this.length + 
                    " rows.");
            
        }
        else{
            this.headerLoc = rowNum;
        }
        
    }
            


    
    protected void mkDir(String dir, boolean inCWD){
        if (inCWD){
            dir = this.Tdir + System.getProperty("file.separator") + dir;
        }
        File check = new File(dir);
        if (!check.exists()){
            check.mkdir();
        }
    
        
        
    }
    protected String[] getLineData(HashMap<E, E> hashLine){
        String[] line = new String[this.header.length];
        for (int i = 0; i < this.header.length; i++){
            line[i] =  (String) hashLine.get(this.header[i]);
        }
        return line;
} 
    public boolean isMultSheet(){
        return false;
    }

    //abstract public HashMap<E,E> getRow(int rownum);
    //abstract public Object[] getHeader();
    ///abstract public void loadFile();
    //abstract public void exportData();
    //abstract public int lineCount();
    
    //abstract public void importData(ArrayList<HashMap<E, E>> nData);
    //abstract public void exportData(String fnameWPath);
    
    
    
    
}
