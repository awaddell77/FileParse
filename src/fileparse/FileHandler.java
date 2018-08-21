/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileparse;
import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author awaddell
 */
abstract public class FileHandler{
    public int length = 0;
    protected String fName;
    protected String Tdir;
    protected ArrayList<HashMap> fData = new ArrayList<>();
    protected String saveDir;
    protected int headerLoc = 0;
    protected String[] header;
    
    public FileHandler(String fName) {
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
    public ArrayList<HashMap> getData(){
        return this.fData;
    
}
    public String fNameExtract(){
        String[] path = this.fName.split(System.getProperty("file.separator"));
        
        return path[path.length-1];
        
    }
    public String getPathWFile(){
        return this.Tdir + "/" + this.fName;
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
    public void setDir(String nDir){
        this.Tdir = nDir;
    }
    protected boolean checkDir(){
        File checker = new File(this.Tdir);
        return checker.exists();
        
        
    }

    
    private void mkDir(String dir, boolean inCWD){
        if (inCWD){
            dir = this.Tdir + System.getProperty("file.separator") + dir;
        }
        File check = new File(dir);
        if (!check.exists()){
            check.mkdir();
        }
    
        
        
    }

    abstract public String[] getHeader();
    abstract public void loadFile();
    //abstract public void exportData();
    //abstract public int lineCount();
    abstract public HashMap getRow(int rownum);
    abstract public void importData(ArrayList<HashMap> nData);
    
    
    
}
