package com.jacken.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class Customer_Order_Test {

	@Test
	public void creatDB() {
		 //  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
		Configuration configuration=new Configuration().configure(); 
		//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
		SessionFactory sessionFactory=configuration.buildSessionFactory();
	}
	
	@Test
	public void add() {
	//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
				Configuration configuration=new Configuration().configure(); 
				//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
				SessionFactory sessionFactory=configuration.buildSessionFactory();
				//3.持久化工具
				Session session=sessionFactory.openSession();
				//4.开启事务
				Transaction transaction=session.beginTransaction();
				Customer customer = new Customer();
				customer.setCname("jacken");
				Order order = new Order();
				order.setDescc("任海涛");
				Set<Order> orders = new HashSet<Order>();
				orders.add(order);
				customer.setOrders(orders);
				session.save(customer);
				transaction.commit();
				session.close();
	}
	
	//删除一条订单
		@Test
		public void deleteOrderById(){
			//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
			Configuration configuration=new Configuration().configure(); 
			//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
			SessionFactory sessionFactory=configuration.buildSessionFactory();
			//3.持久化工具
			Session session=sessionFactory.openSession();
			 Transaction transaction = session.beginTransaction();
			 //session-->sessionFactory-->DB
			 Order order =(Order) session.get(Order.class, 3);
			 session.delete(order);
			 transaction.commit();
			 session.close();
		}
		
		//根据客户来删除订单
		@Test
		public void delete2(){
			//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
			Configuration configuration=new Configuration().configure(); 
			//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
			SessionFactory sessionFactory=configuration.buildSessionFactory();
			//3.持久化工具
			Session session=sessionFactory.openSession();
			 Transaction transaction = session.beginTransaction();
			Customer customer = (Customer)session.get(Customer.class, 1);
			Set<Order> orders = customer.getOrders();
			Iterator<Order> iterator = orders.iterator();
			while(iterator.hasNext()){
				Order order = iterator.next();
				session.delete(order);
			}
			session.delete(customer);
			
			 transaction.commit();
			 session.close();
		}
		
		//删除订单
		@Test
		public void delete3(){
		//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
					Configuration configuration=new Configuration().configure(); 
					//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
					SessionFactory sessionFactory=configuration.buildSessionFactory();
					//3.持久化工具
					Session session=sessionFactory.openSession();
			 Transaction transaction = session.beginTransaction();
			 Query query = session.createQuery("from Order where c_id=4");
			 List<Order> list = query.list();
			 for (int i = 0; i < list.size(); i++) {
				 Order order = list.get(i);
				 session.delete(order);
			}
			 transaction.commit();
			 session.close();
		}
		
		//解除订单与客户2之间的关系
		@Test
		public void delete4(){
		//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
			Configuration configuration=new Configuration().configure(); 
			//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
			SessionFactory sessionFactory=configuration.buildSessionFactory();
			//3.持久化工具
			Session session=sessionFactory.openSession();
			 Transaction transaction = session.beginTransaction();
			 Query query = session.createQuery("from Order where c_id=2");
			 List<Order> list = query.list();
			 for (int i = 0; i < list.size(); i++) {
				Order order = list.get(i);
				order.setCustomer(null);
				session.update(order);
			}
			 transaction.commit();
			 session.close();
		}
		
		//解除订单与客户2之间的关系
		@Test
		public void delete5(){
		//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
					Configuration configuration=new Configuration().configure(); 
					//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
					SessionFactory sessionFactory=configuration.buildSessionFactory();
					//3.持久化工具
					Session session=sessionFactory.openSession();
			 Transaction transaction = session.beginTransaction();
		     Customer customer =(Customer) session.get(Customer.class, 2);
		     customer.setOrders(null);
			 transaction.commit();
			 session.close();
		}
		
		//根据客户 删除订单
		@Test
		public void delete6(){
		//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
			Configuration configuration=new Configuration().configure(); 
			//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
			SessionFactory sessionFactory=configuration.buildSessionFactory();
			//3.持久化工具
			Session session=sessionFactory.openSession();
			 Transaction transaction = session.beginTransaction();
			    Customer customer =(Customer) session.get(Customer.class, 2);
			    session.delete(customer); 
			    transaction.commit();
				 session.close();
		}
		//根据客户修改 全部 订单的描述为  无
		@Test
		public void update1(){
		//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
					Configuration configuration=new Configuration().configure(); 
					//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
					SessionFactory sessionFactory=configuration.buildSessionFactory();
					//3.持久化工具
					Session session=sessionFactory.openSession();
			 Transaction transaction = session.beginTransaction();
			 Query query = session.createQuery("from Customer");
			 List<Customer> list = query.list();
			 for(Customer customer: list ){
				 Set<Order> orders = customer.getOrders();
				 Iterator<Order> iterator = orders.iterator();
				 while(iterator.hasNext()){
					 Order order = iterator.next();
					 order.setDescc("无");
				 }
			 }
			 transaction.commit();
			 session.close();
		}
		
		//修改单条
		@Test
		public void update2(){
		//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
			Configuration configuration=new Configuration().configure(); 
			//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
			SessionFactory sessionFactory=configuration.buildSessionFactory();
			//3.持久化工具
			Session session=sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			 Customer customer = (Customer)session.get(Customer.class,5);
			 Set<Order> orders = customer.getOrders();
			 Iterator<Order> iterator = orders.iterator();
			 while(iterator.hasNext()){
				 Order order = iterator.next();
				 order.setDescc("55");
			 }
			 transaction.commit();
			 session.close();
		}
		
		//查询全部
		@Test
		public void findAll(){
		//  Configuration接口负责配置并启动Hibernate，创建SessionFactory对象。
			Configuration configuration=new Configuration().configure(); 
			//SessionFactory接口负责初始化Hibernate。    其实就是连接数据库的
			SessionFactory sessionFactory=configuration.buildSessionFactory();
			//3.持久化工具
			Session session=sessionFactory.openSession();
			Query query = session.createQuery("from Customer");
			List<Customer> list = query.list();
			for(Customer customer:list){
				System.out.println(customer.getCname());
				Set<Order> orders = customer.getOrders();
				Iterator<Order> iterator = orders.iterator();
				while(iterator.hasNext()){
					Order order = iterator.next();
					System.out.println(order.getDescc());
				}
			}
		}
}
