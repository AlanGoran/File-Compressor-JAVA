import java.io.*;

public class SaveHuffmanTree implements Serializable {
	private static final long serialVersionUID = 1L;
	private SearchTree tree;
    private String s;
    
    SaveHuffmanTree(String treeFileName, SearchTree theTree) throws IOException{
        this.tree = theTree;
        try {
            FileOutputStream fileOut = new FileOutputStream(treeFileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tree);
            out.close();
            fileOut.close();
        }catch(Exception e){
        	System.err.println("Fel fil");
        	}
    	}
 
 
    public String getPath(){
    	return s;}
}
