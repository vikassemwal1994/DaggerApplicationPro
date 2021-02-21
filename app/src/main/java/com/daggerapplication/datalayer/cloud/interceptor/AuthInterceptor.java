package com.daggerapplication.datalayer.cloud.interceptor;

import com.daggerapplication.datalayer.preference.PreferenceManager;

import java.io.IOException;
import javax.inject.Inject;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final String TAG = AuthInterceptor.class.getSimpleName();

    private PreferenceManager mPrefsManager;


    private final String APP_BUNDLE_ID = "App-Bundle-Id";
    private final String APP_KEY = "App-Key";
    private final String APP_VERSION = "App-Version";

    @Inject
    public AuthInterceptor(PreferenceManager mSharedPreference) {

        this.mPrefsManager = mSharedPreference;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
       /* Request request = chain.request();
        mPrefsManager.getToken();
        request.header("bearer"+ mPrefsManager.getToken());*/
        String userId = mPrefsManager.getUserId();
        String authCode = mPrefsManager.getAuthCode();
        Request.Builder requestBuilder = chain.request().newBuilder();
        mPrefsManager.getToken();
        if (!mPrefsManager.getToken().equals("")){
            requestBuilder.header("Authorization", /*"bearer " +*/ mPrefsManager.getToken());
            requestBuilder.header("login_id", /*"bearer " +*/ mPrefsManager.getUserId());
        }
        /*if (userId.equals("")) {
            userId = "0";
        }
        if (authCode.equals("")) {
            authCode = "0";
        }
        requestBuilder.header("Accept-Language", mPrefsManager.getSelectedLanguage().equals("1") ? "en-" + userId + "-" + authCode : "es-" + userId + "-" + authCode);*/
        requestBuilder.header("content-type","application/json; charset=utf-8");
        return chain.proceed(requestBuilder.build());

        // return chain.proceed(request);

    }

}