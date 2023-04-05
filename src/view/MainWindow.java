package view;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
			//System.out.println("\"ASSIGN NEXT JOB\" clicked");
			}
		});
		btnAssignNextJob.setBounds(32, 88, 162, 47);
		mainPanel.add(btnAssignNextJob);
		
		// COMPLETE REPAIR BUTTON
		JButton btnCompleteRepair = new JButton("COMPLETE REPAIR");
		btnCompleteRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				completeRepair(inProgressList, completedList, techQ);
				//System.out.println("\"COMPLETE REPAIR\" clicked");
			}
		});
		btnCompleteRepair.setBounds(226, 88, 162, 47);
		mainPanel.add(btnCompleteRepair);
		
		// REPAIRS NOT STARTED BUTTON
		JButton btnRepairsNotStarted = new JButton("Repairs Not Started");
		btnRepairsNotStarted.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRepairsNotStarted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] sortedRepairArr = sortPriorityQueue(repairsNotStartedQ);
				RepairsNotStartedWindow rNSW = new RepairsNotStartedWindow(sortedRepairArr);
				rNSW.setVisible(true);
				//System.out.println("\"Repairs Not Started\" clicked");
				}
		});
		btnRepairsNotStarted.setBounds(10, 210, 118, 40);
		mainPanel.add(btnRepairsNotStarted);
		
		// REPAIRS IN PROGRESS BUTTON
		JButton btnRepairsInProgress = new JButton("Repairs In Progress");
		btnRepairsInProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] inProgressArr = sortInProgressList(inProgressLL);
				InProgressWindow iPW = new InProgressWindow(inProgressArr);
				iPW.setVisible(true);
				//System.out.println("\"Repairs In Progress\" clicked");
			}
		});
		btnRepairsInProgress.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRepairsInProgress.setBounds(149, 210, 118, 40);
		mainPanel.add(btnRepairsInProgress);
		
		// COMPLETED REPAIRS BUTTON
		JButton btnCompletedRepairs = new JButton("Completed Repairs");
		btnCompletedRepairs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] completedArr = sortCompletedList(completedLL);
				CompletedRepairsWindow cRW = new CompletedRepairsWindow(completedArr);
				cRW.setVisible(true);
				//System.out.println("\"Completed Repairs\" clicked");
			}
		});
		btnCompletedRepairs.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnCompletedRepairs.setBounds(288, 210, 118, 40);
		mainPanel.add(btnCompletedRepairs);
		
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
			JOptionPane.showMessageDialog(null, "No Techs Available");
			return;
			
		// check if repairPQ has at least one repair, if not, then open no repair available window
		} else if (repairPQ.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No Repairs Avaliable");
			return;
		}
		Repair current = repairPQ.poll();
		current.setTech(techQ.poll());
		inProgressList.add(current);
		JOptionPane.showMessageDialog(null, current.getTech().getFullName() + " assigned to Order Number: "
				+ current.getOrderNum() + ", Priority Value: " + current.getPriority());
		//System.out.println(current.getTech().toString() + " assigned to order number: " + current.getOrderNum() + " WINDOW");
	}
	
	/**
	 * This method prompts the user for the order number they wish to complete 
	 * and searches for the matching order number and if found
	 * prompts the user for the completion date.  After that the repair is removed from
	 * the inProgressList and placed into the completedList.  The technician
	 * is also added back into the techQ.
	 * 
	 * @param inProgressList - LinkedList of repair objects currently being repaired
	 * @param completedList - LinkedList of completed repairs
	 * @param techQ - queue data structure containing available technicians
	 */
	static void completeRepair(LinkedList<Repair> inProgressList, LinkedList<Repair> completedList, Queue<Technician> techQ) {
		// check if inProgressList has at least one element in it, if not, then display message dialog
		if (inProgressList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are no repairs currently in progress");
			return;
		}
		
		boolean validInput = false;
		String orderNumInput = JOptionPane.showInputDialog("Enter the order number you wish to complete: ", "Order Number");
		
		// exit method if cancel was clicked or ok was clicked with no input
		if (orderNumInput == null || orderNumInput.equals("Order Number")) {
			return;
		}
		
		// while loop with try/catch block to ensure user input is valid
		while (!validInput) {
			try {
				// check if order number is less than or equal to 0, if so, invalid entry and return to beginning of loop
				if (Integer.parseInt(orderNumInput) <= 0) {
					orderNumInput = JOptionPane.showInputDialog("INVALID ENTRY! Order Number to Complete: ",
							"Order Number");
				} else {
					// check each element in inProgressList for matching orderNum
					for (Repair repairs : inProgressList) {
						if (repairs.getOrderNum() == Integer.parseInt(orderNumInput)) {
							// set matching repair order number to current variable
							Repair current = repairs;

							// get completion date input from user
							String dateInput = JOptionPane.showInputDialog("Enter the completion date: ", "MM/DD/YYYY");
							// System.out.println(dateInput);

							// if user clicks cancel or close window end method
							if (dateInput == null || dateInput.equals("MM/DD/YYYY")) {
								JOptionPane.showMessageDialog(null, "Order Number: " + current.getOrderNum()
										+ " was not completed due to no date being entered.");
								return;
							}

							// create variables used to parse date and ensure user input is valid format
							boolean validDate = false;
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
							LocalDate completionDate = null;
							while (!validDate) {
								try {
									completionDate = LocalDate.parse(dateInput, formatter);
									validDate = true;
								} catch (DateTimeParseException ex) {
									dateInput = JOptionPane.showInputDialog(
											"INVALID FORMAT! Enter the completion date: ", "MM/DD/YYYY");
									// if user clicks cancel or close window end method
									if (dateInput == null || dateInput.equals("MM/DD/YYYY")) {
										JOptionPane.showMessageDialog(null, "Order Number: " + current.getOrderNum()
												+ " was not completed due to no date being entered.");
										return;
									}
								}
							}

							// dateInput from user was valid so set completionDate of current repair
							current.setCompletionDate(completionDate);
							Technician currentTech = current.getTech();
							techQ.add(currentTech);
							completedList.add(current);
							inProgressList.remove(current);
							JOptionPane.showMessageDialog(null,
									"Order number: " + current.getOrderNum() + ", completed on "
											+ current.getCompletionDate() + " by " + current.getTech().getFullName());
							validInput = true;
							return;
						}
					}
					// message dialog indicating the order number was not found in the inProgressList
					JOptionPane.showMessageDialog(null, "Order number not found");
					//System.out.println("Order Number Not Found WINDOW");
					break;
				}
			} catch (NumberFormatException ex) {
				orderNumInput = JOptionPane.showInputDialog("INVALID ENTRY! Order Number to Complete: ", "Order Number");
				// exit method if cancel was clicked or ok was clicked with no input
				if (orderNumInput == null || orderNumInput.equals("Order Number")) {
					return;
				}
			}
		}
		
	}
	
	/**
	 * This method sorts the repair Priority Queue using the insertion sort method
	 * 
	 * @param repairPQ - repair priority queue to be sorted
	 * @return - sorted array of objects
	 */
	static Object[] sortPriorityQueue(PriorityQueue<Repair> repairPQ) {
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
	 * This method sorts the repair objects in the inProgressList linked list using the insertion sort method
	 * and returns a sorted array of the objects
	 * 
	 * @param inProgressList - repairs in progress list to be sorted
	 * @return - sorted array of objects
	 */
	static Object[] sortInProgressList(LinkedList<Repair> inProgressList) {
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
	
	static Object[] sortCompletedList(LinkedList<Repair> completedList) {
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
		
		// print to ensure array sorted correctly
//		for (Object repair : completedArr) {
//			System.out.println(repair.toString() + " Tech ID: " + ((Repair) repair).getTech().getId());
//		}
		
		return completedArr;
	}
	
//	/**
//	 * This method accepts a LinkedList of repair objects and sorts them using
//	 * the insertion sort method and then returns the sorted list
//	 * 
//	 * @param completedList - LinkedList to be sorted
//	 * @return - sorted LinkedList of repair objects
//	 */
//	static LinkedList<Repair> sortCompletedList(LinkedList<Repair> completedList) {
//		// first check is LinkedList passed in is empty. If true return empty list
//		if (completedList.isEmpty()) {
//			return completedList;
//		}
//		
//		// create an empty sorted list
//		LinkedList<Repair> sortedList = new LinkedList<Repair>();
//
//		// assign head of completedList to current
//		Repair current = completedList.poll();
//
//		if (sortedList.size() == 0) {
//			sortedList.add(current);
//		}
//
//		// while loop to traverse each element of list passed into method
//		while (!completedList.isEmpty()) {
//			current = completedList.poll();
//
//			// variable to keep track of sortedLisPosition
//			int listPos = 0;
//
//			// variable for sortedList size before comparisons
//			int sortListSize = sortedList.size();
//
//			// while loop to compare current element's techId to sortedList elements' techId
//			while (listPos < sortListSize) {
//				if (sortedList.get(listPos).getTech().getId() > current.getTech().getId()) {
//					Repair temp = sortedList.get(listPos);
//					sortedList.add(listPos, current);
//					sortedList.add(listPos + 1, temp);
//					listPos++;
//					// else if checks if just compared last element in sortedList
//					// and adds to end of sortedList if true
//				} else if (listPos == sortListSize - 1) {
//					sortedList.add(current);
//					listPos++;
//					// else statement means techId of current element is less than current element
//					// in
//					// sortedList and there are still more elements to check in sortedList and
//					// listPos should be increased by one to look at next element in sortedList
//				} else {
//					listPos++;
//				}
//			}
//		}
//
//		// print of sorted list for accuracy check
////		System.out.println("\nSORTED LIST ELEMENTS AFTER SORT: ");
////		for (Repair repair : sortedList) {
////			System.out.println(repair.toString() + " Tech ID: " + repair.getTech().getId());
////		}
//		
//		// return sortedList
//		return sortedList;
//
//	}
}
