package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		
			
		try {
			// create a student object
			System.out.println("Creating new student object...");
			Student studentDTO = new Student("Larry", "Smith", "smith@luv2code.com", DateUtils.parseDate("31/12/1997"));
			Student studentDTO2 = new Student("Jim", "Smith", "jim@luv2code.com", DateUtils.parseDate("31/12/1997"));
			Student studentDTO3 = new Student("Jane", "Mcgee", "jane@luv2code.com", DateUtils.parseDate("31/12/1997"));

			
			// start a transaction
			System.out.println("Starting a transaction...");
			session.beginTransaction();
			
			// save the student object
			session.save(studentDTO);
			session.save(studentDTO2);
			session.save(studentDTO3);
			System.out.println("Creating new student object...");
			System.out.println("Saving the student...");
			
			// commit transaction
			System.out.println("Creating new student object...");
			session.getTransaction().commit();
			
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
