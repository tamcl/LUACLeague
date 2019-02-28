import java.sql.*;
import java.util.*;
import java.lang.*;
import java.io.File;

public class LUACL {
	boolean	debug	= false;
	private static final String JDBC_DRIVER		= "org.sqlite.JDBC";
	private static final String DATABASE_LOCATION	= "jdbc:sqlite:";
	public Scanner scan = new Scanner(System.in);
	protected Connection	con	= null;
	public    String	dbName	= null;
	public void notify( String message, Exception e ) {
		System.out.println( message + " : " + e );
		e.printStackTrace ( );
		System.exit( 0 );
	}
	private void getConnection( ) {
		try {
			con = DriverManager.getConnection(
					  DATABASE_LOCATION
					+ dbName);

			/*
			 * Turn off AutoCommit:
			 * delay updates until commit( ) called
			 */
			con.setAutoCommit(false);
		}
		catch ( SQLException sqle ) {
			notify( "Db.getConnection database location ["
					+ DATABASE_LOCATION
					+ "] db name["
					+ dbName
					+ "]", sqle);
			close( );
		}
	}
	private void open( ) {
		File dbf = new File( dbName );

		if ( dbf.exists( ) == false ) {
			System.out.println(
				 "SQLite database file ["
				+ dbName
				+ "] does not exist");
			System.exit( 0 );
		}
	
		try {
			Class.forName( JDBC_DRIVER );
			getConnection( );
		}
		catch ( ClassNotFoundException cnfe ) {
			notify( "Db.Open", cnfe );
		}

		if ( debug )
			System.out.println( "Db.Open : leaving" );
	}
	public final void close( ) {
		try {
			con.commit( ); // Commit any updates
			con.close ( );
		}
		catch ( Exception e ) {
			notify( "Db.close", e );
		};
	}
	public LUACL( String _dbName ) {
		dbName = _dbName;
		if ( debug )
			System.out.println(
				  "Db.constructor ["
				+ dbName
				+ "]");
		open( );
		//from here
		boolean CM = false;
		while(true){
			if(division()){
				CM = confirmCommit();
				break;
			}
		}	
		if(CM == true){
			System.out.println("Commited");
			close();
		}
	}
	
	public boolean division(){
		int type = Choice();
		if(type == 4){
			return true;
		}else{
			
			return false;
		}
	}
	
	public boolean confirmCommit(){
		int answer = 0;
		System.out.println("You sure you want to commit? 1=yes 0=No");
		answer = scan.nextInt();
		if(answer == 1){
			return true;
		}else{
			return false;
		}
	}
	
	
	public int Choice(){
		int choice = 0;
		System.out.println("Please enter the corresponding number on the left");
		System.out.println("1\tAdd Score");
		System.out.println("2\tAlter Score");
		System.out.println("3\tDelete Score");
		System.out.println("4\tExit");
		while(true){
			choice = scan.nextInt();
			if(choice>4||choice<1){
				System.out.println("try again");
			}else{
				break;
			}
		}
		return choice;
	}
}
