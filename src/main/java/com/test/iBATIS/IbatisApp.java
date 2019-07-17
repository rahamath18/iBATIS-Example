package com.test.iBATIS;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IbatisApp {

	public static void main(String[] args) throws IOException, SQLException {

		Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);

		insertUser(smc);
		fetchUser(smc);

		updateUser(smc);
		fetchUser(smc);

		deleteUser(smc);
		fetchUser(smc);

		userResultMap(smc);

		userStoredProcedure(smc);

		dynamicSql(smc);

	}

	private static void dynamicSql(SqlMapClient smc) {
		try {
			System.out.println("Using iBATIS - Dynamic SQL...");
			User user = new User();
			user.setId(3);

			List<User> userList = (List<User>) smc.queryForList("User.findUserByID", user);
			System.out.println("Using iBATIS - Dynamic SQL..." + userList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void userStoredProcedure(SqlMapClient smc) {
		try {
			System.out.println("Using iBATIS - Stored Procedures...");
			User user = (User) smc.queryForObject("User.getUserById", 2);
			System.out.println("Using iBATIS - Stored Procedures..." + user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void userResultMap(SqlMapClient smc) {
		try {
			System.out.println("Using Result Map...");
			User user = (User) smc.queryForObject("User.userResultMap", 2);
			System.out.println("Using Result Map..." + user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void deleteUser(SqlMapClient smc) {
		try {
			System.out.println("Deleting In Table...");

			smc.delete("User.deleteUser", 1);

			System.out.println("Record Deleted Successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void updateUser(SqlMapClient smc) {
		try {
			System.out.println("Updating In Table...");
			List<User> userList = fetchUser(smc);
			for (User user : userList) {
				user.setLast_name(user.getLast_name() + " - Updated");
				smc.update("User.updateUser", user);
			}

			System.out.println("Record Updated Successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static List<User> fetchUser(SqlMapClient smc) {
		try {
			System.out.println("Fetching From Table...");

			List<User> userList = smc.queryForList("User.getAllUsers", null);
			for (User user : userList) {
				System.out.println(user);
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<User>();
	}

	private static void insertUser(SqlMapClient smc) {
		try {
			System.out.println("Inserting Into Table...");

			User newUser = new User("Rahamath", "S", "rahamath18@yahoo.com");
			System.out.println("Before Save : " + newUser);
			smc.insert("User.insertUser", newUser);
			System.out.println("After Save : " + newUser);

			for (int i = 2; i <= 3; i++) {
				User user = new User("User-" + i, "U-" + i, "user-" + i + "@info.com");
				smc.insert("User.insertUser", user);
			}

			System.out.println("Record Inserted Successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
