//package org.hrong.springcloudsecurityjwt.config;
//
//import org.hrong.springcloudsecurityjwt.annotations.SetNull;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.lang.Nullable;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ResponseConfig implements ResponseBodyAdvice {
//	@Override
//	public boolean supports(MethodParameter returnType, Class converterType) {
//		return true;
//	}
//
//	@Nullable
//	@Override
//	public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//		//判断返回的对象是单个对象，还是list，活着是map
//		if(o==null){
//			return null;
//		}
//		SetNull methodAnnotation = methodParameter.getMethodAnnotation(SetNull.class);
//		if (methodAnnotation != null) {
//
//		}
//
//		Object retObj = null;
//		if (o instanceof List){
//			//List
//			List list = (List)o;
//			retObj = handleList(list);
//		}else{
//			//Single Object
//			retObj = handleSingleObject(o);
//		}
//		return retObj;
//	}
//
//	/**
//	 * 处理返回值是单个enity对象
//	 *
//	 * @param o
//	 * @return
//	 */
//	private Object handleSingleObject(Object o){
//		Map<String,Object> map = new HashMap<String, Object>();
//
//		Field[] fields = o.getClass().getDeclaredFields();
//		for (Field field:fields){
//			//如果未配置表示全部的都返回
//			if(includes.length==0 && excludes.length==0){
//				String newVal = getNewVal(o, field);
//				map.put(field.getName(), newVal);
//			}else if(includes.length>0){
//				//有限考虑包含字段
//				if(Helper.isStringInArray(field.getName(), includes)){
//					String newVal = getNewVal(o, field);
//					map.put(field.getName(), newVal);
//				}
//			}else{
//				//去除字段
//				if(excludes.length>0){
//					if(!Helper.isStringInArray(field.getName(), excludes)){
//						String newVal = getNewVal(o, field);
//						map.put(field.getName(), newVal);
//					}
//				}
//			}
//
//		}
//		return map;
//	}
//
//	/**
//	 * 处理返回值是列表
//	 *
//	 * @param list
//	 * @return
//	 */
//	private List handleList(List list){
//		List retList = new ArrayList();
//		for (Object o:list){
//			Map map = (Map) handleSingleObject(o);
//			retList.add(map);
//		}
//		return retList;
//	}
//}
