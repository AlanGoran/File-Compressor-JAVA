import java.io.*;
import java.util.*;


public class BitFileWriter {
	private Object savedTreeObject;
    private SearchTree searchTree;
    
    private FileInputStream inOrg = null;
    private FileOutputStream out   = null;
    
    private ArrayList<String> binaryList = new ArrayList<String>();
    private ArrayList<String> byteString = new ArrayList<String>();
    private ArrayList<Byte>   bytesToFile = new ArrayList<Byte>();
    private String totBinStr = "";
    
	public BitFileWriter(String treeFile, String fileToCompress, String compressedFile) throws IOException, FileNotFoundException {
		this.savedTreeObject = null;
        this.searchTree = null;

        FileInputStream fileIn = new FileInputStream(treeFile);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        try {
            this.savedTreeObject = in.readObject();
        }catch (ClassNotFoundException e) {
            System.err.println("bitFileWriter Error 1");
        	}
        in.close();
        fileIn.close();
        this.searchTree = (SearchTree) this.savedTreeObject;
        
        ////////////////////////////////////////
        
        try {
            this.out = new FileOutputStream(compressedFile);
        }catch (Exception e) {
        	System.err.println("bitFileWriter Error 2");
        
        }
        
        ///////////////////////////////////
        try {
            this.inOrg = new FileInputStream(fileToCompress);
        } catch (Exception e){
        	System.err.println("bitFileWriter Error 3");
        	}
        
        addToBinaryList(inOrg);

        getBinaryFromString();
        
        writeToFile(out);
        inOrg.close();
        out.close();
        
		}
	
	
	
	// Creates a list "binaryList" where each element is the binary path to each leaf
	public void addToBinaryList(FileInputStream file) throws IOException {
        byte[] ascii = new byte[1];
        Dictionary<Byte, String> Diction = new Hashtable<Byte, String>();
        String binaryNumb;
        while ((file.read(ascii, 0, 1)) != -1) {
            byte b = ascii[0];
            
            if(Diction.get(b)!= null){
            	binaryNumb = Diction.get(b);
            	binaryList.add(binaryNumb);
            	//totBinStr = totBinStr + binaryNumb;
            }
            else {
	            binaryNumb = searchTree.searchForLeaf(b);
	            
	            Diction.put(b,binaryNumb);
	            
	            binaryList.add(binaryNumb);
            	//totBinStr = totBinStr + binaryNumb;

            }
        }
    }
	
	public void getBinaryFromString(){
        String binary = "";
        byte byteValue;
        Integer lastValue = 0;
        
        String shortBinStr = "";
        for(int l=0; l<binaryList.size();l++){
        	shortBinStr = binaryList.get(l);
        	for(int m=0; m<shortBinStr.length();m++){
                if (binary.length() == 8){
        			byteValue  = calcByteFromBinary(binary);
                    bytesToFile.add(byteValue);
                    binary="";
        		}
                binary = binary + shortBinStr.charAt(m);
                lastValue = binary.length();
        	}
        }
        
        byteValue  = calcByteFromBinary(binary);
        bytesToFile.add(byteValue);
        byteValue = lastValue.byteValue();
        bytesToFile.add(byteValue);
        
    }
	
	public byte calcByteFromBinary(String binary) {
        Integer intValue = 0;
        byte byteValue;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.substring(i, i + 1).equals("1")) {
                double x = Math.pow(2, binary.length() - 1 - i);
                intValue = intValue + (int) x;
            	}
        	}
        byteValue =  intValue.byteValue();
        return byteValue;
    	}
	
	
	
	
	public void writeToFile(FileOutputStream out)throws  IOException{
        byte[] b = new byte[1];
        for(int i = 0; i < bytesToFile.size(); i++){
            b[0] = bytesToFile.get(i);
            out.write(b);
        	}
    	}
	
	
	//Metod som ser till att bara 8 siffror i taget adderas i byteString arrayen
	public void createByteString(){
		String temp="";
		int nextLast= 0;
		int i = 0; 
		try{	
			
			while(true){
				int st = i*8;
				int en= (i+1)*8;
				
				temp = totBinStr.substring(st, en);
				byteString.add(temp);

				nextLast = en;
				
				if(totBinStr.length()-nextLast ==0)
					byteString.add("8");
				i++;
				
				}
			}
		catch(StringIndexOutOfBoundsException e){
			temp = totBinStr.substring(nextLast, totBinStr.length());
			byteString.add(temp);
			byteString.add(Integer.toString(temp.length()));
		}
	}

	
	// For testClass;
    public Object getInObject() {
        return savedTreeObject;
    }
 
    BitFileWriter(String path) throws IOException {
        this.searchTree = null;
 
        this.savedTreeObject = null;
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            this.savedTreeObject = in.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Cant find file");
        }
        in.close();
        fileIn.close();
 
 
 
        this.searchTree = (SearchTree) this.savedTreeObject;
    }
}
