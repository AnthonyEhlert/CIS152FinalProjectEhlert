package view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Repair;

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
public class InProgressWindow extends JFrame {

	private JPanel contentPane;

	// no-arg constructor w/ private access modifier to force usage of other
	// constructor
	private InProgressWindow() {
	}

	public InProgressWindow(Object[] inProgressArr) {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHeader = new JLabel("Repair Orders In Progress:");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(265, 15, 175, 14);
		contentPane.add(lblHeader);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(52, 50, 256, 173);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		String inProgressLLContents = "";

		for (Object repairs : inProgressArr) {
			inProgressLLContents += ("Priority Value: " + ((Repair) repairs).getPriority() + ", Order Number: "
					+ ((Repair) repairs).getOrderNum() + ", Customer: " + ((Repair) repairs).getCustomer()
					+ ", Assigned Tech: " + ((Repair) repairs).getTech().getFullName() + "\n");
		}

		textArea.append(inProgressLLContents);
		contentPane.add(textArea);

		JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 50, 684, 173);
		contentPane.add(scrollPane);

	}
}
