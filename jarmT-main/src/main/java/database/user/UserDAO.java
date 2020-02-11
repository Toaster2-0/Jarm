package database.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.DBController;
import database.DBStatements;
import de.jarm.main.data.User;
import de.jarm.main.database.exceptions.UserExistsException;
import de.jarm.main.database.exceptions.WrongUserOrPasswordException;
import de.jarm.main.utils.DateUtils;

public class UserDAO {
	
	public List<User> loadUsers() throws Exception {
		List<User> users = new ArrayList<User>();		

		try (Connection con = DBController.getInstance().getConnection();
				Statement stmt = con.createStatement();){
			
			ResultSet rs = stmt.executeQuery(DBStatements.SELECT_ALL_USERS);
			
			while (rs.next()) {
				User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"));
				users.add(u);
			}
			
			return users;
		}
	}
	
	public User logIn(String email, String password) throws Exception {
		try (Connection con = DBController.getInstance().getConnection();
				PreparedStatement pstmt = con.prepareStatement(DBStatements.SELECT_USER_LOGIN)){
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"));
				return u;
			} else {
				throw new WrongUserOrPasswordException();
			}
		}
	}
	
	public User loadUserById(int id) throws Exception {
		try (Connection con = DBController.getInstance().getConnection();
				PreparedStatement pstmt = con.prepareStatement(DBStatements.SELECT_USER_BY_ID)){
			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"));
				return u;
			} else {
				throw new Exception("Kein user unter der id " + id + " gefunden");
			}
		}
	}
	
	
	
	public User addUser(User u) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
				PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_USER,
                        Statement.RETURN_GENERATED_KEYS)){
			
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, DateUtils.toString(LocalDateTime.now()));
			pstmt.setString(4, u.getEmail());	
			pstmt.executeUpdate();
			
			ResultSet generatedKey = pstmt.getGeneratedKeys();
			if (generatedKey.next()) {
				u.setId(generatedKey.getInt(1));
				return u;
			} else {
				throw new UserExistsException();
			}
		} catch (Exception e) {
			throw new UserExistsException();
		}
	}	
	
	public List<User> searchUserByNameOrEmail(String input) throws Exception{
		List<User> results = new ArrayList<>();
		
		try (Connection con = DBController.getInstance().getConnection();
				PreparedStatement pstmt = con.prepareStatement(DBStatements.FIND_USER_BY_NAME_OR_EMAIL)){
			
			pstmt.setString(1, "%" + input + "%");
			pstmt.setString(2, "%" + input + "%");
	
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"));
				results.add(u);
			}
			
			return results;
		}		
	}
	
	public List<User> searchUserInProjectByNameOrEmail(String input, int projectId) throws Exception{
		List<User> results = new ArrayList<>();
		
		try (Connection con = DBController.getInstance().getConnection();
				PreparedStatement pstmt = con.prepareStatement(DBStatements.FIND_USER_IN_PROJECT_BY_NAME_OR_EMAIL)){
			
			pstmt.setString(1, "%" + input + "%");
			pstmt.setString(2, "%" + input + "%");
			pstmt.setInt(3, projectId);
			pstmt.setString(4, "%" + input + "%");
			pstmt.setString(5, "%" + input + "%");
			pstmt.setInt(6, projectId);
	
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"));
				results.add(u);
			}
			
			return results;
		}		
	}
}
