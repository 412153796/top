import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Json {
	public static void main(String[] args) {
		String JsonStr = objectToJsonStr();
		System.out.println(JsonStr);
		User  user= JsonStrToObject(JsonStr);
	}

	// ����תJSON�ַ���
	private static String objectToJsonStr() {
		User user = new User();
		user.setId(1L);
		user.setName("����");
		user.setPwd("123");
		user.setRemark("����");
		JSONArray array = JSONArray.fromObject(user);
		String jsonstr = array.toString();
		return jsonstr;
	}

	//json�ַ���ת����
	private static User JsonStrToObject(String jsonStr) {
		User user = new User();
	    if(jsonStr.indexOf("[") != -1){  
            jsonStr = jsonStr.replace("[", "");  
        }  
        if(jsonStr.indexOf("]") != -1){  
            jsonStr = jsonStr.replace("]", "");  
        }  
		JSONObject obj = new JSONObject().fromObject(jsonStr);//��json�ַ���ת��Ϊjson����  
        User jb = (User)JSONObject.toBean(obj,User.class);
		return user;
	}
}
