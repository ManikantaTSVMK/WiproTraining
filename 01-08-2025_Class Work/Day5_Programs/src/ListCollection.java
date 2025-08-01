
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 
		List<Comparable> list = new LinkedList<Comparable>();
		
		list.add(101);
		list.add("Niti");
		
		list.add(101);
		list.add("Niti");
		
		System.out.println("The list elements : " + list);
		
		
		List<List<Comparable>> aList = new ArrayList<List<Comparable>>();
		
		list.add(101);
		list.add("Niti");
		
		aList.add(list);
		
		System.out.println("The list elements : " + aList);
		

	}

}

