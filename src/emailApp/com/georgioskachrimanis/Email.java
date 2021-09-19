package emailApp.com.georgioskachrimanis;

import java.util.*;

public class Email {

    private static final Scanner input = new Scanner(System.in);
    public static List<Email> usersList = new ArrayList<>();

    private final String firstName;
    private final String lastName;
    private String password;
    private final String email;
    private final int defaultPasswordLength = 8;
    private final String department;
    private int mailboxCapacity = 1000;
    private String alternateEmail;


    // Constructors
    public Email(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.department = setDepartment();
        System.out.println("Email created for " + lastName + ", " + firstName + " - " + department);
        this.password = setPassword(defaultPasswordLength);
        String companySuffix = "anyCompany.com";
        this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + companySuffix;
        System.out.println("The new email is: " + this.email);
        System.out.println("The new password is: " + password);
    }

    // setters and getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
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

    private void setAlternateEmail(String alternateEmail) {
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
            case 1:
                return "sales.";
            case 2:
                return "developer.";
            case 3:
                return "accounting.";
        }
        return "";
    }

    private String setPassword(int length) {

        String passwordItems = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUWXYZ0123456789!@#$%^&*()_+?><";

        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            int randomChar = (int) (Math.random() * passwordItems.length());
            password[i] = passwordItems.charAt(randomChar);
        }
        return new String(password);
    }


    public String getPassword() {
        return password;
    }


    // ----------------------------- Methods ------------------------------------------------
    public static void optionsMenu() {
        System.out.println("---------------------------------------------------\n"
                + "Welcome to EmailApp for @genericCompanyName.\n\n\n" +
                "Available actions:\n\t" +
                "0. Exit\n\t" +
                "1. New email account.\n\t" +
                "2. Get users email address.\n\t" +
                "3. Password change by the User.\n\t" +
                "4. Issue new user password.\n\t" +
                "5. Get users mailbox capacity\n\t" +
                "6. Change users mailbox capacity.\n\t" +
                "7. Set alternative email address for user.\n\t" +
                "8. Print all email accounts.\n" +
                "Enter your choise: ");
    }


    public static void createNewUser() {
        String firstName = userInput("first name");
        String lastName = userInput("last name");

        if (findUser(firstName, lastName) == null) {
            usersList.add(new Email(firstName, lastName));
        } else {
            System.out.println("The user already exists in the system.\n" +
                    "Creating new email account is canceled.");
        }
    }


    public static void printEmail() {
        Email user = findUser();
        if (user != null) {
            System.out.println("The email of " + user.getFirstName() + " "
                    + user.getLastName() + " is:\n" + user.getEmail());
        } else {
            System.out.println("There is no user with this name.");
        }
    }


    // Ability of user to change his/her/them password.
    public static void passwordChangeUser() {
        Email user = findUser();
        String oldPassword;
        String newPassword;
        if (user != null) {
            System.out.println("Please enter the old password:\n");
            oldPassword = input.nextLine();
            System.out.println("Please enter the new password:\n");
            newPassword = input.nextLine();
            if (Objects.equals(user.getPassword(), oldPassword)) {
                user.password = newPassword;
                System.out.println("Password for user: " + user.getFirstName() + " "
                        + user.getLastName() + " is changed.");
            } else {
                System.out.println("The old password you provided is wrong. Try again or contact your administrator.");
            }
        }
    }


    // New password for the user.
    public static void createNewPassword() {
        Email user;
        user = findUser();
        if (user != null) {
            user.setPassword(user.getDefaultPasswordLength());
            System.out.println(user.getEmail() + " new password is: "
                    + user.getPassword());
        } else {
            System.out.println("There is no user with this name.");
        }
    }

    //Print mailbox capacity.
    public static void mailboxCapacity() {
       Email user = findUser();
        if (user != null) {
            System.out.println(" User with email: " + user.getEmail() +
                    "\nHas a  mailbox capacity of : " + user.getMailboxCapacity() + " Mb.");
        } else {
            System.out.println("There is no user with this name.");
        }
    }



    public static void changeMailboxCapacity() {
        Email user;
        user = findUser();
        if (user != null) {
            System.out.println("Enter new mailbox capacity for " + user.getEmail());
            int newCapacity = input.nextInt();
            input.nextLine();
            user.setMailboxCapacity(newCapacity);
        } else {
            System.out.println("There is no user with this name.");
        }
    }


    public static void alternateMailbox() {
        Email user = findUser();
        if (user != null) {
            System.out.println("Provide me the alternate email of the user: " + user.getEmail());
            String alternateEmail = input.nextLine();
            alternateEmail = alternateEmail.replaceAll("\\s+", "");
            user.setAlternateEmail(alternateEmail);
            System.out.println("New alternate email is saved.");
        } else {
            System.out.println("There is no user with this name.");
        }
    }


    public static void printEmailAccounts() {
        for (Email email : usersList) {
            System.out.println("\n------------------------------------------------------------------"+
                    "User name: " + email.getFirstName() + " "
                    + email.getLastName() + "\nEmail address: " + email.getEmail()
                    + "\n------------------------------------------------------------------");
        }
    }


    private static String userInput(String firstOrLastName) {
        System.out.println("Enter the " + firstOrLastName + " of the user:\r");
        String name = input.nextLine();
        // The following line is to eliminate white space and
        // also to prevent errors from capitalization
        name = name.replaceAll("\\s+", "").toLowerCase();
        return name;
    }


    private static Email findUser() {
        String firstName = userInput("first name");
        String lastName = userInput("last name");
        for (Email checkedUser : usersList) {
            if (checkedUser.getFirstName().equals(firstName)
                    && checkedUser.getLastName().equals(lastName)) {
                return checkedUser;
            }
        }
        return null;
    }


    // Overloaded method of findUser()
    private static Email findUser(String firstName, String lastName) {

        for (Email checkedUser : usersList) {
            if (checkedUser.getFirstName().equals(firstName)
                    && checkedUser.getLastName().equals(lastName)) {
                return checkedUser;
            }
        }
        return null;
    }

}



