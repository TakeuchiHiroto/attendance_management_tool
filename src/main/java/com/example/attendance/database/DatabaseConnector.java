package com.example.attendance.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnector {
	// DB接続用定数
	final String DATABASE_NAME = "attendance_management_db";
	final String PROPATIES = "?characterEncoding=UTF-8";
	final String URL = "jdbc:mySQL://mukan.0am.jp:3306/" + DATABASE_NAME + PROPATIES;
	
	// DB接続用・ユーザー定数
	final String USER = "karubi";
	final String PASS = "password";
	
	final String USER_TABLE_NAME = "user";
	final String DATE_TABLE_NAME = "date";
	final String TAG_TABLE_NAME = "tag";
	
	private static Connection conn;
	private static Statement stmt;
	private static DatabaseConnector instance = new DatabaseConnector();
	
	public static DatabaseConnector GetInstance() {
		return instance;
	}
	
	public DatabaseConnector() {
		try {
			// MySQLに接続
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースに接続
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement();
			
			CreateTable();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private void CreateTable() {
		CreateUserTable();
        CreateDateTable();
        CreateTagTable();
    }
	
	public void DropTable() {
		String dropTable = "DROP TABLE " + USER_TABLE_NAME + ";";
		try {
			stmt.executeUpdate(dropTable);
		} catch (Exception e) {
		}
		dropTable = "DROP TABLE " + DATE_TABLE_NAME + ";";
		try {
			stmt.executeUpdate(dropTable);
		} catch (Exception e) {
		}
		dropTable = "DROP TABLE " + TAG_TABLE_NAME + ";";
		try {
			stmt.executeUpdate(dropTable);
		} catch (Exception e) {
		}
		CreateTable();
	}
	
	// Userテーブルを作成する
	private void CreateUserTable() {
		// user
		// ID, Name, Password, Admin, tagID, StartDataID
		// ID: プライマリーキーで自動挿入
		// Name:25文字 NOT NULL
		// Password:255文字 NOT NULL
		// Admin:boolean NOT NULL
		// tagID:int
		// StartDateID:int
		String createTable = "CREATE TABLE " + USER_TABLE_NAME + "(ID INT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(25) NOT NULL, Password VARCHAR(255) NOT NULL, Admin BOOLEAN NOT NULL, tagID INT, StartDateID INT);";
		try {
			stmt.executeUpdate(createTable);
		} catch (Exception e) {
		}
	}
	
	// Dateテーブルを作成する
	private void CreateDateTable() {
		// ID: プライマリーキーで自動挿入
		// UserID: int NOT NULL
		// StartTime: date
		// EndTime:date
		String createTable = "CREATE TABLE " + DATE_TABLE_NAME + "(ID INT AUTO_INCREMENT PRIMARY KEY, UserID INT NOT NULL, StartTime DATETIME, EndTime DATETIME);";
		try {
			stmt.executeUpdate(createTable);
		} catch (Exception e) {
		}
	}
	
	// Tagテーブルを作成する
	private void CreateTagTable() {
		// ID: プライマリーキーで自動挿入
        // TagName:25文字 NOT NULL
        String createTable = "CREATE TABLE " + TAG_TABLE_NAME + "(ID INT AUTO_INCREMENT PRIMARY KEY, TagName VARCHAR(25) NOT NULL);";
        try {
            stmt.executeUpdate(createTable);
        } catch (Exception e) {
        }
    }
	
	
	// ユーザー登録
	public void RegisterUser(String name,String password) {
		String cmd = "INSERT INTO " + USER_TABLE_NAME + "(Name, Password, Admin) VALUES ('" + name + "','" + password + "',false);";
		
		try {
			stmt.executeUpdate(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ユーザー名とパスワードが同じ場合falseを返す
	public boolean CheckRegisterUser(String name, String password) {
		String cmd = "SELECT * FROM " + USER_TABLE_NAME + " WHERE Name = '" + name + "' AND Password = '" + password + "';";
		
		try {
			if (stmt.executeQuery(cmd).next()) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	// ユーザー削除
	public void DeleteUser(String name, String password) {
		String cmd = "DELETE FROM " + USER_TABLE_NAME + " WHERE Name = '" + name + "' AND Password = '" + password
				+ "';";
		
		try {
			stmt.executeUpdate(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ログインできるかを試す(できるならtrue)
	public boolean isLogin(String name, String password) {
		String cmd = "SELECT * FROM " + USER_TABLE_NAME + " WHERE Name = '" + name + "' AND Password = '" + password
				+ "';";
		
		try {
			if (stmt.executeQuery(cmd).next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 出勤中かどうかを確認する(出勤中ならtrue)
	public boolean isWorking(String name) {
		// StartDateIDがNULLでなければ出勤中
		String cmd = "SELECT * FROM " + USER_TABLE_NAME + " WHERE Name = '" + name + "' AND StartDateID IS NOT NULL;";
		
		try {
			if (stmt.executeQuery(cmd).next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 出勤
	public void StartWork(String name, String password) {
		// Dateテーブルにデータを追加
		// NameからIDを取得
		// DateテーブルにUserIDとStartTimeを追加
		// 追加したIDを取得し、UserIDのStartDateIDに追加
		String cmd = "SELECT ID FROM " + USER_TABLE_NAME + " WHERE Name = '" + name + "' AND Password = '" + password + "';";
		
		try {
			ResultSet rs = stmt.executeQuery(cmd);
			rs.next();
			int userID = rs.getInt("ID");
			cmd = "INSERT INTO " + DATE_TABLE_NAME + "(UserID, StartTime) VALUES (" + userID + ", NOW());";
			
			stmt.executeUpdate(cmd);
			cmd = "SELECT ID FROM " + DATE_TABLE_NAME + " WHERE UserID = " + userID + " ORDER BY ID DESC LIMIT 1;";
			
			rs = stmt.executeQuery(cmd);
			rs.next();
			int dateID = rs.getInt("ID");
			cmd = "UPDATE " + USER_TABLE_NAME + " SET StartDateID = " + dateID + " WHERE ID = " + userID + ";";
			
			stmt.executeUpdate(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 退勤
	public void EndWork(String name, String password) {
		// Dateテーブルにデータを編集
		// NameとpasswordからuserテーブルのStartDateIDを取得
		// DateテーブルのEndDateを編集
		// userテーブルのStartDateIDをNULLにする
		String cmd = "SELECT StartDateID FROM " + USER_TABLE_NAME + " WHERE Name = '" + name + "' AND Password = '" + password + "';";
		
		try {
			ResultSet rs = stmt.executeQuery(cmd);
			rs.next();
			int dateID = rs.getInt("StartDateID");
			cmd = "UPDATE " + DATE_TABLE_NAME + " SET EndTime = NOW() WHERE ID = " + dateID + ";";
			
			stmt.executeUpdate(cmd);
			cmd = "UPDATE " + USER_TABLE_NAME + " SET StartDateID = NULL WHERE StartDateID = " + dateID + ";";
			
			stmt.executeUpdate(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ログの確認
	public ArrayList<String> CheckLog(String name, String password) {
		// DateテーブルからStartTimeとEndTimeを取得
		// NameとpasswordからUserIDを取得
		// DateテーブルからUserIDが一致するものを取得
		// StartTimeとEndTimeを取得
		String cmd = "SELECT ID FROM " + USER_TABLE_NAME + " WHERE Name = '" + name + "' AND Password = '" + password
				+ "';";
		
		ArrayList<String> log = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery(cmd);
			if (!rs.next()) {
				return log;
			}
			int userID = rs.getInt("ID");
			cmd = "SELECT * FROM " + DATE_TABLE_NAME + " WHERE UserID = " + userID + ";";
			
			rs = stmt.executeQuery(cmd);
			while (rs.next()) {
				log.add(rs.getString("StartTime") + " - " + rs.getString("EndTime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return log;
	}
}