package goods.utils;

import java.util.HashMap;
import java.util.Map;

public class HTMLUtils {

	public static Map<String, String> formatAuthority(String sale, String account) {

		Map<String, String> map = new HashMap<>();

		if(sale.equals("1")){
			if(account.equals("1")) {
				map.put("authority"," and authority = 11");

			}else if(account.equals("0")) {
				map.put("authority"," and authority = 1");

			}else {
				map.put("authority1"," and authority = 1");
				map.put("authority3"," or authority = 11");

			}

		}else if(sale.equals("0")) {
			if(account.equals("1")) {
				map.put("authority"," and authority = 10");


			}else if(account.equals("0")) {
				map.put("authority"," and authority = 0");

			}else {
				map.put("authority1"," and authority = 0");
				map.put("authority2"," or authority = 10");

			}

		}else{
			if(account.equals("1")) {
				map.put("authority1"," and authority = 010");
				map.put("authority2"," or authority = 011");

			}else if(account.equals("0")) {
				map.put("authority1"," and authority = 00");
				map.put("authority2"," or authority = 01");

			}else {
				map.put("authority","");
			}

		}

		return map;

	}

}
