package com.example.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.attendance.database.DatabaseConnector;

@SpringBootApplication
public class AttendanceManagementToolApplication {

	public static void main(String[] args) {
		DatabaseConnector t = DatabaseConnector.GetInstance();
		System.out.println(t.CheckRegisterUser("Test", "pass"));
		t.RegisterUser("Test","pass");
		System.out.println(t.isWorking("Test"));

		System.out.println("出勤");
		t.StartWork("Test", "pass");
		System.out.println(t.isWorking("Test"));

		System.out.println("退勤");
		t.EndWork("Test", "pass");
		System.out.println(t.isWorking("Test"));
		
		if(true)return;
		SpringApplication.run(AttendanceManagementToolApplication.class, args);
	}

}
