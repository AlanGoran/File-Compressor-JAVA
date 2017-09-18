import java.io.*;
import java.util.*;


public class ReadFile {
	private FileInputStream in = null; 
	ArrayList<Byte> list = new ArrayList<Byte>();
	
	ReadFile(String fileToCompress) throws FileNotFoundException, IOException, NullPointerException{ 
        try{
            this.in = new FileInputStream(fileToCompress);
            this.list = bytesToArrayList(in);
        }catch (Exception e){
        	System.out.println(e);
        }finally {
        	in.close();
        }
    }
	
    
    public ArrayList<Byte> bytesToArrayList(FileInputStream File) throws IOException{
        ArrayList<Byte> newList = new ArrayList<Byte>();
        byte[] ascii = new byte[1];
        //Reads the file and converts to byte element by element and returns the list
        while((File.read(ascii,0,1)) != -1){
            byte b = ascii[0];
            newList.add(b);
        }
        return newList;
    }
    
    public ArrayList<Byte> getList(){ 
    	return list;
    }
    
//  public boolean fileExistence(){
//  if(list.isEmpty())
//  	return false;
//  else 
//  	return true;
//}
    
}
