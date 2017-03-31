package com.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.MovieDetailActivity;
import com.demo.R;
import com.demo.constants.CustomProgressDialog;
import com.demo.constants.IUrls;
import com.demo.model.MovieBean;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import listners.OnLoadMoreListener;

import static com.demo.R.id.tvCertificate;

/**
 * Created by Swapnil on 30/03/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<MovieBean> list;
    public Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {
        public ImageView imgPoster;
        public TextView tvTitle;
        public TextView tvRelease;
        public TextView tvCertificate;
        public int postion;

        public MyViewHolder(View v) {
            super(v);
            imgPoster = (ImageView) v.findViewById(R.id.imgPoster);
            tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            tvRelease = (TextView) v.findViewById(R.id.tvReleaseDate);
            tvCertificate = (TextView) v.findViewById(R.id.tvCertificate);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(v.getContext(), MovieDetailActivity.class);
                    newIntent.putExtra("Detail", list.get(postion));
                    context.startActivity(newIntent);

                }
            });

        }

        @Override
        public void onClick(View view) {
        }
    }


    public MovieAdapter(List<MovieBean> ownerlist, Context context) {
        this.list = ownerlist;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_movie_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final MovieBean movieBean = (MovieBean) list.get(position);

        holder.tvTitle.setText(movieBean.getTitle() + "");

        holder.tvRelease.setText(movieBean.getRelease_date() + "");

        holder.tvCertificate.setText(movieBean.getAdult() == false ? "(U/A)" : "(A)");

        holder.postion = position;

            /*((MyViewHolder) holder).tvFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int count = Integer.parseInt(singleWallPaper.getFav());
                    ++count;

                    singleWallPaper.setFav(String.valueOf(count));

                    notifyItemChanged(position);


                }
            });*/
        Picasso.with(context)
                .load(IUrls.IMAGE_BASE + movieBean.getPoster_path())
                .placeholder(R.drawable.circular_progress_dialog)
                .fit()
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
