import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender extends JFrame implements ActionListener {
  /**********
  *Variables*
  ***********/
  //Buttons
  private JButton to;
  private JButton send;
  private JButton clear;
  
  //Labels
  private JLabel from;
  private JLabel subject;
  private JLabel message;
  private JLabel cc;
  
  //JTextFields
  private JTextField subjectBox;
  private JTextField fromBox;
  private JTextField toBox;
  private JTextField ccBox;
  
  //JTextAreas
  private JTextArea messageBox;
  
  //JScrollPane
  private JScrollPane scroll;
  
  //PrintWriter
  private PrintWriter draft;
  
  //Strings
  private String username;
  private String password;
  private String[] creds;
  
  //Static variables
  private int MAX_SIZE_X = 65;
  private int MAX_SIZE_Y = 100;
  
  /************
  *Constructor*
  *************/
  public EmailSender() {
    //Initialize components
    to = new JButton("To:");
    to.addActionListener(this);
    clear = new JButton("Clear");
    clear.addActionListener(this);
    send = new JButton("Send");
    send.addActionListener(this);
    
    from = new JLabel("From:");
    subject = new JLabel("Subject:");
    message = new JLabel(" Message:");
    cc = new JLabel("CC:");
    
    fromBox = new JTextField(MAX_SIZE_X);
    toBox = new JTextField(MAX_SIZE_X);
    subjectBox = new JTextField(MAX_SIZE_X);
    ccBox = new JTextField(MAX_SIZE_X);
    
    messageBox = new JTextArea(MAX_SIZE_X, MAX_SIZE_Y);
    
    

    username = ""; password = "";
    
    
    //Create panel containing to area
    JPanel container = new JPanel(new BorderLayout());
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
	
    leftPanel.setPreferredSize(new Dimension(60, 25));
	
    leftPanel.add(from);
    leftPanel.add(to);
    leftPanel.add(cc);
    leftPanel.add(subject);
	
    rightPanel.add(fromBox);
    rightPanel.add(toBox);
    rightPanel.add(ccBox);
    rightPanel.add(subjectBox);
	
    container.add(leftPanel, BorderLayout.WEST);
    container.add(rightPanel, BorderLayout.CENTER);
    container.setPreferredSize(new Dimension(100, 100));
    
    //Make the Message area
    JPanel messageArea = new JPanel(new BorderLayout());
    JPanel labelPanel = new JPanel(new BorderLayout());
    labelPanel.add(message, BorderLayout.WEST);
    scroll = new JScrollPane(messageBox);
    messageArea.add(labelPanel, BorderLayout.NORTH);
    messageArea.add(scroll, BorderLayout.CENTER);
    
    //Set up other buttons
    JPanel buttonArea = new JPanel();
    buttonArea.add(send);
    buttonArea.add(clear);
    
    
    //Set main panel properties
    setLayout(new BorderLayout());
    
    
    //Add everything
    add(container, BorderLayout.PAGE_START);
    add(messageArea, BorderLayout.CENTER);
    add(buttonArea, BorderLayout.PAGE_END);
  }
  
  /***************
  *ActionListener*
  ****************/
  public void actionPerformed(ActionEvent event) {
    //If send is pressed
    if (event.getSource() == send) {
      Credentials credWindow = new Credentials(fromBox.getText(),
			       new Credentials.OnContactListener() {
	//This code is executed when the frame is closed and a value for s is
	//returned.
	public void onData(String s) {
	  //Split the credential string returned into an array where
	  //creds[0] = username and creds[1] = password.
	  String[] creds = s.split(" ");
	  
	  //Create a GmailSender object with the appropriate credentials.
	  GmailSender gmail = new GmailSender(creds[0], creds[1]);
	  
	  //Declare variables
	  boolean sent;
	  
	  //Send the email to multiple recipients
	  if(ccBox.getText() != "" && ccBox.getText() != null) {
	    String[] ccContacts = ccBox.getText().split(", ");
	    sent = gmail.sendMail(ccContacts, subjectBox.getText(),
				  messageBox.getText());
	  }
	  
	  //Send the email to one recipient
	  else {
	    sent = gmail.sendMail(toBox.getText(), subjectBox.getText(),
				  messageBox.getText());
	  }
	  
	  //For security reasons, clear the password
	  password = "";
	  
	  //Inform the user that their email has been sent or failed to send
	  if(sent) {
	    JOptionPane.showMessageDialog(null, "Email has been sent!");
	  }
	  else {
	    try {
	      draft = new PrintWriter("output.txt");
	      draft.println("From: " + fromBox.getText());
	      draft.println("To: " + toBox.getText());
	      draft.println("CC: " + ccBox.getText());
	      draft.println("Subject: " + subjectBox.getText());
	      draft.println("Message: " + messageBox.getText());
	      draft.close();
	      JOptionPane.showMessageDialog(null, "Message failed to send.",
					  "Message draft has been saved as " +
					  "draft.txt.", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    catch(FileNotFoundException e) {
	      JOptionPane.showMessageDialog(null, "File not found!");
	    }
	  }
	  
	  //Clear the text boxes
	  toBox.setText("");
	  fromBox.setText("");
	  subjectBox.setText("");
	  messageBox.setText("");
	  ccBox.setText("");
	}
      });
      
      //Window settings
      credWindow.setTitle("Enter Credentials");
      credWindow.setPreferredSize(new Dimension(300, 200));
      credWindow.pack();
      credWindow.setVisible(true);
    }
    
    //If clear is pressed
    else if (event.getSource() == clear) {
      toBox.setText("");
      fromBox.setText("");
      subjectBox.setText("");
      messageBox.setText("");
      ccBox.setText("");
      JOptionPane.showMessageDialog(this, "Fields have been reset.");
    }
    
    //If to is pressed
    else if (event.getSource() == to) {
      ContactUI contactPanel = new ContactUI(new ContactUI.OnContactListener()
      {
	  //This code is executed when the frame is closed and a value for s is
	  //returned.
	  public void onData(String s){
	    if(s != null) {
	      if (toBox.getText().equals("")) {
		toBox.setText(s);
	      }
		    
	      else {
		if (ccBox.getText().equals("")) {
		  ccBox.setText(s);
		}
		      
		else {
		  ccBox.setText(ccBox.getText() + ", " + s);
	      }
	    }
	  }
	}
      });
    }
    
    //Fail-safe
    else {
      JOptionPane.showMessageDialog(this, "An unknown error has occurred.");
    }
  }
  
  
  /*****
  *Main*
  ******/
  public static void main(String[] args) {
    EmailSender mainWindow = new EmailSender();
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainWindow.setTitle("Send Email");
    mainWindow.setPreferredSize(new Dimension(800, 600));
    mainWindow.setResizable(false);
    mainWindow.pack();
    mainWindow.setVisible(true);
  }
}