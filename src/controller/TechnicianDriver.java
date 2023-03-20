package controller;

import java.util.LinkedList;
import java.util.Queue;

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


	public static void main(String[] args) {
		
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
		
		// grab first tech from queue and ensure no longer in queue
		Technician tech1Removed = techQ.poll();
		System.out.println(tech1Removed.toString());
		System.out.println(techQ.toString());
		
		// grab next tech from queue and ensure no longer in queue
		Technician tech2Removed = techQ.poll();
		System.out.println(tech2Removed.toString());
		System.out.println(techQ.toString());
		
		// add tech1 and tech2 back into techQ
		techQ.add(tech1Removed);
		techQ.add(tech2Removed);
		
		// grab next tech from queue and ensure it is tech3
		Technician tech3Removed = techQ.poll();
		System.out.println(tech3Removed.toString());
		System.out.println(techQ.toString());
		
	}

}
