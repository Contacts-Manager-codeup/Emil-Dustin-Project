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
        String filename = "Contacts.txt";

        Path dataDirectory = Paths.get(directory);

        Path contactsPath = Paths.get(directory, filename);

        if(Files.notExists(dataDirectory)){
            Files.createDirectories(dataDirectory);
        }

        if(Files.notExists(contactsPath)){
            Files.createFile(contactsPath);
        }

        List<String> contactList = new ArrayList<>();


        contactList.add("Name | Phone number");
        contactList.add("---------------");
        contactList.add("Jack Blank | 1231231234");
        contactList.add("Jane Doe | 2342342345");
        contactList.add("Sam Space | 3453453456");

        Files.write(contactsPath, contactList);

        int userOption;
        List<String> printListFromFile = Files.readAllLines(contactsPath);
        do {
            System.out.println("1. View contacts");
            System.out.println("2. Add a new contact");
            System.out.println("3. Search a contact by name");
            System.out.println("4. Delete an existing contact");
            System.out.println("5. Exit");
            System.out.println("Enter an option (1, 2, 3, 4, or 5");
            userOption = sc.nextInt();
            sc.nextLine();

            if (userOption == 1){
                System.out.println("option 1: View contacts");
                for(int i = 0; i < printListFromFile.size(); i++){
                    System.out.println(printListFromFile.get(i));
                }
                System.out.println();
            }else if(userOption == 2){
                System.out.println("option 2: Add a new contact");
                System.out.println("Enter a new contact: Name | Number");
                String newContact = sc.nextLine();
                Files.write(contactsPath, Arrays.asList(newContact), StandardOpenOption.APPEND);
                printListFromFile = Files.readAllLines(contactsPath);

                for(int i = 0; i < printListFromFile.size(); i++){
                    System.out.println(printListFromFile.get(i));
                }

            } else if (userOption == 3) {
                System.out.println("option 3: Search a contact by name");
                System.out.println("Enter a name to search");
                String userSearch = sc.nextLine();

                for (int i = 0; i < printListFromFile.size(); i++){
                    if (printListFromFile.get(i).contains(userSearch)){
                        System.out.println(printListFromFile.get(i));
                    }
                }


//                for(String line : printListFromFile){
//                    if(line.contains(userSearch)){
//                        System.out.println(line);
//                    }
//
//                }
//                Path ContactsPath = Paths.get(dataDirectory, filename);
//                List<String> Personlist;
//                try {
//                    Personlist = Files.readAllLines(ContactsPath);
//                    for (String person : Personlist) {
//                        if (person.toLowerCase().contains(userSearch.toLowerCase())) {
//                            System.out.println(“Contact:\n” + person);
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
                try (Stream<String> stream = Files.lines(Paths.get("src/data/Contacts.txt"))) {

                    stream.filter(line -> line.contains(" " + userSearch + " ")).forEach(System.out::println);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (userOption == 4) {
                System.out.println("option 4: Delete an existing contact");

            } else if (userOption == 5) {
                System.out.println("option 5: Exiting the application");

            }

        }while (userOption != 5);



    }
}
