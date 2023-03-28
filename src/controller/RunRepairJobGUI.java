package controller;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.JFrame;

import model.Repair;
import model.Technician;
import view.MainWindow;

/*****************************************************************
 * Name				: CIS152FinalProjectEhlert
 * Author			: Tony Ehlert
 * Created			: Mar 27, 2023
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
public class RunRepairJobGUI {

	public static void main(String[] args) {

		// JFrame mainRepairWindow = new MainWindow();

		/// CREATION OF GLOBAL VARIABLES/OBJECTS TO BE USED IN GUI \\\

		// TECHNICIAN OBJECTS & DATA STRUCTURE
		// Queue to store technicians
		Queue<Technician> techQ = new LinkedList<Technician>();

		// create technician objects
		Technician tech1 = new Technician(1, "Tony", "Stark");
		Technician tech2 = new Technician(2, "Steve", "Rodgers");
		Technician tech3 = new Technician(3, "Thor", "Odinson");
		Technician tech4 = new Technician(4, "Bruce", "Banner");
		Technician tech5 = new Technician(5, "Natasha", "Romanoff");
		Technician tech6 = new Technician(6, "Clint", "Barton");

		// REPAIR OBJECTS & DATA STRUCTURE
		// create PriorityQueue to store repairs not started
		PriorityQueue<Repair> repairsNotStartedQ = new PriorityQueue<Repair>();

		// creation of empty linked lists to store inProgress repairs and completed
		// repairs
		LinkedList<Repair> inProgressList = new LinkedList<Repair>();
		LinkedList<Repair> completedList = new LinkedList<Repair>();

		// creation of repair objects
		Repair repair1 = new Repair(1, 4, "Shield Inc.");
		Repair repair2 = new Repair(2, 2, "Hydra Inc.");
		Repair repair3 = new Repair(3, 3, "Stark Enterprises");
		Repair repair4 = new Repair(4, 1, "Avengers");
		Repair repair5 = new Repair(5, 2, "DMACC");
		Repair repair6 = new Repair(6, 1, "City of Ankeny");
		Repair repair7 = new Repair(7, 4, "City of Houston");
		Repair repair8 = new Repair(8, 2, "City of Chicago");
		Repair repair9 = new Repair(9, 3, "City of Omaha");
		Repair repair10 = new Repair(10, 4, "City of Des Moines");
		Repair repair11 = new Repair(11, 1, "City of Columbia");
		Repair repair12 = new Repair(12, 2, "City of Nashville");

		// create Repair objects with a priority value outside of 1-4
		Repair repair13 = new Repair(13, 0, "USA Electronics");
		Repair repair14 = new Repair(14, 5, "Ag Electronics LLC");

		// create Repair object with orderNum == 0
		// Repair repair0 = new Repair(0, 2, "Dark Order");

		// create Repair object with orderNum < 0
		// Repair repairNeg = new Repair(-1, 3, "X Force");

		// add techs to queue
		techQ.add(tech1);
		techQ.add(tech2);
		techQ.add(tech3);
		techQ.add(tech4);
		techQ.add(tech5);
		techQ.add(tech6);

		// addition of repair objects to priority queue
		repairsNotStartedQ.add(repair1);
		repairsNotStartedQ.add(repair2);
		repairsNotStartedQ.add(repair3);
		repairsNotStartedQ.add(repair4);
		repairsNotStartedQ.add(repair5);
		repairsNotStartedQ.add(repair6);
		repairsNotStartedQ.add(repair7);
		repairsNotStartedQ.add(repair8);
		repairsNotStartedQ.add(repair9);
		repairsNotStartedQ.add(repair10);
		repairsNotStartedQ.add(repair11);
		repairsNotStartedQ.add(repair12);
		repairsNotStartedQ.add(repair13);
		repairsNotStartedQ.add(repair14);
		// repairsNotStartedQ.add(repair0);
		// repairsNotStartedQ.add(repairNeg);

		// create mainRepairWindow and pass in data structures
		JFrame mainRepairWindow = new MainWindow(techQ, repairsNotStartedQ, inProgressList, completedList);

	}

}
