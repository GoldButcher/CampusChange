package com.campus.service;

import java.io.Serializable;

public interface IBaseService<T,PK extends Serializable>{
	public T get(Integer id);
	public T get(String propertyName,Object value);
	public T load(PK id);
	public PK save(T entity);
	public void update(T entity);    
	public void delete(PK id);
	public void delete(T entity);
	public Long getTotalCount();
}
