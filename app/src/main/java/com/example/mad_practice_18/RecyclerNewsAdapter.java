package com.example.mad_practice_18;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerNewsAdapter extends RecyclerView.Adapter<RecyclerNewsAdapter.ViewHolder> {
    private final NewsOnClickListener onClickListener;
    private final LayoutInflater inflater;
    private final List<NewsModel> news;

    public interface NewsOnClickListener {
        void onClick(int position);
    }

    public RecyclerNewsAdapter(Context context, List<NewsModel> news, NewsOnClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.news = news;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_news, parent, false);
        return new ViewHolder(view, onClickListener);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView header, datetime, text, author;
        private final NewsOnClickListener onClickListener;

        public ViewHolder(View view, NewsOnClickListener listener) {
            super(view);

            header = view.findViewById(R.id.header_tv);
            datetime = view.findViewById(R.id.datetime_tv);
            text = view.findViewById(R.id.main_text_tv);
            author = view.findViewById(R.id.author_tv);

            onClickListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(getAdapterPosition());
        }
    }
}
