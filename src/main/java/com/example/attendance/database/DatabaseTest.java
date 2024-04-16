package com.example.attendance.database;

import java.util.Scanner;

public class DatabaseTest {
	public static void Test() {
		DatabaseConnector t = DatabaseConnector.GetInstance();
		
		// 情報の入力
		Scanner s = new Scanner(System.in);
		System.out.println("ユーザー名を入力してください");
		String name = s.nextLine();
		System.out.println("パスワードを入力してください");
		String pass = s.nextLine();
		
		s.close();
		
		System.out.println("ユーザーが登録できるか");
		System.out.println(t.CheckRegisterUser(name, pass));
		if(t.CheckRegisterUser(name, pass)) {
			System.out.println("ユーザー登録");
			t.RegisterUser(name,pass);
		}
		else {
			System.out.println("ユーザー登録失敗");
			System.out.println("ログインします");
			System.out.println(t.isLogin(name, pass));
		}
		System.out.println("ユーザーが働いているか");
		System.out.println(t.isWorking(name));

		System.out.println("出勤");
		t.StartWork(name, pass);
		System.out.println("ユーザーが働いているか");
		System.out.println(t.isWorking(name));

		System.out.println("退勤");
		t.EndWork(name, pass);
		System.out.println("ユーザーが働いているか");
		System.out.println(t.isWorking(name));

		System.out.println("ログの確認");
		System.out.println(t.CheckLog(name, pass));
		
		return;
	}
}
