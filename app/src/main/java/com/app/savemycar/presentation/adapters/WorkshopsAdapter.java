package com.app.savemycar.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.savemycar.R;
import com.app.savemycar.model.Workshop;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkshopsAdapter extends RecyclerView.Adapter<WorkshopsAdapter.WorkshopViewHolder> {

    private List<Workshop> workshopList;

    public WorkshopsAdapter(List<Workshop> workshopList) {
        this.workshopList = workshopList;
    }

    @NonNull
    @Override
    public WorkshopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workshop_item_layout, parent, false);
        return new WorkshopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkshopViewHolder holder, int position) {
        Workshop workshop = workshopList.get(position);
        holder.workshopNameTV.setText(workshop.getName());
        holder.workshopAddressTV.setText(workshop.getAddress());
        holder.workshopPhoneTV.setText(workshop.getPhone());
    }

    @Override
    public int getItemCount() {
        return workshopList.size();
    }

    class WorkshopViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.workshopNameTV)
        TextView workshopNameTV;
        @BindView(R.id.workshopAddressTV)
        TextView workshopAddressTV;
        @BindView(R.id.workshopPhoneTV)
        TextView workshopPhoneTV;

        public WorkshopViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
