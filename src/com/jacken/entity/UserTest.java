package com.jacken.entity;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;



public class UserTest {
	@Test
	public void creatDB() {
		 //  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
		Configuration configuration=new Configuration().configure(); 
		//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
		SessionFactory sessionFactory=configuration.buildSessionFactory();
	}
	
	@Test
	public void add(){
			//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
			Configuration configuration=new Configuration().configure(); 
			//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
			SessionFactory sessionFactory=configuration.buildSessionFactory();
			//3.持久化工具
			Session session=sessionFactory.openSession();
			//4.开启事务
			Transaction transaction=session.beginTransaction();
				User user=new User();
				user.setId(123);
				user.setName("Jacken");;
				user.setPwd("123456");
				//5.操作
			session.save(user);
			//6.提交事务
			transaction.commit();
			//7.关闭资源
			session.close();
	}
	
	@Test
	public void delete() {
	//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
				Configuration configuration=new Configuration().configure(); 
				//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
				SessionFactory sessionFactory=configuration.buildSessionFactory();
				//3.持久化工具
				Session session=sessionFactory.openSession();
				//4.开启事务
				Transaction transaction=session.beginTransaction();
				User user = (User)session.get(User.class,123);
				session.delete(user);
				transaction.commit();
				session.close();
	}
	
	@Test
	public void find() {
	//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
				Configuration configuration=new Configuration().configure(); 
				//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
				SessionFactory sessionFactory=configuration.buildSessionFactory();
				//3.持久化工具
				Session session=sessionFactory.openSession();
				//HQL
				Query createQuery=session.createQuery("from com.jacken.entity.User");
				System.out.println(createQuery.list());
	}
}
