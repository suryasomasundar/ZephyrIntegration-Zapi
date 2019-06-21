package zapi.Utils;

import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;

public class ConnectionUtils {
	static Logger logger = Logger.getLogger(ConnectionUtils.class.getName());

	public static String getEncodedAuthKey() {
		String username = "ssuryanarayanan";
		String password = "SaranamIyappa@18";
		String combo = username + ":" + password;
		byte[] base64EncodedUsername = Base64.encodeBase64(combo.getBytes());
		String temp = new String(base64EncodedUsername);
		String encodedUsername = "Basic " + temp;
		return encodedUsername;
	}

}
