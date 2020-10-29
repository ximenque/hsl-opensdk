package com.hsl.sdkdemo.demo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hsl.api.model.SignModel;
import com.hsl.api.utils.HttpUtils;
import com.hsl.api.utils.SignUtil;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

import static com.hsl.api.utils.HttpUtils.getGzip;

/**
 * 书亦open-api接口请求示例
 */

public class OpenDemoMap {
    //开发者appsecret
    private static final String APP_SECRET = "gRauI3jtYecJtFxs";
    //测试环境
    private static final String ORDER_URL = "https://newapi.hesiling.com";


    public static void main(String[] args) {
        try {

            String host = ORDER_URL;
            String path = "/api/open/v2/findOperationByCondition";
            String method = "POST";
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("timestamp", "1596442941470");
            headers.put("content-type","application/json");

            headers.put("accept-encoding","gzip,deflate");

            Map<String, String> querys = new HashMap<String, String>();
            Map<String, Object> bodys = new HashMap<String, Object>();
            bodys.put("appId", "18266975001");
//            bodys.put("eventName",12313);

            //参与签名字段集合




            //调用SDK方法getMapSign，生成公共参数signature的值，generatorStr：签名字段拼接，generatorSig：签名加密结果
            SignModel signModel = SignUtil.getMapSign(bodys, APP_SECRET);

            //签名拼接字符串
            System.out.println("generatorStr : " + signModel.getGeneratorStr());

            //签名加密生成字符串
            System.out.println("generatorSig : " + signModel.getGeneratorSig());

            headers.put("access-token",signModel.getGeneratorSig());




            String result = "";
            try {
                HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, JSONObject.toJSONString(bodys, SerializerFeature.WriteMapNullValue));
//            HttpEntity entity = response.getEntity();
//            String responseContent = EntityUtils.toString(entity, "UTF-8");
                result = getGzip(response);
                System.out.println(result);

            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = JSONObject.parseObject(result);
            System.out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
