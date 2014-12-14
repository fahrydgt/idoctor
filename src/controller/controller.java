package controller;

import java.util.ArrayList;
import java.util.List;


import model.medicine;

public class controller {
	
	 AlgorithmDrugMatcher adm=new AlgorithmDrugMatcher();
	 
	 //Returning list for Spinner 1 with first five high priority drugs
	 public List<String> GetList(ArrayList<String> text,List<medicine> mlist){
		List<String> list = new ArrayList<String>();
     	list.add(adm.list(text.get(0),mlist)[0]);
     	list.add(adm.list(text.get(0),mlist)[1]);
     	list.add(adm.list(text.get(0),mlist)[2]);
     	list.add(adm.list(text.get(0),mlist)[3]);
     	list.add(adm.list(text.get(0),mlist)[4]);
     	
     	return list;
     	
	 }
	 
	 //Retturnig list for Spinner 2 with default drugs having method
	 public List<String> getList1(){
		 List<String> list1 = new ArrayList<String>();
     	 list1.add("BD");
     	 list1.add("TDS");
     	 list1.add("One/Day");
     	 
     	 return list1;
	 }
	 
	 //returning list for Spinner 3 with capcity of drugs
	 public List<String> setCapacity(medicine drg){
		 String[] ar1=null;
		 ar1=drg.getCapacity().split(",");
			
	    List<String> list2 = new ArrayList<String>();
	    for(int k=0;k<ar1.length;k++){
	    	list2.add(ar1[k]);
	    }
	    
	    return list2;
		 
	 }

}
