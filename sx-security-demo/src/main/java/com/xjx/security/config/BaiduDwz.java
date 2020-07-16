package com.xjx.security.config;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

@Slf4j
public class BaiduDwz {
    final static String CREATE_API = "https://dwz.cn/admin/v2/create";
    final static String TOKEN = "59c435419326319b53c03fc935531f8d"; // TODO:设置Token

    class UrlResponse {
        @SerializedName("Code")
        private int code;

        @SerializedName("ErrMsg")
        private String errMsg;

        @SerializedName("LongUrl")
        private String longUrl;

        @SerializedName("ShortUrl")
        private String shortUrl;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public String getLongUrl() {
            return longUrl;
        }

        public void setLongUrl(String longUrl) {
            this.longUrl = longUrl;
        }

        public String getShortUrl() {
            return shortUrl;
        }

        public void setShortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
        }
    }

    /**
     * 创建短网址
     *
     * @param longUrl
     *            长网址：即原网址
     *        termOfValidity
     *            有效期：默认值为long-term
     * @return  成功：短网址
     *          失败：返回空字符串
     */
    public static String createShortUrl(String longUrl, String termOfValidity) {
        String params = "{\"Url\":\""+ longUrl + "\",\"TermOfValidity\":\""+ termOfValidity + "\"}";

        BufferedReader reader = null;
        try {
            // 创建连接
            URL url = new URL(CREATE_API);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.setRequestProperty("Token", TOKEN); // 设置发送数据的格式");

            // 发起请求
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();

            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();

            // 抽取生成短网址
            UrlResponse urlResponse = new Gson().fromJson(res, UrlResponse.class);
            if (urlResponse.getCode() == 0) {
                return urlResponse.getShortUrl();
            } else {
                System.out.println(urlResponse.getErrMsg());
            }

            return ""; // TODO：自定义错误信息
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
        return ""; // TODO：自定义错误信息
    }

    /**
     * 将md5 编码进行base64编码，去掉最后的两个==，16为的md5码base64后最后两位肯定是==
     *
     * @param data    需要编码的 数据
     * @param urlSafe 返回url合法字符
     * @return 将md5 编码进行base64编码，去掉最后的两个==
     */
    public static String encodeBase64(byte[] data, boolean urlSafe) {
        byte[] md5Code = rawEncode(data);
        if (md5Code != null) {
            return new String(
                    urlSafe ? Base64.encodeBase64URLSafe(md5Code) : Base64.encodeBase64(md5Code), 0, 22);
        } else {
            return null;
        }
    }

    public static byte[] rawEncode(byte[] data) {
        MessageDigest MD5;
        try {
            MD5 = MessageDigest.getInstance("MD5");
            MD5.update(data, 0, data.length);
            return MD5.digest();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);//e.printStackTrace();
            return null;
        }
    }

    public static String encodeBase64(String data, boolean urlSafe) {
        try {
            return encodeBase64(data.getBytes("utf8"), urlSafe);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);//e.printStackTrace();
            return null;
        }
    }


    /**
     * 简化的url压缩算法，算法如下：
     * 1. 对Url进行md5编码
     * 2. 对md5码进行base64编码，长度为22
     * 3. 剔除base64码中的‘+’和‘/’， 取前面的一段，
     * 4. 如果位数不够，用base64码加上url再进行一次md5，用这个补齐，
     * 5. 循环4直到位数满足短码的长度需求
     * 说明一般短码的长度在6～10之间，一次就可以了。解决冲突的方法也简单，可以取长一点，比如目标是8位，可以取16位，如果发现0～7冲突，就取1～8 以此类推。
     *
     * @param longUrl   原始url
     * @param urlLength 输出url长度
     * @return 压缩后的rul
     */

    public static String shortenCodeUrl(String longUrl, int urlLength) {
        if (urlLength < 4) {
            urlLength = 8;// defalut length
        }
        StringBuilder sbBuilder = new StringBuilder(urlLength + 2);
        String md5Hex = "";
        int nLen = 0;
        while (nLen < urlLength) {
            md5Hex = encodeBase64(md5Hex + longUrl, true);
            int md5Len = md5Hex.length();
            int copylen = md5Len < urlLength - nLen ? md5Len : urlLength - nLen;
            sbBuilder.append(md5Hex, 0, copylen);
            nLen += copylen;
            if (nLen == urlLength) {
                break;
            }
        }
        return sbBuilder.toString();
    }

    public static void main(String[] args) {
        String res = shortenCodeUrl("http://www.aisinogz.com:19876/AisinoFp_tqgl/pd?id=202007042300v02000022081_044001600111_41158001.pdf",8);
        System.out.println(res);

    }

}
