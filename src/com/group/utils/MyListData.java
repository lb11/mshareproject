package com.group.utils;

import java.util.ArrayList;

/**
 * 两层树形结构的数据集合类。
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
	 * 记录表条的是否展开状态。是否是父节点用在父链表里面查找来得到， 是否有孩子节点，查看父节点对应的孩子节点链表的个数是否不为0 来判断。
	 * 
	 * 记得每次跟新数据后，应该准备下输出的result数据的重置。
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
	 * 对父节点的操作。将数据项添加到制定的位置。
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
	 * 对父节点的操作。将数据项添加到数组的最后面。
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
	 * 清空孩子节点的信息。
	 */
	public void clearnChilds() {
		for (ArrayList<String> list : childs) {
			list.clear();
		}
		initialResult();
	}

	/**
	 * 将数据源清空并只是加载父树节点的资源,并按照节点的类型来加载子节点的信息。
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
	 * 返回在父链节点中的位置。
	 * 
	 * @param info
	 * @return
	 */
	public int inParentindexOf(String info) {
		return parent.indexOf(info);
	}

	/**
	 * 获取数据。
	 * 
	 * @return
	 */
	public ArrayList<String> getDatas() {
		return result;
	}

	/**
	 * 获取对应子列的数据源。
	 * 
	 * @param index
	 * @return
	 */
	public ArrayList<String> getWhichChild(int index) {
		return childs.get(index);
	}

	/**
	 * 获取状态来判断是否是展开状态。
	 * 
	 * @param index
	 * @return
	 */
	public Type getItemType(int index) {
		return isOpenlist.get(index);
	}

	/**
	 * 改变父节点对应的Item的展示状态。
	 * 
	 * @param index
	 */
	public void changeItemTypeState(int index) {
		isOpenlist.set(index, (isOpenlist.get(index) == Type.OPEN) ? Type.CLOSE
				: Type.OPEN);
		initialResult();
	}

	/**
	 * 判断父节点是否有孩子节点。
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
