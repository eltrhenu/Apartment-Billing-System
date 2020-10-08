package G14_CENG211_HW1;

import java.text.ParseException;

public class ApartmentBillingApp {
    public static void main (String[] args) throws ParseException {

        while (true) {
            ConsoleInputOutput.MenuStart();
            if (!ConsoleInputOutput.getAndExecuteMainMenu())
                break;
        }
    }



}