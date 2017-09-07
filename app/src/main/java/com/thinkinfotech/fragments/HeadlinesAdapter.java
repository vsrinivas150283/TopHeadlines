package com.thinkinfotech.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thinkinfotech.R;
import com.thinkinfotech.fragments.HeadlinesFragment.OnHeadlineSelectedListener;
import com.thinkinfotech.fragments.dummy.DummyContent.DummyItem;
import com.thinkinfotech.models.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.HEAD;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnHeadlineSelectedListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class HeadlinesAdapter extends RecyclerView.Adapter<HeadlinesAdapter.ViewHolder> {

    private List<Article> articles = new ArrayList<>();
    private final OnHeadlineSelectedListener mListener;
    private Context context;

    public HeadlinesAdapter(List<Article> articles, OnHeadlineSelectedListener listener) {
        this.articles = articles;
        mListener = listener;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_headlines, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Article article = articles.get(position);
        Log.d("Articles", article.toString());
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        Picasso.with(context).load(article.getUrlToImage())
                .tag(this)
                .into(holder.urlToImage);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onHeadlineSelected(article);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.urlToImage)
        ImageView urlToImage;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.content) TextView description;
        public Article article;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + description.getText() + "'";
        }
    }
}
