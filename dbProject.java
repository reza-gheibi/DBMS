//Importing all the required packages
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection; 
import java.sql.Statement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.DriverManager;

public class sample {
	
	public static void main(String[] args) throws SQLException { 
		
		// Connecting to the database 
		final String hostName = "username-sql-server.database.windows.net"; 
		final String dbName = "databasename-sql-db"; 
		final String user = username
		final String password = "password"; 
		final String url =String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;host "
							+ "NameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
	
		try (final Connection connection = DriverManager.getConnection(url)) {
		// Declaring a scanner object for user input
		Scanner myScan = new Scanner(System.in);
			
		int choice = 0;
		// Loop for repeating the menu and executing queries based on user input
		// Terminates when user inputs 18 
		while(choice != 18) 
		{
			// Displaying the menu to the user
			System.out.println("                          ==================================================");
			System.out.println("                          WELCOME TO THE JOB-SHOP ACCOUNTING DATABASE SYSTEM");			
			System.out.println("                          ==================================================");
			System.out.println("                               Choose an option from the following:\n");
			System.out.println("1. Enter a new customer"); 
			System.out.println("2. Enter a new department");
			System.out.println("3. Enter a new assembly with its customer-name, assembly-details, assembly-id, and date ordered");
			System.out.println("4. Enter a new process-id and its department together with its type and information relevant to the type");
			System.out.println("5. Create a new account and associate it with the process, assembly, or department to which it is applicable");
			System.out.println("6. Enter a new job, given its job-no, assembly-id, process-id, and date the job commenced");
			System.out.println("7. At the completion of a job, enter the date it completed and the information relevant to the type of job");
			System.out.println("8. Enter a transaction-no and its sup-cost and update all the costs of the affected accounts");
			System.out.println("9. Retrieve the cost incurred on an assembly-id");
			System.out.println("10. Retrieve the total labor time within a department for jobs completed in the department during a given date");
			System.out.println("11. Retrieve the processes through which a given assembly-id has passed so far and the department responsible for each process");
			System.out.println("12. Retrieve the jobs completed during a given date in a given department");
			System.out.println("13. Retrieve the customers whose category is in a given range");
			System.out.println("14. Delete all cut-jobs whose job-no is in a given range");
			System.out.println("15. Change the color of a given paint job");
			System.out.println("16. Import: enter new customers from a data file");
			System.out.println("17. Export: Retrieve the customers (in name order) whose category is in a given range and output them to a data file");
			System.out.println("18. Quit\n"); 
			
			
			// Reading user's choice 
			choice = myScan.nextInt();
			
			// Based on the value of choice, performing the required query/action 
			if (choice == 1) {
				//Declarations
				int cat;
				String name, add;
				//Taking the user inputs
				System.out.println("Enter customer's name:"); 
				name = myScan.next();
				System.out.println("Enter customer's address:"); 
				add = myScan.next();
				System.out.println("Enter customer's category (1-10):"); 
				cat = myScan.nextInt();
				
				//Executing stored procedure proc1
				final String sql1 = "EXEC proc1 @name = '"+name+"' , @add = '"+add+"' , @cat = '"+cat+"';";
				final Statement statement = connection.createStatement(); 
				statement.executeUpdate(sql1);
				System.out.println("Customer record inserted successfully.\n");
			}
			
			if (choice == 2) {
				//Declarations
				int dno;
				String data;
				//Taking the user inputs
				System.out.println("Enter department number:"); 
				dno = myScan.nextInt();
				System.out.println("Enter department data:"); 
				data = myScan.next();
				
				//Executing stored procedure proc2
				final String sql1 = "EXEC proc2 @dno = '"+dno+"' , @data = '"+data+"';";
				final Statement statement = connection.createStatement(); 
				statement.executeUpdate(sql1);
				System.out.println("Department record inserted successfully.\n");
			}
			
			if (choice == 3) {
				//Declarations
				int aid;
				String date, det, cname;
				//Taking the user inputs
				System.out.println("Enter customer name:"); 
				cname = myScan.next();
				System.out.println("Enter assembly details:"); 
				det = myScan.next();
				System.out.println("Enter assembly id:"); 
				aid = myScan.nextInt();
				System.out.println("Enter date ordered:"); 
				date = myScan.next();
								
				//Executing stored procedure proc3
				final String sql1 = "EXEC proc3 @aid = '"+aid+"' , @date = '"+date+"' , @det = '"+det+"', @cname = '"+cname+"';";
				final Statement statement = connection.createStatement(); 
				statement.executeUpdate(sql1);
				System.out.println("Assembly record inserted successfully.\n");
			}
			
			if (choice == 4) {
				//Declarations
				int pid, dno, type;
				String pdata, ctype, mtype, ftype, ptype, pmethod;
				//Taking the user inputs
				System.out.println("Enter process id:"); 
				pid = myScan.nextInt();
				System.out.println("Enter process data:"); 
				pdata = myScan.next();
				System.out.println("Enter department number for the process:"); 
				dno = myScan.nextInt();
								
				//Executing stored procedure proc4
				final String sql1 = "EXEC proc4 @pid = '"+pid+"' , @pdata = '"+pdata+"' , @dno = "+dno+";";
				final Statement statement = connection.createStatement(); 
				statement.executeUpdate(sql1);
				
				//Taking the user input for type
				System.out.println("What is the type of the process?");
				System.out.println("Enter: 1 for Cut, 2 for Fit, 3 for Paint");
				type = myScan.nextInt();
				
				// Executing appropriate procedure based on type chosen after taking inputs specific to the type   
				if (type == 1) {
					System.out.println("Enter cut type:"); 
					ctype = myScan.next();
					System.out.println("Enter machine type:"); 
					mtype = myScan.next();
					
					//Executing stored procedure proc4_1
					final String sql = "EXEC proc4_1 @pid = '"+pid+"' , @ctype = '"+ctype+"' , @mtype = '"+mtype+"';";
					final Statement stat = connection.createStatement(); 
					stat.executeUpdate(sql);
				}
				
				if (type == 2) {
					System.out.println("Enter fit type:"); 
					ftype = myScan.next();
					
					//Executing stored procedure proc4_3
					final String sql = "EXEC proc4_3 @pid = '"+pid+"' , @ftype = '"+ftype+"';";
					final Statement stat = connection.createStatement(); 
					stat.executeUpdate(sql);
				}
				
				if (type == 3) {
					System.out.println("Enter paint type:"); 
					ptype = myScan.next();
					System.out.println("Enter paint method:"); 
					pmethod = myScan.next();
					
					//Executing stored procedure proc4_2
					final String sql = "EXEC proc4_2 @pid = '"+pid+"' , @ptype = '"+ptype+"' , @pmethod = '"+pmethod+"';";
					final Statement statm = connection.createStatement(); 
					statm.executeUpdate(sql);
				}
				
				System.out.println("Process record inserted successfully.\n");
			}
			
			if (choice == 5) {
				//Declarations
				int acc, type, id;
				String date;
				//Taking the user inputs
				System.out.println("Enter account number:"); 
				acc = myScan.nextInt();
				System.out.println("Enter date established:"); 
				date = myScan.next();
				System.out.println("What is this account for?");
				System.out.println("Enter: 1 for Assembly, 2 for Department, 3 for Process");
				type = myScan.nextInt();
				System.out.println("Enter the assembly id/ process id/ department number:"); 
				id = myScan.nextInt();
								
				//Executing stored procedure proc5
				final String sql1 = "EXEC proc5 @acc = '"+acc+"' , @date = '"+date+"' , @type = "+type+", @id = "+id+";";
				final Statement statement = connection.createStatement(); 
				statement.executeUpdate(sql1);
				System.out.println("Account record inserted successfully.\n");
			}
			
			if (choice == 6) {
				//Declarations
				int jno, aid, pid;
				String sdate;
				//Taking the user inputs
				System.out.println("Enter job number:"); 
				jno = myScan.nextInt();
				System.out.println("Enter assembly id:"); 
				aid = myScan.nextInt();
				System.out.println("Enter process id:"); 
				pid = myScan.nextInt();
				System.out.println("Enter date commenced:"); 
				sdate = myScan.next();
								
				//Executing stored procedure proc6
				final String sql1 = "EXEC proc6 @jno = '"+jno+"' , @aid = "+aid+" , @pid = "+pid+", @sdate = '"+sdate+"';";
				final Statement statement = connection.createStatement(); 
				statement.executeUpdate(sql1);
				System.out.println("Job record inserted successfully.\n");
			}
			
			if (choice == 7) {
				//Declarations
				int jno, type;
				float mtime, ltime;
				String edate, mtype, mat;
				//Taking the user inputs
				System.out.println("Enter job number:"); 
				jno = myScan.nextInt();
				System.out.println("Enter end date:"); 
				edate = myScan.next();
								
				//Executing stored procedure proc7
				final String sql1 = "EXEC proc7 @jno = "+jno+" , @edate = '"+edate+"';";
				final Statement statement = connection.createStatement(); 
				statement.executeUpdate(sql1);
				
				//Taking the user input for type
				System.out.println("What is the type of the job?");
				System.out.println("Enter: 1 for Cut, 2 for Fit, 3 for Paint");
				type = myScan.nextInt();
				
				// Executing appropriate procedure based on type chosen after taking inputs specific to the type   
				if (type == 1) {
					System.out.println("Enter type of machine used:"); 
					mtype = myScan.next();
					System.out.println("Enter amount of time machine used:"); 
					mtime = myScan.nextFloat();
					System.out.println("Enter material used:"); 
					mat = myScan.next();
					System.out.println("Enter labor time spent:"); 
					ltime = myScan.nextFloat();
					//Executing stored procedure proc7_1
					final String sql = "EXEC proc7_1 @jno="+jno+", @mtype='"+mtype+"', @mtime='"+mtime+"', @mat='"+mat+"', @ltime="+ltime+";";
					final Statement stat = connection.createStatement(); 
					stat.executeUpdate(sql);
				}
				
				if (type == 2) {
					System.out.println("Enter labor time spent:"); 
					ltime = myScan.nextFloat();
					//Executing stored procedure proc7_2
					final String sql = "EXEC proc7_2 @jno="+jno+", @ltime="+ltime+";";
					final Statement stat = connection.createStatement(); 
					stat.executeUpdate(sql);
				}
				
				if (type == 3) {
					System.out.println("Enter color:"); 
					mtype = myScan.next();
					System.out.println("Enter volume:"); 
					mtime = myScan.nextFloat();
					System.out.println("Enter labor time spent:"); 
					ltime = myScan.nextFloat();
					//Executing stored procedure proc7_3
					final String sql = "EXEC proc7_3 @jno="+jno+", @color='"+mtype+"', @vol='"+mtime+"', @ltime="+ltime+";";
					final Statement stat = connection.createStatement(); 
					stat.executeUpdate(sql);
				}
				System.out.println("Job record updated successfully.\n");
			}
			
			if (choice == 8) {
				//Declarations
				int tno, jno;
				float scost;
				//Taking the user inputs
				System.out.println("Enter transaction number:"); 
				tno = myScan.nextInt();
				System.out.println("Enter sup_cost:"); 
				scost = myScan.nextFloat();
				System.out.println("Enter- for which job number this transaction is:"); 
				jno = myScan.nextInt();
				
				//Executing stored procedure proc8
				final String sql1 = "EXEC proc8 @tno = '"+tno+"' , @scost = "+scost+" , @jno = "+jno+";";
				final Statement statement = connection.createStatement(); 
				statement.executeUpdate(sql1);
				System.out.println("Transaction record inserted successfully.\n");
			}
			
			if (choice == 9) {
				//Declarations
				int aid;
				//Taking the user input
				System.out.println("Enter assembly id:"); 
				aid = myScan.nextInt();
				
				//Executing stored procedure proc9 and displaying the results
				final String sql1 = "EXEC proc9 @aid = "+aid+";";
				try (final Statement stat = connection.createStatement(); 
				final ResultSet resultSet = stat.executeQuery(sql1)) {
				System.out.println("Cost incurred on this assembly id is:"); 
				while (resultSet.next()) {
				    System.out.println(String.format("%.2f", resultSet.getFloat(1)));
				}
			  }	
			}
			
			if (choice == 10) {
				//Declarations
				int dno;
				String date;
				//Taking the user inputs
				System.out.println("Enter department number:"); 
				dno = myScan.nextInt();
				System.out.println("Enter date completed:"); 
				date = myScan.next();
				
				//Executing stored procedure proc10 and displaying the results
				final String sql1 = "EXEC proc10 @dno = "+dno+", @date = '"+date+"';";
				try (final Statement stat = connection.createStatement(); 
				final ResultSet resultSet = stat.executeQuery(sql1)) {
				System.out.println("Total labor time within this department for jobs completed in the department during this date is:"); 
				while (resultSet.next()) {
				    System.out.println(String.format("%.2f", resultSet.getFloat(1)));
				}
			  }	
			}
			
			if (choice == 11) {
				//Declarations
				int aid;
				//Taking the user input
				System.out.println("Enter assembly id:"); 
				aid = myScan.nextInt();
				
				//Executing stored procedure proc11 and displaying the results
				final String sql1 = "EXEC proc11 @aid = "+aid+";";
				try (final Statement stat = connection.createStatement(); 
				final ResultSet resultSet = stat.executeQuery(sql1)) {
				System.out.println("Process-id | Dept. no. | Date commenced"); 
				while (resultSet.next()) {
					System.out.println(String.format("    %d    |     %d     |   %s ", resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
				}
				System.out.println();
			  }	
			}
			
			if (choice == 12) {
				//Declarations
				int dno;
				String date;
				//Taking the user inputs
				System.out.println("Enter date completed:"); 
				date = myScan.next();
				System.out.println("Enter department number:"); 
				dno = myScan.nextInt();
				
				//Executing stored procedure proc12_1 (for cut jobs) and displaying the results
				final String sql1 = "EXEC proc12_1 @dno = "+dno+", @date = '"+date+"';";
				try (final Statement stat = connection.createStatement(); 
				final ResultSet resultSet = stat.executeQuery(sql1)) {
					System.out.println("Cut jobs (if any) are the following (blank if none):");
					System.out.println("Job no|Date commenced|Date completed|Machine type|Machine time|Material|Labor time|Assembly id\n"); 
					while (resultSet.next()) {
						System.out.println(String.format("%d | %s | %s | %s | %.2f | %s | %.2f | %d", resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
								resultSet.getFloat(5),resultSet.getString(6), resultSet.getFloat(7), resultSet.getInt(8)));
				}
				System.out.println();
			  }	
				//Executing stored procedure proc12_2 (for fit jobs) and displaying the results
				final String sql2 = "EXEC proc12_2 @dno = "+dno+", @date = '"+date+"';";
				try (final Statement stat = connection.createStatement(); 
				final ResultSet resultSet = stat.executeQuery(sql2)) {
					System.out.println("Fit jobs (if any) are the following (blank if none):");
					System.out.println("Job no|Date commenced|Date completed|Labor time|Assembly id\n"); 
					while (resultSet.next()) {
						System.out.println(String.format("%d | %s | %s | %.2f | %d", resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),
								resultSet.getFloat(4), resultSet.getInt(5)));
				}
				System.out.println();
			  }	
				
				//Executing stored procedure proc12_3 (for paint jobs) and displaying the results
				final String sql3 = "EXEC proc12_3 @dno = "+dno+", @date = '"+date+"';";
				try (final Statement stat = connection.createStatement(); 
				final ResultSet resultSet = stat.executeQuery(sql3)) {
					System.out.println("Paint jobs (if any) are the following (blank if none):");
					System.out.println("Job no|Date commenced|Date completed|Color|Volume|Labor time|Assembly id\n"); 
					while (resultSet.next()) {
						System.out.println(String.format("%d | %s | %s | %s | %.2f | %.2f | %d", resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
								resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getInt(7)));
				}
				System.out.println();
			  }	
				System.out.println("Query 12 executed successfully.\n");
			}		
			
			if (choice == 13) {
				//Declarations
				int lb, ub;
				//Taking the user inputs
				System.out.println("Enter lower bound for customer category:"); 
				lb = myScan.nextInt();
				System.out.println("Enter upper bound for customer category:"); 
				ub = myScan.nextInt();
				
				//Executing stored procedure proc13 and displaying the results
				final String sql1 = "EXEC proc13 @lb = "+lb+", @ub = "+ub+";";
				try (final Statement stat = connection.createStatement(); 
				final ResultSet resultSet = stat.executeQuery(sql1)) {
				System.out.println("Customer name | Address | Category"); 
				while (resultSet.next()) {
					System.out.println(String.format("%10s    |%8s |   %d ", resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
				}
				System.out.println();
			  }	
			}
			
			if (choice == 14) {
				//Declarations
				int lb, ub;
				//Taking the user inputs
				System.out.println("Enter lower bound for job-no:"); 
				lb = myScan.nextInt();
				System.out.println("Enter upper bound for job-no:"); 
				ub = myScan.nextInt();
				
				//Executing stored procedure proc14
				final String sql1 = "EXEC proc14 @lb = "+lb+", @ub = "+ub+";";
				final Statement statement = connection.createStatement(); 
				statement.executeUpdate(sql1);
				System.out.println("Cut-jobs in given range deleted successfully.\n");
			}
							
			if (choice == 15) {
				//Declarations
				int jno;
				String col;
				//Taking the user inputs
				System.out.println("Enter job-no:"); 
				jno = myScan.nextInt();
				System.out.println("Enter new color:"); 
				col = myScan.next();
				
				//Executing stored procedure proc15
				final String sql1 = "EXEC proc15 @jno = "+jno+", @col = '"+col+"';";
				final Statement statement = connection.createStatement(); 
				statement.executeUpdate(sql1);
				System.out.println("Color of the job updated successfully.\n");
			}	
			
			if (choice == 16) {
				//Declarations
				String fname, name, add, cat, attr[];
				//Taking the user inputs
				System.out.println("Enter the name of the input data file\n(as <filename>.csv):");
				fname = myScan.next();
				FileInputStream fstream = new FileInputStream("/Users/abina/Desktop/" + fname); 
				DataInputStream is = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				//Reading from the given .csv file until all data are read
				String fline;
				while ((fline = br.readLine()) != null) { 
				//Splitting each line into 3 attributes and inserting into customer table using stored procedure proc1
					attr = fline.split(",");
					name = attr[0];
					add  = attr[1];
					cat  = attr[2];
					final String sql1 = "EXEC proc1 @name = '"+name+"' , @add = '"+add+"' , @cat = '"+cat+"';";
					final Statement statement = connection.createStatement(); 
					statement.executeUpdate(sql1);
				}
				System.out.println("Data imported from file and inserted succesfully!"); 
				br.close();
			}
			
			if (choice == 17) {
				//Declarations
				int lb, ub;
				String fname;
				String s = null;
				final Statement statement = connection.createStatement();
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				//Taking the user inputs
				System.out.println("Enter lower bound for category:"); 
				lb = myScan.nextInt();
				System.out.println("Enter upper bound for category:"); 
				ub = myScan.nextInt();
				System.out.println("Enter the name of the output data file\n(as <filename>.csv):");
				fname = br.readLine();
				
				//Executing stored procedure proc13 and retrieving the data to write to file
				final String sql1 = "EXEC proc13 @lb = "+lb+", @ub = "+ub+";";
				ResultSet rs = statement.executeQuery(sql1);		
				BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/abina/Desktop/" + fname));
				
			    //Writing to the given .csv file until the result set is empty 
				while (rs.next()) {
					s = rs.getString(1)+","+rs.getString(2)+","+rs.getString(3); 
					bw.write(s);
					bw.write("\n"); 
				} 
				System.out.println("Data exported to file succesfully!"); 
				bw.close();
			}
						
			if (choice == 18)
				// Choice 18 will terminate the loop and also the program
				System.out.println("Exiting from the application\nBye!");
		}
		myScan.close();
	   } 
		catch (IOException e) {
		e.printStackTrace();
	 }
   }
}