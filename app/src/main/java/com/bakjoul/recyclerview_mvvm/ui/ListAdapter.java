package com.bakjoul.recyclerview_mvvm.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bakjoul.recyclerview_mvvm.databinding.RvItemBinding;
import com.bakjoul.recyclerview_mvvm.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends androidx.recyclerview.widget.ListAdapter<Meeting, ListAdapter.ViewHolder> {
    @Override
    public void submitList(@Nullable List<Meeting> list) {
        super.submitList(list != null ? new ArrayList<>(list) : null);
    }

    public ListAdapter() {
        super(new ItemCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemBinding b = RvItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final RvItemBinding b;

        public ViewHolder(RvItemBinding binding) {
            super(binding.getRoot());
            this.b = binding;
        }

        public void bind(Meeting meeting) {
            b.itemTv.setText(meeting.getSubject());
        }

    }

    private static class ItemCallback extends DiffUtil.ItemCallback<Meeting> {

        @Override
        public boolean areItemsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
            return oldItem.equals(newItem);
        }
    }
}
