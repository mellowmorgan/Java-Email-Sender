import java.io.*;
import java.util.*;
import javax.swing.*;

public class ContactList
{
	Scanner scan;
	FileWriter writer;
	File contacts;
	
	public ContactList()
	{
		try
		{
			contacts = new File("contacts.txt");
			if (!contacts.exists())
			{
				contacts.createNewFile();
			}
		}
		
		catch (IOException ioe)
		{
			System.out.println("IOException: The file could not be created.");
		}
	}
	
	public Boolean add(Contact contact)
	{
		// Reinstantiate the FileWriter
		try
		{
			writer = new FileWriter(new File("contacts.txt"), true);
		}
		
		// FileWriter throws an IOException if an error occurs.
		catch (IOException ioe)
		{
			System.out.println("IOException: The file could not be created.");
		}
	
		try
		{
			writer.write(contact.getFullName() + " " + contact.getEmail() + "\r\n");
			writer.close();
			
			return true;
		}
		
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "The contact could not be added.\nPlease try again.", "Error", 0);
			System.out.println("The contact could not be added. Please try again.");
			
			return false;
		}
	}
	
	public Boolean modify(String oldInfo, String changedInfo)
	{		
		String output = "";
		String temp = "";
		
		// Reinstantiate the Scanner and the FileWriter
		try
		{
			scan = new Scanner(new File("contacts.txt"));
			
		}
		
		// When using Scanner to read from a file you have to catch the FileNotFoundException
		// in case the file does not exist.
		catch (FileNotFoundException fnfException)
		{
			System.out.println("The file contacts.txt could not be found.");
		}
		
		while (scan.hasNextLine())
		{
			temp = scan.nextLine();
			if (temp.equals(oldInfo))
			{
				output += (changedInfo + "\r\n");
			}
			
			else
			{
				output += (temp + "\n");
			}
		}
		
		try
		{
			writer = new FileWriter(new File("contacts.txt"));
			writer.write(output);
			System.out.println(output);
			
			// Since writer was instantiated it needs to be closed
			writer.close();
		}
		
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "The contact could not be modified.\nPlease try again.", "Error", 0);
			System.out.println("The contact could not be modified. Please try again.");
			return false;
		}
			
		return true;
	}
	
	public Boolean remove(String contact)
	{
		String output = "";
		String temp = "";

		// Reinstantiate the Scanner and the FileWriter
		try
		{
			scan = new Scanner(new File("contacts.txt"));
		}
		
		// When using Scanner to read from a file you have to catch the FileNotFoundException
		// in case the file does not exist.
		catch (FileNotFoundException fnfException)
		{
			System.out.println("The file contacts.txt could not be found.");
		}
		
		while(scan.hasNextLine())
		{
			temp = scan.nextLine();

			if (!temp.equals(contact))
			{
				output += temp;
			}
			
			else
			{
				output += "";
				
			}
		}
		
		try
		{
			writer = new FileWriter(new File("contacts.txt"));
			writer.write(output);
			writer.close();
			return true;
		}
		
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "The contact could not be removed.\nPlease try again.\n" + ioe.getMessage(), "Error", 0);
			System.out.println("The contact could not be removed. Please try again.");
			return false;
		}
	}
	
	public List<String> getContacts()
	{
		// Reinstantiate the Scanner and the FileWriter
		try
		{
			scan = new Scanner(new File("contacts.txt"));
		}
		
		// When using Scanner to read from a file you have to catch the FileNotFoundException
		// in case the file does not exist.
		catch (FileNotFoundException fnfException)
		{
			System.out.println("The file contacts.txt could not be found.");
		}
	
		List<String> data = new ArrayList<String>();
		while (scan.hasNextLine())
		{
			data.add(scan.nextLine());
		}
		return data;
	}
}