/* Email App
 This is based on an idea from Master Skills Youtube channel
(https://www.youtube.com/channel/UCJDMU2Z2A70Yu3pVe_c-HYw/featured Master)
Of course I had changed quite a bit.
I would love to hear your comment/criticism on GitHub.
* */
package emailApp.com.georgioskachrimanis;

import java.util.ArrayList;
import java.util.List;

public class EmailApp {

    public static List<Email> usersList;

    public static void main(String[] args) {
        usersList= new ArrayList<>();

        Email.mainMethod();
   }
}
