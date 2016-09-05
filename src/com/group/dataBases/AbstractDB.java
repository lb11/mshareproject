package com.group.dataBases;

import java.util.List;
import java.util.Map;

public interface AbstractDB {
	/**
	 * ������ݡ�
	 * 
	 * @param objects
	 */
	public void add(Object[] objects);

	/**
	 * ɾ���������������ݡ�
	 * 
	 * @param where
	 *            ɾ����������Ϊ��Ӧ�������Զ�������͡�
	 * @param objects
	 *            ��������Ҫ��ֵ
	 */
	public void delete(String where, Object[] objects);

	/**
	 * ��ѯ���ݿ�������ݡ�
	 * 
	 * @param where
	 * @param objects
	 * @return
	 */
	public List<Map<String, String>> quere(String where, String[] objects);

}
