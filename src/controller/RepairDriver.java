package controller;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import model.Repair;
import model.Technician;

/*****************************************************************
 * Name				: CIS152FinalProjectEhlert
 * Author			: Tony Ehlert
 * Created			: Mar 21, 2023
 * Course			: CIS152 Data Structures
 * Version			: 1.0
 * OS				: Windows 11
 * Copyright		: This is my own original work based on
 *         	  	  	  specifications issued by our instructor
 * Description		: This program serves as a final project for CIS152 Data Structures.
 * 					  It is meant to help a service manager assign repairs in the proper
 * 					  order.  It also contains a GUI with buttons to assign repairs, complete
 * 					  repairs, and view different reports
 *					 Input: Required information needed to create various technician objects as
 *							well as the information needed to create various repair job objects.
 *					 Output: Window displaying the next repair job along with the technician assigned to it.
 *							 Various reports displaying jobs not started, jobs in progress, and completed jobs.
 * Academic Honesty	: I attest that this is my original work.
 * I have not used unauthorized source code, either modified or 
 * unmodified. I have not given other fellow student(s) access to
 * my program.         
 *****************************************************************/
public class RepairDriver {

	public static void main(String[] args) {
		
		// create PriorityQueue to store repairs not started
		PriorityQueue<Repair> repairsNotStartedQ = new PriorityQueue<Repair>();
		
		// creation of empty linked lists to store inProgress repairs and completed repairs
		LinkedList<Repair> inProgressList = new LinkedList<Repair>();
		LinkedList<Repair> completedList = new LinkedList<Repair>();
		
		// creation of repair objects
		Repair repair1 = new Repair(1, 4, "Shield Inc.");
		Repair repair2 = new Repair(2, 2, "Hydra Inc.");
		Repair repair3 = new Repair(3, 3, "Stark Enterprises");
		Repair repair4 = new Repair(4, 1, "Avengers");
		
		// create Repair objects with a priority value outside of 1-4
		Repair repair5 = new Repair(5, 0, "USA Electronics");
		Repair repair6 = new Repair(6, 5, "Ag Electronics LLC");
		
		// create Repair object with orderNum == 0
		Repair repair0 = new Repair(0, 2, "Dark Order");
		
		// create Repair object with orderNum < 0
		Repair repairNeg = new Repair(-1, 3, "X Force");
		
		// addition of repair objects to priority queue
		repairsNotStartedQ.add(repair1);
		repairsNotStartedQ.add(repair2);
		repairsNotStartedQ.add(repair3);
		repairsNotStartedQ.add(repair4);
		repairsNotStartedQ.add(repair5);
		repairsNotStartedQ.add(repair6);
		repairsNotStartedQ.add(repair0);
		repairsNotStartedQ.add(repairNeg);
		
		// printout of queue to ensure objects created correctly
		System.out.println("Repairs In Priority Queue");
		for (Repair repairs : repairsNotStartedQ) {
			System.out.println(repairs.toString());
		}
		
		// print out of elements in custPriorityQueue
//		while (repairsNotStartedQ.peek() != null) {
//			System.out.println(repairsNotStartedQ.poll());
//		}
		
		// create Queue to store technicians
		Queue<Technician> techQ = new LinkedList<Technician> ();

		// create technician objects
		Technician tech1 = new Technician(1, "Tony", "Stark");
		Technician tech2 = new Technician(2, "Steve", "Rodgers");
		Technician tech3 = new Technician(3, "Thor", "Odinson");
		Technician tech4 = new Technician(4, "Bruce", "Banner");
		Technician tech5 = new Technician(5, "Natasha", "Romanoff");
		Technician tech6 = new Technician(6, "Clint", "Barton");
		
		// add techs to queue
		techQ.add(tech1);
		techQ.add(tech2);
		techQ.add(tech3);
		techQ.add(tech4);
		techQ.add(tech5);
		techQ.add(tech6);
		
		System.out.println("\nTechnicians in Queue before assignment to repairs: ");
		for (Technician tech : techQ) {
			System.out.print(tech.getFirstName() + " " + tech.getLastName() + ", ");
		}
		
		// assign techs to repair by calling assignTech method
		assignTech(repairsNotStartedQ, techQ, inProgressList);
		assignTech(repairsNotStartedQ, techQ, inProgressList);
		assignTech(repairsNotStartedQ, techQ, inProgressList);
		
		System.out.println();
		System.out.println("\nTechnicians in Queue after assignment to repairs: ");
		for (Technician tech : techQ) {
			System.out.print(tech.getFirstName() + " " + tech.getLastName() + ", ");
		}
		
		System.out.println();
		System.out.println("\nRepairs in Progress: ");
		for (Repair repairs : inProgressList) {
			System.out.println(repairs);
		}
		
		// complete two of three repairs to test completeRepair method
		completeRepair(inProgressList, 2, completedList, techQ);
		
		System.out.println("\nCompleted Repairs:");
		for (Repair repairs : completedList) {
			System.out.println(repairs);
		}
		
		System.out.println();
		System.out.println("\nRepairs in Progress after completion of repairs: ");
		for (Repair repairs : inProgressList) {
			System.out.println(repairs);
		}
		
		System.out.println("\nTechnicians in Queue after completion of repairs: ");
		for (Technician tech : techQ) {
			System.out.print(tech.getFullName() + ", ");
		}
	}
	
