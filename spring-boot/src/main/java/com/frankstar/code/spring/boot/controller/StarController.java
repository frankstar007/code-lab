package com.frankstar.code.spring.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :  frankstar
 * @AddTime :  2020/6/29
 * @EMail :  yehongxing@meituan.com
 * @Project :  code-lab
 * @Desc :
 */
@RestController
public class StarController {

	@GetMapping("/star")
	public String star() {
		return "frank star!";
	}
}
