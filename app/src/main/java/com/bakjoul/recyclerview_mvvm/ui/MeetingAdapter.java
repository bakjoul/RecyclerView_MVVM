package com.bakjoul.recyclerview_mvvm.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bakjoul.recyclerview_mvvm.databinding.MeetingItemviewBinding;

import java.util.ArrayList;
import java.util.List;

public class MeetingAdapter extends ListAdapter<MeetingItemViewState, MeetingAdapter.ViewHolder> {
    @Override
    public void submitList(@Nullable List<MeetingItemViewState> list) {
        super.submitList(list != null ? new ArrayList<>(list) : null);
    }

    public MeetingAdapter() {
        super(new MeetingDiffCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MeetingItemviewBinding b = MeetingItemviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final MeetingItemviewBinding b;

        public ViewHolder(MeetingItemviewBinding binding) {
            super(binding.getRoot());
            this.b = binding;
        }

        public void bind(@NonNull final MeetingItemViewState meetingItemViewState) {
            b.itemAvatar.setBackgroundColor(meetingItemViewState.getIconColor());
            b.itemInfo.setText(meetingItemViewState.getSubject());
            b.itemParticipants.setText(meetingItemViewState.getParticipants().toString());
        }

    }

    private static class MeetingDiffCallback extends DiffUtil.ItemCallback<MeetingItemViewState> {

        @Override
        public boolean areItemsTheSame(@NonNull MeetingItemViewState oldItem, @NonNull MeetingItemViewState newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MeetingItemViewState oldItem, @NonNull MeetingItemViewState newItem) {
            return oldItem.equals(newItem);
        }
    }
}
