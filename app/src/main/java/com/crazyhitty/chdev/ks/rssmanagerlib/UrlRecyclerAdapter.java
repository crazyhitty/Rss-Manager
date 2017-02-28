package com.crazyhitty.chdev.ks.rssmanagerlib;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     2/27/2017 11:24 PM
 * Description: Unavailable
 */

public class UrlRecyclerAdapter extends RecyclerView.Adapter<UrlRecyclerAdapter.ViewHolder> {
    private static final String[] FEED_URLS = new String[]{"http://feeds.arstechnica.com/arstechnica/index?format=xml",
            "http://feeds.reuters.com/reuters/topNews?format=xml",
            "http://feeds.ign.com/ign/all?format=xml"};
    private List<Url> urls = new ArrayList<>();

    public UrlRecyclerAdapter() {
        for (String feedUrl : FEED_URLS) {
            urls.add(new Url(feedUrl, false));
        }
    }

    public void addNewUrl() {
        urls.add(new Url());
        notifyItemInserted(getItemCount() - 1);
    }

    public String[] getUrls() {
        List<String> urls = new ArrayList<String>();
        for (Url url : this.urls) {
            if (!TextUtils.isEmpty(url.getUrl())) {
                urls.add(url.getUrl());
            }
        }
        return urls.toArray(new String[urls.size()]);
    }

    @Override
    public UrlRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_url, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UrlRecyclerAdapter.ViewHolder holder, int position) {
        holder.eTxtUrl.setText(urls.get(position).getUrl());
        holder.eTxtUrl.setEnabled(urls.get(position).isEditing());
        holder.btnEdit.setText(urls.get(position).isEditing() ? R.string.save : R.string.edit);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (urls.get(holder.getAdapterPosition()).isEditing()) {
                    // Save.
                    urls.get(holder.getAdapterPosition()).setEditing(false);
                    urls.get(holder.getAdapterPosition()).setUrl(holder.eTxtUrl.getText().toString());
                } else {
                    // Edit.
                    urls.get(holder.getAdapterPosition()).setEditing(true);
                }
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urls.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private EditText eTxtUrl;
        private Button btnEdit, btnRemove;

        public ViewHolder(View itemView) {
            super(itemView);
            eTxtUrl = (EditText) itemView.findViewById(R.id.edit_text_feed_url);
            btnEdit = (Button) itemView.findViewById(R.id.button_edit);
            btnRemove = (Button) itemView.findViewById(R.id.button_remove);
        }
    }

    private static class Url {
        private String url;
        private boolean isEditing;

        public Url() {
        }

        public Url(String url, boolean isEditing) {
            this.url = url;
            this.isEditing = isEditing;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isEditing() {
            return isEditing;
        }

        public void setEditing(boolean editing) {
            isEditing = editing;
        }
    }
}
