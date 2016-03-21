import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Arrays;
        
        public class ContactUI extends JFrame implements ActionListener
        {
            // Declare all of the variables
			private JPanel[] panel;
			private JScrollPane scrollPane;
			private JList listview;
			private JButton[] button;
			private JLabel[] label;
			private JTextField[] userInput;
			private String recipients;
			private DefaultListModel listModel;
			private ContactList contactList;
			private OnContactListener listener;
			private Contact contact;
			
            public interface OnContactListener
			{
				public void onData(String s);
			}
			
            public ContactUI(OnContactListener l)
            {
				listener = l;
				contactList = new ContactList();
				panel = new JPanel[7];
				listModel = new DefaultListModel();
				
				List<String> data = contactList.getContacts();
				
				if (data.size() > 0)
				{
					for (String singleContact : data)
					{
						listModel.addElement(singleContact);
					}
					
					listview = new JList(listModel);
				}
				
				else
				{
					listview = new JList(listModel);
				}
				
				listview.addListSelectionListener(lsl);
				scrollPane = new JScrollPane(listview);
				
				scrollPane.setPreferredSize(new Dimension(300, 200));
				
				button = new JButton[5];
				label = new JLabel[2];
				userInput = new JTextField[2];
				
				// Instantiate all of the JPanels
				for (int i = 0; i < panel.length; i++)
				{
					panel[i] = new JPanel();
				}
				
				// Instantiate all of the JButtons
				for (int j = 0; j < button.length; j++)
				{
					button[j] = new JButton();
					button[j].addActionListener(this);
				}
				
				// Instantiate all of the JLabels
				for (int k = 0; k < label.length; k++)
				{
					label[k] = new JLabel();
				}
				
				// Instantiate all of the JTextFields
				for (int m = 0; m < userInput.length; m++)
				{
					userInput[m] = new JTextField(20);
				}
              
				// Set the text for all of the JButtons
				button[0].setText("Add");
				button[1].setText("Delete");
				button[2].setText("Save");
				button[3].setText("OK");
				button[4].setText("Cancel");
				
				// Set the text for all of the JLables
				label[0].setText("Name:");
				label[1].setText("Email:");
				
				// Layout for the button and scrollPane panels
				panel[1].setLayout(new BorderLayout());
				panel[1].add(panel[2], BorderLayout.NORTH);
				panel[1].add(panel[3], BorderLayout.CENTER);
				
				// Layout for the Add, Delete, and Save buttons
				panel[2].setLayout(new FlowLayout());
				panel[2].add(button[0]);
				panel[2].add(button[1]);
				panel[2].add(button[2]);
				
				panel[3].setLayout(new BorderLayout());
				//contactListPanel.add(panel[1], BorderLayout.NORTH);
				panel[3].add(scrollPane, BorderLayout.CENTER);
				
				panel[5].setLayout(new GridLayout(6, 1));
				panel[5].add(label[0]);
				panel[5].add(userInput[0]);
				panel[5].add(label[1]);
				panel[5].add(userInput[1]);
				
				panel[4].setLayout(new BorderLayout());
				panel[4].add(panel[5], BorderLayout.NORTH);
				panel[4].add(panel[6], BorderLayout.CENTER);
				
				panel[6].add(button[3]);
				panel[6].add(button[4]);
				
				panel[0].add(panel[1]);
				panel[0].add(panel[4]);
				add(panel[0]);

				setTitle("Address Book");
				pack();
				setVisible(true);
            }
			
			public void actionPerformed(ActionEvent event)
			{
				// Add button
				if (event.getSource() == button[0])
				{
					String[] contactArr = new String[3];
					contactArr = userInput[0].getText().split("\\s+");
					
					if(contactArr.length == 2)
					{
						contact = new Contact(contactArr[0], contactArr[1], userInput[1].getText());
					}
					else
					{
						contact = new Contact(contactArr[0], contactArr[1], contactArr[2], userInput[1].getText());
					}
					
					Boolean addScuessful = contactList.add(contact);
					{
						listModel.addElement(userInput[0].getText() + " " + userInput[1].getText());
						
						// Reset the fields
						userInput[0].setText("");
						userInput[1].setText("");
					}
				}
				
				// Delete Button
				else if (event.getSource() == button[1])
				{
					String delContact = String.valueOf(listview.getSelectedValue());
					Boolean removeSuccessful = contactList.remove(delContact);
					
					if (removeSuccessful)
					{
						listModel.removeElement(listview.getSelectedValue());
						
						// Reset the fields
						userInput[0].setText("");
						userInput[1].setText("");
					}
				}
				
				// Save Button
				else if (event.getSource() == button[2])
				{
					String oldInfo = String.valueOf(listview.getSelectedValue());
					String changedInfo = userInput[0].getText() + " " + userInput[1].getText();
					Boolean saveSuccessful = contactList.modify(oldInfo, changedInfo);
					
					if (saveSuccessful)
					{
						listModel.removeElement(listview.getSelectedValue());
						listModel.addElement(userInput[0].getText() + " " + userInput[1].getText());
						// Reset the fields
						userInput[0].setText("");
						userInput[1].setText("");
					}
				}
				
				// OK Button
				else if (event.getSource() == button[3])
				{
					recipients = String.valueOf(listview.getSelectedValue());
					String[] selectedContact = recipients.split("\\s+");
					listener.onData(selectedContact[selectedContact.length - 1]);
					
					dispose();
					
				}
				
				// Cancel Button
				else if (event.getSource() == button[4])
				{
					dispose();
				}
			}
			
			// This takes the selection and puts into the JTextFields
			private ListSelectionListener lsl = new ListSelectionListener()
			{
				@Override
				public void valueChanged(ListSelectionEvent evt)
				{
					String[] selectedContact = String.valueOf(listview.getSelectedValue()).split("\\s+");
			
					if (selectedContact.length == 3)
					{
						userInput[0].setText(selectedContact[0] + " " + selectedContact[1]);
						userInput[1].setText(selectedContact[2]);
					}
					
					if (selectedContact.length == 4)
					{
						userInput[0].setText(selectedContact[0] + " " + selectedContact[1] + " " + selectedContact[2]);
						userInput[1].setText(selectedContact[3]);
					}
				}
			};
			
			public static void main(String[] args)
			{			
				ContactUI dialog = new ContactUI(new ContactUI.OnContactListener()
				{
					public void onData(String s)
					{

					}
				});
			}
        }