/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileparse;
import java.io.File;

/**
 *
 * @author awaddell
 */
abstract class FileHandler<T>{
    public int length = 0;
    protected String fName;
    protected String Tdir;
    protected T fData;
    protected String saveDir;
    
    public FileHandler(String fName) {
        this.fName = fName;
        this.Tdir = null;
        
}
    public FileHandler(String fName, String Tdir) {
        this.fName = fName;
        this.Tdir = Tdir;
        
}
    public String getFname(){
        return this.fName;
    }
    public T getData(){
        return this.fData;
    
}
    public String fNameExtract(){
        String[] path = this.fName.split(System.getProperty("file.separator"));
        
        return path[path.length-1];
        
    }
    public String getDir(){
        return this.Tdir;
    }
    protected String dirExtract(){
        String temp;
        if (this.Tdir == null){
            temp = this.fName;
        }
        else{
            return this.Tdir;
            
        }
        
        
    
        String[] path = temp.split("/");
        //String[] dir = new String[path.length-2];
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
    protected void mkDir(String dir, boolean inCWD){
        if (inCWD){
            dir = this.Tdir + System.getProperty("file.separator") + dir;
        }
        File check = new File(dir);
        if (!check.exists()){
            check.mkdir();
        }
        
        
        
    }
    abstract public void openFile();
    //abstract public void exportData();
    //abstract public int lineCount();
    
    
    
}
