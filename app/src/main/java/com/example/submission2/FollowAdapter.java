package com.example.submission2;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission2.databinding.ListFollowerBinding;

import java.util.ArrayList;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.ListViewHolder> {

    private ArrayList<FollowerModel> models;

    public FollowAdapter(ArrayList<FollowerModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public FollowAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ListFollowerBinding binding = ListFollowerBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new FollowAdapter.ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowAdapter.ListViewHolder holder, int position) {
        FollowerModel followerModel = models.get(position);
        Glide.with(holder.itemView.getContext())
                .load(followerModel.getAvatar())
                .circleCrop()
                .into(holder.binding.imgAvatar);
        holder.binding.tvUsername.setText(followerModel.getUsername());
        holder.binding.tvType.setText(followerModel.getType());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {

        private final ListFollowerBinding binding;

        public ListViewHolder(ListFollowerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
