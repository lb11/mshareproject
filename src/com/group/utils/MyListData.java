package com.group.utils;

import java.util.ArrayList;

/**
 * �������νṹ�����ݼ����ࡣ
 * 
 * @author Administrator
 * 
 */
public class MyListData {
	private ArrayList<String> parent;
	private ArrayList<ArrayList<String>> childs;
	private ArrayList<String> result;
	private ArrayList<Type> isOpenlist;

	/**
	 * ��¼�������Ƿ�չ��״̬���Ƿ��Ǹ��ڵ����ڸ���������������õ��� �Ƿ��к��ӽڵ㣬�鿴���ڵ��Ӧ�ĺ��ӽڵ�����ĸ����Ƿ�Ϊ0 ���жϡ�
	 * 
	 * �ǵ�ÿ�θ������ݺ�Ӧ��׼���������result���ݵ����á�
	 * 
	 * @author Administrator
	 * 
	 */
	public enum Type {
		OPEN, CLOSE
	}

	public MyListData() {
		parent = new ArrayList<String>();
		childs = new ArrayList<ArrayList<String>>();
		result = new ArrayList<String>();
		isOpenlist = new ArrayList<MyListData.Type>();
	}

	public void add(int postion, String data, int parentindex) {
		if (parentindex >= 0 && parentindex < parent.size()) {
			childs.get(parentindex).add(postion, data);
			isOpenlist.set(parentindex, Type.CLOSE);
			initialResult();
		}
	}

	public void add(String data, int parentindex) {
		if (parentindex >= 0 && parentindex < parent.size()) {
			childs.get(parentindex).add(data);
			isOpenlist.set(parentindex, Type.CLOSE);
			initialResult();
		}
	}

	public int getParentindex(String data) {
		int index = -1;
		for (int i = 0; i < childs.size(); i++) {
			if (childs.get(i).contains(data)) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * �Ը��ڵ�Ĳ���������������ӵ��ƶ���λ�á�
	 * 
	 * @param position
	 * @param data
	 */
	public void add(int position, String data) {
		parent.add(position, data);
		isOpenlist.add(position, Type.CLOSE);
		childs.add(position, new ArrayList<String>());
		initialResult();
	}

	/**
	 * �Ը��ڵ�Ĳ���������������ӵ����������档
	 * 
	 * @param data
	 */
	public void add(String data) {
		parent.add(data);
		isOpenlist.add(Type.CLOSE);
		childs.add(new ArrayList<String>());
		initialResult();
	}

	/**
	 * ��պ��ӽڵ����Ϣ��
	 */
	public void clearnChilds() {
		for (ArrayList<String> list : childs) {
			list.clear();
		}
		initialResult();
	}

	/**
	 * ������Դ��ղ�ֻ�Ǽ��ظ����ڵ����Դ,�����սڵ�������������ӽڵ����Ϣ��
	 */
	private void initialResult() {
		result.clear();
		result.addAll(parent);
		int index = 0;
		for (int i = 0; i < parent.size(); i++) {
			index = result.indexOf(parent.get(i));
			index++;
			if (Type.OPEN == isOpenlist.get(i)) {
				for (String info : childs.get(i)) {
					result.add(index, info);
					index++;
				}
			}
		}
	}

	/**
	 * �����ڸ����ڵ��е�λ�á�
	 * 
	 * @param info
	 * @return
	 */
	public int inParentindexOf(String info) {
		return parent.indexOf(info);
	}

	/**
	 * ��ȡ���ݡ�
	 * 
	 * @return
	 */
	public ArrayList<String> getDatas() {
		return result;
	}

	/**
	 * ��ȡ��Ӧ���е�����Դ��
	 * 
	 * @param index
	 * @return
	 */
	public ArrayList<String> getWhichChild(int index) {
		return childs.get(index);
	}

	/**
	 * ��ȡ״̬���ж��Ƿ���չ��״̬��
	 * 
	 * @param index
	 * @return
	 */
	public Type getItemType(int index) {
		return isOpenlist.get(index);
	}

	/**
	 * �ı丸�ڵ��Ӧ��Item��չʾ״̬��
	 * 
	 * @param index
	 */
	public void changeItemTypeState(int index) {
		isOpenlist.set(index, (isOpenlist.get(index) == Type.OPEN) ? Type.CLOSE
				: Type.OPEN);
		initialResult();
	}

	/**
	 * �жϸ��ڵ��Ƿ��к��ӽڵ㡣
	 * 
	 * @param index
	 * @return
	 */
	public boolean haveChild(int index) {
		boolean flag = false;
		if (childs.get(index).size() > 0) {
			flag = true;
		}
		return flag;
	}

	public int size() {
		return result.size();
	}

}
