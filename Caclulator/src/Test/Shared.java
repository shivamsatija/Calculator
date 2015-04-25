package Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Shared {
	public static ConcurrentLinkedQueue<Task> theSharedQueue = new ConcurrentLinkedQueue<Task>(); 
	public static ConcurrentHashMap<String, Result > result= new ConcurrentHashMap<String, Result>();
	public static boolean serverQuit=false;
	public static boolean hasElem(){
		synchronized (Shared.class) {
			return ! (theSharedQueue.isEmpty());
		}
	}
	
}
