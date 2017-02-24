package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.StaticData;
import bean.ssdutInfo;

public class updateSsdutInfo {
	
	
	
	public static void main(String[] args) {
		
		
		updateSsdutInfo ssdut=new updateSsdutInfo();
    	ssdut.updateInfoList();
		
		//System.out.println(gettCurrentIndex());
    	
    	
    	int start = StaticData.list.size() - 1;

		int tag = 0;

		String str = "";

		for (int i = start; i > 0 && tag < 16; i--) {

			// System.out.println(li.get(i).getTitle()+"~"+li.get(i).getUrl());

			try {

				str = str + "<a href=\"" + StaticData.list.get(i).getUrl().trim() + "\">"
						+ StaticData.list.get(i).getTitle().trim() + "</a>" + "\n";

			} catch (Exception e) {

			}

			tag++;

		}
		
		System.out.println(str);
		
	}
	
	
	
	private List<ssdutInfo> li = new ArrayList<ssdutInfo>();
	private boolean tag=false;

	public void updateInfoList() {
		
		StaticData.counter++;

		int currentindex = getCurrentIndex();
		
		System.out.println(currentindex);

		int size = StaticData.list.size();

		int oldIndex = 8582;

		if (size != 0) {
			
			if(StaticData.counter>=10){
				
				oldIndex = StaticData.list.get(0).getId()-5;
				
				StaticData.counter=0;
				
				tag=true;
				
				
			}else{
				
				oldIndex = StaticData.list.get(size - 1).getId();
				
			}		

		}

		oldIndex++;
		
		//System.out.println(oldIndex);

		for (int i = oldIndex; i <= currentindex; i++) {

			if (UrlTest(1122, i) == 1) {

			} else {
				if (UrlTest(1120, i) == 1) {

				} else {
					if (UrlTest(1111, i) == 1) {

					} else {
						if (UrlTest(1110, i) == 1) {

						} else {
							if (UrlTest(1070, i) == 1) {

							} else {
								if (UrlTest(1116, i) == 1) {

								} else {
									if (UrlTest(1206, i) == 1) {

									} else {

										if (UrlTest(1122, i) == 1) {

										} else {
											
											if (UrlTest(1041, i) == 1) {

											} else {

											}

										}

									}
								}
							}
						}
					}
				}
			}

		}
		
		//System.out.println("haha");
		
		if(tag==true){
			
			
			//System.out.println("haha111111111");
			
			StaticData.list.clear();
			
			int leng=li.size();
			
			if(leng>0){
				
				for(int j=0;j<leng;j++){
					
					ssdutInfo info=li.get(j) ;
					
					ssdutInfo infos = new ssdutInfo();
					infos.setId(info.getId());
					infos.setTime("ti");
					infos.setTitle(info.getTitle());
					infos.setUrl(info.getUrl());
					
					StaticData.list.add(infos);
					
				}
				
			}
	
		}
		else{
			
			
			//System.out.println("haha22222222");
			
			int leng=li.size();
			
			
			//System.out.println("haha33333"+leng);
			
			if(leng>0){
				
				for(int j=0;j<leng;j++){
					
					ssdutInfo info=li.get(j) ;
					
					ssdutInfo infos = new ssdutInfo();
					infos.setId(info.getId());
					infos.setTime("ti");
					infos.setTitle(info.getTitle());
					infos.setUrl(info.getUrl());
					
					StaticData.list.add(infos);
					
				}
				
			}

			if (size > 16) {

				int cha = size - 16;
				
				//System.out.println("haha3333333333");

				for (int i = 0; i < cha; i++) {

					StaticData.list.remove(i);

				}

			}
		}
		
		/*System.out.println(StaticData.list.size());

		int cc =StaticData.list.size();
		
		System.out.println("haha3333333333");

		for (int i = 0; i < cc; i++) {

			System.out.println(StaticData.list.get(i));

		}*/

	}

