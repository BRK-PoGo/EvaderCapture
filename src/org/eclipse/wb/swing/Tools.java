package org.eclipse.wb.swing;

import java.util.*;

public class Tools {
	
	 public static <T> ArrayList<T> union(List<T> list1, List<T> list2) {
	        Set<T> set = new HashSet<T>();

	        set.addAll(list1);
	        set.addAll(list2);

	        return new ArrayList<T>(set);
	    }
	 public static <T>ArrayList<T> subtract(ArrayList<T> total, ArrayList<T> toSubtract) {
			ArrayList<T> set = new ArrayList<T>();

	        set.addAll(total);
	        set.removeAll(toSubtract);
			
			return set;
		}
}
