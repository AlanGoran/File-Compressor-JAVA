import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class SearchTree implements Serializable{
	private static final long serialVersionUID = 1L;
	private Node root;
    private String binary;
    private byte leaf;
 
    SearchTree(BuildTree tree) throws IOException {
        this.root = tree.getRoot();
    	}
 
    SearchTree(Node root) {
        this.root = root;
    	}
 
    // Just for test code
    public Node getRoot() {
        return this.root;
    	}
    
    public String getBin() {
        return binary;
    	}
 
    public void setLeaf(byte b) {
        this.leaf = b;
    }
 
    public byte getOrgLeaf() {
        return leaf;
    }
 
 
    public boolean searchPath(ArrayList<String> b) {
        Node node = root;
        if(!root.getleafExist()) {
            for (int i = 0; i < b.size(); i++) {
                if (b.get(i).equals("0")) {
                    node = node.getLeftNode();
                	} 
                else if (b.get(i).equals("1")) {
                    node = node.getRightNode();
                	}              
            	}
            if (node.getleafExist()) {
                setLeaf(node.getLeaf());
                return true;
            	} 
            else 
                return false;
        	}
        else{
            setLeaf(root.getLeaf());
            }
        return true;
    	}
 
 
 
    public String searchForLeaf(byte b) {
            if(!root.getleafExist()) {
                Node leftNode = root.getLeftNode();
                Node rightNode = root.getRightNode();
 
                if (!searchN(leftNode, "0", b)) { //SEND BYTE INSTEAD OF STRING
                    searchN(rightNode, "1", b);
                }
                return getBin();
            }
 
       return "0";
    }
    
    public boolean searchN(Node n, String path, byte b) {
    	 
        if (n.getLeftNode() != null) {
            searchN(n.getLeftNode(), path + "0", b);
        	}
        if (n.getRightNode() != null) {
            searchN(n.getRightNode(), path + "1", b);
        	}
 
        if (n.getRightNode() == null && n.getLeftNode() == null) {
            if (n.getLeaf() == b) {
            	//System.out.println(path);
                setBin(path);
                return true;
            	}
        	}
        return false;
    	}
    
    public void setBin(String s) {
        this.binary = s;
    	}
}
