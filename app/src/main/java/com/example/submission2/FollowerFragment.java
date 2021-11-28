package com.example.submission2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.submission2.databinding.ActivityMainBinding;
import com.example.submission2.databinding.FragmentFollowerBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FollowerFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section";
    private static final String TAG = FollowerFragment.class.getSimpleName();
    private final ArrayList<FollowerModel> arrayList = new ArrayList<>();
    private final FollowAdapter followAdapter = new FollowAdapter(arrayList);
    private FragmentFollowerBinding followerBinding;

    public static FollowerFragment newInstance(int index) {
        FollowerFragment followerFragment = new FollowerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        followerFragment.setArguments(bundle);
        return followerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        followerBinding = FragmentFollowerBinding.inflate(inflater, container, false);
        return followerBinding.getRoot();
//        return inflater.inflate(R.layout.fragment_follower, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
        followerBinding.rvFollower.setVisibility(View.VISIBLE);
//        FragmentFollowerBinding binding;
//        RecyclerView recyclerView = view.findViewById(R.id.rv_follower);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        recyclerView.setAdapter(followAdapter);

//        int index = 1;
//
//        if (getArguments() != null) {
//            index = getArguments().getInt(ARG_SECTION_NUMBER);
//            binding.sectionFollower.setText(getString(R.string.company, index));
        getFollower();
    }

    private void getFollower() {
        AndroidNetworking.get("https://api.github.com/users/" + "mojombo" + "/followers")
                .addHeaders("Authorization", "token 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")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String user = jsonObject.getString("login");
                                String type = jsonObject.getString("type");
                                String avatarUrl = jsonObject.getString("avatar_url");

                                arrayList.add(new FollowerModel(
                                        user,
                                        type,
                                        avatarUrl
                                ));
                            }
                            recyleViewList();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void recyleViewList() {
        RecyclerView recyclerView = followerBinding.rvFollower;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(followAdapter);

    }
}