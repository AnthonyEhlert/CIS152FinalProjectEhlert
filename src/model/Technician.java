package model;

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
 *							well as the information needed to create various repairs job objects.
 *					 Output: Various reports displaying jobs not started, jobs in progress, and completed jobs.
 * Academic Honesty	: I attest that this is my original work.
 * I have not used unauthorized source code, either modified or 
 * unmodified. I have not given other fellow student(s) access to
 * my program.         
 *****************************************************************/
public class Technician {

	// DATA MEMBERS
	private int id;
	private String fName;
	private String lName;
	
	// CONSTRUCTORS

	/**
	 * This is the default no-arg constructor with a private access
	 * modifier to force the usage of the other constructors
	 */
	private Technician() {
		this.id = Integer.MAX_VALUE;
		this.fName = "No First Name Provided";
		this.lName = "No Last Name Provided";
	}
	
	/**
	 * This is a Technician constructor that creates a Technician object
	 * using the passed in id, first name, and last name.
	 */
	public Technician(int id, String firstName, String lastName) {
		this.id = id;
		this.fName = firstName;
		this.lName = lastName;
	}
	
	// GETTERS AND SETTERS
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	@Override
	public String toString() {
		return "Technician [id=" + id + ", fName=" + fName + ", lName=" + lName + "]";
	}
}
