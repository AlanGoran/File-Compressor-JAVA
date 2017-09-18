import java.io.IOException;
import java.io.Serializable;
import java.util.*;


public class BuildTree implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private CountingFrequency freq;
	private Node root;
	private ArrayList<Node> nodeArray = new ArrayList<Node>();
	
	BuildTree(CountingFrequency frequency) throws IOException{
		this.freq = frequency;
        this.nodeArray = createNodeList();
        this.root = buildTree();
		}
	
	//Creates a list of nodes with leafExistence=true
	public ArrayList<Node> createNodeList(){
        ArrayList<Node> nodeArray = new ArrayList<Node>();
        for (Enumeration<Byte> e = freq.Dict.keys(); e.hasMoreElements();) { //Itererar genom nycklarna i dictionary
            byte leaf = (Byte) e.nextElement();
            int frequency = (Integer) freq.Dict.get(leaf);
            nodeArray.add(new Node(leaf, frequency));
        	}
        return nodeArray;
    	}
	
	public Node getRoot(){
    	return root;}
    public ArrayList<Node> getNodeList(){
    	return nodeArray;}
    
    
	public Node buildTree() {
		while (nodeArray.size() > 2){
            Collections.sort(nodeArray);
            nodeArray.add(newNode());
        	}
        Collections.sort(nodeArray);
        Node root = newNode();
        return root;
		}
	
	public Node newNode() {
		if(nodeArray.size()!=1) {
            int newFreq = nodeArray.get(0).getFrequency() + nodeArray.get(1).getFrequency();
            Node newNode = new Node(nodeArray.get(0), nodeArray.get(1), newFreq);
            nodeArray.remove(0);
            nodeArray.remove(0);
            return newNode;
        	}
        Node nodeSing = new Node(nodeArray.get(0).getLeaf(), nodeArray.get(0).getFrequency());
        return nodeSing;
		}

	
	//For test
	public Node buildTree(ArrayList<Node> node) {
        while (node.size() > 2) {
            Collections.sort(node);
            node.add(newNode(node));
        	}
        Collections.sort(node);
        return newNode(node);
    	}
	
    public Node newNode(ArrayList<Node> node){
        int newFreq = node.get(0).getFrequency() + node.get(1).getFrequency();
        Node newNode = new Node(node.get(0), node.get(1), newFreq);
        node.remove(0);
        node.remove(0);
        return newNode;
    	}
 
 
    
}
