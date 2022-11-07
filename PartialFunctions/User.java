import java.util.ArrayList;
import java.util.Scanner;

public class User {
    //user should have username and password to login 
    //also has a list of account associated with them
    private ArrayList<Account> acc;
    private String username;
    private String password;

    //default
    public User(){
        acc = new ArrayList<>();
        username = "";
        password = "";
    }

    //use during registration
    public User(String username, String password, ArrayList<Account> acc){
        this.username = username;
        this.password = password;
        this.acc = acc;
    }

    //use during login
    public User(String username, String password, Bank b){
        this.username = username;
        this.password = password;
        acc = (ArrayList<Account>) b.getMap().get(username);
    }

    //homepage
    public void service(){
        Scanner in = new Scanner(System.in);

        //service menu
        System.out.println("Choose your servcie: ");
        System.out.println("1. Transfer");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View Account");
        System.out.println("5. exit");

        int choice = in.nextInt();

        //allow multiple services 
        while(choice != 5){
            switch(choice){
                case 1:
                    //cant transfer money if less than 2 account
                    if(acc.size() < 2){
                        System.out.println("No enough accounts to perform this action");
                    }
                    else{
                        //menu
                        System.out.println("Choose your accout to withdraw from: ");
                        displayAcc();
                        int src = in.nextInt();
                        System.out.println("Choose your account to transfer to: ");
                        displayAcc();
                        int dest1 = in.nextInt();
                        System.out.println("Enter the amount to transfer:");
                        double amt1 = in.nextDouble();
        
                        //Create transaction
                        Transaction t1 = new Transaction();
                        if(t1.modify(acc.get(src - 1), amt1, acc.get(dest1 - 1), "transfer")){ //transaction could failed
                            System.out.println("Source account");
                            t1.display(acc.get(src - 1));
                            System.out.println("Destination account");
                            t1.display(acc.get(dest1 - 1));
                        }
                        else{
                            System.out.println("Transaction Failed"); 
                        }

                        
                    }
                    
                    break;
                case 2:
                    //menu
                    System.out.println("Choose your account to deposit:");
                    displayAcc();
                    int dest2 = in.nextInt();
                    System.out.println("Enter the amount to deposit:");
                    double amt2 = in.nextDouble();

                    //Create transaction
                    Transaction t2 = new Transaction();
                    if(t2.modify(acc.get(dest2 - 1), amt2, null, "deposit")){//transaction could failed
                        System.out.println("Destination account");
                        t2.display(acc.get(dest2 - 1));
                    }
                    else{
                        System.out.println("Transaction Failed");
                    }
                    
                    
                    break;

                case 3:
                    System.out.println("Choose your account to withdraw:");
                    displayAcc();
                    int dest3 = in.nextInt();
                    System.out.println("Enter the amount to withdraw:");
                    double amt3 = in.nextDouble();

                    //Create transaction
                    Transaction t3 = new Transaction();
                    if(t3.modify(acc.get(dest3 - 1), amt3, null, "withdraw")){//transaction could failed
                        System.out.println("Destination account");
                        t3.display(acc.get(dest3 - 1));
                    }
                    else{
                        System.out.println("Transaction Failed");
                    }

                    
                    break;
                case 4:
                    System.out.println("Choose an account to view:");
                    displayAcc();
                    int dest4 = in.nextInt();
                    System.out.println(acc.get(dest4 - 1).toString() + " has a balance of " + acc.get(dest4 - 1).getBalance());
                    break;
            }

            System.out.println("Choose your servcie: ");
            System.out.println("1. Transfer");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. exit");
            choice = in.nextInt();
        }
        

    }

    //display all account associated with user
    public void displayAcc(){
        for(int i = 0; i < acc.size(); i++){
            System.out.println((i + 1)+ ". " + acc.get(i).toString());
        }

        System.out.println("");
    }


}
