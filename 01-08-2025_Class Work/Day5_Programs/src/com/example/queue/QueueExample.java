package com.example.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExample {
	
	public static void main(String[] args) {
		
//	Queue<String> q = new LinkedList(); // In one common queue or a general queue we can use enqueue(adding)--offer or dequeue (Deleting)-poll
//	
//	q.offer("Niti"); // insertion from back but it does not throws an exception 
//	q.offer("Jatin");
//	q.offer("Ayesha");
//	q.add("dfshj");
//	
//	
//	System.out.println("Those who are in queue are given as :" + q);
//	q.remove();
//	q.poll(); // retrieving the element and then delete from the front
//	
//	System.out.println("Those who are in queue are given as :" + q);
//	
//	System.out.println("The element retreived " + q.element());// retrieving the element but not removing (so before polling we can retrieve ) but it throws an exception 
//	System.out.println(q.size());
//	System.out.println(q.isEmpty());
//	System.out.println(q.peek());// retrieving the element but not removing (so before polling we can retrieve )
//	
//	
//	//so to retrieve the element from the middle , we have convert into list to work with the index no's 
//	List<String> list = new ArrayList<>(q);
//	System.out.println(list.get(1));
//	
//	
//	//  we can get the element using for loop also 
//	int i =0;
//	int indexno =1;
//	for(String items : q)
//	{
//		if(i == indexno)
//		{
//			
//			System.out.println("Element at index : " + i + " item is : " + items);
//			break;
//		}
//		i++;
//	}
//	

	   // which is faster than the linked list -- It will be useful for stack or double-ended queue operations
		Queue<String> q = new ArrayDeque(); // In one common queue or a general queue we can use enqueue(adding)--offer or dequeue (Deleting)-poll
		
		q.offer("Niti"); // insertion from back but it does not throws an exception 
		q.offer("Jatin");
		q.offer("Ayesha");
		q.add("dfshj");
		
		
		System.out.println("Those who are in queue are given as :" + q);
		q.remove();
		q.poll(); // retrieving the element and then delete from the front
		
		System.out.println("Those who are in queue are given as :" + q);
		
		System.out.println("The element retreived " + q.element());// retrieving the element but not removing (so before polling we can retrieve ) but it throws an exception 
		System.out.println(q.size());
		System.out.println(q.isEmpty());
		System.out.println(q.peek());// retrieving the element but not removing (so before polling we can retrieve )
		
		
		//so to retrieve the element from the middle , we have convert into list to work with the index no's 
		List<String> list = new ArrayList<>(q);
		System.out.println(list.get(1));
		
		
		//  we can get the element using for loop also 
		int i =0;
		int indexno =1;
		for(String items : q)
		{
			if(i == indexno)
			{
				
				System.out.println("Element at index : " + i + " item is : " + items);
				break;
			}
			i++;
		}
		
	  System.out.println("_______________ Priority Queue Example _________");
	//	Queue<Integer> pq = new LinkedList <>(); // ordinary queue 
		Queue<Integer> pq = new PriorityQueue <>(); // Priority Queue ( Sorted Queue)  -- It' not the guaranteed one , ( If we are traversing using iterator or foreach )
		pq.offer(500); pq.offer(70) ; pq.offer(50);
		System.out.println("The elements " + pq);
		System.out.println(pq.poll()); // It is lowest one   // Scheduling 
		
}
}
