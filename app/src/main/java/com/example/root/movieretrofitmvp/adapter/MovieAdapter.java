package com.example.root.movieretrofitmvp.adapter;import android.annotation.SuppressLint;import android.content.Context;import android.content.Intent;import android.support.annotation.NonNull;import android.support.v7.widget.RecyclerView;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.Button;import android.widget.ImageView;import android.widget.TextView;import com.example.root.movieretrofitmvp.BuildConfig;import com.example.root.movieretrofitmvp.R;import com.example.root.movieretrofitmvp.data.MovieModel;import com.example.root.movieretrofitmvp.view.DetailActivity;import com.squareup.picasso.Picasso;import java.text.ParseException;import java.text.SimpleDateFormat;import java.util.Date;import java.util.List;public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{    private Context context;    private List<MovieModel> modelList;    public MovieAdapter(Context context, List<MovieModel> modelList) {        this.context = context;        this.modelList = modelList;    }    @NonNull    @Override    public MovieAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);        return new MovieHolder(view);    }    @Override    public void onBindViewHolder(@NonNull MovieAdapter.MovieHolder holder, int position) {        //setting title component        final String set_title = modelList.get(position).getTitle();        holder.title.setText(set_title);        //setting release date component        String release_date = "";        String getDate = modelList.get(position).getRelease();        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");        try {            Date date = dateFormat.parse(getDate);            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MM dd, yyyy");            release_date = simpleDateFormat.format(date);            holder.release.setText(release_date);        } catch (ParseException e){            e.printStackTrace();        }        //setting overview component        String overview = "Nothing overview";        if (!modelList.get(position).getOverview().isEmpty()){            overview = modelList.get(position).getOverview();        }        holder.overview.setText(overview);        //setting image showing up using picasso        String poster_path = modelList.get(position).getPoster_path();        String poster_url = BuildConfig.POSTER_PATH;        Picasso.with(context)                .load(poster_url+poster_path)                .placeholder(context.getResources().getDrawable(R.mipmap.ic_launcher))                .error(context.getResources().getDrawable(R.mipmap.ic_launcher))                .into(holder.poster);        final String finalRelease_date = release_date;        final String finalOverview = overview;        final String finalBackdrop = modelList.get(position).getBackdrop_path();        holder.detail.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                Intent intent = new Intent(context, DetailActivity.class);                MovieModel movieModel = new MovieModel();                movieModel.setTitle(set_title);                movieModel.setRelease(finalRelease_date);                movieModel.setOverview(finalOverview);                movieModel.setBackdrop_path(finalBackdrop);                intent.putExtra(DetailActivity.MOVIE_DATA, movieModel);                context.startActivity(intent);            }        });    }    @Override    public int getItemCount() {        return 0;    }    class MovieHolder extends RecyclerView.ViewHolder {        TextView title, release, overview;        ImageView poster;        Button detail;        MovieHolder(View itemView) {            super(itemView);            title = (TextView)itemView.findViewById(R.id.title);            release = (TextView)itemView.findViewById(R.id.release);            overview = (TextView)itemView.findViewById(R.id.overview);            poster = (ImageView) itemView.findViewById(R.id.poster);            detail = (Button)itemView.findViewById(R.id.btnDetail);        }    }}