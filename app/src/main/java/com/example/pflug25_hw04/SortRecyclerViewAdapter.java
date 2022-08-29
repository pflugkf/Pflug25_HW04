/*
 * Assignment #: HW04
 * File Name: Pflug25_HW04 --- SortRecyclerViewAdapter.java
 * Full Name: Kristin Pflug
 */

package com.example.pflug25_hw04;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class SortRecyclerViewAdapter extends RecyclerView.Adapter<SortRecyclerViewAdapter.SortViewHolder> {
    String[] sortAttributes;
    SortAdapterToFragmentListener mListener;

    public SortRecyclerViewAdapter(String[] sortAttributes, SortAdapterToFragmentListener mListener) {
        this.sortAttributes = sortAttributes;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public SortRecyclerViewAdapter.SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sort_list_item, parent, false);
        SortRecyclerViewAdapter.SortViewHolder sortViewHolder = new SortRecyclerViewAdapter.SortViewHolder(view, mListener);

        return sortViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SortRecyclerViewAdapter.SortViewHolder holder, int position) {
        holder.sortCriteria.setText(sortAttributes[position]);
    }

    @Override
    public int getItemCount() {
        return sortAttributes.length;
    }

    public class SortViewHolder extends RecyclerView.ViewHolder {
        TextView sortCriteria;
        ImageButton sortAscending;
        ImageButton sortDescending;
        SortAdapterToFragmentListener mListener;

        public SortViewHolder(@NonNull View itemView, SortAdapterToFragmentListener mListener) {
            super(itemView);

            this.mListener = mListener;
            sortCriteria = itemView.findViewById(R.id.sortAttributeLabel);
            sortAscending = itemView.findViewById(R.id.button_sort_ascending);
            sortDescending = itemView.findViewById(R.id.button_sort_descending);

            sortAscending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("qSortTest", "Sort users by " + sortCriteria.getText() + " ascending");
                    mListener.getSortAttribute(sortCriteria.getText().toString(), "Ascending");
                }
            });

            sortDescending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("qSortTest", "Sort users by " + sortCriteria.getText() + " descending");
                    mListener.getSortAttribute(sortCriteria.getText().toString(), "Descending");
                }
            });
        }
    }

    interface SortAdapterToFragmentListener {
        void getSortAttribute(String sortAttribute, String sortDirection);
    }
}
