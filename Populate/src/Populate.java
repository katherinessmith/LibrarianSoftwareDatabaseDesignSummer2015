import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.*;


public class Populate {

	private static Connection con = null;

	public Populate() {
	}

	public static String RemoveEndingDot(String inputString)
	{
		char[] input = inputString.toCharArray();
		char[] trimStr = new char[input.length - 1];
		System.arraycopy(input, 0, trimStr, 0, input.length - 1);
		return new String(trimStr);
	}

	public static void setUpConnection() throws SQLException {
		if(con != null)
			return;
		else {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
			Statement stmt = con.createStatement();
			stmt.execute("use library;");
		}
	}

	public static void closeConnection() throws SQLException {
		if(con != null)
			return;
		else {
			con.close();
		}
	}

	public static void PopulateDB()
	{
		String csvFile = "/Users/ninaad/Documents/MyJavaWork/JavaWorkspace/Populate/src/books_authors.csv";
		String line = null;
		String insertBookStmt = "insert into book(book_id, title) values(?,?)";

		try {
			setUpConnection();

			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			while((line=br.readLine())!=null && line.length()!=0) {
				String[] split = line.split("\t");
				PreparedStatement ps = con.prepareStatement(insertBookStmt);
				ps.setString(1, split[0]);
				ps.setString(2, split[2]);
				ps.execute();

				InsertBookAuthors(line);
			}
			System.out.println("BOOK and BOOK_AUTHORS: Completed");
			InsertLibraryBranches();
			System.out.println("LIBRARY_BRANCES: Completed");
			InsertBookCopies();
			System.out.println("BOOK_COPIES: Completed");
			InsertBorrowers();
			System.out.println("BORROWERS: Completed");
			InsertBookLoans();
			System.out.println("BOOK_LOANS: Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void InsertBorrowers() throws Exception {
		String csvFile = "/Users/ninaad/Documents/MyJavaWork/JavaWorkspace/Populate/src/borrowers.csv";
		String line = null;
		String insertBookCopiesStmt = "insert into borrower(card_no, fname, lname, address, phone) "
				+ "values(?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(insertBookCopiesStmt);
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		br.readLine();
		while((line=br.readLine())!=null && line.length()!=0) {
			String[] split = line.split("\t");
			ps.setInt(1, Integer.parseInt(split[0]));
			ps.setString(2, split[1]);
			ps.setString(3, split[2]);
			ps.setString(4, split[3] + "," + split[4]  + "," + split[5]);
			ps.setString(5, split[6]);
			ps.execute();
		}
	}

	private static void InsertBookCopies() throws Exception {
		String csvFile = "/Users/ninaad/Documents/MyJavaWork/JavaWorkspace/Populate/src/book_copies.csv";
		String line = null;
		String insertBookCopiesStmt = "insert into book_copies(book_id, branch_id, no_of_copies) "
				+ "values(?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(insertBookCopiesStmt);
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		br.readLine();
		while((line=br.readLine())!=null && line.length()!=0) {
			String[] split = line.split("\t");
			ps.setString(1, split[0]);
			ps.setInt(2, Integer.parseInt(split[1]));
			ps.setInt(3, Integer.parseInt(split[2]));
			ps.execute();
		}
	}

	public static void InsertBookLoans() throws Exception {
		String csvFile = "/Users/ninaad/Documents/MyJavaWork/JavaWorkspace/Populate/src/book_loans_data.csv";
		String line = null;
		String insertBookLoansStmt = "insert into book_loans(loan_id, book_id, branch_id, "
				+ "card_no, date_out, due_date, date_in) values(?,?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(insertBookLoansStmt);
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		br.readLine();
		while((line=br.readLine())!=null && line.length()!=0) {
			String[] split = line.split("\t");
			ps.setInt(1, Integer.parseInt(split[0]));
			ps.setString(2, split[1]);
			ps.setInt(3, Integer.parseInt(split[2]));
			ps.setInt(4, Integer.parseInt(split[3]));
			ps.setString(5, split[4]);
			ps.setString(6, split[5]);
			if(split[6].equalsIgnoreCase("NULL"))
				ps.setNull(7, Types.DATE);
			else
				ps.setString(7, split[6]);
			ps.execute();
		}
	}


	private static void InsertLibraryBranches() throws Exception {
		String csvFile = "/Users/ninaad/Documents/MyJavaWork/JavaWorkspace/Populate/src/library_branch.csv";
		String line = null;
		String insertLibBranchStmt = "insert into library_branch(branch_id, branch_name, address) "
				+ "values(?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(insertLibBranchStmt);
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		br.readLine();
		while((line=br.readLine())!=null && line.length()!=0) {
			String[] split = line.split("\t");
			ps.setInt(1, Integer.parseInt(split[0]));
			ps.setString(2, split[1]);
			ps.setString(3, split[2]);
			ps.execute();
		}
	}

	public static void InsertBookAuthors(String line) throws Exception {
		String insertBookAuthStmt = "insert into book_authors(book_id, author_name, type) "
				+ "values(?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(insertBookAuthStmt);
		String[] split = line.split("\t");
		ps.setString(1, split[0]);
		String[] authNames = split[1].split(",");

		for (String authName : authNames) {
			authName = authName.trim();
			ps.setString(2, authName);
			if(authName.contains("Various") || 
					authName.contains("Museum") ||
					authName.contains("The"))
				ps.setInt(3, 2);
			else
				ps.setInt(3, 1);
			ps.execute();
		}
	}
	
	public static void main(String[] args) {
		try {
			setUpConnection();
			PopulateDB();
			closeConnection();
			System.out.println("Database creation complete");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
