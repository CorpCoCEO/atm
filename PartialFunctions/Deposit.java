public class Deposit extends Transaction{
    public Deposit(){

    }

    //deposit function
    public static boolean deposit(Account acc, double amt){
        if(amt >= 0){
            acc.setBalance(amt);
            return true;
        }
        else{
            return false;
        }
        
    }
}