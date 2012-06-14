package uk.co.jwlawson.plan;

import java.util.List;

public class Utils {
	
	public static <T> T[] listToArray(List<T> list) {
		
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			T f = list.get(i);
			array[i] = (f != null ? f : null); // Or whatever default you want.
		}
		return array;
	}
	
	public static float[] floatListToArray(List<Float> list) {
		
		float[] array = new float[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			float f = list.get(i) * 75;
			array[i] = f; // (f != null ? f : Float.NaN); // Or whatever default you want.
		}
		return array;
	}
	
}
