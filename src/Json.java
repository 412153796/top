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

	// 对象转JSON字符串
	private static String objectToJsonStr() {
		User user = new User();
		user.setId(1L);
		user.setName("王科");
		user.setPwd("123");
		user.setRemark("测试");
		JSONArray array = JSONArray.fromObject(user);
		String jsonstr = array.toString();
		return jsonstr;
	}

	//json字符串转对象
	private static User JsonStrToObject(String jsonStr) {
		User user = new User();
	    if(jsonStr.indexOf("[") != -1){  
            jsonStr = jsonStr.replace("[", "");  
        }  
        if(jsonStr.indexOf("]") != -1){  
            jsonStr = jsonStr.replace("]", "");  
        }  
		JSONObject obj = new JSONObject().fromObject(jsonStr);//将json字符串转换为json对象  
        User jb = (User)JSONObject.toBean(obj,User.class);
		return user;
	}
}
