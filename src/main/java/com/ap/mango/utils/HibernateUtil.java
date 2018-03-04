package com.ap.mango.utils;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
 
public class HibernateUtil {
 
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
   // private static final SessionFactory sessionFactory;
 
	static {
		try {
			// sessionFactory = new
			// AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();

			
//			 * final Configuration configuration = new Configuration();
//			 * configuration.configure("hibernate.cfg.xml"); final
//			 * ServiceRegistry serviceRegistry = new
//			 * StandardServiceRegistryBuilder().applySettings(
//			 * configuration.getProperties()).build(); sessionFactory =
//			 * configuration.buildSessionFactory(serviceRegistry);
			 

			final Configuration configuration = new Configuration().configure();
			final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());

		} catch (final Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
 
    public static Session openSession() {
    	/*final String hibernatePropsFilePath = "hibernate.cfg.xml";
    	final File hibernatePropsFile = new File(hibernatePropsFilePath);
    	final Configuration configuration = new Configuration().configure(hibernatePropsFile);
		final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());*/
		//sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory.openSession();
    }
    
    
   /* public static SessionFactory createSessionFactory() {
        final Configuration configuration = new Configuration();
        configuration.configure();
        final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }*/
}