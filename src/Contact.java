import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Contact {

    public String name;
    public String number;


    //constructor for contact class
    public Contact(String contactName, String contactNumber){
        this.name = contactName;
        this.number = contactNumber;
    }

    public static void main(String[] args) throws IOException {
        //creating 3 existing contact objects
        Contact contact1 = new Contact("Jack Blank", "(123)123-1234");
        Contact contact2 = new Contact("Jane Doe", "(234)234-2345");
        Contact contact3 = new Contact("Sam Space", "(345)345-3456");


        Scanner sc = new Scanner(System.in);
        String directory = "./src/data";
        String fileName = "Contacts.txt";

        Path dataDirectory = Paths.get(directory);

        Path contactsPath = Paths.get(directory, fileName);


        List<String> contactList = new ArrayList<>();


        //adding Strings and contacts to the array list
        contactList.add("Name | Phone number");
        contactList.add("---------------");
        contactList.add(contact1.name+ " | "+ contact1.number);
        contactList.add(contact2.name+ " | "+ contact2.number);
        contactList.add(contact3.name+ " | "+ contact3.number);

        // creating the txt file if it does not exist
        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }

        //used the file write to push the initial list to the txt file
//        Files.write(contactsPath, contactList);


        int userOption;
        // used a do while loop to run the menu
        do {
            System.out.println("1. View contacts");
            System.out.println("2. Add a new contact");
            System.out.println("3. Search a contact by name");
            System.out.println("4. Delete an existing contact");
            System.out.println("5. Exit");
            System.out.println("Enter an option (1, 2, 3, 4, or 5)");
            userOption = sc.nextInt();
            sc.nextLine();

            List<String> printListFromFile = Files.readAllLines(contactsPath);

            // used an if else statement to run the function for the user option on the menu
            if (userOption == 1) {
                // prints out each line in the text file using a for loop
                System.out.println("option 1: View contacts");
                for (int i = 0; i < printListFromFile.size(); i++) {
                    System.out.println(printListFromFile.get(i));
                }
                System.out.println();
            } else if (userOption == 2) {
                // crating a new variable for a new contact name and number
                System.out.println("option 2: Add a new contact");
                System.out.println("Enter a new contact: Name");
                String newContactName = sc.nextLine();
                System.out.println("Enter new contact number");
                String newContactNumber = sc.nextLine();
                // formatting the number to include - and () depending on the length of the new number
                if (newContactNumber.length() == 7){
                    newContactNumber = newContactNumber.substring(0,3)+ "-" +newContactNumber.substring(3);
                } else if (newContactNumber.length() == 10) {
                    newContactNumber = "("+newContactNumber.substring(0,3)+ ")" +newContactNumber.substring(3,6)+"-"+newContactNumber.substring(6);
                }
                System.out.println();
                //creating the new contact object the is being pushed into the txt file
                Contact newContact = new Contact(newContactName,newContactNumber);
                Files.write(contactsPath, Arrays.asList(newContact.name +" | "+newContact.number), StandardOpenOption.APPEND);


            } else if (userOption == 3) {
                System.out.println("option 3: Search a contact by name");
                System.out.println("Enter a name to search");
                String userSearch = sc.nextLine().toLowerCase();
                // useing a for loop to search the txt lines for anything that contains what the user searched
                for (int i = 0; i < printListFromFile.size(); i++) {
                    if (printListFromFile.get(i).toLowerCase().contains(userSearch)) {
                        System.out.println(printListFromFile.get(i));
                    }
                }
                System.out.println();

            } else if (userOption == 4) {
                System.out.println("option 4: Delete an existing contact");
                System.out.println("Enter the contact name to delete");
                String userDelete = sc.nextLine().toLowerCase();
                //crating a new list to replace what we want to be deleted
                List<String> newList = new ArrayList<>();

                //used a for loop to look at each line and used the contain method to search for any matches to the user input
                for (int i = 0; i < printListFromFile.size(); i++) {
                    if (printListFromFile.get(i).toLowerCase().contains(userDelete)) {
                        System.out.println(printListFromFile.get(i) + " being deleted. continue? Yes/No");
                        String userAnswer = sc.nextLine();
                        //used a nested if statement to ask the user if they are sure they want to delete the contact
                        if (userAnswer.equalsIgnoreCase("yes")){
                            //replaces the line with a blank line
                            newList.add("");
                            System.out.println("contact deleted");
                            continue;
                        }else {
                            System.out.println("Choose another option");
                        }
                    }
                    newList.add(printListFromFile.get(i));
                }
                //for loop to search for blank lines and deletes it
                for (int i = 0; i < newList.size(); i++){
                    if(newList.get(i).isEmpty()){
                        newList.remove(i);
                    }
                }
                //updates the txt file with the new list
                Files.write(contactsPath, newList);

            } else if (userOption == 5) {
                System.out.println("option 5: Exiting the application");

            }

        } while (userOption != 5);



    }
}
