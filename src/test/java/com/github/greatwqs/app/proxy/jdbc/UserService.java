package com.github.greatwqs.app.proxy.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

public class UserService {
	UserDAO userDAO;
	DataSource dataSource;

	public void addUser(String name, String attr) {
		Connection connection = null;
		try {
			connection = DataSourceUtils.getConnection(dataSource);
			connection.setAutoCommit(false);

			// 插入用户主表
			userDAO.insertUser(connection, name);

			// 插入用户附属信息表
			userDAO.insertUserAttr(connection, name, attr);

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("rollback failed..");
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
			}

			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
			}
		}
	}
}
