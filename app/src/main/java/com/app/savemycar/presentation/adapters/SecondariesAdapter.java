package com.app.savemycar.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.savemycar.R;
import com.app.savemycar.model.Secondary;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondariesAdapter extends RecyclerView.Adapter<SecondariesAdapter.SecondaryViewHolder> {

    private List<Secondary> secondaryList;
    private OnSecondaryItemClicked onSecondaryItemClicked;

    public SecondariesAdapter(List<Secondary> secondaryList, OnSecondaryItemClicked onSecondaryItemClicked) {
        this.secondaryList = secondaryList;
        this.onSecondaryItemClicked = onSecondaryItemClicked;
    }

    @NonNull
    @Override
    public SecondaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.secondary_item_layout, parent, false);
        return new SecondaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondaryViewHolder holder, int position) {
        Secondary secondary = secondaryList.get(position);
        holder.secondaryTitle.setText(secondary.getName());
        holder.itemView.setOnClickListener(v -> onSecondaryItemClicked.onSecondaryClicked(secondary));
    }

    @Override
    public int getItemCount() {
        return secondaryList.size();
    }

    static class SecondaryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.secondaryTitle)
        TextView secondaryTitle;

        public SecondaryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnSecondaryItemClicked {
        void onSecondaryClicked(Secondary secondary);
    }
}
