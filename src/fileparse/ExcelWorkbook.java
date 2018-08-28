/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileparse;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author awaddell
 * @param <E>
 */
public interface ExcelWorkbook<E> {
    public ArrayList<E> getSheetNames();
    public boolean isMultSheet();
    public HashMap<E,E> getRow(int sheetnum, int rownum );
    
    public void exportData(String fNameWPath, String sheetName);//exports single sheet
    public String[] getHeader(String sheetName);
    public String[] getHeader(int sheetNum);
    
}
