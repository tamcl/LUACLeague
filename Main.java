import java.util.*;
import java.lang.*;


public class Main{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter database name:");
		String databaseName = scan.next();
		LUACL reader = new LUACL(databaseName);
	}

}
