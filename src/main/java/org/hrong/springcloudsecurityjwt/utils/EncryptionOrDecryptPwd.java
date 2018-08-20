package org.hrong.springcloudsecurityjwt.utils;

import org.hrong.springcloudsecurityjwt.exception.EncryptionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptionOrDecryptPwd {

	private static final String SALT = "security";
	private static Logger logger = LoggerFactory.getLogger(EncryptionOrDecryptPwd.class);

	public static String encryptionPwd(String pwd) {
		try {
			byte[] encrypt = AES.encrypt(pwd, SALT);
			String res = new String(encrypt);
			return res;
		} catch (Exception e) {
			logger.info("加密失败：{  }"+e.getMessage());
			throw new EncryptionException("加密失败");
		}
	}
	public static String decryptPwd(String pwd) {
		try {
			byte[] decrypt = AES.decrypt(pwd.getBytes(), SALT);
			String res = new String(decrypt);
			return res;
		} catch (Exception e) {
			logger.info("解密失败：{  }"+e.getMessage());
			throw new EncryptionException("解密失败");
		}
	}


}
