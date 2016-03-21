import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class Credentials extends JFrame implements ActionListener {
  /**********
  *Variables*
  ***********/
  //JButtons
  private JButton ok;
  private JButton cancel;
  
  //JLabels
  private JLabel user;
  private JLabel pass;
  private JLabel instructions;
  
  //JTextFields
  private JTextField userBox;
  
  //JPasswordField
  private JPasswordField passBox;
  
  //Strings
  private String username;
  private char[] password;
  private String creds;
  
  //OnContactListener
  private OnContactListener listener;
  
  
  /************
  *Constructor*
  *************/
  public Credentials(String defaultUsername, OnContactListener l) {
    //Initialize components
    listener = l;
    
    ok = new JButton("OK");
    ok.addActionListener(this);
    cancel = new JButton("Cancel");
    cancel.addActionListener(this);
    
    user = new JLabel("Username:");
    pass = new JLabel("Password:");
    instructions = new JLabel("Please enter your GMail credentials:");
    
    if (defaultUsername != null && defaultUsername != "") {
      userBox = new JTextField(defaultUsername, 20);
    }
    else {
      userBox = new JTextField(20);
    }
    
    passBox = new JPasswordField(20);
    
    
    //Set up username area
    JPanel userArea = new JPanel(new GridLayout(2, 1));
    userArea.add(user);
    userArea.add(userBox);
    
    
    //Set up password area
    JPanel passArea = new JPanel(new GridLayout(2, 1));
    passArea.add(pass);
    passArea.add(passBox);
    
    
    //Set up button area
    JPanel buttonArea = new JPanel();
    buttonArea.add(ok);
    buttonArea.add(cancel);
    
    
    //Set up window
    setLayout(new GridLayout(4, 1));
    
    
    //Add panels to main window
    add(instructions);
    add(userArea);
    add(passArea);
    add(buttonArea);
  }
  
  
  /******************
  *OnContactListener*
  *******************/
  public interface OnContactListener {
    public void onData(String s);
  }
  
  
  /***************
  *ActionListener*
  ****************/
  public void actionPerformed(ActionEvent event) {
    //If user presses OK button
    password = passBox.getPassword();
    
    //For checking if username is blank or not
    boolean blankUser = false;
    String user = userBox.getText();
    if (user.length() == 0) {
      blankUser = true;
    }
    
    //For checking if password is blank or not
    boolean blankPass = false;
    if (password.length == 0) {
      blankPass = true;
    }
    
    if (event.getSource() == ok) {
      //If both username and password fields are empty
      if(blankUser &&  blankPass) {
	JOptionPane.showMessageDialog(this, "Username and password cannot be blank!");
      }
      
      //If username field is empty
      else if(blankUser) {
	JOptionPane.showMessageDialog(this, "Username cannot be blank!");
      }
      
      //If password field is empty
      else if(blankPass) {
	JOptionPane.showMessageDialog(this, "Password cannot be blank!");
      }
      
      //If there exists information for both the username and password fields
      else {
	//Retrieve username and password the user entered.
	username = userBox.getText();
	String temp = "";
	for (int i = 0; i < password.length; i++) {
	  temp += password[i];
	}
	creds = username + " " + temp;
	
	//Return the creds to the main.
	listener.onData(creds);
	
	//Clear password for security
	for (int i = 0; i < password.length; i++) {
	  password[i] = ' ';
	}
	
	//Destroy window
	dispose();
      }
    }
    
    //If the user presses the cancel button
    else if (event.getSource() == cancel) {
      dispose();
    }
    
    //Fail-safe
    else {
      JOptionPane.showMessageDialog(this, "An unknown error has occurred.");
    }
  }
}