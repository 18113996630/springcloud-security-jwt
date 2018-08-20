package org.hrong.springcloudsecurityjwt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hrong.springcloudsecurityjwt.annotations.NotAuth;
import org.hrong.springcloudsecurityjwt.model.User;
import org.hrong.springcloudsecurityjwt.service.IUserService;
import org.hrong.springcloudsecurityjwt.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户controller")
@RestController
public class UserController {

	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userServiceImpl;

	@ApiOperation(value = "登陆方法",response = ResultVo.class)
	@ApiParam(name = "user",required = true)
	@NotAuth
	@PostMapping(value = "/login")
	public ResultVo login(@RequestBody User user) {
		try {
			return userServiceImpl.login(user);
		} catch (Exception e) {
			logger.info("login:{  }" + e.getMessage());
			return ResultVo.err(500, "faild");
		}
	}

	@ApiOperation(value = "查询所有用户")
	@GetMapping(value = "/users")
	public ResultVo findAllUser() {
		return ResultVo.success(userServiceImpl.findAllUser());
	}

	@ApiOperation(value = "注册")
	@NotAuth
	@PostMapping(value = "/regist")
	public ResultVo regist(@RequestBody User user) {
		try {
			User userByNameAndPwd = userServiceImpl.saveUser(user);
			return ResultVo.success(userByNameAndPwd);
		} catch (Exception e) {
			logger.info("login:{  }" + e.getMessage());
			return ResultVo.err(500, "faild");
		}
	}

	@NotAuth
	@PostMapping(value = "/to_login")
	public ResultVo toLogin() {
		return ResultVo.err(500, "先登陆");
	}
}
