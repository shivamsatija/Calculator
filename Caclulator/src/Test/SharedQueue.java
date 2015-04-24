package Test;

import java.util.concurrent.ConcurrentLinkedQueue;

public class SharedQueue {
	public static ConcurrentLinkedQueue<Task> theSharedQueue = new ConcurrentLinkedQueue<Task>(); 
	
	public static boolean hasElem(){
		synchronized (SharedQueue.class) {
			return ! (theSharedQueue.isEmpty());
		}
	}
	
}
