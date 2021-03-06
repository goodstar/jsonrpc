package com.jsonrpc;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jsonrpc.core.RunAnnotation;

@SuppressWarnings("serial")
public class Example implements Serializable {

	@RunAnnotation(value = "com.star.json.rpc")
	public String sayString(String name) {
		// if ("Huaxu".equals(name)) throw new RuntimeException("dsa");
		return "Hello " + name + " !";
	}

	@RunAnnotation(value="auth")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List sayList(List list) {
		list.add(new Integer(6));
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map sayMap(Map map) {
		map.put("age", "23");
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Set saySet(Set set) {
		set.add("sex");
		return set;
	}

	public User sayUser(User user) {
		user.setAge(25);
		return user;
	}

}
