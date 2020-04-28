package com.example.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.adapter.AdapterHome;
import com.example.login.models.ModelHome;
import com.example.login.models.VideoID;
import com.example.login.models.VideoYT;
import com.example.login.network.YoutubeAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private AdapterHome adapter;
    private LinearLayoutManager manager;
    private List<VideoYT> videoList = new ArrayList<>();

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        RecyclerView rv = view.findViewById(R.id.recyclerView);

        adapter = new AdapterHome(getContext(),videoList);
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

        if (videoList.size() == 0) {
            getJson();
        }
        
        return view;
    }

    private void getJson() {
        String url = YoutubeAPI.BASE_URL + YoutubeAPI.sch + YoutubeAPI.KEY + YoutubeAPI.chId + YoutubeAPI.PART;
        Call<ModelHome> data = YoutubeAPI.getHomeVideo().getYT(url);
        data.enqueue(new Callback<ModelHome>() {
            @Override
            public void onResponse(Call<ModelHome> call, Response<ModelHome> response) {
                if (response.errorBody() != null) {
                    Log.v("TAG", "onResponse : " + response.errorBody());
                } else {
                    ModelHome  mh = response.body();
                    videoList.addAll(mh.getItems());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelHome> call, Throwable t) {
                Log.e("TAG", "onFailure : " ,t);
            }
        });
    }


}
