package com.daggerapplication.di.module;

import android.util.Log;

import com.daggerapplication.BuildConfig;
import com.daggerapplication.base.BaseApplication;
import com.daggerapplication.datalayer.cloud.CloudApi;
import com.daggerapplication.datalayer.cloud.UrlConstants;
import com.daggerapplication.datalayer.cloud.interceptor.AuthInterceptor;
import com.daggerapplication.datalayer.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class CloudModule {

    @Provides
    @Singleton
    Cache provideOkHttpCache(BaseApplication application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    AuthInterceptor provideAuthInterceptor(PreferenceManager mSharedPreference) {
        return new AuthInterceptor(mSharedPreference);
    }


    @Provides
    @Singleton
    @Named("intercepted")
    OkHttpClient provideOkHttpClient(Cache cache, AuthInterceptor authInterceptor) {

        if (BuildConfig.showHttpLog) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.level(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder()
                    .readTimeout(360, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .cache(cache)
                    .addInterceptor(authInterceptor)
//                    .addNetworkInterceptor(new StethoInterceptor())
                    .addNetworkInterceptor(logging);
            hostnameVerifier(clientBuilder);
            return clientBuilder.build();


        } else {
            OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder()
                    .readTimeout(360, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .cache(cache)
                    .addInterceptor(authInterceptor);
//                    .addNetworkInterceptor(new StethoInterceptor());

            hostnameVerifier(clientBuilder);

            return clientBuilder.build();


        }
    }

    private void hostnameVerifier(OkHttpClient.Builder clientBuilder) {

        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] cArrr = new X509Certificate[0];
                return cArrr;
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain,
                                           final String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(final X509Certificate[] chain,
                                           final String authType) throws CertificateException {
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//            clientBuilder.sslSocketFactory(sslContext.getSocketFactory());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            clientBuilder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
        } catch (Exception x) {

        }

        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                Log.d("TAG", "Trust Host :" + hostname);
                return true;
            }
        };
//        HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
        clientBuilder.hostnameVerifier(hostnameVerifier);
    }

    @Provides
    @Singleton
    @Named("nonintercepted")
    OkHttpClient provideOkHttpClientNonIntercepted() {

        if (BuildConfig.showHttpLog) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.level(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder()
                    .readTimeout(360, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .addNetworkInterceptor(logging);

            hostnameVerifier(clientBuilder);
            return clientBuilder.build();

        } else {
            OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder()
                    .readTimeout(360, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS);

            hostnameVerifier(clientBuilder);
            return clientBuilder.build();

        }
    }

    @Provides
    @Singleton
    @Named("intercepted")
    CloudApi provideApiService(@Named("intercepted") OkHttpClient client, GsonConverterFactory gsonConverterFactory) {


        return new Retrofit.Builder()
                .baseUrl(UrlConstants.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(CloudApi.class);
    }

    @Provides
    @Singleton
    @Named("nonintercepted")
    CloudApi provideApiServiceNonIntercepted(@Named("nonintercepted") OkHttpClient client, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(UrlConstants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(CloudApi.class);
    }
/*
    @Provides
    @Singleton
    @Named("nonintercepted")
    CloudRepository provideApiServiceNonIntercepted(@Named("nonintercepted") OkHttpClient client, GsonConverterFactory gsonConverterFactory) {
        return (CloudRepository)new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_ENDPOINT)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build()
                .create(CloudApi.class);
    }

    @Provides
    @Singleton
    @Named("intercepted")
    CloudRepository provideApiService(@Named("intercepted") OkHttpClient client, GsonConverterFactory gsonConverterFactory) {
        return (CloudRepository) new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_ENDPOINT)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build()
                .create(CloudApi.class);
    }*/

    @Provides
    @Singleton
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("MM dd yyyy");
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }



    /*@Provides
    @Singleton
    CloudDataSource provideCloudDataSource(@Named("intercepted") CloudApi apiService) {
        return new CloudDataSource(apiService);
    }
*/


}
