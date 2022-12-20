package com.studentsmarks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class Subjects {
	private int rollNo;
	private String studentName;
	private float tamil, english, maths, science, social;

	public void setData(int rollNo, String studentName, float tamil,
			float english, float maths, float science, float social) {
		this.rollNo = rollNo;
		this.studentName = studentName;
		this.tamil = tamil;
		this.english = english;
		this.maths = maths;
		this.science = science;
		this.social = social;
	}

	public int getrollNo() {
		return rollNo;
	}

	public String getstudentName() {
		return studentName;
	}

	public float gettamil() {
		return tamil;
	}

	public float getenglish() {
		return english;
	}

	public float getmaths() {
		return maths;
	}

	public float getscience() {
		return science;
	}

	public float getsocial() {
		return social;
	}
}

class StudentsMarkDetails {
	ArrayList<Subjects> studentList = new ArrayList<Subjects>();
	File file = new File("G:\\com\\info\\Students\\MarkDetails.txt");

	public void add(Subjects studentDetails) {
		studentList.add(studentDetails);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(file, studentList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readJsonObjectAsList() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Subjects[] subject = mapper.readValue(file, Subjects[].class);
			for (Subjects marks : subject) {
				System.out.println("Roll No : " + marks.getrollNo());
				System.out.println("Student Name : " + marks.getstudentName());
				System.out.println("Tamil   : " + marks.gettamil());
				System.out.println("English : " + marks.getenglish());
				System.out.println("Maths   : " + marks.getmaths());
				System.out.println("Science : " + marks.getscience());
				System.out.println("Social  : " + marks.getsocial() + "\n");
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class StudentsMarks {

	public static int strength;

	public static void main(String[] args) {
		StudentsMarks studentsMarks = new StudentsMarks();
		studentsMarks.studentInfo();
	}

	public void studentInfo() {
		Scanner scanner = new Scanner(System.in);
		StudentsMarkDetails details = new StudentsMarkDetails();
		System.out.println("Enter the Students Strength");
		strength = scanner.nextInt();
		for (int i = 0; i < strength; i++) {
			Subjects subjects = new Subjects();
			System.out
					.println("Enter the Student Roll number,Name and their marks");
			subjects.setData(scanner.nextInt(), scanner.next(),
					scanner.nextFloat(), scanner.nextFloat(),
					scanner.nextFloat(), scanner.nextFloat(),
					scanner.nextFloat());
			details.add(subjects);
		}
		System.out.println("Students Mark Details");
		details.readJsonObjectAsList();
	}
}