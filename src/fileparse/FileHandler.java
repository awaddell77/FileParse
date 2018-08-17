/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileparse;

/**
 *
 * @author awaddell
 */
abstract class FileHandler<T>{
    protected String fName;
    protected T fData;
    public FileHandler(String fName) {
        this.fName = fName;
        
}
    public String getFname(){
        return this.fName;
    }
    public T getData(){
        return this.fData;
    
}
    abstract public void openFile();
    //abstract public void exportData();
    //abstract public int lineCount();
    
    
    
}
