package org.i7606.jigsaw_puzzle.commons.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.stream.StreamUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author SingleKey
 * @Date 2023/9/17 14:57
 */
public class ConfigUtil {

    public static HashMap<String, Object> getConfig(String levelName) {

        String jsonString = readJsonObject(levelName + "/config.json");
        JSONObject jsonObject = (JSONObject) JSON.parse(jsonString);

        int num = jsonObject.getIntValue("num");
        String deletion = jsonObject.getString("deletion");
        String name = jsonObject.getString("name");
        String reference = jsonObject.getString("reference");
        JSONArray answerObjs = jsonObject.getJSONArray("answer");
        String[] answer = new String[answerObjs.size()];
        for (int i = 0; i < answerObjs.size(); i++) {
            answer[i] = answerObjs.getString(i);
        }
        HashMap<String, Object> config = new HashMap<>();
        config.put("num", num);
        config.put("name", name);
        config.put("reference", reference);
        config.put("deletion", deletion);
        config.put("answer", answer);

        return config;
    }

    public static HashMap<String, Object> getLevelsConfig() {
        String jsonString = readJsonObject("levels.json");
        JSONObject jsonObject = (JSONObject) JSON.parse(jsonString);
        JSONArray objects = jsonObject.getJSONArray("levels");
        String[] levels = new String[objects.size()];
        for (int i = 0; i < objects.size(); i++) {
            levels[i] = objects.getString(i);
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("levels", levels);
        return hashMap;
    }

    private static String readJsonObject(String configPath) {
        InputStream urlStream = UrlUtil.getURLStream(configPath);
        if (urlStream == null) {
            throw new RuntimeException("不存在配置文件：" + configPath);
        }

        StringBuffer out = new StringBuffer();
        try {
            byte[] b = new byte[4096];
            for (int n; (n = urlStream.read(b)) != -1;) {
                if (n == b.length) {
                    out.append(new String(b, StandardCharsets.UTF_8));
                } else {
                    byte[] copy = Arrays.copyOf(b, n);
                    out.append(new String(copy, StandardCharsets.UTF_8));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return out.toString();
    }
}
