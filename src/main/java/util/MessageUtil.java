package util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class MessageUtil {  

    public static final String RESP_MESSAGE_TYPE_TEXT = "text";  
 
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";  

    public static final String RESP_MESSAGE_TYPE_NEWS = "news";  

    public static final String REQ_MESSAGE_TYPE_TEXT = "text";  
 
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";  

    public static final String REQ_MESSAGE_TYPE_LINK = "link";  
  
 
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";  
  

    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";  

    public static final String REQ_MESSAGE_TYPE_EVENT = "event";  
  

    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  

    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
 
    public static final String EVENT_TYPE_CLICK = "CLICK";  
  
    /** 
     *  
     * @param request 
     * @return 
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {  

        Map<String, String> map = new HashMap<String, String>();  

        InputStream inputStream = request.getInputStream();  

        SAXReader reader = new SAXReader();  
        Document document = reader.read(inputStream);  

        Element root = document.getRootElement();  

        List<Element> elementList = root.elements();  

        for (Element e : elementList)  
            map.put(e.getName(), e.getText());  

        inputStream.close();  
        inputStream = null;  

        return map;  
    }  
}  
