package com.blue.sky.http;


import android.content.Context;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

public class HttpRequest {

    private static final AsyncHttpClient CLIENT = new AsyncHttpClient();

    public static void httpRequest(final Context context)
    {

        CLIENT.get("http://www.google.com", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });

        String url = "http://url";
        RequestParams params = new RequestParams();
        params.put("name", "张三");
        params.put("pass", "123456");
        CLIENT.post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                Toast.makeText(context, "请求成功" + new String(responseBody), 0).show();
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });

    }

    private static final String BASE_URL = "http://api.twitter.com/1/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
