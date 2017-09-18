import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class BitFileReader {
 
    private Object savedTreeObject;
    private SearchTree searchTree;
    private ArrayList<Byte> bytesList = new ArrayList<Byte>();
    private ArrayList<String> binaryStringList = new ArrayList<String>();
    private Dictionary<Integer, String> Dictor = new Hashtable<Integer,String>();
 
    BitFileReader(String treeFile, String compressedFile, String newOrgCode) throws FileNotFoundException, IOException {
        this.searchTree = null;
        this.savedTreeObject = null;
        FileInputStream treeIn = new FileInputStream(treeFile);
        ObjectInputStream in = new ObjectInputStream(treeIn);
        try {
            this.savedTreeObject = in.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Cant find file");
        	}
        in.close();
        treeIn.close();
        this.searchTree = (SearchTree) this.savedTreeObject;
 
        FileInputStream huffman = new FileInputStream(compressedFile);
        FileOutputStream outOrg = new FileOutputStream(newOrgCode);
        
        fileToByteList(huffman);
//        System.out.println(bytesList);

        getBinString();
//      System.out.println(binaryStringList);
        
        writeToFile(outOrg);
        huffman.close();
        outOrg.close();
 
    }
    
    public void fileToByteList(FileInputStream File) throws IOException {
    	byte[] singleByte = new byte[1];
        while ((File.read(singleByte, 0, 1)) != -1) {
            byte b = singleByte[0];
            bytesList.add(b);
            }
        }
    
    public void getBinString() {
    	String bin="";
        while(bytesList.size() > 2){
        	int x = bytesList.get(0);
        	if (Dictor.get(x)!= null){
        		bin = Dictor.get(x);
            	binaryStringList.add(bin);
            	
        		}
        	else{
        		calculateBinary(x);
            	
        		}
        	bytesList.remove(0);
        	}

        calculateBinary(bytesList.get(0),bytesList.get(1)); //calculateBinary(x,lastLength);
        }
    
    public void calculateBinary(int x){
        String binString = "";
        int key = x;
        boolean neg = false;
        if (x < 0) {
            neg = true;
            x = x + 128;
        	}
        while (x != 0) {
            if (x % 2 == 0) {
                x = x / 2;
                binString = "0" + binString;
            	} 
            else if (x % 2 == 1) {
                x = x / 2;
                binString = "1" + binString;
            	}
            }
 
        int strLength = binString.length();
        if(strLength<7){
            int y = 7-strLength;
            for(int j = 0; j<y ; j++){
                binString = "0" + binString;
            	}
            }
 
        if (neg) {
            binString = "1" + binString;
        	}
        else if(!neg){
            binString = "0" + binString;
        	}
        
        Dictor.put(key,binString);
        binaryStringList.add(binString);
        binString = "";
    	}
    
    public void calculateBinary(int x, int length){
    	if(length == 8){
            calculateBinary(x);
        	}
    	else if(x==0){
    		String binString="";
    		for(int i = 0; i < length;i++){
                binString = "0" + binString;
            	}
    		binaryStringList.add(binString);
    		}
    	else {
            String binString1 = "";
            while (x != 0) {
                if (x % 2 == 0) {
                    x = x / 2;
                    binString1 = "0" + binString1;
                }else if (x % 2 == 1) {
                    x = x / 2;
                    binString1 = "1" + binString1;
                	}
            }
            if(binString1.length() != length){
            	int l = binString1.length();
                for(int i = 0; i < length-l; i++){
                    binString1 = "0" + binString1;
                	}
            	}
            binaryStringList.add(binString1);
        	}
        }
 

    public void writeToFile(FileOutputStream out) throws IOException{
        ArrayList<String> pathList = new ArrayList<String>();
        String eight = "";
        String path ="";
        for(int i = 0; i<binaryStringList.size(); i++){
        	eight = binaryStringList.get(i);
        	for(int j =0; j< eight.length();j++ ){
        		path = String.valueOf(eight.charAt(j));
        		pathList.add(path);
        	
            if(searchTree.searchPath(pathList)){
            	byte[] b = new byte[1];
            	b[0]     = searchTree.getOrgLeaf();
            	out.write(b);
            	pathList.clear();
            	}
        	}
        }
    }
 
}