package com.github.greatwqs.app.proxy.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

	private static final String SQL_INSERT_USER = "";
	private static final String SQL_INSERT_USER_ATTR = "";

	/***
	 * 用户主表
	 */
	public void insertUser(Connection connection, String name) throws SQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SQL_INSERT_USER);
			statement.setString(1, name);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/***
	 * 用户附属信息表
	 */
	public void insertUserAttr(Connection connection, String name, String attr) throws SQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SQL_INSERT_USER_ATTR);
			statement.setString(1, name);
			statement.setString(2, attr); // 引发异常
			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}
}
