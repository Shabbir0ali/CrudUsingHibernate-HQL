package com.shabbir.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.shabbir.bean.ContectBean;
import com.shabbir.helper.FactoryProvider;

public class DaoImpl implements Dao {

	public void register(ContectBean bean, BufferedReader rd) throws IOException {

		SessionFactory factory = FactoryProvider.getFactory();

		System.out.println("Enter your first name");
		bean.setFirstName(rd.readLine());
		System.out.println("Enter your last name");
		bean.setLastName(rd.readLine());
		System.out.println("Enter your phone number");
		bean.setPhoneNum(Long.parseLong(rd.readLine()));
		System.out.println("Enter groups");
		bean.setGroup(rd.readLine());

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		session.save(bean);
		transaction.commit();

		session.close();

	}

	public void delete(ContectBean bean, BufferedReader rd) throws NumberFormatException, IOException {

		SessionFactory factory = FactoryProvider.getFactory();

		Session session = factory.openSession();

		Transaction begin = session.beginTransaction();

		System.out.println("Enter your phone number you want to delete");
		bean.setPhoneNum(Long.parseLong(rd.readLine()));

		Query query = session.createQuery("delete from ContectBean where phoneNum=:phone");

		query.setParameter("phone", bean.getPhoneNum());
		int i = query.executeUpdate();
		if (i > 0) {
			System.err.println("contect deleted");
		} else {
			System.out.println("someting went wrong");
		}

		begin.commit();
		session.close();

	}

	public void display(ContectBean bean, BufferedReader rd) throws NumberFormatException, IOException {
		SessionFactory factory = FactoryProvider.getFactory();
		Session session = factory.openSession();
		System.out.println("Enter 1 to see your data only");
		System.out.println("Enter 2 to see all data");
		System.out.println("Enter 3 to see your group member");
		byte b = Byte.parseByte(rd.readLine());
		Query query = session.createQuery("from ContectBean");
		List<ContectBean> list = query.list();
		if (b == 1) {
			System.out.println("Enter your phone number:\t");
			bean.setPhoneNum(Long.parseLong(rd.readLine()));
			Query query1 = session.createQuery("from ContectBean where phoneNum=:phone");
			query.setParameter("phone", bean.getPhoneNum());

			List<ContectBean> list1 = query1.list();

			System.out.println(list.get(0));

		} else if (b == 2) {

			for (ContectBean contectBean : list) {
				System.out.println("First Name : " + contectBean.getFirstName() + "\t Last Name : "
						+ contectBean.getLastName() + "\t    Phone Number : " + contectBean.getPhoneNum()
						+ "\t groups : " + contectBean.getGroups());
			}

		} else if (b == 3) {
			System.out.println("Enter your group name");
			bean.setGroup(rd.readLine());
			for (ContectBean contectBean : list) {
				if (contectBean.getGroups().equalsIgnoreCase(bean.getGroups())) {

					Query createQuery = session.createQuery("from ContectBean where groups = :group");
					createQuery.setParameter("group", bean.getGroups());
					List<ContectBean> list2 = createQuery.list();
					System.out.println(list2);
				} else {
					System.out.println("Group dosn't exits");

				}
			}
		} else {
			System.out.println("please enter number only are given above");
		}
	}

	public void update(ContectBean bean, BufferedReader rd, byte b) throws NumberFormatException, IOException {
		Session session = FactoryProvider.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter your Phone Number To Update Any Thing");
		bean.setPhoneNum(Long.parseLong(rd.readLine()));
		Query query = session.createQuery("from ContectBean");
		List<ContectBean> list = query.list();
		boolean phonemtch = false;
		for (ContectBean contectBean : list) {
			if(contectBean.getPhoneNum() == bean.getPhoneNum()) {
				
			phonemtch = true;
				
			System.out.println("Enter 1 to update first Name :\t");
			System.out.println("Enter 2 to update Last Name :\t");
			System.out.println("Enter 3 to update Group :\t");

			b = Byte.parseByte(rd.readLine());
			Query createQuery = null;
			int update = 0;
			switch (b) {
			case 1:
				System.out.println("Enter User First Name :");
				bean.setFirstName(rd.readLine());
				createQuery = session
						.createQuery("update ContectBean set firstName = :first where phoneNum = :phone");
				createQuery.setParameter("first", bean.getFirstName());
				createQuery.setParameter("phone", bean.getPhoneNum());
				update = createQuery.executeUpdate();
				transaction.commit();
				if (update > 0) {
					System.out.println("Updated");
				} else {
					System.out.println("Someting went wrong");
				}
				break;
			case 2:
				System.out.println("Enter User Last Name :");
				bean.setLastName(rd.readLine());
				createQuery = session
						.createQuery("update ContectBean set lastName = :last where phoneNum = :phone");
				createQuery.setParameter("last", bean.getLastName());
				createQuery.setParameter("phone", bean.getPhoneNum());
				update = createQuery.executeUpdate();
				transaction.commit();
				if (update > 0) {
					System.out.println("Updated");
				} else {
					System.out.println("Someting went wrong");
				}
				break;
			case 3:
				System.out.println("Enter User Group :");
				bean.setGroup(rd.readLine());
				createQuery = session.createQuery("update ContectBean set groups = :g where phoneNum = :phone");
				createQuery.setParameter("g", bean.getGroups());
				createQuery.setParameter("phone",bean.getPhoneNum());
				update = createQuery.executeUpdate();
				if (update > 0) {
					System.out.println("Updated");
				} else {
					System.out.println("Someting went wrong");
				}
				break;
			default:
				System.out.println("Enter only given number above");
				break;
			}
		}
			else {
				phonemtch =false;
			}
		}
		if(!phonemtch) {
			System.out.println("please Enter valid Contect number");
		}
		
	}
}