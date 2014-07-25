package com.blue.sky.code.home;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.blue.sky.component.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

public class HomeFragment extends Fragment {

    private static final AsyncHttpClient CLIENT = new AsyncHttpClient();

	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.code_activity_home_fragment, container, false);

        String url = "http://codestudy.sinaapp.com/api/list.php";
        RequestParams params = new RequestParams();
        params.put("action","list");
        params.put("m",2);
        params.put("c", 12);
        params.put("s", "");
        params.put("k", "");
        params.put("i", 1);
        params.put("p", 10);
        CLIENT.post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(">>>HTTP onSuccess", new String(responseBody));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d(">>>HTTP onFailure", new String(bytes));
            }
        });


        RequestParams categoryParams = new RequestParams();
        categoryParams.put("action","category.list");
        categoryParams.put("categoryId",2);
        categoryParams.put("count", 10);
        CLIENT.post("http://codestudy.sinaapp.com/api/category.php", categoryParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(">>>HTTP onSuccess", new String(responseBody));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d(">>>HTTP onFailure", new String(bytes));
            }
        });

        return rootView;
    }
}
