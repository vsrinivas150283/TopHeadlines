package com.thinkinfotech.network;

import com.thinkinfotech.models.Article;
import com.thinkinfotech.models.Articles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by App on 7/09/2017.
 */
/*
articles?source=the-next-web&sortBy=latest&apiKey={API_KEY}
 */

public interface NewsAPIService {
    String NEWS_API_URL = "https://newsapi.org/v1/";

    @GET("articles?sortBy=top&apiKey=4e57228b21d84b9e876330ed4b3146c2")
    Call<Articles> listArticles(@Query("source") String source);
}
