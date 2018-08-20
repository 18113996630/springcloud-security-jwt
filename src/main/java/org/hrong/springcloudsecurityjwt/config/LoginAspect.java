package org.hrong.springcloudsecurityjwt.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hrong.springcloudsecurityjwt.model.User;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Aspect
@Component
public class LoginAspect {

	private static final String LOG_URI = "/login";
	public static final String KEY = "hello";

	@Pointcut(value = "execution(* *org.hrong.springcloudsecurityjwt.controller.UserController.login(..)))")
	public void pointcutExpression() {
	}

	@AfterReturning(pointcut = "pointcutExpression()", returning = "res")
	public void afterReturningAdvice(JoinPoint jp, Object res) {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		Object[] args = jp.getArgs();
		User user = (User) args[0];
		//如果是成功登陆，则生成jwt
		JwtBuilder jwtBuilder = Jwts.builder()
				.setSubject(String.valueOf(user.getId()))
				.setAudience("audience")
				.setExpiration(getExpiration())
				.setIssuer("issuer")
				.signWith(SignatureAlgorithm.HS512, Base64Utils.encodeToString(KEY.getBytes()));
		String token = jwtBuilder.compact();
		response.addHeader("Authorization", "Bearer " + token);
	}

	private Date getExpiration() {
		Long time = 24 * 60 * 60 * 1000L;
		Date date = new Date(new Date().getTime() + time);
		return date;
	}
}
