package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class QueryStudentDemo3 {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		
			
		try {
			session.beginTransaction();
			
			displayAllStudents(session);
			
			
			displayJeffersonStudent(session);
			
			List<Student> theStudents;
 
			int studentID = 1;
			Student theStudent = session.get(Student.class, studentID);
			theStudent.setFirstName("Tom");
			
			session.getTransaction().commit();			
			System.out.println("Done!");
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			factory.close();
		}
	}

	
	
	private static void displayJeffersonStudent(Session session) {
		// query students: lastname='Jefferson'
		List<Student> theStudents;
		theStudents = session.createQuery("from Student s where s.lastName='Jefferson'").getResultList();
		
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private static void displayAllStudents(Session session) {
		// query students
		List<Student> theStudents = session.createQuery("from Student").list();

		
		// display the students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}