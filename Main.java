import java.io.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
        System.out.println("File name to compress:");
        String fileToCompress = sc.nextLine();

        ReadFile readFile = new ReadFile(fileToCompress);
 
        CountingFrequency freq = new CountingFrequency(readFile);
        
        BuildTree Tree = new BuildTree(freq);
        
        SearchTree searchTree = new SearchTree(Tree);
        
       
        SaveHuffmanTree saveTree = new SaveHuffmanTree("SavedTree.ser", searchTree);
        
        System.out.println("Compressed file name to save:");
        String compressedFile = sc.nextLine();
        BitFileWriter bitFileWriter = new BitFileWriter("SavedTree.ser", fileToCompress, compressedFile);
        
        System.out.println("Decompressed file name:");
        String decompressedFile = sc.nextLine();
        BitFileReader bitFileReader = new BitFileReader("SavedTree.ser", compressedFile, decompressedFile);
        
	}
}

