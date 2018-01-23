
package com.linkGap.projectManage.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linkGap.projectManage.model.ReturnResultUtil;

/**
 * @author renhengli
 * @date 2018年1月9日
 * @version 1.0
 */
@RestController
@RequestMapping(value = "demo")
public class DemoController {

	@GetMapping(value = "firstDemo", produces = "application/json;charset=UTF-8")
	public ReturnResultUtil firstdemo(String a) {
		List<String> list = new LinkedList<String>();
		list.add("zhangsan");
		list.add("lisi");
		list.add("zhaowu");
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("tokens", "linkgap_projectManage");
		return new ReturnResultUtil("00", "请求成功", list, extras);
	}

	@PostMapping(value = "secondDemo", consumes = "application/json;charset=UTF-8")
	public ReturnResultUtil seconddemo(String a, String b) {
		List<String> list = new LinkedList<String>();
		list.add(a);
		list.add(b);
		return new ReturnResultUtil("00", "请求成功", list);
	}

	@GetMapping("/403")
	public ReturnResultUtil error403() {
		return new ReturnResultUtil("01", "has no permission");
	}

}
