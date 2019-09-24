package com.example.navx.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.navx.R;
import com.example.navx.newsapp.newsModel.NewsHeadlineModel;
import com.example.navx.newsapp.newsViewmodel.NewsActivityViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView newsrecyclerview;
    NewsActivityViewModel newsActivityViewModel;
    List<NewsHeadlineModel.ArticlesBean> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        //connect viewmodel
        newsActivityViewModel = ViewModelProviders.of(this).get(NewsActivityViewModel.class);
        newsActivityViewModel.init();

        //get data and observe changes from live data
        newsActivityViewModel.getNewsRepository().observe(this, new Observer<NewsHeadlineModel>() {
            @Override
            public void onChanged(NewsHeadlineModel newsHeadlineModel) {
                newsList= newsHeadlineModel.getArticles();
                LinearLayoutManager verticalLayoutmanager
                        = new LinearLayoutManager(NewsActivity.this, VERTICAL, false);

                newsrecyclerview.setLayoutManager(verticalLayoutmanager);
                newsrecyclerview.setAdapter(new NewsAdapter(newsList));
                newsrecyclerview.setItemAnimator(new SimpleItemAnimator() {
                    @Override
                    public boolean animateRemove(RecyclerView.ViewHolder holder) {
                        return false;
                    }

                    @Override
                    public boolean animateAdd(RecyclerView.ViewHolder holder) {
                        return false;
                    }

                    @Override
                    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
                        return false;
                    }

                    @Override
                    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
                        return false;
                    }

                    @Override
                    public void runPendingAnimations() {

                    }

                    @Override
                    public void endAnimation(@NonNull RecyclerView.ViewHolder item) {

                    }

                    @Override
                    public void endAnimations() {

                    }

                    @Override
                    public boolean isRunning() {
                        return false;
                    }
                });
                newsrecyclerview.setNestedScrollingEnabled(true);
            }
        });

    }

    private void initView() {
        newsrecyclerview = findViewById(R.id.newsrecyclerview);
    }
    public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsVH> {

        List<NewsHeadlineModel.ArticlesBean> newsList;

        public NewsAdapter(List<NewsHeadlineModel.ArticlesBean> newsList) {
            this.newsList = newsList;
        }

        @NonNull
        @Override
        public NewsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlenewsitem, parent, false);
            return new NewsVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsVH holder, int position) {
                holder.newstitle.setText(newsList.get(position).getTitle());
            Picasso.get().load(newsList.get(position).getUrlToImage()).into(holder.newsimage);



        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

        class NewsVH extends RecyclerView.ViewHolder{

            ImageView newsimage;
            TextView newstitle;
            public NewsVH(@NonNull View itemView) {
                super(itemView);
                newsimage=itemView.findViewById(R.id.newsimage);
                newstitle=itemView.findViewById(R.id.newstitle);

            }
        }
    }

}
