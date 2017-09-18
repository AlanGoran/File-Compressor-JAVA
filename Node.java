import java.io.Serializable;

public class Node implements Comparable<Node>, Serializable {

	private static final long serialVersionUID = 1L;
	private byte leaf;
    private boolean leafExist;
    
    private Node leftChild;
    private Node rightChild;
 
    private int frequency;
 
    Node(Node left, Node right, int frequency){
    	
    	this.leafExist = false;
        this.leftChild = left;
        this.rightChild = right;
        this.frequency = frequency;
    	}
    
    Node(byte leaf, int frequency){
        
    	this.leafExist = true;
    	this.leaf = leaf;
        this.frequency = frequency;
    	}
	
	//Implemented method to sort the queue.	
	@Override
	public int compareTo(Node node) {
		//Sort to the bottom of the queue.
		if(this.getFrequency() < node.getFrequency()){
			return -1; 
			}
		//Sort to the top of the queue.
		else if(this.getFrequency() > node.getFrequency()){
			return 1; 
			}
		//The queue is empty.
		else
			return 0;
		}
	
	public byte getLeaf(){
		return leaf;}
    public int getFrequency(){
    	return frequency;}
    public boolean getleafExist(){
    	return leafExist;}
    public Node getLeftNode(){
    	return leftChild;}
    public Node getRightNode(){
    	return rightChild;}
    
}    
	
//	public static ArrayList<String> encoding(Node root, String binary){
//		if(root.character != ""){
//			encodedArray.add(root.character + binary);
//		}
//		if(root.leftChild != null){
//			encoding(root.leftChild, binary +"0");
//		}
//		if(root.rightChild != null){
//			encoding(root.rightChild, binary +"1");
//		}
//		return encodedArray;
//		
//	}
//	
//	public static Node huffmanTree(int frequencies[], String characters[]){
//		//Goes through character and frequency arrays and creates nodes of the elements and adds them to the queue.
//		PriorityQueue<Node> tempQueue = new PriorityQueue<Node>();
//		for(int i=0;i<characters.length;i++){
//			Node node = new Node(characters[i], frequencies[i]);
//			tempQueue.add(node);
//		}
//		Node root = null;
//		while(tempQueue.size()>1){
//			Node lowestFreq = tempQueue.poll();
//			Node nextLowestFreq = tempQueue.poll();
//			Node addedFreq = new Node(lowestFreq.frequency+nextLowestFreq.frequency);
//			addedFreq.rightChild = lowestFreq;
//			addedFreq.leftChild = nextLowestFreq;
//			lowestFreq.parent = addedFreq;
//			nextLowestFreq = addedFreq;
//			tempQueue.add(addedFreq);
//			root = addedFreq;
//
//		}
//		return root;		
//	}
//	
//}
//
//
