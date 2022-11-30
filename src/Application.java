import javax.sound.sampled.Line;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Application {


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String directory = "./src/data";
        String fileName = "Contacts.txt";

        Path dataDirectory = Paths.get(directory);

        Path contactsPath = Paths.get(directory, fileName);


        List<String> contactList = new ArrayList<>();


        contactList.add("Name | Phone number");
        contactList.add("---------------");
        contactList.add("Jack Blank | (123)123-1234");
        contactList.add("Jane Doe | (234)234-2345");
        contactList.add("Sam Space | (345)345-3456");

        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }


//        Files.write(contactsPath, contactList);


        int userOption;

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
            if (userOption == 1) {
                System.out.println("option 1: View contacts");
                for (int i = 0; i < printListFromFile.size(); i++) {
                    System.out.println(printListFromFile.get(i));
                }
                System.out.println();
            } else if (userOption == 2) {
                System.out.println("option 2: Add a new contact");
                System.out.println("Enter a new contact: Name | Number (xxx)xxx-xxxx");
                String newContact = sc.nextLine();

                System.out.println();

                for (int i = 0; i < printListFromFile.size(); i++) {
                    if (printListFromFile.get(i).toLowerCase().contains(newContact)) {
                        System.out.println("There's already a contact named" + newContact + "Do you want to overwrite it? (Yes/No)");
                        String userYesNo = sc.nextLine();
                        if (userYesNo.equalsIgnoreCase("Yes")){
//                            Files.write(contactsPath, Arrays.asList(newContact), StandardOpenOption.APPEND);
//                            printListFromFile.get(i).replace(newContact);
                        }
                    }


                }
//                Files.write(contactsPath, Arrays.asList(newContact), StandardOpenOption.APPEND);
//                System.out.println();


            } else if (userOption == 3) {
                System.out.println("option 3: Search a contact by name");
                System.out.println("Enter a name to search");
                String userSearch = sc.nextLine().toLowerCase();

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

                List<String> newList = new ArrayList<>();

                for (int i = 0; i < printListFromFile.size(); i++) {
                    if (printListFromFile.get(i).toLowerCase().contains(userDelete)) {
                        System.out.println(printListFromFile.get(i) + " being deleted. continue? Yes/No");
                        String userAnswer = sc.nextLine();
                        if (userAnswer.equalsIgnoreCase("yes")){
                            newList.add("");
                            System.out.println("contact deleted");
                            continue;
                        }else {
                            System.out.println("Choose another option");
                        }
                    }
                    newList.add(printListFromFile.get(i));
                }
                for (int i = 0; i < newList.size(); i++){
                    if(newList.get(i).isEmpty()){
                        newList.remove(i);
                    }
                }
                Files.write(contactsPath, newList);

            } else if (userOption == 5) {
                System.out.println("option 5: Exiting the application");

            }

        } while (userOption != 5);



    }
}
