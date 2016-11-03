package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import util.MessageUtil;
import util.StaticData;

public class CoreService {

	public static String processRequest(HttpServletRequest request) {
		String content;
		int flag = -1;
		String city = "";
		String respMessage = null;
		try {

			String respContent = "您输入的内容是：";
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			String msgType = requestMap.get("MsgType");
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = requestMap.get("Content");

				content = requestMap.get("Content");

				if (content == null || content.length() == 0) {
					flag = 10;

				} else {

					if (content.length() >= 2 && content.substring(0, 2).equals("����")) {
						city = content.substring(3);
						respContent = Weather.Getweather(city);
						flag = 1;
					} else {

						if (content.equals("学生周知")) {

							// UpdateSsdut up=new UpdateSsdut();

							// List<ssdutInfo> li=up.getInfoList();

							int start = StaticData.list.size() - 1;

							int tag = 0;

							String str = "";

							for (int i = start; i > 0 && tag < 16; i--) {

								// System.out.println(li.get(i).getTitle()+"~"+li.get(i).getUrl());

								try {

									str = str + "<a href= \"" + StaticData.list.get(i).getUrl().trim() + " \">"
											+ StaticData.list.get(i).getTitle().trim() + "</a>" + "\n";

								} catch (Exception e) {

								}

								tag++;

							}

							respContent = str;

							// respContent="<a href=
							// \""+li.get(0).getUrl().trim()+"
							// \">"+li.get(0).getTitle().trim()+"</a>"+"\n";

							// respContent ="<a href=
							// \"http://ssdut.dlut.edu.cn/info/1206/5941.htm
							// \">"+"У����Ӣ�����ʹ󹤽�̳����ҵ����"+"</a>";

							// String str2 = "name \"is\" wgb";//�ַ����м京��˫����

							flag = 2;

						} else {
							if (content.equals("招募队友")) {

								respContent = "<a href= \"http://vzan.cc/f/s-578278 \">" + "点击进入招募队友论坛" + "</a>";

							} else {

								// respContent =
								// "�������������"+content+"\n"+"���롰����-�������ơ��ɻ��������Ϣ�����磺����-����"+"\n"+"���롰ѧ����֪���ɻ�����µ�ѧԺ���棬"+"\n"+"���롰��ļ���ѡ��ɽ��������ļ��̳��"+"\n"+"��ӭʹ�á�";

								if (content.equals("klaus林清轩")) {

									respContent = "<a href= \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa571e8dc7778540e&redirect_uri=http%3A%2F%2Fm.lqxshop.com%2Frespond%2Fweixinwindowurl&response_type=code&scope=snsapi_base&state=9&connect_redirect=1#wechat_redirect \">"
											+ "Klaus快快抢!!" + "</a>";

								} else {

									if (content.equals("i am damy")) {

										respContent = "<a href= \"http://120.27.117.232/uploadtestexcel/login/login.html \">"
												+ "Are you damy??" + "</a>";

									} else {

										if (content.equals("就业信息")) {

											// respContent ="<a href=
											// \"http://120.27.117.232/uploadtestexcel/login/login.html
											// \">"+"Are you damy??"+"</a>";

											flag = 6;

										} else {

											respContent = "您输入的内容是" + content + "\n" + "输入“天气-城市名称”可获得天气信息，例如：天气-大连"
													+ "\n" + "输入“学生周知”可获得最新的学院公告，" + "\n" + "输入“招募队友”可进入队友招募论坛。" + "\n"
													+ "欢迎使用～";

										}

										// respContent =
										// "�������������"+content+"\n"+"���롰����-�������ơ��ɻ��������Ϣ�����磺����-����"+"\n"+"���롰ѧ����֪���ɻ�����µ�ѧԺ���棬"+"\n"+"���롰��ļ���ѡ��ɽ��������ļ��̳��"+"\n"+"��ӭʹ�á�";

									}

								}

							}

						}

						// respContent = content;

					}

				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					// respContent = "ZYM谢谢您的关注！";
					respContent = "谢谢您的关注！本平台定期发布互联网相关比赛信息，并提供比赛团队招募队友的服务。" + "\n" + "输入“招募队友”可进入队友招募论坛。" + "\n"
							+ "此外，还提供一些生活辅助功能" + "\n" + "输入“天气-城市名称”可获得天气信息，例如：天气-大连" + "\n" + "输入“学生周知”可获得最新的学院公告，"
							+ "\n" + "欢迎使用～";

				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
				}
			}

			if (flag == -1) {
				respMessage = ReturnFuction.getBackError(toUserName, fromUserName, respContent);
				flag = 0;
			}

			if (flag == 1) {
				respMessage = ReturnFuction.getBackMenu(toUserName, fromUserName, respContent, city);
				flag = 0;
			}

			if (flag == 2) {
				respMessage = ReturnFuction.getBackError(toUserName, fromUserName, respContent);
				flag = 0;
			}

			if (flag == 6) {
				respMessage = ReturnFuction.getSsdutEmpMenu(toUserName, fromUserName);
				flag = 0;
			}

			if (flag == 10) {
				respMessage = ReturnFuction.getBackError(toUserName, fromUserName, "哈哈哈～");
				flag = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
}