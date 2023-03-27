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
				RepairsNotStartedWindow rNSW = new RepairsNotStartedWindow(repairsNotStartedQ);
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
				InProgressWindow iPW = new InProgressWindow(inProgressLL);
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
				CompletedRepairsWindow cRW = new CompletedRepairsWindow(completedLL);
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
	static void completeRepair(LinkedList<Repair> inProgressList, LinkedList<Repair> completedList, Queue<Technician> techQ) {
		// check if inProgressList has at least one element in it, if not, then display message dialog
		if (inProgressList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are no repairs currently in progress");
			return;
		}
		
		boolean validInput = false;
		String orderNumInput = JOptionPane.showInputDialog("Enter the order number you wish to complete: ", "Order Number");
		
		while (!validInput && orderNumInput != null) {
			try {
				if (Integer.parseInt(orderNumInput) <= 0) {
					orderNumInput = JOptionPane.showInputDialog("INVALID ENTRY! Order Number to Complete: ", "Order Number");
				} else {
					// check each element in inProgressList for matching orderNum
					for (Repair repairs : inProgressList) {
						if(repairs.getOrderNum() == Integer.parseInt(orderNumInput)) {
							Repair current = repairs;
							current.setCompletionDate(LocalDate.now());
							Technician currentTech = current.getTech();
							techQ.add(currentTech);
							completedList.add(current);
							inProgressList.remove(current);
							JOptionPane.showMessageDialog(null, "Order number: " + current.getOrderNum() + ", completed on " + current.getCompletionDate() + " by " + current.getTech().getFullName());
							System.out.println("Order number: " + current.getOrderNum() + ", completed on " + current.getCompletionDate() + " by " + current.getTech().getFullName() + " WINDOW");
							validInput = true;
							return;
						} 
					}
					// message dialog indicating the order number was not found in the inProgressList
					JOptionPane.showMessageDialog(null, "Order number not found");
					System.out.println("Order Number Not Found WINDOW");
					break;
				}
			} catch (NumberFormatException ex) {
				orderNumInput = JOptionPane.showInputDialog("INVALID ENTRY! Order Number to Complete: ", "Order Number");
			}
		}
		
	}
}
