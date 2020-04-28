package com.example.login.network;

import com.example.login.models.ModelHome;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class YoutubeAPI {
//https://www.googleapis.com/youtube/v3/
// search?
// key=AIzaSyDp5dQ330DBm-pzSB2VpzPD-1Jw3A2A_4s
// &part=snippet
// &channelId=UCo5dKImAb9P77IrtSQrBfNg
public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    public static final String sch = "search?";
    public static final String KEY = "key=AIzaSyDp5dQ330DBm-pzSB2VpzPD-1Jw3A2A_4s";
    public static final String chId = "&channelId=UCo5dKImAb9P77IrtSQrBfNg";
    public static final String PART = "&part=snippet";

    public interface HomeVideo {
        @GET
        Call<ModelHome> getYT(@Url String url);
    }

    private static HomeVideo homeVideo = null;

    public static HomeVideo getHomeVideo() {
        if (homeVideo == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            homeVideo = retrofit.create(HomeVideo.class);
        }
        return homeVideo;
    }
}
