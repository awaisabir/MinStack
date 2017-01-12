package comp2402a3;

import java.util.AbstractQueue;
import java.util.Iterator;

/**
 * An implementation of a Stack that also can efficiently return  
 * the minimum element in the current Stack.  Every operation -
 * push, pop, peek, and min - must run in constant time
 *
 * @param <T> the class of objects stored in the queue
 */
public class MinStack<T extends Comparable<T>> extends SLListStack<T> {
	
	public SLListStack<T> reg_stack = new SLListStack<T>();
	public SLListStack<T> min_stack = new SLListStack<T>();
	
	T currentMinimum;
	
	/* PUSH  */
	public T push(T x)
	{
		//pushing into the list
		Node u = new Node();
		u.x = x;
		u.next = head;
		head = u;
		if (n == 0)
			tail = u;
		n++;
		
		//if the stack is empty then push into both stacks
		if(reg_stack.n == 0)
		{
			reg_stack.push(x);
			min_stack.push(x);
					
		} 
		//else just push into the regular stack
		else
			reg_stack.push(x);
		
		//currentMin is the first element of the minStack			
		currentMinimum = min_stack.peek();

		//compare the pushed item and the currentmin
		int compareValue = currentMinimum.compareTo(reg_stack.peek());
		

		//if the pushed value is lower than the minimum then add it to the minStack and update the currentMin
		if(compareValue >= 0)
		{
			min_stack.push(x);
			currentMinimum = min_stack.peek();
		}
		
		return x;
	}
 	
	/* POP */
	public T pop()
	{
		//popping from the list
		if (n == 0)	return null;
		T x = head.x;
		head = head.next;
		if (--n == 0) tail = null;
		
		
		/* If the popped element is the same then as the currentMin then 
		 * pop it off the minStack as well
		 * and update currentMin */
	    if(reg_stack.pop() == min_stack.peek())
		{
			min_stack.pop();
			currentMinimum = min_stack.peek();
		}	
		
		  return x;
	}
	public T min(){
		//
		// ...and add your min function code here
		//
		
		//return null if the list is empty
		if(reg_stack.size() == 0)
			return null;
		
		//return the first element in the minstack
		return min_stack.peek();
	}

	public static void main(String[] args){
		
		MinStack<Character> stack = new MinStack<Character>();
		
		String datasequence = "JKLMNFGHICDEBAA";
		for (int i = 0; i < datasequence.length(); i++){
			stack.push(datasequence.charAt(i));
			System.out.println(stack + ", min = " + stack.min());
		}
		while(stack.size() > 0) {
			stack.pop();
			System.out.println(stack + ", min = " + stack.min());
		}
	}

}
