/* Email App
 This is based on an idea from Master Skills Youtube channel
(https://www.youtube.com/channel/UCJDMU2Z2A70Yu3pVe_c-HYw/featured Master)
Of course I had changed quite a bit.
I would love to hear your comment/criticism on GitHub.
* */
package emailApp.com.georgioskachrimanis;

import java.util.Scanner;

public class EmailApp {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;

        while (!quit) {

            Email.optionsMenu();

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 0: // Exit the program.
                    System.out.println("Exiting Application now.\nSee you again soon.");
                    quit = true;
                    break;

                case 1: // Create new email account
                    Email.createNewUser();
                    break;

                case 2: // Show Users email
                    Email.printEmail();
                    break;

                case 3: // Password change by the user.
                    Email.userChangePassword();
                    break;

                case 4: // Issue new password.
                    Email.issueNewPassword();
                    break;

                case 5: // Get mailbox capacity.
                   Email.mailboxCapacity();
                    break;

                case 6: // Change mailbox capacity.
                    Email.changeMailboxCapacity();
                    break;

                case 7: // Declare alternate email.
                    Email.alternateMailbox();
                    break;

                case 8:// print email accounts.
                    Email.printEmailAccounts();
                    break;
            }
        }
   }
}
