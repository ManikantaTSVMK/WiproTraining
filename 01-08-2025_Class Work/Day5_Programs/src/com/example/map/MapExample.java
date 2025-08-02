package com.example.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// We store the data or elements in the form of key / value pair
public class MapExample {
	
	
	// Main category and in that you have to save subcategories 
	public static void main(String[] args) {
		
		Map<String , List<String>>  map = new HashMap<>();
		
		boolean isEmpty = map.isEmpty();
		System.out.println(" Map is Empty " + isEmpty);
		
		map.put("Stationary", Arrays.asList("Notebooks" , " Pencil" , "Eraser"));
		map.put("Electronics", Arrays.asList("Laptop" , "Mouse" , "Speakers"));
		
		for(Map.Entry<String,List<String>> entries : map.entrySet())
		{
			
			System.out.println("Category :" + entries.getKey());
			System.out.println("SubCategories :" + entries.getValue());
		}
		
		int map1size = map.size();
		
		System.out.println("Map size is : " + map1size);
		
		Map<Integer , String >  newmap = new HashMap<>();
		
		boolean isEmpty1 = newmap.isEmpty();
		System.out.println(" Map is Empty " + isEmpty1);
		
//		newmap.putAll(map);
//		
//		System.out.println(" Map elements are  :  " + newmap);
//		
//		newmap.remove(102);
//		
//		System.out.println(" After deletion the Map elements are  : " + newmap);
//		
//		//newmap.clear();
//		
//		int key = 101;
//		System.out.println(newmap.containsKey(key));
//		String keyvalue = newmap.get(key);
//		System.out.println("The value based on key is " + keyvalue) ;
//		System.out.println(newmap.containsValue("Niti"));
//		
//		Iterator itr = newmap.values().iterator();
//		
//		while(itr.hasNext())
//		{
//			Object obj = itr.next();
//			System.out.println(obj);
//			System.out.println(newmap.entrySet());
//			
//			
//			
//		}
		
	}
	

	

}
