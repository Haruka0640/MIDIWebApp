package control.arraylist;

import java.util.ArrayList;

public class ControllArrayList {


	public ArrayList<Object> invert(ArrayList<Object> list,int pattern){
		
    	@SuppressWarnings("unused")
		Object baf;
    	Object baf2;
    	int arrayLength;
    	
    	arrayLength = list.size();
    	
    	for (int i = 0; i < pattern; i++) {
    		baf = list.get(0);
        	for (int j = 1; j < arrayLength ; j++) {
        		baf2 = list.get(j);
        		list.set(j - 1,baf2);
        	}
    	}
    	
    	return list;
		
	}
	
}
