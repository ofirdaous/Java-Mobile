package Abstracts;

import java.util.ArrayList;

public interface IRepository<T> {
	public void create(T model) throws Exception;

	public ArrayList<T> getAll() throws Exception;

	public T getByID(int id) throws Exception;

	public void update(T model) throws Exception;

	public void deleteByID(int id) throws Exception;
}
