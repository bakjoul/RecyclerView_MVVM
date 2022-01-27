package com.bakjoul.recyclerview_mvvm.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bakjoul.recyclerview_mvvm.databinding.RvItemBinding;
import com.bakjoul.recyclerview_mvvm.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Item> itemList;

    public RecyclerViewAdapter() {
        this.itemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemBinding b = RvItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.b.itemTv.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateItemList(final List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RvItemBinding b;

        public ViewHolder(RvItemBinding binding) {
            super(binding.getRoot());
            this.b = binding;
        }
    }
}
