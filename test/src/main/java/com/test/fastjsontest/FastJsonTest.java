package com.test.fastjsontest;

import com.alibaba.fastjson.JSON;

public class FastJsonTest {

	/**
	 * 序列化，由对象生成json
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		encode();

		decode();

	}

	/**
	 * 通工作类构造json串
	 */
	public static void encode() {
		Group group = new Group();

		group.setId(0L);
		group.setName("admin");

		User guestUser = new User();
		guestUser.setId(2L);
		guestUser.setName("guest");

		User rootUser = new User();

		rootUser.setId(3L);
		rootUser.setName("root");

		group.addUser(guestUser);
		group.addUser(rootUser);

		String jsonString = JSON.toJSONString(group);
		System.out.println(jsonString);
	}

	/**
	 * 通过json串，产生类
	 */
	private static void decode() {
		// TODO Auto-generated method stub
		String jsonString = "{\"id\":0,\"name\":\"admin\",\"users\":[{\"id\":2,\"name\":\"guest\"},{\"id\":3,\"name\":\"root\"}]}";
		Group group = JSON.parseObject(jsonString, Group.class);
		System.out.println(group.getName());
	}

}
