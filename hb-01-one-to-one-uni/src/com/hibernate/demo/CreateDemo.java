package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// create the objects

			
			/*
			Instructor instructor = 
			new Instructor("test", "instructor", "email@email.com");
			  
			InstructorDetail instructorDetail = 
			new InstructorDetail("http://www.youtube.com", "code");
			*/ 

			Instructor instructor = 
					new Instructor("john", "doe", "email@email.com");

			InstructorDetail instructorDetail =
					new InstructorDetail("http://www.youtube.com/johndoe", "deceased");

			// associate the objects
			// with the setter from instructor to instructor detail, the objects are
			// now associated in memory
			instructor.setInstructorDetail(instructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of Cascade.ALL
			//
			// it will save both objects (cascade) to two different tables for instructor
			// and instructor details
			System.out.println("Saving instructor: " + instructor);
			session.save(instructor);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}

	}

}
