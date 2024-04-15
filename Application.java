import java.io.IOException;
import java.util.Scanner;

/**
 * The driver class of this Java application
 */
public class Application {
    /**
     * Start of the execution of the Java program; parses and handles user input, which includes the equality, range, and inequality query commands
     * @param args the list of command line arguments passed to main
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {
        RecordRetriever rV = new RecordRetriever();
        System.out.println("Program is ready and waiting for user command.");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            //Include exit command to escape the program.
            if (userInput.equalsIgnoreCase("EXIT")) {
                System.out.println("Program Exited.");
                break;
            }
            if (userInput.equals("CREATE INDEX ON Project2Dataset (RandomV)") && !(rV.isIndexesCreated())) {
                rV.initializeHashIndex();
                rV.initializeArrayIndex();
                rV.setIndexesCreated(true);
                System.out.println("The hash-based and array-based indexes are built successfully. Program is ready and waiting for user command.");
            }
            if (userInput.equals("CREATE INDEX ON Project2Dataset (RandomV)") && rV.isIndexesCreated()) {
                System.out.println("Indexes have already been created on Project2Dataset (RandomV).");
                System.out.println("Program is ready and waiting for user command.");
            }
            if (userInput.substring(36,45).equals("RandomV =")) {
                //Case 1, Equality: SELECT * FROM Project2Dataset WHERE RandomV = v
                rV.handleEqualityQueryLookup(Integer.parseInt(userInput.substring(46,userInput.length())));
                System.out.println("Program is ready and waiting for user command.");
            }
            if (userInput.substring(36,45).equals("RandomV >")) {
                //Case 2, Range: SELECT * FROM Project2Dataset WHERE RandomV > v1 AND RandomV < v2
                rV.handleRangeQueryLookup(Integer.parseInt(userInput.substring(46,(userInput.indexOf("A") - 1))),
                        Integer.parseInt(userInput.substring(63, userInput.length())));
                System.out.println("Program is ready and waiting for user command.");
            }
            if (userInput.substring(36,46).equals("RandomV !=")) {
                //Case 3, Inequality: SELECT * FROM Project2Dataset WHERE RandomV != v
                rV.handleInequalityQueryLookup(Integer.parseInt(userInput.substring(47, userInput.length())));
                System.out.println("Program is ready and waiting for user command.");
            }
        }
    }
}
