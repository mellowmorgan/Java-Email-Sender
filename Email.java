import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class Email extends JFrame implements ActionListener {
	private JLabel toLabel;
	private JTextField toField;
	private JLabel ccLabel;
	private JTextField ccField;
	private JLabel bccLabel;
	private JTextField bccField;
	private JLabel subjectLabel;
	private JTextField subjectField;
	private JLabel messageLabel;
	private JTextArea messageArea;
	private JButton submitButton;
	
	public Email() {
		setLayout(new FlowLayout());
		toLabel = new JLabel("        To:        ");
		toField = new JTextField(18);
		
		ccLabel = new JLabel("        CC:        ");
		ccField = new JTextField(18);
		
		bccLabel = new JLabel("       Bcc:        ");
		bccField = new JTextField(18);
	
		subjectLabel = new JLabel("Subject:          ");
		subjectField = new JTextField(30);
		
		messageLabel = new JLabel("Message: ");
		messageArea = new JTextArea(10, 30);
		
		submitButton = new JButton("Send");
		submitButton.addActionListener(this);
		
		
		add(toLabel);
		add(toField);
		add(ccLabel);
		add(ccField);
		add(bccLabel);
		add(bccField);
		add(subjectLabel);
		add(subjectField);
		add(messageLabel);
		add(messageArea);
		add(submitButton);
		
	}
		
	public void actionPerformed(ActionEvent event) {
		String to = toField.getText();
		if(to.length() < 1) {	
			JOptionPane.showMessageDialog(this, "Please enter a to field.");
		}
		else {
			try{
					
				FileWriter fstream = new FileWriter("email.txt",true);
				BufferedWriter out = new BufferedWriter(fstream);
					
				out.write("To: " + toField.getText());
				out.newLine();
				
				out.write("CC: " + ccField.getText());
				out.newLine();
				
				out.write("Bcc: " + bccField.getText());
				out.newLine();
				
				out.write("Subject: " + subjectField.getText());
				out.newLine();
				
				out.write("Message: \n" + messageArea.getText());
				out.newLine();
				out.close();
				
				
				toField.setText("");
				ccField.setText("");
				bccField.setText("");
				subjectField.setText("");
				messageArea.setText("");
				
				JOptionPane.showMessageDialog(this, "Your message has been sent");
				}
				catch (Exception e) {
					System.err.println("Error: " + e.getMessage());
				}
		}
	}
	
	public static void main(String[] args) {
		Email window = new Email();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack(); 
		window.setSize(360,400);
		window.setVisible(true);
	}
}