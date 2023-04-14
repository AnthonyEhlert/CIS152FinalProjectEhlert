package controller;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import exceptions.OrderNumNotFoundException;
import exceptions.RepairListEmptyException;
import exceptions.RepairQueueEmptyException;
import exceptions.TechQueueEmptyException;
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

	public static void main(String[] args) throws TechQueueEmptyException {

		// create PriorityQueue to store repairs not started
		PriorityQueue<Repair> repairsNotStartedQ = new PriorityQueue<Repair>();

		// creation of empty linked lists to store inProgress repairs and completed
		// repairs
		LinkedList<Repair> inProgressList = new LinkedList<Repair>();
		LinkedList<Repair> completedList = new LinkedList<Repair>();

		// create Queue to store technicians
		Queue<Technician> techQ = new LinkedList<Technician>();

		// test empty repair priority queue customer exception
		System.out.println("REPAIR_QUEUE_EMPTY_EXCEPTION TEST");
		Object[] repairArray = null;
		try {
			assignTech(repairsNotStartedQ, techQ, inProgressList);
			sortPriorityQueue(repairsNotStartedQ);

		} catch (RepairQueueEmptyException e) {
			System.out.println("RepairQueueEmptyException THROWN WINDOW/ from assignTech()");
		} catch (TechQueueEmptyException e) {
			System.out.println("TechQueueEmptyException THROWN WINDOW/ from assignTech()");
		}

		try {
			repairArray = sortPriorityQueue(repairsNotStartedQ);
		} catch (RepairQueueEmptyException e) {
			System.out.println("RepairQueueEmptyException THROWN WINDOW/ from sortPriortyQueue()");
		}

		// creation of repair objects
		Repair repair1 = new Repair(1, 4, "Shield Inc.");
		Repair repair2 = new Repair(2, 2, "Hydra Inc.");
		Repair repair3 = new Repair(3, 3, "Stark Enterprises");
		Repair repair4 = new Repair(4, 1, "Avengers");

		// create Repair objects with a priority value outside of 1-4
		Repair repair5 = new Repair(5, 0, "USA Electronics");
		Repair repair6 = new Repair(6, 5, "Priority_Out_Of_Range Customer");

		// create Repair object with orderNum == 0
		Repair repair0 = new Repair(0, 4, "Zero_Cust_Num Customer");

		// create Repair object with orderNum < 0
		Repair repairNeg = new Repair(-1, 4, "Neg_Cust_Num Customer");

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
		System.out.println("\nTEST TO ENSURE REPAIR OBJECTS CREATED CORRECTLY AND ADDED TO PRIORITY QUEUE");
		System.out.println("REPAIRS IN PRIORITY QUEUE:");
		for (Repair repairs : repairsNotStartedQ) {
			System.out.println(repairs.toString());
		}

		// test empty techQ exception
		System.out.println("\nTECH_QUEUE_EMPTY_EXCEPTION TEST");
		try {
			assignTech(repairsNotStartedQ, techQ, inProgressList);
		} catch (TechQueueEmptyException e1) {
			System.out.println("TechQueueEmptyException THROWN WINDOW/ from assignTech()");
		} catch (RepairQueueEmptyException e) {
			System.out.println("RepairQueueEmptyException THROWN/ from assignTech()");
		}

		// RepairListEmptyException Tests
		System.out.println("\nREPAIR_LIST_EMPTY_EXCEPTION TESTS");

		System.out.println("Exception from completeRepair()/ Empty inProgress List");
		try {
			completeRepair(inProgressList, 2, completedList, techQ);
		} catch (RepairListEmptyException e) {
			System.out.println("RepairListEmptyException THROWN WINDOW/ from completeRepair()");
		} catch (OrderNumNotFoundException e) {
			System.out.println("OrderNumNotFoundException THROWN WINDOW/ from completeRepair()");
		}

		System.out.println("Exception from sortInProgressList()/ Empty inProgress List");
		try {
			sortInProgressList(inProgressList);
		} catch (RepairListEmptyException e) {
			System.out.println("RepairListEmptyException THROWN WINDOW/ from sortInProgressList()");
		}

		System.out.println("Exception from sortCompletedList()/ Empty completedList List");
		try {
			sortCompletedList(completedList);
		} catch (RepairListEmptyException e) {
			System.out.println("RepairListEmptyException THROWN WINDOW/ from sortCompletedList()");
		}

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

		// print of techs in techQ before assigning to array
		System.out.println("\nTECHS IN QUEUE BEFORE ASSIGNMENT TO REPAIRS: ");
		for (Technician tech : techQ) {
			System.out.print(tech.getFirstName() + " " + tech.getLastName() + ", ");
		}

		// print of sorted repairs not started array
		System.out.println();
		System.out.println("\nSORTED NOT STARTED REPAIR Q ARRAY BEFORE ASSIGMENT TO REPAIRS: ");
		try {
			repairArray = sortPriorityQueue(repairsNotStartedQ);
			for (Object rpr : repairArray) {
				System.out.println(rpr.toString());
			}
		} catch (RepairQueueEmptyException e) {
			System.out.println("RepairQueueEmptyException THROWN/ from sortPriorityQueue()");
		}

		// assign techs to repair by calling assignTech method
		System.out.println("\nREPAIRS ASSIGNED TO TECHS: ");
		try {
			assignTech(repairsNotStartedQ, techQ, inProgressList);
			assignTech(repairsNotStartedQ, techQ, inProgressList);
			assignTech(repairsNotStartedQ, techQ, inProgressList);
			assignTech(repairsNotStartedQ, techQ, inProgressList);
			assignTech(repairsNotStartedQ, techQ, inProgressList);
		} catch (RepairQueueEmptyException e) {
			System.out.println("RepairQueueEmptyException THROWN WINDOW/ from assignTech()");
		} catch (TechQueueEmptyException e) {
			System.out.println("TechQueueEmptyException THROWN WINDOW/ from assignTech()");
		}

		// print of techs in techQ after assigning to repairs
		System.out.println("\nTECHS IN QUEUE AFTER ASSIGNMENT TO REPAIRS: ");
		for (Technician tech : techQ) {
			System.out.println(tech.getFirstName() + " " + tech.getLastName() + ", ");
		}

		// print of inProgress List
		System.out.println("\nREPAIRS IN PROGRESS BEFORE SORT: ");
		for (Repair repairs : inProgressList) {
			System.out.println(repairs);
		}

		// print out of sorted inProgress array after assignment
		System.out.println("\nSORTED IN PROGRESS REPAIRS ARRAY AFTER ASSIGNMENT OF REPAIRS: ");
		Object[] inProgressArray = null;
		try {
			inProgressArray = sortInProgressList(inProgressList);
			for (Repair repairs : inProgressList) {
				System.out.println(repairs);
			}
		} catch (RepairListEmptyException e1) {
			System.out.println("RepairListEmptyException THROWN WINDOW/ sortInProgressList()");
		}

		// complete two of three repairs to test completeRepair method
		System.out.println("\nREPAIRS ASSIGNED: ");
		try {
			completeRepair(inProgressList, 2, completedList, techQ);
			completeRepair(inProgressList, 6, completedList, techQ);
		} catch (RepairListEmptyException e) {
			System.out.println("RepairListEmptyException THROWN WINDOW/ from completeRepair()");
		} catch (OrderNumNotFoundException e) {
			System.out.println("OrderNumNotFoundException THROWN WINDOW/ from completeRepair()");
		}

		// try to complete repair number not in inProgressList
		System.out.println("\nTEST TO TRY AND COMPLETE ORDER #(2) NOT IN inProgressList");
		try {
			completeRepair(inProgressList, 2, completedList, techQ);
		} catch (RepairListEmptyException e) {
			System.out.println("RepairListEmptyException THROWN WINDOW/ from completeRepair()");
		} catch (OrderNumNotFoundException e) {
			System.out.println("OrderNumNotFoundException THROWN WINDOW/ from completeRepair()");
		}

		// print of sorted inProgress array elements
		System.out.println("\nSORTED IN PROGRESS REPAIRS ARRAY AFTER COMPLETION OF REPAIRS: ");
		try {
			inProgressArray = sortInProgressList(inProgressList);
			for (Object rpr : inProgressArray) {
				System.out.println(rpr.toString());
			}
		} catch (RepairListEmptyException e1) {
			System.out.println("RepairListEmptyException THROWN WINDOW/ sortInProgressList()");
		}

		// print out of completed repairs
		System.out.println("\nCOMPLETED REPAIRS:");
		for (Repair repairs : completedList) {
			System.out.println(repairs);
		}

		// print of inProgress list contents after completing repairs
		System.out.println("\nREPAIRS IN PROGRESS AFTER COMPLETION OF REPAIRS: ");
		for (Repair repairs : inProgressList) {
			System.out.println(repairs);
		}

		System.out.println("\nTECHS IN QUEUE AFTER COMPLETION OF REPAIRS: ");
		for (Technician tech : techQ) {
			System.out.print(tech.getFullName() + ", ");
		}

		System.out.println();
		System.out.println("\nSORTED REPAIRS ARRAY AFTER COMPLETEION OF REPAIRS: ");
		Object[] completedArray = null;
		try {
			completedArray = sortCompletedList(completedList);
			for (Object rpr : completedArray) {
				System.out.println(rpr.toString() + " Tech ID: " + ((Repair) rpr).getTech().getId());
			}
		} catch (RepairListEmptyException e) {
			System.out.println("RepairListEmptyException THROWN WINDOW/ from sortCompletedList()");
		}
	}

	/**
	 * This method assigns a technician from the techQ to the highest priority
	 * repair in the repairPQ. The tech that is assigned is removed for the techQ
	 * and the repair is removed from the repairPQ and placed in the inProgressList
	 * 
	 * @param repairPQ       - priority queue containing repairs not yet started
	 * @param techQ          - queue containing available technicians
	 * @param inProgressList - linked list to store repair jobs in progress
	 */
	static void assignTech(PriorityQueue<Repair> repairPQ, Queue<Technician> techQ, LinkedList<Repair> inProgressList)
			throws RepairQueueEmptyException, TechQueueEmptyException {
		// check if queue has at least one technician, if not, then open no tech
		// available window
		if (repairPQ.isEmpty()) {
			throw new RepairQueueEmptyException();
		} else if (techQ.size() <= 0) {
			throw new TechQueueEmptyException();
			// check if repairPQ has at least one repair, if not, then open no repair
			// available window (throw exception)
		}

		Repair current = repairPQ.poll();
		current.setTech(techQ.poll());
		inProgressList.add(current);
		System.out.println(
				current.getTech().toString() + " assigned to order number: " + current.getOrderNum() + " WINDOW");
	}

	/**
	 * This method searches for the matching order number and if found sets the
	 * completion date. After that the repair is removed from the inProgressList and
	 * placed into the completedList. The technician is also added back into the
	 * techQ.
	 * 
	 * @param inProgressList - LinkedList of repair objects currently being repaired
	 * @param orderNum       - order number of repair to be completed
	 * @param completedList  - LinkedList of completed repairs
	 * @param techQ          - queue data structure containing available technicians
	 */
	static void completeRepair(LinkedList<Repair> inProgressList, int orderNum, LinkedList<Repair> completedList,
			Queue<Technician> techQ) throws RepairListEmptyException, OrderNumNotFoundException {

		// check if inProgressList has at least one element in it, if not, then open no
		// repairs in progress window
		if (inProgressList.size() <= 0) {
			throw new RepairListEmptyException();
		}

		// check each element in inProgressList for matching orderNum
		for (Repair repairs : inProgressList) {
			if (repairs.getOrderNum() == orderNum) {
				Repair current = repairs;
				current.setCompletionDate(LocalDate.now());
				Technician currentTech = current.getTech();
				techQ.add(currentTech);
				completedList.add(current);
				inProgressList.remove(current);
				// print statement for window/jFrame implementation in main driver
				System.out.println("Order number: " + current.getOrderNum() + ", completed on "
						+ current.getCompletionDate() + " by " + current.getTech().getFullName() + " WINDOW");
				return;
			}
		}

		// if for loop completes and never entered if block, order number not found
		throw new OrderNumNotFoundException();
	}

	/**
	 * This method sorts the repair Priority Queue using the insertion sort method
	 * 
	 * @param repairPQ - repair priority queue to be sorted
	 * @return - sorted array of objects
	 */
	static Object[] sortPriorityQueue(PriorityQueue<Repair> repairPQ) throws RepairQueueEmptyException {

		// empty repairPQ check
		if (repairPQ.isEmpty()) {
			throw new RepairQueueEmptyException();
		}

		Object[] repairArray = repairPQ.toArray();
		int arrayLength = repairArray.length;

		for (int i = 1; i < arrayLength; i++) {
			// assign element at index i to object variable current
			Object current = repairArray[i];

			// assign priority value of current object variable to int variable to use for
			// comparison
			int keyPriority = ((Repair) repairArray[i]).getPriority();

			// create variable for element index to the left of current object that gets
			// decreased by one per iteration of while loop
			int j = i - 1;

			// check if j is greater or equal to zero and if so, compare the priority of the
			// object at index j to the priority of the current element. If greater move
			// element at index j to the right
			while ((j >= 0) && ((Repair) repairArray[j]).getPriority() > keyPriority) {
				repairArray[j + 1] = repairArray[j];
				j = j - 1;
			}
			// assign stored current element to position j + 1 of array
			repairArray[j + 1] = current;
		}

		return repairArray;
	}

	/**
	 * This method sorts the repair objects in the inProgressList linked list using
	 * the insertion sort method and returns a sorted array of the objects
	 * 
	 * @param inProgressList - repairs in progress list to be sorted
	 * @return - sorted array of objects
	 */
	static Object[] sortInProgressList(LinkedList<Repair> inProgressList) throws RepairListEmptyException {
		// empty inProgressList check
		if (inProgressList.isEmpty()) {
			throw new RepairListEmptyException();
		}

		Object[] inProgressArr = inProgressList.toArray();
		int arrayLength = inProgressArr.length;

		for (int i = 1; i < arrayLength; i++) {
			// assign element at index i to object variable current
			Object current = inProgressArr[i];

			// assign priority value of current object variable to int variable to use for
			// comparison
			int keyPriority = ((Repair) inProgressArr[i]).getPriority();

			// create variable for element index to the left of current object that gets
			// decreased by one per iteration of while loop
			int j = i - 1;

			// check if j is greater or equal to zero and if so, compare the priority of the
			// object at index j to the priority of the current element. If greater move
			// element at index j to the right
			while ((j >= 0) && ((Repair) inProgressArr[j]).getPriority() > keyPriority) {
				inProgressArr[j + 1] = inProgressArr[j];
				j = j - 1;
			}
			// assign stored current element to position j + 1 of array
			inProgressArr[j + 1] = current;

		}
		return inProgressArr;
	}

	/**
	 * This method sorts the repair objects in the completedList linked list using
	 * the insertion sort method and returns a sorted array of the objects
	 * 
	 * @param completedList - repairs in completed list to be sorted
	 * @return - sorted array of objects
	 * @throws RepairListEmptyException
	 */
	static Object[] sortCompletedList(LinkedList<Repair> completedList) throws RepairListEmptyException {

		// empty completedList check
		if (completedList.isEmpty()) {
			throw new RepairListEmptyException();
		}

		Object[] completedArr = completedList.toArray();
		int arrayLength = completedArr.length;

		for (int i = 1; i < arrayLength; i++) {
			// assign element at index i to object variable current
			Object current = completedArr[i];

			// assign techId value of current object variable to int variable to use for
			// comparison
			int keyTechId = ((Repair) completedArr[i]).getTech().getId();

			// create variable for element index to the left of current object that gets
			// decreased by one per iteration of while loop
			int j = i - 1;

			// check if j is greater or equal to zero and if so, compare the techId of the
			// object at index j to the techId of the current element. If greater move
			// element at index j to the right
			while ((j >= 0) && ((Repair) completedArr[j]).getTech().getId() > keyTechId) {
				completedArr[j + 1] = completedArr[j];
				j = j - 1;
			}
			// assign stored current element to position j + 1 of array
			completedArr[j + 1] = current;

		}

		return completedArr;
	}

}
