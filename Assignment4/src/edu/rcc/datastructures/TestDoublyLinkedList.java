package edu.rcc.datastructures;

public class TestDoublyLinkedList {

	public static void main(String[] args) {
		// Testing the DoublyLinkedList class
		DoublyLinkedList<Stuff> list1 = new DoublyLinkedList<Stuff>();
		System.out.println("Is list empty? " + list1.isEmpty());
		Stuff mystuff = new Stuff(1, "apples", false);
		Stuff mystuff2 = new Stuff(1, "oranges", true);
		Stuff mystuff3 = new Stuff(2, "melons", true);
		Stuff mystuff4 = new Stuff(-1, "carrot", false);
		System.out.println("Added one item.");
		list1.pushToFront(mystuff);

		System.out.println("Is list empty? " + list1.isEmpty());

		System.out.println("After pushing at the front list  has "
				+ list1.size() + " elements and now contains: ");
		list1.displayList();
		list1.pushToFront(mystuff2);
		System.out.println("After pushing at the front list  has "
				+ list1.size() + " elements and now contains: ");
		list1.displayList();
		list1.pushToFront(mystuff3);
		System.out.println("After pushing at the front list  has "
				+ list1.size() + " elements and now contains: ");
		list1.displayList();

		list1.pushToBack(mystuff4);
		System.out.println("After pushing at the back list  has "
				+ list1.size() + " elements and now contains: ");
		list1.displayList();

		// Test the pop functions one at a time
		try {
			list1.popFromFront();
			System.out.println("After popping from front list has "
					+ list1.size() + " elements and now contains: ");
			list1.displayList();
			list1.popFromBack();
			System.out.println("After popping from back list has "
					+ list1.size() + " now contains: ");
			list1.displayList();
			list1.popFromBack();
			System.out.println("After popping from back list has "
					+ list1.size() + " now contains: ");
			list1.displayList();
			// Finish emptying the list
			list1.popFromBack();
			//Testing pop when list is empty
			System.out.println("Popping from front when list is empty:");
			list1.popFromFront();
			//This will not execute because exception is caught before this statement
			System.out.println("Popping from back when list is empty:");
			list1.popFromBack();
		} catch (EmptyListException e) {
			System.out.println("Error! The list has " + e.getInputNumber()
					+ " total elements:");
		}
		boolean thing3 = false;
		// Fill the list with random stuff
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				thing3 = true;
			} else {
				thing3 = false;
			}
			Stuff stuff = new Stuff(i, "thing2#" + i, thing3);
			list1.pushToFront(stuff);
		}
		// Test the clear function
		try {
			System.out.println("After pushing ten times at the front list has "
					+ list1.size() + " items and now contains: ");
			list1.displayList();
			list1.clear();
			System.out.println("Clearing the list. Stack has " + list1.size()
					+ " and is empty: " + list1.isEmpty());
			list1.clear();
		} catch (EmptyListException e) {
			System.out.println("Error! The list has " + e.getInputNumber()
					+ " total elements:");
		}
	}
}
