package Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Shared {
	/*This class represent a shared memory which is open to all servers and clients
	*/
	
	public static ConcurrentLinkedQueue<Task> theSharedQueue = new ConcurrentLinkedQueue<Task>(); 	//Contains tasks added by clients and popped by server threads
					
	public static ConcurrentHashMap<String, Result > result= new ConcurrentHashMap<String, Result>();	//The collection where a client can find its result 
	public static boolean serverQuit=false;		 	//Stores whether the server is Quit or runnning
	
	//Return if there exist any element in the shared queue
	public static boolean hasElem(){
		synchronized (Shared.class) {
			return ! (theSharedQueue.isEmpty());
		}
	}
	
}
