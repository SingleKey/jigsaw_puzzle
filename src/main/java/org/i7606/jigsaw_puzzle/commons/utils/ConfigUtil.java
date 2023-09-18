package org.i7606.jigsaw_puzzle.commons.utils;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

/**
 * @Author SingleKey
 * @Date 2023/9/17 14:57
 */
public class ConfigUtil {

    public static HashMap<String, Object> getConfig(String levelName) {
        URL url = UrlUtil.getURL(levelName + "/config.json");
        if (url == null) {
            throw new RuntimeException("不存在配置文件：" + levelName + "/config.json");
        }

        HashMap<String, Object> config = new HashMap<>();

        File jsonFile = new File(url.getFile().substring(1));
        String jsonString = FileUtil.readUtf8String(jsonFile);
        JSONObject jsonObject = (JSONObject) JSON.parse(jsonString);

        int num = jsonObject.getIntValue("num");
        String deletion = jsonObject.getString("deletion");
        String name = jsonObject.getString("name");
        JSONArray answerObjs = jsonObject.getJSONArray("answer");
        String[] answer = new String[answerObjs.size()];
        for (int i = 0; i < answerObjs.size(); i++) {
            answer[i] = answerObjs.getString(i);
        }

        config.put("num", num);
        config.put("name", name);
        config.put("deletion", deletion);
        config.put("answer", answer);

        return config;
    }
}
