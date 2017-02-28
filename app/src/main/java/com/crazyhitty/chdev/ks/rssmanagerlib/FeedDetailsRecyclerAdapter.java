package com.crazyhitty.chdev.ks.rssmanagerlib;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazyhitty.chdev.ks.rssmanager.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     2/28/2017 11:21 AM
 * Description: Unavailable
 */

public class FeedDetailsRecyclerAdapter extends RecyclerView.Adapter<FeedDetailsRecyclerAdapter.ViewHolder> {
    private List<FeedDetail> feedDetails = new ArrayList<FeedDetail>();

    public FeedDetailsRecyclerAdapter(Channel.Item item) {
        feedDetails.add(new FeedDetail("title", item.getTitle()));
        feedDetails.add(new FeedDetail("link", item.getLink()));
        feedDetails.add(new FeedDetail("description", item.getDescription()));
        feedDetails.add(new FeedDetail("author", item.getAuthor()));
        feedDetails.add(new FeedDetail("category", item.getCategory()));
        feedDetails.add(new FeedDetail("comments", item.getComments()));
        feedDetails.add(new FeedDetail("enclosure", item.getEnclosure()));
        feedDetails.add(new FeedDetail("guid", item.getGuid()));
        feedDetails.add(new FeedDetail("pubDate", item.getPubDate()));
        feedDetails.add(new FeedDetail("source", item.getSource()));
    }

    @Override
    public FeedDetailsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_feed_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedDetailsRecyclerAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(feedDetails.get(position).getTitle());
        holder.txtDescription.setText(TextUtils.isEmpty(feedDetails.get(position).getDescription()) ?
                "Unavailable" : feedDetails.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return feedDetails.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.text_view_title);
            txtDescription = (TextView) itemView.findViewById(R.id.text_view_description);
        }
    }

    private static class FeedDetail {
        private String title;
        private String description;

        public FeedDetail() {
        }

        public FeedDetail(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
