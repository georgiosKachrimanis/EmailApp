package emailApp.com.georgioskachrimanis;

import java.util.Objects;
import java.util.Scanner;

public class Email {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private int defaultPasswordLength = 8;
    private String department;
    private String companySuffix = "anyCompany.com";
    private int mailboxCapacity = 1000;
    private String alternateEmail;


    private static Scanner input = new Scanner(System.in);

    // Constructors
    public Email(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.department = setDepartment();
        System.out.println("Email created for " + lastName + ", " + firstName + " - " + department);
        this.password = setPassword(defaultPasswordLength);
        this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + companySuffix;
        System.out.println("The new email is: " + this.email);
        System.out.println("The new password is: " + password);
    }

    // Setters and getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDefaultPasswordLength() {
        return defaultPasswordLength;
    }

    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public void setMailboxCapacity(int mailboxCapacity) {
        this.mailboxCapacity = mailboxCapacity;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    private static String setDepartment() {
        System.out.print("Enter the department.\n\t" +
                "1. Sales\n\t" +
                "2. Development\n\t" +
                "3. Accounting\n\t" +
                "0. For none\nMake your choice");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 0:
                return "";
            case 1:
                return "sales.";
            case 2:
                return "developer.";
            case 3:
                return "accounting.";
        }
        return null;
    }

    public String setPassword(int length){

        String passwordItems = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUWXYZ0123456789!@#$%^&*()_+?><";

        char[] password = new char[length];
        for (int i = 0; i < length ; i++) {
            int randomChar = (int)(Math.random() * passwordItems.length());
            password[i] = passwordItems.charAt(randomChar);
        }
        return new String(password);
    }

    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return password;
    }

    // Methods

    public static void menuScreen() {
        System.out.println("---------------------------------------------------\n"
                + "Welcome to EmailApp for @genericCompanyName.\n\n\n" +
                "Available actions:\n\t" +
                "0. Exit\n\t" +
                "1. New email account.\n\t" +
                "2. Get users email address.\n\t" +
                "3. Get users email password.\n\t" +
                "4. Password change by the User.\n\t" +
                "5. Issue new user password.\n\t" +
                "6. Get users mailbox capacity\n\t" +
                "7. Change users mailbox capacity.\n\t" +
                "8. Set alternative email address for user.\n\t" +
                "9. Print all email accounts.\n" +
                "Enter your action number: ");
    }

    // For new password set by the user.
    public void changePassword(String oldPassword, String newPassword) {
        if (Objects.equals(this.password, oldPassword)){
            this.password = newPassword;
            System.out.println("Password for user: " + firstName + " " + lastName + " is changed.");
        } else {
            System.out.println("The old password you provided is not wrong. Try again or contact your administrator.");
        }
    }

    private static Email findUser() {
        System.out.println("Enter the first name of the user:\r");
        String firstName = input.nextLine();
        System.out.println("Enter the last name of the user:\r");
        String lastName = input.nextLine();

        // fixing mistypes by users
        firstName = firstName.replaceAll("\\s+","");
        lastName = lastName.replaceAll("\\s+", "");

        for (Email checkedUser : EmailApp.usersList) {
            if (checkedUser.getFirstName().equals(firstName)
                    && checkedUser.getLastName().equals(lastName)) {
                return checkedUser;
            }
        }
        return null;
    }

    // Overloaded method of findUser()
    private static Email findUser(String firstName, String lastName) {

        for (Email checkedUser : EmailApp.usersList) {
            if (checkedUser.getFirstName().equals(firstName)
                    && checkedUser.getLastName().equals(lastName)) {
                return checkedUser;
            }
        }

        return null;
    }

    // Main process of the program.
    public static void mainMethod() {
        boolean quit = false;
        Email user;
        while (!quit) {

            menuScreen();

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 0: // Exit the program.
                    System.out.println("Exiting Application now.\nSee you again soon.");
                    quit = true;
                    break;

                case 1: // Create new customer
                    System.out.println("Enter the first name of the user:\r");
                    String firstName = input.nextLine();
                    System.out.println("Enter the last name of the user:\r");
                    String lastName = input.nextLine();

                    // fixing mistypes by users
                    firstName = firstName.replaceAll("\\s+", "");
                    lastName = lastName.replaceAll("\\s+", "");

                    if (findUser(firstName, lastName) == null) {
                        EmailApp.usersList.add(new Email(firstName, lastName));
                    } else {
                        System.out.println("The user already exists in the system.\n" +
                                "Creating new email account is canceled.");
                    }
                    break;

                case 2: // Get email
                    user = findUser();
                    if (user != null) {
                        System.out.println("The email of " + user.getFirstName() + " "
                                + user.getLastName() + " is:\n" + user.getEmail());
                    } else {
                        System.out.println("There is no user with this name.");
                    }
                    break;

                case 3: // Get users password
                    user = findUser();
                    if (user != null) {
                        System.out.println("The password of " + user.getFirstName() + " "
                                + user.getLastName() + " is:\n" + user.getPassword());
                    } else {
                        System.out.println("There is no user with this name.");
                    }
                    break;

                case 4: // Password change by the user.
                    user = findUser();
                    if (user != null) {
                        System.out.println("Please enter the old password:\n");
                        String oldPassword = input.nextLine();
                        System.out.println("Please enter the new password:\n");
                        String newPassword = input.nextLine();
                        user.changePassword(oldPassword, newPassword);
                    } else {
                        System.out.println("There is no user with this name.");
                    }
                    break;

                case 5: // Issue new password.
                    user = findUser();
                    if (user != null) {
                        user.setPassword(user.getDefaultPasswordLength());
                        System.out.println(user.getEmail() + " new password is: "
                                + user.setPassword(user.getDefaultPasswordLength()));
                    } else {
                        System.out.println("There is no user with this name.");
                    }
                    break;

                case 6: // Get mailbox capacity.
                    user = findUser();
                    if (user != null) {
                        System.out.println(" User with email: " + user.getEmail() +
                                "\nHas a  mailbox capacity of : " + user.getMailboxCapacity() + " Mb.");
                    } else {
                        System.out.println("There is no user with this name.");
                    }
                    break;

                case 7: // Change mailbox capacity.
                    user = findUser();
                    if (user != null) {
                        System.out.println("Enter new mailbox capacity for " + user.getEmail());
                        int newCapacity = input.nextInt();
                        input.nextLine();
                        user.setMailboxCapacity(newCapacity);
                    } else {
                        System.out.println("There is no user with this name.");
                    }
                    break;

                case 8: // Declare alternate email.
                    user = findUser();
                    if (user != null) {
                        System.out.println("Provide me the alternate email of the user: " + user.getEmail());
                        String alternateEmail = input.nextLine();
                        alternateEmail = alternateEmail.replaceAll("\\s+", "");
                        user.setAlternateEmail(alternateEmail);
                        System.out.println("New alternate email is saved.");
                    } else {
                        System.out.println("There is no user with this name.");
                    }
                    break;

                case 9:// print email accounts.

                    for (Email email : EmailApp.usersList) {
                        System.out.println("User name: " + email.getFirstName() + " "
                                + email.getLastName() + "\nEmail address: " + email.getEmail()
                                + "\n------------------------------------------------------------------");
                    }
                    break;
            }
        }
    }
}



