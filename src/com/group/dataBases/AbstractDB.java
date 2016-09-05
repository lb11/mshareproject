package com.group.dataBases;

import java.util.List;
import java.util.Map;

public interface AbstractDB {
	/**
	 * 添加数据。
	 * 
	 * @param objects
	 */
	public void add(Object[] objects);

	/**
	 * 删除符合条件的数据。
	 * 
	 * @param where
	 *            删除的条件。为对应类里面自定义的类型。
	 * @param objects
	 *            条件所需要的值
	 */
	public void delete(String where, Object[] objects);

	/**
	 * 查询数据库里的数据。
	 * 
	 * @param where
	 * @param objects
	 * @return
	 */
	public List<Map<String, String>> quere(String where, String[] objects);

}