	/**
	 * This method assigns a technician from the techQ to the highest priority repair
	 * in the repairPQ.  The tech that is assigned is removed for the techQ and the
	 * repair is removed from the repairPQ and placed in the inProgressList
	 * 
	 * @param repairPQ - priority queue containing repairs not yet started
	 * @param techQ - queue containing available technicians
	 * @param inProgressList - linked list to store repair jobs in progress
	 */
	static void assignTech(PriorityQueue<Repair> repairPQ, Queue<Technician> techQ, LinkedList<Repair> inProgressList) {
		// check if queue has at least one technician, if not, then open no tech available window
		if (techQ.size() <= 0) {
			System.out.println("No techs available WINDOW");
			return;
		// check if repairPQ has at least one repair, if not, then open no repair available window
		} else if (repairPQ.isEmpty()) {
			System.out.println("No repairs available WINDOW");
			return;
		}
		Repair current = repairPQ.poll();
		current.setTech(techQ.poll());
		inProgressList.add(current);
		System.out.println(current.getTech().toString() + " assigned to order number: " + current.getOrderNum() + " WINDOW");
	}
	
	/**
	 * This method searches for the matching order number and if found
	 * sets the completion date.  After that the repair is removed from
	 * the inProgressList and placed into the completedList.  The technician
	 * is also added back into the techQ.
	 * 
	 * @param inProgressList - LinkedList of repair objects currently being repaired
	 * @param orderNum - order number of repair to be completed
	 * @param completedList - LinkedList of completed repairs
	 * @param techQ - queue data structure containing available technicians
	 */
	static void completeRepair(LinkedList<Repair> inProgressList, int orderNum, LinkedList<Repair> completedList, Queue<Technician> techQ) {
		// check if inProgressList has at least one element in it, if not, then open no repairs in progress window
		if (inProgressList.size() <= 0) {
			System.out.println("No repairs are in progress WINDOW");
			return;
		}
		
		// check each element in inProgressList for matching orderNum
		for (Repair repairs : inProgressList) {
			if(repairs.getOrderNum() == orderNum) {
				Repair current = repairs;
				current.setCompletionDate(LocalDate.now());
				Technician currentTech = current.getTech();
				techQ.add(currentTech);
				completedList.add(current);
				inProgressList.remove(current);
				System.out.println("Order number: " + current.getOrderNum() + ", completed on " + current.getCompletionDate() + " by " + current.getTech().getFullName() + " WINDOW");
				return;
			}
		}
		
		// message/window indication the order number was not found in the inProgressList
		System.out.println("Order Number Not Found WINDOW");
		return;
	}

}
