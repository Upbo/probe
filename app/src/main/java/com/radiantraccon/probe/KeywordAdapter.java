package com.radiantraccon.probe;

import android.animation.LayoutTransition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder> {
    private List<KeywordData> keywordDataList;

    @Override
    public KeywordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_keyword_item, parent,false);

        return new KeywordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KeywordViewHolder holder, int position) {
        holder.bind(keywordDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return keywordDataList.size();
    }


    // keyword view holder class
    public class KeywordViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        TextView desc;
        public KeywordViewHolder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.imageView_icon);
            title = itemView.findViewById(R.id.textView_title);
            desc = itemView.findViewById(R.id.textView_desc);
        }

        public void bind(KeywordData data) {
            icon.setImageResource(data.getImageId());
            title.setText(data.getTitle());
            desc.setText(data.getDescription());
        }
    }
}
