import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Email Client Application");
        System.out.println("What you would like to do?");
        System.out.println("1.Read your inbox");
        System.out.println("2.Write an email");

        int option = userInput.nextInt();


        switch (option){
            case 1:
                try {
                    Read.ReadEmail();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;

            case 2:
                Write.WriteEmail();
                break;

            default:
                System.out.println("Enter a valid option");
                break;
        }
    }
}