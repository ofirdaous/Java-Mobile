package Abstracts;

import java.util.ArrayList;

public interface IRepository<T> {
	public void Create(T model) throws Exception;
	public ArrayList<T> GetAll() throws Exception;
	public T GetByID(int id) throws Exception;
	public void Update(T model) throws Exception;
	public void DeleteByID(int id) throws Exception;
}
