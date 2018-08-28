/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileparse;

import java.util.ArrayList;
import java.util.HashMap;


public interface SpreadsheetDoc<E> {
    void loadFile();
    void importData(ArrayList<HashMap<E, E>> nData);
    void exportData(String fnameWPath);
    int getHeaderLoc();
    void setHeaderLoc(int rowNum);
    String getFname();
    HashMap<E,E> getRow(int rownum);
    Object[] getHeader();
    
    
    
}
