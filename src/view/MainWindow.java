package view;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Repair;
import model.Technician;

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
public class MainWindow extends JFrame {

	
//	private JPanel mainPane;
//	private JButton btnAssignNextJob;
//	private JButton btnCompleteRepair;
	
	/// CREATION OF GLOBAL VARIABLES/OBJECTS TO BE USED IN GUI \\\
	
	// TECHNICIAN OBJECTS & DATA STRUCTURE
	// Queue to store technicians
//	static Queue<Technician> techQ = new LinkedList<Technician> ();
//
//	// create technician objects
//	static Technician tech1 = new Technician(1, "Tony", "Stark");
//	static Technician tech2 = new Technician(2, "Steve", "Rodgers");
//	static Technician tech3 = new Technician(3, "Thor", "Odinson");
//	static Technician tech4 = new Technician(4, "Bruce", "Banner");
//	static Technician tech5 = new Technician(5, "Natasha", "Romanoff");
//	static Technician tech6 = new Technician(6, "Clint", "Barton");
//	
//	// REPAIR OBJECTS & DATA STRUCTURE
//	// create PriorityQueue to store repairs not started
//	static PriorityQueue<Repair> repairsNotStartedQ = new PriorityQueue<Repair>();
//	
//	// creation of empty linked lists to store inProgress repairs and completed repairs
//	static LinkedList<Repair> inProgressList = new LinkedList<Repair>();
//	static LinkedList<Repair> completedList = new LinkedList<Repair>();
//	
//	// creation of repair objects
//	static Repair repair1 = new Repair(1, 4, "Shield Inc.");
//	static Repair repair2 = new Repair(2, 2, "Hydra Inc.");
//	static Repair repair3 = new Repair(3, 3, "Stark Enterprises");
//	static Repair repair4 = new Repair(4, 1, "Avengers");
//	
//	// create Repair objects with a priority value outside of 1-4
//	static Repair repair5 = new Repair(5, 0, "USA Electronics");
//	static Repair repair6 = new Repair(6, 5, "Ag Electronics LLC");
//	
//	// create Repair object with orderNum == 0
//	static Repair repair0 = new Repair(0, 2, "Dark Order");
//	
//	// create Repair object with orderNum < 0
//	static Repair repairNeg = new Repair(-1, 3, "X Force");
	
//	// add techs to queue
//	techQ.add(tech1);
//	techQ.add(tech2);
//	techQ.add(tech3);
//	techQ.add(tech4);
//	techQ.add(tech5);
//	techQ.add(tech6);
//	
//	// addition of repair objects to priority queue
//	repairsNotStartedQ.add(repair1);
//	repairsNotStartedQ.add(repair2);
//	repairsNotStartedQ.add(repair3);
//	repairsNotStartedQ.add(repair4);
//	repairsNotStartedQ.add(repair5);
//	repairsNotStartedQ.add(repair6);
//	repairsNotStartedQ.add(repair0);
//	repairsNotStartedQ.add(repairNeg);
//		
//		// open the MainWindow
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainWindow frame = new MainWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	// private constructor to force usage of other constructor
	private MainWindow() {
	}

	/**
	 * Create the frame.
	 */
	public MainWindow(Queue<Technician> techQueue, PriorityQueue<Repair> repairsNotStartedPQ, LinkedList<Repair> inProgressLL, LinkedList<Repair> completedLL) {
		Queue<Technician> techQ = techQueue;
		PriorityQueue<Repair> repairsNotStartedQ = repairsNotStartedPQ;
		LinkedList<Repair> inProgressList = inProgressLL;
		LinkedList<Repair> completedList = completedLL;
		
		// create frame
		JFrame mainFrame = new JFrame();
		
		mainFrame.setTitle("Repair Jobs");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 436, 300);
		
		// create mainPanel
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// add panel to mainFrame
		mainFrame.setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		// MAIN WINDOW JLABEL HEADING
		JLabel lblWindowTitle = new JLabel("Electronic Repair Dashboard");
		lblWindowTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblWindowTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWindowTitle.setBounds(111, 0, 206, 29);
		mainPanel.add(lblWindowTitle);
		
		// ASSIGN NEXT REPAIR BUTTON
		JButton btnAssignNextJob = new JButton("ASSIGN NEXT REPAIR");
		btnAssignNextJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assignTech(repairsNotStartedQ, techQ, inProgressList);
				System.out.println("\"ASSIGN NEXT JOB\" clicked");
			}
		});
		btnAssignNextJob.setBounds(32, 88, 162, 47);
		mainPanel.add(btnAssignNextJob);
		
		// COMPLETE REPAIR BUTTON
		JButton btnCompleteRepair = new JButton("COMPLETE REPAIR");
		btnCompleteRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\"COMPLETE REPAIR\" clicked");
			}
		});
		btnCompleteRepair.setBounds(226, 88, 162, 47);
		mainPanel.add(btnCompleteRepair);
		
		// REPAIRS NOT STARTED BUTTON
		JButton btnRepairsNotStarted = new JButton("Repairs Not Started");
		btnRepairsNotStarted.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRepairsNotStarted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\"Repairs Not Started\" clicked");
				RepairsNotStartedWindow rNSW = new RepairsNotStartedWindow(repairsNotStartedQ);
				rNSW.setVisible(true);
			}
		});
		btnRepairsNotStarted.setBounds(10, 210, 118, 40);
		mainPanel.add(btnRepairsNotStarted);
		
		// REPAIRS IN PROGRESS BUTTON
		JButton btnRepairsInProgress = new JButton("Repairs In Progress");
		btnRepairsInProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\"Repairs In Progress\" clicked");
			}
		});
		btnRepairsInProgress.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRepairsInProgress.setBounds(149, 210, 118, 40);
		mainPanel.add(btnRepairsInProgress);
		
		// COMPLETED REPAIRS BUTTON
		JButton btnCompletedRepairs = new JButton("Completed Repairs");
		btnCompletedRepairs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\"Completed Repairs\" clicked");
			}
		});
		btnCompletedRepairs.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnCompletedRepairs.setBounds(288, 210, 118, 40);
		mainPanel.add(btnCompletedRepairs);
		
		//mainFrame.add(mainPanel);
		//set mainFrame to be visible
		mainFrame.setVisible(true);

	}
	
	//// STATIC METHODS TO BE USED WITHIN GUI \\\\
	
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
			NoTechsDialog nTD = new NoTechsDialog();
			nTD.setVisible(true);
			return;
		// check if repairPQ has at least one repair, if not, then open no repair available window
		} else if (repairPQ.isEmpty()) {
			NoRepairsDialog nRD = new NoRepairsDialog();
			nRD.setVisible(true);
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
