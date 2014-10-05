//The javascript file of the JSON-RPC-Java example application.
var jsonrpc = null;
// 初始化JSONRpcClient对象
function onLoad() {
	jsonrpc = new JSONRpcClient("JSON-RPC");
}
// 提交普通的字符串对象
function sayString() {
	var who = document.getElementById("who");
	var result = jsonrpc.example.sayString(cbString, who.value);
}
function cbString(result, exception) {
	if (exception == null) {
		document.getElementById("say").innerHTML = "<em>操作提示：提交一个字符串并返回.</em><h2>"
				+ result + "</h2><hr/>";
	} else {
		alert(exception.message);
	}
}

// 提交一个list对象并且将其返回
function sayList() {
	var list = {
		"javaClass" : "java.util.ArrayList",
		"list" : [ 1, 2, 3 ]
	};
	jsonrpc.example.sayList(cbList, list);
}
function cbList(result, exception) {
	if (exception == null) {
		var list = result.list;
		var str = "<em>操作提示：提交一个List并返回.</em>";
		for ( var value in list) {
			str += "<h2>list[" + value + "]=" + list[value] + "</h2><hr/>";
		}
		document.getElementById("say").innerHTML = str;
		// "list"可以直接用下面的语句输出
		// document.getElementById("say").innerHTML = "<h2>" + list + "</h2>";
	} else {
		alert(exception.message);
	}
}

// 提交一个map对象并且将其返回
function sayMap() {
	var map = {
		"javaClass" : "java.util.HashMap",
		"map" : {
			"name" : "Huaxu",
			"sex" : "女"
		}
	};
	jsonrpc.example.sayMap(cbMap, map);
}
function cbMap(result, exception) {
	if (exception == null) {
		var map = result.map;
		var str = "<em>操作提示：提交一个Map并返回.</em>";
		for ( var key in map) {
			str += "<h2>" + key + ":" + map[key] + "</h2><hr/>";
		}
		document.getElementById("say").innerHTML = str;
	} else {
		alert(exception.message);
	}
}

// 提交一个set对象并且将其返回
function saySet() {
	// set属性是一个数组对象,每个数组元素就是set里的一个元素.
	var set = {
		"javaClass" : "java.util.HashSet",
		"set" : {
			"name" : "name"
		}
	};
	jsonrpc.example.saySet(cbSet, set);
}

function cbSet(result, exception) {
	if (exception == null) {
		var set = result.set;
		var str = "<em>操作提示：提交一个Set并返回.</em>";
		for ( var value in set) {
			str += "<h2>" + value + "</h2><hr/>";
		}
		document.getElementById("say").innerHTML = str;
	} else {
		alert(exception.message);
	}
}

// 提交一个自定义的User对象并且将其返回
function sayUser() {
	// 每个JSON对象属性对应一个User对象属性.
	var user = {
		"javaClass" : "com.jsonrpc.User",
		"name" : "Huaxu",
		"age" : 23
	};
	jsonrpc.example.sayUser(cbUser, user);
}
function cbUser(user, exception) {
	if (exception == null) {
		var str = "<em>操作提示：提交一个自定义的User对象并返回.</em><h2>name:" + user.name
				+ "</h2><hr/>" + "<h2>age:" + user.age + "</h2><hr/>";
		document.getElementById("say").innerHTML = str;
	} else {
		alert(exception.message);
	}
}
