package org.hrong.springcloudsecurityjwt.service;

import org.hrong.springcloudsecurityjwt.model.User;
import org.hrong.springcloudsecurityjwt.vo.ResultVo;

import java.util.List;

public interface IUserService {
	User findUserByUserName(String userName);

	ResultVo login(User user);

	User saveUser(User user);

	List<User> findAllUser();
}
