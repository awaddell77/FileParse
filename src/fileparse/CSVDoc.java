
package fileparse;


public interface CSVDoc<E> extends SpreadsheetDoc<E>{
    char getDelimiter();
    void setDelimiter(char c);
    
    
}
