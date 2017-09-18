import java.util.*;


public class CountingFrequency {
	private ReadFile File;
    Dictionary<Byte, Integer> Dict = new Hashtable<Byte, Integer>();
    
    CountingFrequency(ReadFile file){
    	this.File = file;
        this.Dict = makeDictionary(File);
    }

	public Dictionary<Byte, Integer> makeDictionary(ReadFile file2) {
		Dictionary<Byte, Integer> Dict2 = new Hashtable<Byte, Integer>();
		for (int i=0; i < file2.list.size(); i++){
			byte key = file2.list.get(i);
            if(Dict2.get(key)!= null){
                int value = Dict2.get(key); 
                Dict2.put(key, value+1);
            }
            else
            	Dict2.put(key, 1);
            //System.out.println(Dict2);
		}
		
		return Dict2;
	}
	
	public Dictionary<Byte, Integer> getFreqDict(){return Dict;}
}
