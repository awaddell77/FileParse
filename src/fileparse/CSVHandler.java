package fileparse;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;

class CSVHandler<E> extends FileHandler<E> implements CSVDoc<E>{
    protected char delim = ',';
    public CSVHandler(String fName){
        super(fName);
        
        
        
    }
    public CSVHandler(String fName, String tDir){
        super(fName, tDir);
    }
    @Override
    public void setDelimiter(char c){
        this.delim = c;
    }
    @Override
    public char getDelimiter() {
        return this.delim;
    }


    @Override
    public void loadFile(){
        Reader reader = null;
        try {
            
            //reader = Files.newBufferedReader((Paths.get(this.fName)));
           reader = Files.newBufferedReader(Paths.get(this.getPathWFile()));
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        CSVReader temp =  new CSVReaderBuilder(reader)
                .withCSVParser(
                new CSVParserBuilder()
                        .withSeparator(this.delim)
                        .build())
                .build();
                
        
        int count = 0;
        String[] line;
        try {
            //TODO: conver to foreach loop using CSVreader's iterator
            while ((line = temp.readNext()) != null){
                if (count == this.headerLoc){
                    this.header = line;
                    count++;
                    
                }
                else{
                    HashMap lineH = new HashMap<>();
                    //will throw index error if there is a column will no header
                    for (int i = 0; i < line.length; i++){
                        lineH.put(this.header[i], line[i]);
                    }
                    this.fData.add(lineH);
                    count++;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CSVHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.length = count;
        
    }
    @Override
    public HashMap<E, E> getRow(int rownum){
        return this.fData.get(rownum);
    }
    
    @Override
    public  Object[] getHeader(){
        return this.header;
    }
    @Override
    public void importData(ArrayList<HashMap<E, E>> nData){
        this.fData = nData;
        this.length = nData.size();
        HashSet<Set> h = new HashSet<>();
        for (HashMap line: nData){
            h.add(line.keySet());
        }
        this.header = (String[]) h.toArray();
    }
    @Override
    public void exportData(String fnameWPath) {
        FileWriter tFile = null;
        
        try {
            tFile = new FileWriter(fnameWPath);
        } catch (IOException ex) {
            Logger.getLogger(CSVHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        CSVWriter writer = new CSVWriter(tFile);
        writer.writeNext(this.header);
        for (int i = 0; i < this.fData.size(); i++){
            writer.writeNext((String[]) this.getLineData(this.fData.get(i)));
            
        }
        try {
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(CSVHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
        
    }


    
    
    
}
