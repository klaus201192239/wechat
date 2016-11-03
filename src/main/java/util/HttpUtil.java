package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {

	public static String sendGet(String url, Map<?, ?> map) {

		if (map == null || map.size() == 0) {

			return sendGet(url);

		} else {

			StringBuilder sb = new StringBuilder();
			sb.append(url);
			sb.append("?");

			for (Entry<?, ?> entry : map.entrySet()) {

				sb.append(entry.getKey().toString());
				sb.append("=");
				sb.append(entry.getValue().toString());
				sb.append("&");

			}

			int len = sb.toString().length();

			return sendGet(sb.toString().substring(0, len - 1));

		}

	}

	private static String sendGet(String urlstr) {

		//System.out.println(urlstr);

		String l = "";
		try {

			URL url = new URL(urlstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			if (conn.getResponseCode() != 200) // 从Internet获取网页,发送请求,将网页以流的形式读回来
			{
				throw new RuntimeException("请求url失败");
			}

			InputStream is = conn.getInputStream();
			InputStreamReader reader = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(reader);
			String s = null;
			while ((s = br.readLine()) != null) {
				if (s != null) {
					s.trim();
					l = l + s;
				}
			}

			br.close();
			reader.close();
			l.trim();
			conn.disconnect();

		} catch (Exception e) {

			l = "error";
		}
		return l;
	}

	
	
}
