package com.example.mad_practice_sqlitenews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerNewsAdapter extends RecyclerView.Adapter<RecyclerNewsAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<NewsModel> news;

    public RecyclerNewsAdapter(Context context, List<NewsModel> news) {
        this.inflater = LayoutInflater.from(context);
        this.news = news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerNewsAdapter.ViewHolder holder, int position) {
        NewsModel news = this.news.get(position);
        holder.header.setText(news.Header);
        holder.text.setText(news.Text);
        holder.author.setText(news.Author);
        holder.datetime.setText(news.Datetime);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView header, datetime,text, author;

        public ViewHolder(View view) {
            super(view);

            header = view.findViewById(R.id.header_tv);
            datetime = view.findViewById(R.id.datetime_tv);
            text = view.findViewById(R.id.main_text_tv);
            author = view.findViewById(R.id.author_tv);
        }
    }
}
