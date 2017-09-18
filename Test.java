//import java.io.File;
//import java.io.IOException;
//import java.lang.*;
//import java.util.*;
// 
///**
// * Created by Micke on 2015-02-20.
// */
//public class TestForHuffman {
//    private ArrayList<Node> nodes = new ArrayList<Node>();
//    private FileRead read;
//    Frequency freq;
//    CreateTree createTree;
//    SearchTree searchTree;
// 
//    TestForHuffman() throws IOException{
// 
//        this.read = new FileRead();
//        testNewList(read);
//        this.freq = new Frequency(read);
//        testFreqDict(freq, read);
//        this.createTree = new CreateTree(freq);
//        this.searchTree = new SearchTree(createTree);
//        testCreateNode();
//        if(testSortNode()){
//           System.out.println("Sort works!");
//        }
//        else{System.out.println("Sort doesnt work...");}
//        testNodeList();
//        testTree();
//        testSaveTree();
//        testOpenTree();
//        testSearchTree();
// 
// 
//    }
//    // if no input is added to arrayList.
//    public void testNewList(FileRead r){
//        if(r.getAsciiList().isEmpty()) {
//            System.out.println("arrayList empty");
//        }
//        else{
//           // System.out.println(r.getAsciiList());
//        }
//    }
// 
//    // removes everything in the asciiList except all components of element 0, whatever it is.
//    // Checks if the number of element in arrList is equal to the value for key(element 0) in freqDict.
//    public void testFreqDict(Frequency freq, FileRead r) {
// 
//        ArrayList<Byte> arrList = r.getAsciiList();
//        LinkedList lList = new LinkedList();
//        byte firstElement = arrList.get(0);
//        lList.add(firstElement);
//        arrList.retainAll(lList);
//        int size = arrList.size();
// 
//        if (freq.getFreqDict().get(firstElement).equals(size)) {
//        } else {
//            System.out.println("wrong num of ELEMENTS");
//        }
//    }
// 
//    /* This three should look like  (58)
//                              (25)      (33)
//                               4
//                                     (15)    (18)
//                                       10
//                                            (4) (14)
//                                             4    5
//     */
//    public void testTree() throws IOException{
//        CreateTree tree = new CreateTree(freq);
//        nodes.clear();
//        nodes.add(new Node((byte)10, 15));
//        nodes.add(new Node((byte)5, 14));
//        nodes.add(new Node((byte)4, 25));
//        nodes.add(new Node((byte)4, 4));
//        Node root = tree.createTree(nodes);
//        Node r0 = root.getLeftNode();
//        if((r0.getFrequency()!= 25) && r0.getLeaf() != 4) {
//            System.out.println("fail in r0");
//        }
//        Node r1 = root.getRightNode();
//        if(r1.getFrequency() != 33) {
//            System.out.println("fail in r1");
//        }
//        Node r10 = r1.getLeftNode();
//        if(r10.getFrequency()!= 15 && r10.getLeaf() != 10) {
//            System.out.println("fail in r10");
//        }
//        Node r11 = r1.getRightNode();
//        if(r11.getFrequency() != 18) {
//            System.out.println("fail in r11");
//        }
//        Node r110 = r11.getLeftNode();
//        if(r110.getFrequency() != 4 && r110.getLeaf() != 4){
//            System.out.println("fail in r110");
//        }
//        Node r111 = r11.getRightNode();
//        if(r111.getFrequency() != 14 && r111.getLeaf() != 5) {
//            System.out.println("fail in r111");
//        }
//        }
//        // sorting by frequncy;
// 
//    public void testSaveTree() throws IOException{
//        SaveTree tree = new SaveTree(searchTree);
//        String c = tree.getPath();
//        if(new File(c).isFile()){
//            System.out.println("File exist");
//        }
//        else{
//            System.out.println("File doesnt exist");
//        }
//        //System.out.println(tree.getPath());
// 
//    }
// 
//    public void testOpenTree() throws IOException{
//        //SaveTree tree = new SaveTree();
//        CreateTree tree = new CreateTree(freq);
//        BitFileWriter openTree = new BitFileWriter();
//        SearchTree searchTree = (SearchTree) openTree.getInObject();
//        if(tree.getRoot().getFrequency() == searchTree.getRoot().getFrequency()){
//            System.out.println("rootfreqs are equal after reading from Tree.ser");
//            System.out.println(tree.getRoot().getFrequency());
//        }
//        else {
//            System.out.println("roots arent equal");
//        }
// 
// 
//    }
// 
//    public boolean testSortNode() {
// 
//            nodes.add(new Node((byte)10, 15));
//            nodes.add(new Node((byte)5, 14));
//            nodes.add(new Node((byte)4, 25));
//            nodes.add(new Node((byte)4, 4));
// 
//            Collections.sort(nodes);
// 
//            if (nodes.get(0).getFrequency() > nodes.get(1).getFrequency()) {
//                return false;
//            } else if (nodes.get(1).getFrequency() > nodes.get(2).getFrequency()) {
//                return false;
//            } else if (nodes.get(2).getFrequency() > nodes.get(3).getFrequency()) {
//                return false;
//            } else {
//                return true;
//            }
//                /*for(int i = 0; i <4; i++){
//                System.out.print(nodes.get(i).getFrequency() + ", ");
//            }
//            System.out.println("");
//            Collections.sort(nodes);
//            for(int i = 0; i <4; i++){
//                System.out.print(nodes.get(i).getFrequency() + ", ");
//            }*/
//        }
// 
//        // Checks if every leaf in nodeList exist in the frequency Dictionary. leaf is keys in the dictionary.
//        public void testNodeList() throws IOException{
//        CreateTree tree = new CreateTree(freq);
//        Frequency freq = new Frequency(read);
//        try {
//            for (int i = 0; i < tree.getNodeList().size(); i++) {
//                int leaf = tree.getNodeList().get(i).getLeaf();
//                freq.getFreqDict().get(leaf);
//            }
//        }catch (NullPointerException e){System.out.println("leaf doesnt exist in Dict");}
// 
//    }
// 
// 
//        public void testCreateNode(){
//            Node node1 = new Node((byte)10,15);
//            Node node2 = new Node((byte)5, 14);
//            Node node3 = new Node(node1, node2, node1.getFrequency() + node2.getFrequency());
//            if(node3.getHasLeaf()){
//                System.out.println("node3 doesnt have a leaf");
//            }
//            if(node1.getHasLeaf() == false){
//                System.out.println("node1 has leaf");
//            }
//            int node1freq = node3.getLeftNode().getFrequency();
// 
//            if(node1.getFrequency() != node1freq){
//                System.out.println("node1freq and node1 freq are equal.....");
//            }
// 
// 
//        }
// 
// 
//      public void testSearchTree(){
//         Node node1 = new Node((byte)1, 3);
//         Node node2 = new Node((byte)33, 5);
//         Node node3 = new Node((byte)22, 4);
//         Node node4 = new Node(node1, node3, 8);
//         Node rootNode = new Node(node2, node4, 13);
//         SearchTree st = new SearchTree(rootNode);
//         String c = st.searchForLeaf((byte) 1);
//          System.out.println(c);
// 
//      }
// 
// 
//    public static void main(String[] args)throws IOException{
//        TestForHuffman test = new TestForHuffman();
// 
//    }
//}
// 
// 
// 
// 
///* Algorithm
//LŠs in en valfri fil, kan vara txt en img osv. LŠs in bit fšr bit!.*/