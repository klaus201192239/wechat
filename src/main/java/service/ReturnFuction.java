package service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReturnFuction {

	ReturnFuction() {
	}

	public static String a() {
		return "fsd";
	}

	public String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	public String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}

	public boolean CompareStr(String str) {
		char a, b, c, d, e, f;
		int x, y, z, m, n, sum = 0;
		a = str.charAt(0);
		if (!(a == 'a' || a == 'b' || a == 'c')) {
			return false;
		}
		b = str.charAt(1);
		m = b - '0';
		if (!(m == 1 || m == 2 || m == 3 || m == 5)) {
			return false;
		}
		c = str.charAt(2);
		d = str.charAt(3);
		e = str.charAt(4);
		x = c - '0';
		y = d - '0';
		z = e - '0';
		sum = y * 10 + z;
		if (!((x >= 1 && x <= 7) && (sum >= 1 && sum <= 21))) {
			return false;
		}
		f = str.charAt(5);
		n = f - '0';
		if (!(n > 0 && n < 5)) {
			return false;
		}
		return true;
	}

	public static String getBackError(String toName, String FromName, String respContent) {
		String returnStr = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append(respContent);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		Element rootXML = new Element("xml");
		rootXML.addContent(new Element("ToUserName").setText(FromName));
		rootXML.addContent(new Element("FromUserName").setText(toName));
		rootXML.addContent(new Element("CreateTime").setText(times));
		rootXML.addContent(new Element("MsgType").setText("text"));
		rootXML.addContent(new Element("Content").setText(buffer.toString()));
		Document doc = new Document(rootXML);
		XMLOutputter XMLOut = new XMLOutputter();
		returnStr = XMLOut.outputString(doc);
		return returnStr;
	}

	public static String getBackMenu(String toName, String FromName, String respContent, String city) {
		String returnStr = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append(respContent);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		Element rootXML = new Element("xml");
		rootXML.addContent(new Element("ToUserName").setText(FromName));
		rootXML.addContent(new Element("FromUserName").setText(toName));
		rootXML.addContent(new Element("CreateTime").setText(times));
		rootXML.addContent(new Element("MsgType").setText("news"));
		rootXML.addContent(new Element("ArticleCount").setText("2"));
		Element Articles = new Element("Articles");
		Element item = new Element("item");
		item.addContent(new Element("Title").setText("����" + city + "����"));
		item.addContent(new Element("Description").setText(respContent));
		item.addContent(new Element("PicUrl").setText("http://pic.qiantucdn.com/58pic/14/12/04/58k58PICPwX_1024.jpg"));
		// item.addContent(new
		// Element("Url").setText("http://www.zhihu.com/question/26639110"));

		Articles.addContent(item);
		rootXML.addContent(Articles);
		Document doc = new Document(rootXML);
		XMLOutputter XMLOut = new XMLOutputter();
		returnStr = XMLOut.outputString(doc);
		return returnStr;
	}

	private static String getJsonTemp() {

		try {
			
			InputStreamReader isr = new InputStreamReader(new FileInputStream("C:/file/empinfolist.txt"),"utf-8"); 
			
			BufferedReader bf = new BufferedReader(isr);

			String content = "";
			
			StringBuilder sb = new StringBuilder();

			while (content != null) {
				
				content = bf.readLine();

				if (content == null) {
					break;
				}

				sb.append(content.trim());
			}

			bf.close();
			
			return sb.toString();

		} catch (Exception e) {

			return "error";

		}

	}

	public static void main(String[] args) {

		

	/*	String jsonList =  getJsonTemp();

		System.out.println(jsonList);

		JSONArray array = new JSONArray(jsonList);

		for (int i = 0; i < array.length(); i++) {

			JSONObject obj = array.getJSONObject(i);

			System.out.println(obj.get("title"));

		}*/
		
	}

	public static String getSsdutEmpMenu(String toName, String FromName) {

		/*String url = "http://localhost:8080/empforecast/api/rest/ssdut/empinfolist";

		Map<String, Integer> map = new HashMap<String, Integer>();

		map.put("page", 1);

		String jsonList = HttpUtil.sendGet(url, map);*/		
		
		String temp="http://10.2.207.45:8080/empforecast/api/rest/ssdut/empinfodetail?id=";

		String returnStr = "";
		// StringBuffer buffer = new StringBuffer();
		// buffer.append(respContent);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		Element rootXML = new Element("xml");
		rootXML.addContent(new Element("ToUserName").setText(FromName));
		rootXML.addContent(new Element("FromUserName").setText(toName));
		rootXML.addContent(new Element("CreateTime").setText(times));
		rootXML.addContent(new Element("MsgType").setText("news"));
		rootXML.addContent(new Element("ArticleCount").setText("7"));

		Element Articles = new Element("Articles");

		
		String jsonList =getJsonTemp();

		JSONArray array = new JSONArray(jsonList);

		for (int i = 0; i < array.length(); i++) {

			JSONObject obj = array.getJSONObject(i);

			Element item = new Element("item");
			item.addContent(new Element("Title").setText(obj.getString("title")));
			item.addContent(new Element("Description").setText("haha"));
			item.addContent(new Element("PicUrl").setText("http://pic.qiantucdn.com/58pic/14/12/04/58k58PICPwX_1024.jpg"));
			
			item.addContent(new Element("Url").setText(temp+obj.getString("htmlurl").substring(35, 39)));

			Articles.addContent(item);

		}
		
		
		Element item = new Element("item");
		item.addContent(new Element("Title").setText("点击加载更多"));
		item.addContent(new Element("Description").setText("haha"));
		item.addContent(new Element("PicUrl").setText("http://pic.qiantucdn.com/58pic/14/12/04/58k58PICPwX_1024.jpg"));
		item.addContent(new Element("Url").setText("http://10.2.207.45:8080/empforecast/ssdutemp/ssdutempinfolist.jsp?page=1"));

		Articles.addContent(item);
		
	
		rootXML.addContent(Articles);
		Document doc = new Document(rootXML);
		XMLOutputter XMLOut = new XMLOutputter();
		returnStr = XMLOut.outputString(doc);
		return returnStr;
	}

}