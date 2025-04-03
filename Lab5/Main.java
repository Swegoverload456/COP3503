// Skip List Implementation. Most part was discussed in the class

import java.util.*;

public class Main {

	public static void main(String[] args) {
	
	int[] arr = new int[100];
	Random r = new Random();
	for(int i = 0; i < 1000000; i++){
		arr[r.nextInt(100)]++;

	}

	for(int i = 0; i < 100; i++){
		System.out.print(arr[i] + "\t");
	}

	}
}