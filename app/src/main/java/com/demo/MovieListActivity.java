package com.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.demo.adapter.MovieAdapter;
import com.demo.constants.AppController;
import com.demo.constants.Connectivity;
import com.demo.constants.CustomProgressDialog;
import com.demo.constants.IConstants;
import com.demo.constants.IUrls;
import com.demo.model.MovieBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import listners.OnLoadMoreListener;

/**
 * Created by Swapnil on 30/03/2017.
 */

public class MovieListActivity extends AppCompatActivity {

    private static final String TAG = "WallpaperActivity";


    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    public static int pageNumber;
    private List<MovieBean> movieList;
    public static JSONArray jsonArray1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movie List");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        pageNumber = 1;
        movieList = new ArrayList<MovieBean>();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getWebservice();
    }


    public void getWebservice() {

        HashMap<String, String> param = new HashMap<>();
        JSONObject parameters = new JSONObject(param);

        if (Connectivity.isConnected(this)) {
            CustomProgressDialog.showDialog(this, "Loading....");
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, IUrls.GET_LIST + "b7cd3340a794e5a2f35e3abb820b497f", parameters,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                CustomProgressDialog.dismissDialog(MovieListActivity.this);
                                JSONObject jsonObject = response;
                                long datacount = jsonObject.getInt(IConstants.RESPONSE_KEY);

                                if (datacount > 0) {
                                    jsonArray1 = jsonObject.getJSONArray(IConstants.RESPONSE_INFO);
                                    GsonBuilder gsonBuilder = new GsonBuilder();
                                    Gson gson = gsonBuilder.create();
                                    MovieBean[] object = gson.fromJson(String.valueOf(jsonArray1), MovieBean[].class);
                                    buildList(object);
                                } else {
                                    CustomProgressDialog.dismissDialog(MovieListActivity.this);
                                }
                            } catch (JSONException e) {
                                CustomProgressDialog.dismissDialog(MovieListActivity.this);
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    CustomProgressDialog.dismissDialog(MovieListActivity.this);
                }


            });
            AppController.getInstance().addToRequestQueue(req);

        } else {
            CustomProgressDialog.showAlertDialogMessage(MovieListActivity.this, "Check Internet Connection", "Check Internet Connection");
        }
    }


    private void buildList(MovieBean[] object) {
        if (object[0] instanceof MovieBean) {
            for (MovieBean bean : object) {
                movieList.add(bean);
            }
            mAdapter = new MovieAdapter(movieList, MovieListActivity.this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

}
