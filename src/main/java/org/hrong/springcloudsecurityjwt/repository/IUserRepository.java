package org.hrong.springcloudsecurityjwt.repository;

import org.hrong.springcloudsecurityjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	User findUserByUserName(String userName);

	User findUserByUserNameAndPassword(String userName, String password);
}
