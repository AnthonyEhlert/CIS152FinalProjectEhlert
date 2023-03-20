package model;

import java.time.LocalDate;

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

public class Repair implements Comparable<Repair>{

	// DATA MEMBERS
	private int orderNum;
	private int priority; // variable representing the priority of the repair (1-4 inclusive)
	private String customer;
	private Technician tech;
	private LocalDate completionDate;
	
	// CONSTRUCTORS
	
	/**
	 * Default no-arg constructor with a private access modifier 
	 * to force usage of other constructors
	 */
	private Repair() {
		this.orderNum = 0;
		this.priority = 4;
		this.customer = "No Customer Name Provided";
		this.tech = null;
		this.completionDate = null;
	}
	
	/**
	 *This constructor creates repairs objects using the orderNum, priority, and customer values
	 *that are passed in and sets the tech and completionDate variables to null.
	 *
	 *If statements are also used to ensure orderNum is greater than zero
	 *and the priority is between 1-4 inclusive.
	 */
	public Repair(int orderNum, int repairPriority, String customer) {
		this.customer = customer;
		this.tech = null;
		this.completionDate = null;
		
		if (orderNum < 0) {
			this.orderNum = Integer.MAX_VALUE;
		} else {
			this.orderNum = orderNum;
		}
		
		if (repairPriority > 4 || repairPriority < 1) {
			this.priority = 4;
		} else {
			this.priority = repairPriority;
		}
	}

	// GETTERS AND SETTERS
	
	/**
	 * @return the orderNum
	 */
	public int getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * This metho
	 * 
	 * @param priority the priority to set
	 */
	public void setPriority(int repairPriority) {
		if (repairPriority > 4 || repairPriority < 1) {
			this.priority = 4;
		} else {
			this.priority = repairPriority;
		}
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the tech
	 */
	public Technician getTech() {
		return tech;
	}

	/**
	 * @param tech the tech to set
	 */
	public void setTech(Technician tech) {
		this.tech = tech;
	}

	/**
	 * @return the completionDate
	 */
	public LocalDate getCompletionDate() {
		return completionDate;
	}

	/**
	 * @param completionDate the completionDate to set
	 */
	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	/**
	 * This toString method has been overwritten so the tech object 
	 * fName and lName variables will be printed
	 */
	@Override
	public String toString() {
		if (tech == null) {
			return "Repair [orderNum=" + orderNum + ", priority=" + priority + ", customer=" + customer + ", tech="
					+ null + ", completionDate=" + completionDate + "]";
		} else {
			return "Repair [orderNum=" + orderNum + ", priority=" + priority + ", customer=" + customer + ", tech="
				+ tech.getFirstName() + " " + tech.getLastName() + ", completionDate=" + completionDate + "]";
		}
	}

	/**
	 * This is the required method from the comparator class that compares the
	 * priority value of each repair object. It is also required to be able to
	 * instantiate a Priority Queue object with the repair class
	 */
	@Override
	public int compareTo(Repair r) {
		if (getPriority() > r.getPriority()) {
			return 1;
		} else if (getPriority() < r.getPriority()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	
	
}
