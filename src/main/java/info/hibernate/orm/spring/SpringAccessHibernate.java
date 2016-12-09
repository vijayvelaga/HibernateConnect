package info.hibernate.orm.spring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import info.hibernate.orm.dto.UserDetails;

public class SpringAccessHibernate {
	public static void main(String[] args) {
		Session session = null;
		try {
		ApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDetails user = container.getBean("user", UserDetails.class);
		SessionFactory sessionFactory =  container.getBean("hbnConfiguration", Configuration.class).configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		}catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

}
