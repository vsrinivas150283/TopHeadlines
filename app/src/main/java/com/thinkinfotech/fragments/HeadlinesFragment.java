package com.thinkinfotech.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.NestedScrollingChild;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thinkinfotech.R;
import com.thinkinfotech.fragments.dummy.DummyContent;
import com.thinkinfotech.models.Article;
import com.thinkinfotech.models.Articles;
import com.thinkinfotech.network.NewsAPIService;
import com.thinkinfotech.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnHeadlineSelectedListener}
 * interface.
 */
public class HeadlinesFragment extends Fragment {
    private OnHeadlineSelectedListener mListener;
    private HeadlinesAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HeadlinesFragment() {
    }

    public static HeadlinesFragment newInstance(int columnCount) {
        HeadlinesFragment fragment = new HeadlinesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadNewsFromApi();
    }

    private void loadNewsFromApi(){
        Retrofit client = RetrofitClient.getClient();
        NewsAPIService service = client.create(NewsAPIService.class);
        service.listArticles("abc-news-au").enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if(response.isSuccessful()){
                   mAdapter.setArticles(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.d("Response", t.getMessage());
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headline_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            mAdapter = new HeadlinesAdapter(new ArrayList<Article>(), mListener);
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHeadlineSelectedListener) {
            mListener = (OnHeadlineSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Represents a listener that will be notified of headline selections.
     */
    public interface OnHeadlineSelectedListener {
        /**
         * Called when a given headline is selected.
         * @param index the index of the selected headline.
         */
        public void onHeadlineSelected(int index);
    }

}
