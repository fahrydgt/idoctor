package controller;

import java.util.Arrays;
import java.util.List;

import model.medicine;

public class AlgorithmDrugMatcher {

	 public static String [] list(String gt,List<medicine> mlist) {
		 
		 String[] arrDrugList=new String[mlist.size()];
		
		 	for(int j=0;j<mlist.size();j++) {
			 arrDrugList[j]=mlist.get(j).getName();
		 	}
	
	    	String arr[]=new String[5];//creating returning array
	    	int count[]=new int[arrDrugList.length];//for count probability
	    	int count1[]=new int[arrDrugList.length];//for sort probaility
	        
	    	char cgt1;//get char value for google apn word 
	    	char clt1;//get char value for Array value
	    	for(int i=0;i<gt.length();i++)//recognized text gt
	    	{
	    		 cgt1=gt.charAt(i);
	    		 
	    		for(int m=0;m<arrDrugList.length;m++)//number of drugs in array
	    		{
	    			if(arrDrugList[m].length()>i)//Check length of a drug to avoid null exception 
	                    {
	                        clt1=arrDrugList[m].charAt(i);
	    				
	    				if(Character.toLowerCase(cgt1)==Character.toLowerCase(clt1))
	    					{
	    						if(i==0)//firstLetter Priority
	    							{
	    								count[m]=count[m]+2;
	    								count1[m]=count1[m]+2;
	    							}
	    						if(i==1)//Second Letter Priority
	    							{
                                
	    								count[m]=count[m]+1;
	    								count1[m]=count1[m]+1;
	    							}
                     
	    					count[m]++;
	                        count1[m]++;
	                         }	
	    				
	   System.out.println(arrDrugList[m]+"--"+count[m]);
	                   }	
	    		}	
	    	}
	    	
	// sorting array
	   Arrays.sort(count1);
	       
	   int a=0;
	   int b=arrDrugList.length-1;
	   while(a!=5){
		   for(int i=arrDrugList.length-1;i>=0;i--){
			   if(count1[b]==count[i]){
				   arr[a]=arrDrugList[i];
	    		   a++;
	                   if(a==5) {
	                	   break;
	                   	}
	                   
	                   b--;
			   }
		   }
	   }
	   
	       return arr;
	 }
	 
}
