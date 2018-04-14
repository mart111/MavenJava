package com.company;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * Hello world!
 */
public class App {


    static Session getSession() {

        Configuration cfg = new Configuration().configure();
        ServiceRegistryBuilder sb = new ServiceRegistryBuilder().applySettings(cfg.getProperties());

        return cfg.buildSessionFactory(sb.buildServiceRegistry()).openSession();
    }

    public static void main(String[] args) {

        try {

            Session session = getSession();

            session.beginTransaction();

            session.save(new Student("Martin", "Knyazyan"));

            session.getTransaction().commit();

            /**
             *    Queries to MySQL table!!!
             * */
            SQLQuery query = session.createSQLQuery("select * from student_table where id=4");
            query.addEntity(Student.class);
            List<Student> list = query.list();
            /**
             *
             * */

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
