package numbers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        welcome();
        printInstructions();
        process();
        goodBye();
    }

    public static void process() {
        Scanner scanner = new Scanner(System.in);
        Long[] parameters = new Long[2];
        long num = 1;
        int iter = 0;
        do {
            System.out.print("\nEnter a request: ");
            String request = scanner.nextLine();

            if (request.length() == 0) {
                printInstructions();
                continue;
            }

            String[] textParameters = request.split(" ");
            if (textParameters.length == 1) {
                if (!processParameters(parameters, textParameters)) { continue; }

                num = parameters[0];
                OwnNumber number = new OwnNumber(num);

                if (number.isNatural()) {
                    processSingleNumber(number);
                } else {
                    if (number.getNumber() != 0) { printFirstParameterError(); }
                }
            } else if (textParameters.length == 2) {
                if (!processParameters(parameters, textParameters)) { continue; }

                num = parameters[0];
                iter = (int) parameters[1].longValue();

                if (iter > 0) {
                    System.out.println();
                    int j = 0;
                    while (j < iter) {
                        processOneItem(new OwnNumber(num + j));
                        j++;
                    }
                } else {
                    printSecondParameterError();
                }
            } else if (textParameters.length >= 3) {
                if (processParameters(parameters, textParameters)) {
                    num = parameters[0];
                    iter = (int) parameters[1].longValue();
                    List<String> properties = new ArrayList<>();
                    for (int i = 2; i < textParameters.length; i++) {
                        properties.add(textParameters[i].toLowerCase());
                    }
                    processItemWithProperties(num, iter, properties);
                }
            }

        } while (num != 0);
    }


    public static boolean processParameters(Long[] parameters, String[] textParameters) {
        int numOfPar = textParameters.length;
        try {
            parameters[0] = Long.parseLong(textParameters[0]);
        } catch (NumberFormatException e) {
            printFirstParameterError();
            return false;
        }

        if (numOfPar > 1) {
            try {
                parameters[1] = Long.parseLong(textParameters[1]);
            } catch (NumberFormatException e) {
                printSecondParameterError();
                return false;
            }
        }

        boolean isValid;
        if (numOfPar >= 3) {
            boolean isEvenValid = true;
            List<String> properties = new ArrayList<>();

            for (int j = 2; j < numOfPar; j++) {
                isValid = false;
                for (OwnNumber.Prop property : OwnNumber.Prop.values()) {
                    if ('-' == textParameters[j].charAt(0)) {
                        isValid |= property.name().equals(textParameters[j].toLowerCase().substring(1));
                    } else {
                        isValid |= property.name().equals(textParameters[j].toLowerCase());
                    }
                }
                if (!isValid) {
                    properties.add(textParameters[j].toLowerCase());
                }
                isEvenValid &= isValid;
            }

            if (!isEvenValid) {
                printPropertiesError(properties);
                return false;
            }

            properties.addAll(Arrays.asList(textParameters).subList(2, numOfPar));
            List<String> wrongProperties = new ArrayList<>();
            if (OwnNumber.isMutuallyExclusiveProperties(properties, wrongProperties)) {
                printMutuallyError(wrongProperties);
                return false;
            }
        }

        return true;
    }

    public static void processSingleNumber(OwnNumber number) {
        System.out.printf("\nProperties of %d\n", number.getNumber());
        System.out.printf("%s: %b\n", OwnNumber.Prop.even.name(), number.isEven());
        System.out.printf("%s: %b\n", OwnNumber.Prop.odd.name(), number.isOdd());
        System.out.printf("%s: %b\n", OwnNumber.Prop.buzz.name(), number.isBuzz());
        System.out.printf("%s: %b\n", OwnNumber.Prop.duck.name(), number.isDuck());
        System.out.printf("%s: %b\n", OwnNumber.Prop.palindromic.name(), number.isPalindromic());
        System.out.printf("%s: %b\n", OwnNumber.Prop.gapful.name(), number.isGapful());
        System.out.printf("%s: %b\n", OwnNumber.Prop.spy.name(), number.isSpy());
        System.out.printf("%s: %b\n", OwnNumber.Prop.square.name(), number.isSquare());
        System.out.printf("%s: %b\n", OwnNumber.Prop.sunny.name(), number.isSunny());
        System.out.printf("%s: %b\n", OwnNumber.Prop.jumping.name(), number.isJumping());
        System.out.printf("%s: %b\n", OwnNumber.Prop.happy.name(), number.isHappy());
        System.out.printf("%s: %b\n", OwnNumber.Prop.sad.name(), number.isSad());
    }

    public static void processOneItem(OwnNumber number) {
        List<String> properties = new ArrayList<>();

        if (number.isEven()) { properties.add(OwnNumber.Prop.even.name()); }
        if (number.isOdd()) { properties.add(OwnNumber.Prop.odd.name()); }
        if (number.isBuzz()) { properties.add(OwnNumber.Prop.buzz.name()); }
        if (number.isDuck()) { properties.add(OwnNumber.Prop.duck.name()); }
        if (number.isPalindromic()) { properties.add(OwnNumber.Prop.palindromic.name()); }
        if (number.isGapful()) { properties.add(OwnNumber.Prop.gapful.name()); }
        if (number.isSpy()) { properties.add(OwnNumber.Prop.spy.name()); }
        if (number.isSquare()) { properties.add(OwnNumber.Prop.square.name()); }
        if (number.isSunny()) { properties.add(OwnNumber.Prop.sunny.name()); }
        if (number.isJumping()) { properties.add(OwnNumber.Prop.jumping.name()); }
        if (number.isHappy()) { properties.add(OwnNumber.Prop.happy.name()); }
        if (number.isSad()) { properties.add(OwnNumber.Prop.sad.name()); }

        StringBuilder allProperties = new StringBuilder(properties.get(0));

        for (int i = 1; i < properties.size(); i++) {
            allProperties.append(", ").append(properties.get(i));
        }

        System.out.println(number.getNumber() + " is " + allProperties);
    }

    public static void processItemWithProperties(long number, int count, List<String> properties) {
        int i = 0;
        int n = 0;

        System.out.println();

        boolean isExist;
        while (i < count) {
            OwnNumber num = new OwnNumber(number + n);
            isExist = checkChosenProperty(num, properties.get(0));
            int j = 1;
            while (j < properties.size()) {
                isExist &= checkChosenProperty(num, properties.get(j));
                j++;
            }

            if (isExist) {
                processOneItem(num);
                i++;
            }
            n++;
        }
    }

    public static boolean checkChosenProperty(OwnNumber num, String property) {
        boolean isExist = false;
        boolean isInverse = false;
        String tempPro = property;
        if (property.charAt(0) == '-') {
            tempPro = property.substring(1);
            isInverse = true;
        }
        switch (tempPro) {
            case "even":
                isExist = num.isEven();
                break;
            case "odd":
                isExist = num.isOdd();
                break;
            case "buzz":
                isExist = num.isBuzz();
                break;
            case "duck":
                isExist = num.isDuck();
                break;
            case "palindromic":
                isExist = num.isPalindromic();
                break;
            case "gapful":
                isExist = num.isGapful();
                break;
            case "spy":
                isExist = num.isSpy();
                break;
            case "square":
                isExist = num.isSquare();
                break;
            case "sunny":
                isExist = num.isSunny();
                break;
            case "jumping":
                isExist = num.isJumping();
                break;
            case "happy":
                isExist = num.isHappy();
                break;
            case "sad":
                isExist = num.isSad();
                break;
            default:
                break;
        }
        return isInverse ? !isExist : isExist;
    }

    public static void printFirstParameterError() {
        System.out.println("\nThe first parameter should be a natural number or zero.");
    }

    public static void printSecondParameterError() {
        System.out.println("\nThe second parameter should be a natural number.");
    }

    public static void printPropertiesError(List<String> properties) {
        StringBuilder temp = new StringBuilder();
        temp.append(properties.get(0));
        int i = 1;
        while (i < properties.size()) {
            temp.append(", ").append(properties.get(i));
            i++;
        }

        String msg1 = properties.size() == 1 ? "y" : "ies";
        String msg2 = properties.size() == 1 ? "is" : "are";
        System.out.printf("\nThe propert%s [%s] %s wrong.\n", msg1, temp.toString().toUpperCase(), msg2);
        System.out.printf("Available properties: [%s]\n", OwnNumber.getProperties().toUpperCase());
    }

    public static void printMutuallyError(List<String> properties) {
        StringBuilder temp = new StringBuilder();
        temp.append(properties.get(0));
        int i = 1;
        while (i < properties.size()) {
            temp.append(", ").append(properties.get(i));
            i++;
        }

        System.out.printf("\nThe request contains mutually exclusive properties: [%s]\n", temp.toString().toUpperCase());
        System.out.println("There are no numbers with these properties.");
    }

    public static void welcome() {
        System.out.println("Welcome to Amazing Numbers!");
    }

    public static void printInstructions() {
        System.out.println("\nSupported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    public static void goodBye() {
        System.out.println("\nGoodbye!");
    }
}