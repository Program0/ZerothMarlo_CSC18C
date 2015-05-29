package edu.rcc.datastructures;

import java.util.Random;

public class TestSearch {

	/**
	 * 
	 * @param anArray
	 * @param searchItem
	 * @return
	 */
	public static boolean linearSearch(String[] anArray, String searchItem) {
		System.out.println("Doing linear search");
		boolean found = false;
		int index = 0;
		// Loop through the array or until we find the searchItem
		while (!found && (index < anArray.length)) {
			// Check to see if current element is equal to our searchItem
			if (searchItem.equals(anArray[index])) {
				found = true;
			}
			// Go to the next element
			index++;
		}
		return found;
	}

	/**
	 * @param [] anArray, E searchItem An array to search and a search item to
	 *        search in the array
	 * @return boolean Returns true if the item was found, false otherwise
	 */
	// Assumes array has already been sorted
	public static boolean binarySearch(String[] anArray, String searchItem) {
		System.out.println("\nDoing binary search");
		int low = 0;
		int high = anArray.length - 1;
		while (low <= high) {
			// Go to the middle of the range at each iteration
			int middle = (low + high) / 2;

			// Check to see if we found the target
			if (searchItem.equals(anArray[middle])) {
				return true;
			}
			// If we haven't found it, change the range
			else if (searchItem.compareTo(anArray[middle]) < 0) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		// We never found it.
		return false;
	}

	/**
	 * Receives an array and a hash table to be filled with random strings
	 * 
	 * @param anArray
	 * @param charactersToUse
	 * @param stringLength
	 * @param hashTable
	 */
	public static void fillArray(String[] anArray, String charactersToUse,
			int stringLength, HashingSearch<String, Integer> hashTable) {

		System.out.println("\nFilling the array and hash table");
		// Random number to select a random character
		Random randomNumber = new Random(System.currentTimeMillis());
		char[] tempString = new char[stringLength];
		// Iterate through the array and fill it
		for (int i = 0; i < anArray.length; i++) {
			// Get random characters and fill an array with it up to the
			// required string length
			for (int j = 0; j < stringLength; j++) {
				tempString[j] = charactersToUse.charAt(randomNumber
						.nextInt(charactersToUse.length()));
			}
			// Insert the new string in the array and hash table
			anArray[i] = new String(tempString);
			hashTable.insert(new String(tempString), i);
		}
	}

	public static void printArray(String[] anArray) {
		for (String s : anArray) {
			System.out.println(s);
		}
	}

	// Sorts an array using mergeSort
	public static <E extends Comparable<? super E>> void mergeSort(E[] array,
			int first, int last) {
		System.out.println("\nSorting the array");
		@SuppressWarnings("unchecked")
		E[] tempArray = (E[]) new Comparable<?>[array.length];
		// To save memory pass a temporary array to the recursive merge sort
		// function.
		// System.out.println("Inside mergeSort Public | array.size: "
		// + array.length + " tempArray.size: " + tempArray.length
		// + " first: " + first + " last: " + last);
		mergeSort(array, tempArray, first, last);
	}

	// Need to make sure method accepts any object that can be compared
	// with another object of the same type so use generics
	private static <E extends Comparable<? super E>> void mergeSort(E[] array,
			E[] tempArray, int first, int last) {
		// Base case
		if (first < last) {
			int mid = first + (last - first) / 2;
			// Divide and conquer. Split into left and right subarrays
			// recursively
			// System.out.println("Inside mergeSort 1st part | array.size: "
			// + array.length + " tempArray.size: " + tempArray.length
			// + " first: " + first + " mid: " + mid + " last: " + last);
			mergeSort(array, tempArray, first, mid);
			// System.out.println("Inside mergeSort 2nd part | array.size: "
			// + array.length + " tempArray.size: " + tempArray.length
			// + " first: " + first + " mid: " + mid + " last: " + last);
			mergeSort(array, tempArray, mid + 1, last);
			// Combine both subarrays back into one array
			mergeArrays(array, tempArray, first, mid, last);

		}

	}

	private static <E extends Comparable<? super E>> void mergeArrays(
			E[] array, E[] tempArray, int first, int mid, int last) {
		// System.out.println("Inside mergeArrays| array.size: " + array.length
		// + " tempArray.size: " + tempArray.length + " first: " + first
		// + " mid: " + mid + " last: " + last);
		// To keep track of which indexes have been merged into the tempArray
		int beginHalf1 = first;// subarray1 starting index
		int beginHalf2 = mid + 1;// subarray2 starting index

		// Need to copy array into tempArray to actually sort it. Important!!
		// Book's algorithm did not work for me.
		for (int i = first; i <= last; i++) {
			tempArray[i] = array[i];
		}
		// To keep track of the current element added to tempArray
		int index = first;
		// Need to keep within range of both sub arrays
		while ((beginHalf1 <= mid) && (beginHalf2 <= last)) {
			// Only copy if 1st half element is less than or equal to element in
			// 2nd half
			if (tempArray[beginHalf1].compareTo(tempArray[beginHalf2]) <= 0) {
				array[index] = tempArray[beginHalf1];
				// go the next element in subarray1 to sort
				beginHalf1++;
			} else {
				array[index] = tempArray[beginHalf2];
				// go the next element in subarray 2 to sort
				beginHalf2++;

			}
			// Go to the next elements in both subarrays to be sorted
			index++;
		}

		// Now we need to copy any of the remaining elements in the first
		// subarray into array[]
		while (beginHalf1 <= mid) {
			array[index] = tempArray[beginHalf1];
			index++;
			beginHalf1++;
		}

	}

	public static void main(String[] args) {

		// Randomly generated string array and a random string to search
		int size = 5000000;
		int stringSize = 20;
		// Only allow these characters
		String charactersAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String[] searchArray = new String[size];

		// Variables to hold time
		long startTime = 0;
		long endTime = 10;

		HashingSearch<String, Integer> searchTable = new HashingSearch<String, Integer>();
		// Fill the array and the hash table with the allowed characters with
		// strings of predefined size

		//startTime = System.currentTimeMillis();
		fillArray(searchArray, charactersAllowed, stringSize, searchTable);
		//endTime = System.currentTimeMillis();
		//System.out.println("Filling took: " + (endTime - startTime) / 1000.0f
				//+ " seconds");

		// Prepare a random string from those generated strings in searchArray
		Random stringNumber = new Random(System.currentTimeMillis());
		String randomString;
		if ((stringNumber.nextInt(2) - 1) < 0) {
			randomString = searchArray[stringNumber.nextInt(size + 1)];
		} else {
			randomString = "thesestringisnothere";
		}

		System.out.println("Size: " + size);
		// Print the random string
		System.out
				.println("Random string to search is: " + randomString + "\n");

		// To determine whether the item was found.
		boolean found = false;

		// Reset the timers
		//startTime = 0;
		//endTime = 0;

		// Begin the linear search test
		// Get the start time
		//startTime = System.currentTimeMillis();
		// Begin linearSearch
		//found = linearSearch(searchArray, randomString);
		// Get the stoppage time
		//endTime = System.currentTimeMillis();
		// Output the actual time ran.
		//System.out.println("\nLinear search looking for: " + randomString
				//+ " Did we find it? " + found);
		//System.out.println("That took " + (endTime - startTime) / 1000.0f
				//+ " seconds");

		// Reset the timers and boolean
		//startTime = 0;
		//endTime = 0;

		// We sort the array first so that binarySearch will work
		//startTime = System.currentTimeMillis();
		mergeSort(searchArray, 0, searchArray.length - 1);
		//endTime = System.currentTimeMillis();
		//System.out.println("\nSorting took: " + (endTime - startTime) / 1000.0f
				//+ " seconds");
		
		// Reset the timers and boolean
		//startTime = 0;
		//endTime = 0;
		found = false;

		// Begin binarySearch. Get the start and end time
		startTime = System.currentTimeMillis();
		found = binarySearch(searchArray, randomString);
		endTime = System.currentTimeMillis();
		// Output the actual time ran.
		System.out.println("\nBinary search looking for: " + randomString
				+ " Did we find it? " + found);
		System.out.println("That took " + (endTime - startTime) / 1000.0f
				+ " seconds");

		// Reset the timers and the boolean
		//startTime = 0;
		//endTime = 0;
		//found = false;

		// Begin the hash table search
		//startTime = System.currentTimeMillis();
		//found = searchTable.contains(randomString);
		//endTime = System.currentTimeMillis();
		//System.out.println("\nHasing search looking for: " + randomString
				//+ " Did we find it? " + found);
		//System.out.println("That took " + (endTime - startTime) / 1000.0f
				//+ " seconds");

	}

}