package com.shabbir.dao;

import java.io.BufferedReader;
import java.io.IOException;

import com.shabbir.bean.ContectBean;

public interface Dao {

	public abstract void display(ContectBean bean, BufferedReader rd) throws NumberFormatException, IOException;

	public abstract void register(ContectBean bean, BufferedReader rd) throws IOException;

	public abstract void delete(ContectBean bean, BufferedReader rd) throws NumberFormatException, IOException;

	public abstract void update(ContectBean bean, BufferedReader rd, byte b) throws NumberFormatException, IOException;
}
