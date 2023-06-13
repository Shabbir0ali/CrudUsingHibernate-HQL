package com.shabbir.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.shabbir.bean.ContectBean;
import com.shabbir.dao.Dao;
import com.shabbir.dao.DaoImpl;
import com.shabbir.helper.FactoryProvider;

public class Controller {
	public static void main(String[] args) {
		try {
			ContectBean bean = new ContectBean();
			Dao daoImpl = new DaoImpl();
			BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

			boolean flag = true;
			while (flag) {
				System.out.println("Enter 1 to REGISTER:\t");
				System.out.println("Enter 2 to DELETE:\t");
				System.out.println("Enter 3 to UPDATE:\t");
				System.out.println("Enter 4 to SEE CONTECT DETAIL:\t");
				System.out.println("Enter 5 to EXIT:\t");
				byte b;

				b = Byte.parseByte(rd.readLine());

				switch (b) {
				case 1:
					daoImpl.register(bean, rd);
					break;
				case 2:
					daoImpl.delete(bean, rd);
					break;
				case 3:
					daoImpl.update(bean, rd, b);
					break;
				case 4:
					daoImpl.display(bean, rd);
					break;
				case 5:
					FactoryProvider.closeFactory();
					flag = false;
					break;
				default:
					System.out.println("Enter only number are given above");
					break;
				}
			}

		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
