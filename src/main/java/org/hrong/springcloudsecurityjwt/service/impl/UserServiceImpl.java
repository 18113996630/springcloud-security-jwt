package org.hrong.springcloudsecurityjwt.service.impl;

import org.hrong.springcloudsecurityjwt.model.User;
import org.hrong.springcloudsecurityjwt.repository.IUserRepository;
import org.hrong.springcloudsecurityjwt.service.IUserService;
import org.hrong.springcloudsecurityjwt.utils.EncryptionOrDecryptPwd;
import org.hrong.springcloudsecurityjwt.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public User findUserByUserName(String userName) {
		return userRepository.findUserByUserName(userName);
	}

	@Override
	public ResultVo login(User user) {
		String pwd = EncryptionOrDecryptPwd.encryptionPwd(user.getPassword());
		User res = userRepository.findUserByUserNameAndPassword(user.getUserName(), pwd);
		boolean flag = ObjectUtils.isEmpty(res);
		boolean result = !flag && res.getPassword().equals(pwd);
		ResultVo resultVo = !result?ResultVo.err(500,"fail"):ResultVo.success("success");
		return resultVo;
	}

	@Transactional
	@Override
	public User saveUser(User user) {
		user.setPassword(EncryptionOrDecryptPwd.encryptionPwd(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public List<User> findAllUser() {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			user.setPassword(null);
		}
		return users;
	}
}