	public int getCurrentIndex() {
		int result = 0;
		try {
			URL url = new URL("http://ssdut.dlut.edu.cn/");
			InputStreamReader reader = new InputStreamReader(url.openStream(),"UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String s = null;
			while ((s = br.readLine()) != null) {
				if (s.contains("<a href")) {
					int x = s.indexOf("<a href");
					String str = s.substring(x);
					String strr = str.substring(9, 13);
					if (strr.equals("info")) {
						strr = str.substring(19, 23);
						int indexCurrent = Integer.parseInt(strr);
						if (indexCurrent > result) {
							result = indexCurrent;
						}
					}
				}
			}
			br.close();
			reader.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String getHref(String str) {
		Pattern pattern = Pattern.compile("<a href=.*</a>");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find())
			return matcher.group(0);
		return null;
	}

	public int UrlTest(int classid, int id) {
		try {
			String url = "http://ssdut.dlut.edu.cn/info/" + classid + "/" + id+ ".htm";
			
			
			System.out.println(url);

			String html = GetSsdut(url);	
			
			String title = getTitle(html);	

			ssdutInfo infos = new ssdutInfo();
			infos.setId(id);
			infos.setTime("ti");
			infos.setTitle(title);
			infos.setUrl(url);

			
			System.out.println(id);
			System.out.println(title);
			System.out.println(url);


			li.add(infos);

		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public String GetSsdut(String ui) throws Exception {
		URL url = new URL(ui);
		InputStreamReader reader = new InputStreamReader(url.openStream(),
				"UTF-8");
		BufferedReader br = new BufferedReader(reader);
		String s = null;
		String l = "";
		while ((s = br.readLine()) != null) {
			s = GetContent(s);
			if (s != null) {
				s.trim();
				l = l + s;
				l.trim();
			}
		}
		br.close();
		reader.close();
		return delHTMLTag(l);
	}

	public String GetContent(String html) {
		// String html = "<ul><li>1.hehe</li><li>2.hi</li><li>3.hei</li></ul>";
		String ss = ">[^<]+<";
		String temp = null;
		Pattern pa = Pattern.compile(ss);
		Matcher ma = null;
		ma = pa.matcher(html);
		String result = null;
		while (ma.find()) {
			temp = ma.group();
			if (temp != null) {
				if (temp.startsWith(">")) {
					temp = temp.substring(1);
				}
				if (temp.endsWith("<")) {
					temp = temp.substring(0, temp.length() - 1);
				}
				if (!temp.equalsIgnoreCase("")) {
					if (result == null) {
						result = temp;
					} else {
						result += "____" + temp;
						// result+=temp;
					}
				}
			}
		}
		return result;
	}

	public String GetLabel(String html) {
		// String html = "<ul><li>1.hehe</li><li>2.hi</li><li>3.hei</li></ul>";
		String ss = "<[^>]+>";
		String temp = null;
		Pattern pa = Pattern.compile(ss);
		Matcher ma = null;
		ma = pa.matcher(html);
		String result = null;
		while (ma.find()) {
			temp = ma.group();
			if (temp != null) {
				if (temp.startsWith(">")) {
					temp = temp.substring(1);
				}
				if (temp.endsWith("<")) {
					temp = temp.substring(0, temp.length() - 1);
				}
				if (!temp.equalsIgnoreCase("")) {
					if (result == null) {
						result = temp;
					} else {
						result += "____" + temp;
						// result+=temp;
					}
				}
			}
		}
		return result;
	}

	public String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // ����script��������ʽ
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // ����style��������ʽ
		String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ
		// String regEx_blank = "\\s*|\t|\r|\n"; // ����ո񣬻س������з��ȵı�ǩ��������ʽ
		String regEx_nbsp = " ";

		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // ����script��ǩ

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // ����style��ǩ

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // ����html��ǩ

		// System.out.println("before  :");
		// System.out.println(htmlStr);
		Pattern p_nbsp = Pattern.compile(regEx_nbsp, Pattern.CASE_INSENSITIVE);
		Matcher m_nbsp = p_nbsp.matcher(htmlStr);
		htmlStr = m_nbsp.replaceAll(""); // ���˿ո��ǩ
		// System.out.println("after  :");
		// System.out.println(htmlStr);

		Pattern p_blank = Pattern.compile(regEx_nbsp, Pattern.CASE_INSENSITIVE);
		Matcher m_blank = p_blank.matcher(htmlStr);
		htmlStr = m_blank.replaceAll(""); // ���˿ո��ǩ

		return htmlStr.trim(); // �����ı��ַ���
	}

	public static String getTitle(String htmlStr) {
		int Xtitle = htmlStr.indexOf("-大连理工大学软件学院(新)_jsq_");// ///////
		String title = htmlStr.substring(0, Xtitle);
		return title;
	}

	public static String getContent(String htmlStr) {
		int Xtitle1 = htmlStr.indexOf("点击：[_____showDyn");
		int Xtitle2 = htmlStr.indexOf("版权所有：大连理工大学软件学院地址：大连经济技术开发区图强");
		String html1 = htmlStr.substring(Xtitle1 + 53, Xtitle2);
		String temp = html1.replaceAll("&nbsp;", "").replaceAll("____", "");
		int x = temp.indexOf("getClickTimes");
		int y = temp.indexOf("attach");
		while (x != -1) {
			String str = temp.substring(0, x) + temp.substring(y + 2);
			temp = str;
			x = temp.indexOf("getClickTimes");
			y = temp.indexOf("attach");
		}
		return temp.replaceAll("【关闭】?", "");
	}

}
