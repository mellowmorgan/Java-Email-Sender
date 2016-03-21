public class Contact {
  /**********
  *Variables*
  ***********/
  private String[] name; //Array of Strings containing name.
			 //[0] - First name
			 //[1] - Middle name/middle initial (doesn't
			 //	 matter which)
			 //[2] - Last name
			 //[3] - Name as a full concatenated string,
			 //	 e.g. the equivalant of name[0] +
			 //	 name[1] + name[2] + "";
  private String email; //The contact's email address e.g. bob@gmail.com
  
  
  /*************
  *Constructors*
  **************/
  public Contact() {
    //Constructs an empty contact if no information is passed
    this.name = new String[4];
    this.name[0] = "null";
    this.name[1] = "null";
    this.name[2] = "null";
    this.name[3] = this.name[0] + " " + this.name[1] + " "
		   + " " + this.name[2];
    this.email = "null";
  }
  
  public Contact(String first, String middle, String last, String email) {
    //Constructs a contact with all available information passed
    this.name = new String[4];
    this.name[0] = first;
    this.name[1] = middle;
    this.name[2] = last;
    this.name[3] = this.name[0] + " " + this.name[1] + " "
		   + this.name[2];
    this.email = email;
  }
  
  public Contact(String[] name, String email) {
    //Constructs a contact with all available information passed,
    //allows the caller to use a String array to pass the information
    //instead of individual Strings
    this.name = new String[4];
    this.name[0] = name[0];
    this.name[1] = name[1];
    this.name[2] = name[2];
    if (name[3] == "null" || name[3] == "") {
      this.name[3] = this.name[0] + " " + this.name[1] + " "
		     + this.name[2];
    }
    else {
      this.name[3] = name[3];
    }
    this.email = email;
  }
  
  public Contact(String first, String last, String email) {
    //Constructs a contact without a middle name
    this.name = new String[4];
    this.name[0] = first;
    this.name[1] = "";
    this.name[2] = last;
    this.name[3] = this.name[0] + " " + this.name[2];
    this.email = email;
  }
  
  
  /**********
  *Functions*
  ***********/
  //Getters
  public String[] getName() {
    return this.name;
  }
  
  public String getFirstName() {
    return this.name[0] + "";
  }
  
  public String getMiddleName() {
    return this.name[1] + "";
  }
  
  public String getLastName() {
    return this.name[2] + "";
  }
  
  public String getFullName() {
    return this.name[3] + "";
  }
  
  public String getEmail() {
    return this.email + "";
  }
  
  //Setters
  public void setName(String[] name) {
    this.name[0] = name[0];
    this.name[1] = name[1];
    this.name[2] = name[2];
    this.name[3] = this.name[0] + " " + this.name[1] + " "
		   + this.name[2];
  }
  
  public void setFirstName(String first) {
    this.name[0] = first;
    this.name[3] = this.name[0] + " " + this.name[1] + " "
		   + this.name[2];
  }
  
  public void setMiddleName(String middle) {
    this.name[1] = middle;
    this.name[3] = this.name[0] + " " + this.name[1] + " "
		   + this.name[2];
  }
  
  public void setLastName(String last) {
    this.name[2] = last;
    this.name[3] = this.name[0] + " " + this.name[1] + " "
		   + this.name[2];
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  //Other functions
  public String toString() {
    return this.name[3] + " " + this.email + "";
  }
  
  /**************************************************
  *Test main                                        *
  *NOTE: THIS SHOULD NOT BE RUN EXCEPT FOR DEBUGGING*
  *	 OTHERWISE, DON'T CALL IT                   *
  ***************************************************/
  public static void main(String[] args) {
    System.out.println("This program just executed!");
    System.out.println("Test operations------------------------");
    String first = "David";
    String middle = "Lee";
    String last = "Smith";
    String fullname = first + " " + middle + " " + last;
    String email = "dlSmith@gmail.com";
    String[] nameArray = new String[] {first, middle, last, fullname};
    Contact davidLeeSmith1 = new Contact();
    Contact davidLeeSmith2 = new Contact(nameArray, email);
    Contact davidLeeSmith3 = new Contact(first, middle, last, email);
    Contact davidLeeSmith4 = new Contact(first, last, email);
    System.out.println("davidLeeSmith1:");
    System.out.println("Name: " + davidLeeSmith1.getFullName());
    System.out.println("First: " + davidLeeSmith1.getFirstName());
    System.out.println("Middle: " + davidLeeSmith1.getMiddleName());
    System.out.println("Last: " + davidLeeSmith1.getLastName());
    System.out.println("Email: " + davidLeeSmith1.getEmail());
    System.out.println("toString: " + davidLeeSmith1.toString());
    System.out.println("davidLeeSmith2:");
    System.out.println("Name: " + davidLeeSmith2.getFullName());
    System.out.println("First: " + davidLeeSmith2.getFirstName());
    System.out.println("Middle: " + davidLeeSmith2.getMiddleName());
    System.out.println("Last: " + davidLeeSmith2.getLastName());
    System.out.println("Email: " + davidLeeSmith2.getEmail());
    System.out.println("toString: " + davidLeeSmith2.toString());
    System.out.println("davidLeeSmith3:");
    System.out.println("Name: " + davidLeeSmith3.getFullName());
    System.out.println("First: " + davidLeeSmith3.getFirstName());
    System.out.println("Middle: " + davidLeeSmith3.getMiddleName());
    System.out.println("Last: " + davidLeeSmith3.getLastName());
    System.out.println("Email: " + davidLeeSmith3.getEmail());
    System.out.println("toString: " + davidLeeSmith3.toString());
    System.out.println("davidLeeSmith4:");
    System.out.println("Name: " + davidLeeSmith4.getFullName());
    System.out.println("First: " + davidLeeSmith4.getFirstName());
    System.out.println("Middle: " + davidLeeSmith4.getMiddleName());
    System.out.println("Last: " + davidLeeSmith4.getLastName());
    System.out.println("Email: " + davidLeeSmith4.getEmail());
    System.out.println("toString: " + davidLeeSmith4.toString());
  }
  
}
