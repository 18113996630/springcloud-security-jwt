package org.hrong.springcloudsecurityjwt.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class ResultVo<T> implements Serializable {
	private static final long serialVersionUID = 633214804597167094L;

	private Integer code;

	private String message;

	private T data;

	public static <T> ResultVo success(T data) {
		ResultVo resultVo = new ResultVo();
		resultVo.setCode(200);
		resultVo.setMessage("success");
		resultVo.setData(data);
		return resultVo;
	}

	public static <T> ResultVo err(Integer code, String message) {
		ResultVo resultVo = new ResultVo();
		resultVo.setCode(code);
		resultVo.setMessage(message);
		return resultVo;
	}
}
