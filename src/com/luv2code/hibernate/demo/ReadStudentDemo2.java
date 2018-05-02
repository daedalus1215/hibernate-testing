package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo2 {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		
			
		try {
			// create a student object
			System.out.println("Creating new student object...");
			Student studentDTO = new Student("Thomas", "Jefferson", "tj@yahoo.com");
			
			session.beginTransaction();
					
			System.out.println("saving student");
			session.save(studentDTO);
			session.getTransaction().commit();
			
			
			
			// Let's read the 	
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction(); // always query with transaction
			
			System.out.println("Getting student with id: " + studentDTO.getId());
			Student myStudent = session.get(Student.class, studentDTO.getId());
			session.getTransaction().commit();
			
			System.out.println("My retrieved student has a name of: " + myStudent.getFirstName());
			// commit the transaction		
			System.out.println("Done!");
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			factory.close();
		}
	}

}