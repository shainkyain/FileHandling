package in.org.java;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Account implements Serializable{
	String name;
	String accNo;
	double balance;
	public Account() {}
	public Account(String name, String accNo , double balance) {
		this.name = name;
		this.accNo = accNo;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [name=" + name + ", accNo=" + accNo + ", balance=" + balance + "]";
	}
	
}



public class MenuDrivenAccountProgram {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in); 
		Account acc= null;
		HashMap<String , Account> hm = new HashMap<>();
		 
		try {
			FileInputStream fis = new FileInputStream("AccountFile.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
//			int count = ois.readInt();
			int count = 0; 
			count = ois.read();
			for(int i =  0 ; i<count ; i++) {
				acc = (Account) ois.readObject();
				
				System.out.println(acc);
				hm.put(acc.accNo, acc);
				
			}
			fis.close();
			ois.close();
		} catch (FileNotFoundException e) {
			 
			e.printStackTrace();
		}
		
		
		FileOutputStream fos = new FileOutputStream("AccountFile.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		int choice;
		String accno = null , name = null;
		double balance;
		
		
		System.out.println("menu");
		
		
		do {
			System.out.println("WelCome");
			System.out.println("Enter 1 : Create Account");
			System.out.println("Enter 2 : Delete Account");
			System.out.println("Enter 3 : View Account");
			System.out.println("Enter 4 : view All Accounts");
			System.out.println("Enter 5 : Exit & Save Account");
			System.out.println("Enter 6 : Exit");
			
			choice = sc.nextInt();
			
			  sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			switch(choice) {
			case 1 : System.out.println("enter your name , accno,  ,balance");
			name =  sc.nextLine();
			  sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			accno = sc.nextLine();
			  sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		
			balance = sc.nextDouble();
			acc = new Account(name, accno, balance);
			hm.put(accno, acc);
			 sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			System.out.println("Account Created For  " +name );
			break;
			
			case 2 : System.out.println("enter ACC  number");
			String temp = sc.nextLine();
			if(hm.containsKey(temp)) {
				hm.remove(temp);
			}
			break;
			
			case 3 :
				System.out.println("view Accout");
				temp  = sc.nextLine();
				if(hm.containsKey(temp)) {
					System.out.println(hm.get(temp));
				}
				
				break;
			case 4 :
				
				System.out.println("ALL ACCOUNT DETAILS");
			for(Account a : hm.values()) {
				System.out.println(a);
			}
			break;
			
			case 5 :
			case 6 :
				oos.write(hm.size());
				for(Account  a  : hm.values()) {
					oos.writeObject(a);
				}
				
		
				
				break;
				
				
				
		}
				
			
		} while (choice != 5  );
		oos.flush();
		sc.close();
		oos.close();
		fos.close();
	}

}
