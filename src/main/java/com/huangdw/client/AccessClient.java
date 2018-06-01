package com.huangdw.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-06-01 15:30
 */
public class AccessClient {

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10); // 最多10个线程同时运行

    /**
     * get请求
     *
     * @param getUrl
     * @return
     */
    public static String sendGet(URL getUrl) {
        String result = "";
        BufferedReader reader = null;
        try {
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到服务器
            connection.connect();
            // 取得输入流，并使用Reader读取
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void access() throws Exception {
        final URL getUrl = new URL("http://localhost:8080/access.do?username=" + URLEncoder.encode("黄德文", "UTF-8"));
//        final URL getUrl = new URL("http://localhost:8080/access.do?username=黄德文");

        for (int i = 0; i < 10; i++) { // 模拟10个请求并发执行
            fixedThreadPool.submit(new Runnable() {
                public void run() {
                    System.out.println(sendGet(getUrl));
                }
            });
        }

        fixedThreadPool.shutdown();
        fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception {
        AccessClient accessClient = new AccessClient();
        accessClient.access();
    }
}
