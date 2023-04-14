package controller;

import java.util.LinkedList;
import java.util.Queue;

import exceptions.TechQueueEmptyException;
import model.Technician;

/*****************************************************************
 * Name				: CIS152FinalProjectEhlert
 * Author			: Tony Ehlert
 * Created			: Mar 20, 2023
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
public class TechnicianDriver {


	public static void main(String[] args) throws TechQueueEmptyException {
		
		// create Queue to store technicians
		Queue<Technician> techQ = new LinkedList<Technician> ();

		// test TechQueueEmptyException
		System.out.println("TECH_QUEUE_EMPTY_EXCEPTION TEST");
		try {
			if (techQ.isEmpty()) {
				throw new TechQueueEmptyException();
			} 
		} catch (TechQueueEmptyException e) {
			System.out.println("TechQueueEmptyException THROWN");
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
		
		// test to ensure all techs have been added to queue
		System.out.println("\nTEST TO ENSURE ALL 6 TECHS ARE IN QUEUE AFTER ADDING");
		System.out.println("TECHS IN QUEUE:");
		for (Technician tech : techQ) {
			System.out.println(tech.toString());
		}
		
		// grab first tech from queue and ensure no longer in queue
		System.out.println("\nTEST TO ENSURE 5 TECHS ARE IN QUEUE AFTER FIRST REMOVED");
		Technician tech1Removed = techQ.poll();
		System.out.println(tech1Removed.toString() + " REMOVED");
		System.out.println("TECHS REMAINING IN QUEUE:");
		for (Technician tech : techQ) {
			System.out.println(tech.toString());
		}
		
		// grab next tech from queue and ensure no longer in queue
		System.out.println("\nTEST TO ENSURE 4 TECHS ARE IN QUEUE AFTER SECOND REMOVED");
		Technician tech2Removed = techQ.poll();
		System.out.println(tech2Removed.toString() + " REMOVED");
		System.out.println("TECHS REMAINING IN QUEUE:");
		for (Technician tech : techQ) {
			System.out.println(tech.toString());
		}
		
		
		// add tech1 and tech2 back into techQ
		System.out.println("\nTEST TO ENSURE 6 TECHS ARE IN QUEUE AFTER ADDING REMOVED TECHS");
		techQ.add(tech1Removed);
		techQ.add(tech2Removed);
		System.out.println("TECHS IN QUEUE:");
		for (Technician tech : techQ) {
			System.out.println(tech.toString());
		}
		
		// grab next tech from queue and ensure it is tech3
		System.out.println("\nTEST TO ENSURE TECH 3 IS REMOVED(FIFO TEST)");
		Technician tech3Removed = techQ.poll();
		System.out.println(tech3Removed.toString() + " REMOVED");
		System.out.println("TECHS REMAINING IN QUEUE:");
		for (Technician tech : techQ) {
			System.out.println(tech.toString());
		}
		
	}

}
