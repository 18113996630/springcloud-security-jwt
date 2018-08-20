package org.hrong.springcloudsecurityjwt.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.hrong.springcloudsecurityjwt.annotations.NotAuth;
import org.hrong.springcloudsecurityjwt.exception.TokenException;
import org.hrong.springcloudsecurityjwt.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.util.Base64Utils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Configuration
public class JwtFilter implements HandlerInterceptor {


	public static final String KEY = "hello";

	private Logger logger = LoggerFactory.getLogger(JwtFilter.class);
	@Autowired
	private IUserService userServiceImpl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			NotAuth notAuth = handlerMethod.getMethodAnnotation(NotAuth.class);
			if (notAuth != null) {
				return true;
			}
			try {
				String url = handlerMethod.getBeanType().getName()+"."+ handlerMethod.getMethod().getName();
				//解密jwt
				String token = request.getHeader("Authorization");
				if (token != null) {
					String user = Jwts.parser()
							.setSigningKey(Base64Utils.encodeToString(KEY.getBytes()))
							.requireAudience("audience")
							.requireIssuer("issuer")
							.parseClaimsJws(token.replace("Bearer ", ""))
							.getBody()
							.getSubject();
					logger.info("这是user：===========》"+user);
					return true;
				}
				logger.info(url + "被拦截，请先登陆");
				response.setStatus(403);
				String requestType = request.getHeader("X-Requested-With");// ajax请求
				if ("XMLHttpRequest".equals(requestType)) {
					response.setContentType("text/html;charset=gb2312");
					PrintWriter out = response.getWriter();
					out.println("地址:" + url + "无权限访问，请登录后重试");
				} else {
					response.sendRedirect(request.getContextPath() + "/to_login");
				}
				return false;
			} catch (ExpiredJwtException e) {
				logger.error("Token已过期: {} " + e);
				throw new TokenException("Token已过期");
			} catch (UnsupportedJwtException e) {
				logger.error("Token格式错误: {} " + e);
				throw new TokenException("Token格式错误");
			} catch (MalformedJwtException e) {
				logger.error("Token没有被正确构造: {} " + e);
				throw new TokenException("Token没有被正确构造");
			} catch (SignatureException e) {
				logger.error("签名失败: {} " + e);
				throw new TokenException("签名失败");
			} catch (IllegalArgumentException e) {
				logger.error("非法参数异常: {} " + e);
				throw new TokenException("非法参数异常");
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

	}

}
