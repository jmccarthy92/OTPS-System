//package Main;
//
//import java.sql.SQLException;
//import java.util.Scanner;
//
///**
// * Basic Testing Client.
// * @author Randy
// */
//public class AccountClient 
//{
//    public static void main(String[] args) throws SQLException, ClassNotFoundException
//    {
//        Account_Collection AC = new Account_Collection();
//        Scanner scan = new Scanner(System.in);
//        int id = 0;
//
//        /*TEST FOR ACCOUNT INSERT.
//        int id = 0;
//
//        System.out.println("Enter in the username");
//        String username = "TEST32";//scan.nextLine();
//        
//        System.out.println("Enter in the password");
//        String password = "TEST3";//scan.nextLine();
//
//        System.out.println("Enter in the email");
//        String email = "TEST3";//scan.nextLine();
//
//        System.out.println("Enter in the phoneNumber");
//        String number = "TEST3";//scan.nextLine();
//
//        System.out.println("Enter in the address");
//        String address = "TEST3";//scan.nextLine();
//
//
//
//        User Default = new User();
//        User nonDefault = new User(username, password, id, email, number, address, false);
//
//        if(AC.addUser(nonDefault) == null)
//            System.out.println("Username Already Within System");
//        else
//        {
//            System.out.println("Account Added.");
//            System.out.println(nonDefault.toString());
//        }
//        if(AC.addUser(Default) == null)
//            System.out.println("Username Already Within System");
//        else
//        {
//            System.out.println("Account Added.");
//            System.out.println(Default.toString());
//        }// */
//
//        /*DELETE TEST:
//        //AC.deleteUser(1);
//        //AC.deleteUser(5);
//        if(AC.deleteUser(6) == null)
//            System.out.println("Account not Found or Account is Admin");
//        else
//            System.out.println("Account Deleted"); // */
//
//        /*
//          if(AC.searchUser(4) == null)
//            System.out.println("Account not Found");
//        */
//
//        /*if(AC.viewAccount() == null)
//            System.out.println("DB is Empty");*/
//        
//        /* LOGIN TEST:
//        System.out.println("Time To Login My Guy");
//        System.out.println("Enter Username");
//        String username = scan.nextLine();
//        System.out.println("Enter Password");
//        String password = scan.nextLine();
//        
//        User mainuser = null;
//        if(AC.loginUser(username, password) == null)
//        {
//            System.out.println("Invalid Username or Password.");
//        }
//        else
//        {
//            mainuser = new User(AC.loginUser(username, password));
//            System.out.println("Login Successful.");
//            //System.out.println(mainuser.toString());
//        }
//        if(mainuser == null){}
//        else
//        {
//            // EDIT BASIC INFO TEST
//            System.out.println(mainuser.toString());
//            System.out.println("What Account Info Would You Like to Alter?");
//            String UserChangeMenu = "1. Username\n2. Password \n3. Email \n4. Phone Number \n5. Address \n";
//            System.out.println(UserChangeMenu);
//            int choice = scan.nextInt();
//            scan.nextLine(); // <--- Eat Extra Input From Scanner.
//            String input = "";
//            
//            switch (choice)
//            {
//                case 1:
//                    System.out.println("Enter New Username");
//                    input = scan.nextLine();
//                    break;
//                case 2:
//                    System.out.println("Enter New Password");
//                    input = scan.nextLine();
//                    break;
//                case 3:
//                    System.out.println("Enter New Email");
//                    input = scan.nextLine();
//                    break;
//                case 4:
//                    System.out.println("Enter New Phone Number");
//                    input = scan.nextLine();
//                    break;
//                case 5:
//                    System.out.println("Enter New Address");
//                    input = scan.nextLine();
//                    break;
//                default:
//                    System.out.println("Invalid Choice.");
//                    break;
//            }
//            System.out.println(input);
//            if(AC.editBasicInfo(mainuser, choice, input) == false)
//                System.out.println("Invalid Info Entered or Username Already Taken");
//            System.out.println(mainuser.toString()); 
//        }//*/
//        
//        /*ADMIN LOGIN TEST.
//        System.out.println("Time To Login My Guy");
//        System.out.println("Enter Username");
//        String username = scan.nextLine();
//        System.out.println("Enter Password");
//        String password = scan.nextLine();
//        
//        Account mainuser = null;
//        if(AC.loginAdmin(username, password) == null)
//        {
//            System.out.println("Invalid Username or Password.");
//        }
//        else
//        {
//            mainuser = new Account(AC.loginAdmin(username, password));
//            System.out.println("Login Successful.");
//            System.out.println(mainuser.toString());
//        }
//        if(mainuser == null){} // */
//        
//        /*TEST FOR ADMIN
//        //int id = 0;
//
//        System.out.println("Enter in the username");
//        String Username = "TESTAd";//scan.nextLine();
//
//        System.out.println("Enter in the password");
//        String Password = "TESTAd";//scan.nextLine();
//
//        System.out.println("Enter in the email");
//        String Email = "TESTAd";//scan.nextLine();
//
//        Account Default2 = new Account();
//        Account nonDefault2 = new Account(Username, Password, id, Email, 911, true);
//        //System.out.println(nonDefault.toString());
//
//        if(AC.newAdmin(nonDefault2) == null)
//            System.out.println("Username Already Within System");
//        else
//        {
//            System.out.println("Account Added.");
//            System.out.println(nonDefault.toString());
//        }
//        if(AC.newAdmin(Default2) == null)
//            System.out.println("Username Already Within System");
//        else
//        {
//            System.out.println("Account Added.");
//            System.out.println(Default.toString());
//        } //*/
//
//        /*DELETE TEST:
//        //AC.deleteAdmin(1);
//        //AC.deleteUser(5);
//        if(AC.deleteAdmin(9) == null)
//            System.out.println("Account not Found or Account is a User");
//        else
//            System.out.println("Account Deleted"); // */
//        AC.viewAccounts();
//    }
//
//}
