package com.example.matsedillvikunnar.lib.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    List<Recipe> recipeList;
    Context context;
    private OnNoteListener mOnNoteListener;

    public RecipesAdapter(List<Recipe> recipeList, Context context, OnNoteListener onNoteListener) {
        this.recipeList = recipeList;
        this.context = context;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card,parent, false);
        ViewHolder holder = new ViewHolder(view, mOnNoteListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardTitle.setText(recipeList.get(position).getRecipeTitle());
        Picasso.get().load(recipeList.get(position).getRecipeImage()).into(holder.cardImage);
        holder.cardSummary.setText(recipeList.get(position).getRecipeSummary());
       
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView cardImage;
        TextView cardTitle;
        TextView cardSummary;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.cardTitle);
            cardImage = itemView.findViewById(R.id.cardImage);
            cardSummary = itemView.findViewById(R.id.cardSummary);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAbsoluteAdapterPosition());

        }
    }


}
