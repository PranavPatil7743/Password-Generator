import java.util.Scanner;

public class Generator {
    Alphabet alphabet;
    public static Scanner sc;

    public Generator(Scanner scanner) {
        sc = scanner;
    }

    public Generator(boolean IncludeUpper, boolean IncludeLower, boolean IncludeNum, boolean IncludeSym) {
        alphabet = new Alphabet(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    }

    public void passwordGen() {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║       Welcome to MYSTERIOUS Password      ║");
        System.out.println("║                Services                   ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        printMenu();

        String userOption = "-1";

        while (!userOption.equals("4")) {
            userOption = sc.next();

            switch (userOption) {
                case "1":
                    requestPassword();
                    printMenu();
                    break;
                case "2":
                    checkPassword();
                    printMenu();
                    break;
                case "3":
                    printUsefulInfo();
                    printMenu();
                    break;
                case "4":
                    printQuitMessage();
                    break;
                default:
                    System.out.println();
                    System.out.println("we don't have that option try again! WILL BE IMPROVING SOON!");
                    printMenu();
            }
        }
    }

    private Password generatePassword(int length) {
        final StringBuilder pass = new StringBuilder();

        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(alphabet.getAlphabet().charAt(index));
        }

        return new Password(pass.toString());
    }

    private void printUsefulInfo() {
        String[] info = {
                "Use a minimum password length of 8 or more characters if permitted",
                "Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted",
                "Generate passwords randomly where feasible",
                "Avoid using the same password twice (e.g., across multiple user accounts and/or software systems)",
                "Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences," +
                        "\nusernames, relative or pet names, romantic links (current or past) " +
                        "and biographical information (e.g., ID numbers, ancestors' names or dates).",
                "Avoid using information that the user's colleagues and/or " +
                        "acquaintances might know to be associated with the user",
                "Do not use passwords which consist wholly of any simple combination of the aforementioned weak components"
        };
        System.out.println();
        for (String line : info) {
            System.out.println(line);
        }
    }

    private void requestPassword() {
        boolean IncludeUpper = false;
        boolean IncludeLower = false;
        boolean IncludeNum = false;
        boolean IncludeSym = false;

        boolean correctParams;

        System.out.println();
        System.out.println("Hello, welcome to the Password Generator :) answer"
                + " the following questions by Yes or No \n");

        do {
            String input;
            correctParams = false;

            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = sc.next();
                passwordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeLower = true;

            do {
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = sc.next();
                passwordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeUpper = true;

            do {
                System.out.println("Do you want Numbers \"1234...\" to be used? ");
                input = sc.next();
                passwordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeNum = true;

            do {
                System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
                input = sc.next();
                passwordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeSym = true;

            //No Pool Selected
            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("You have selected no characters to generate your " +
                        "password, at least one of your answers should be Yes\n");
                correctParams = true;
            }

        } while (correctParams);

        System.out.println("Great! Now enter the length of the password");
        int length = sc.nextInt();

        final Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
        final Password password = generator.generatePassword(length);

        System.err.println("Your generated password -> " + password);
    }

    private boolean isInclude(String Input) {
        if (Input.equalsIgnoreCase("yes")) {
            return true;
        }
        else {
            return false;
        }
    }

    private void passwordRequestError(String i) {
        if (!i.equalsIgnoreCase("yes") && !i.equalsIgnoreCase("no")) {
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }

    private void checkPassword() {
        String input;

        System.out.print("\nEnter your password:");
        input = sc.next();

        final Password p = new Password(input);

        System.out.println(p.calculateScore());
    }

    private void printMenu() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║ Choose your thing dude                    ║");//💪
        System.out.println("║                                           ║");
        System.out.println("║  Enter 1 - Password Generator             ║");
        System.out.println("║  Enter 2 - Password Strength Check        ║");
        System.out.println("║  Enter 3 - Useful Information             ║");
        System.out.println("║  Enter 4 - Quit                           ║");
        System.out.println("║                                           ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.println("\n╭─────────────────────────────────────────────╮");
        System.out.print("Choice: -> ");
    }


    private void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    }
}