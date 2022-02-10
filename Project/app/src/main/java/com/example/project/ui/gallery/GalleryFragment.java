package com.example.project.ui.gallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    private String htmlPageUrl; //파싱할 홈페이지의 URL주소
    EditText searchBook;
    ArrayList<String> bookName2 = new ArrayList<String>();
    ArrayList<String> booking2 = new ArrayList<String>();
    ArrayList<String> bookRank = new ArrayList<String>();

    private RecyclerView mRecyclerView2;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager mLayoutManager2;
    ArrayList<PaintTitle2> myDataset2 = new ArrayList<PaintTitle2>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        bookName2.clear();
        booking2.clear();
        bookRank.clear();

        mRecyclerView2 = (RecyclerView) root.findViewById(R.id.searchList2);
        mLayoutManager2 = new GridLayoutManager(getActivity(), 1);
        mRecyclerView2.setLayoutManager(mLayoutManager2);
        mAdapter2 = new MyAdapter2(myDataset2);
        mRecyclerView2.setAdapter(mAdapter2);
        htmlPageUrl = "https://lib.hanbat.ac.kr/statistics/popularloanList";
        GalleryFragment.JsoupAsyncTask jsoupAsyncTask = new GalleryFragment.JsoupAsyncTask();
        jsoupAsyncTask.execute();

        return root;
    }

    public class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(htmlPageUrl).get();
                Elements titles = doc.select(".title");
                Elements author = doc.select(".author");
                Elements rank = doc.select("#divList").select(".num");
                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    System.out.println("title: " + e.text());
                    bookName2.add(e.text());
                }
                for(Element e: author){
                    System.out.println("Reservation: " + e.text());
                    booking2.add(e.text());
                }
                for(Element e: rank){
                    System.out.println("Rank: " + e.text());
                    bookRank.add(e.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            for(int i = 0; i < bookRank.size(); i++){
                myDataset2.add(new PaintTitle2(bookName2.get(i), booking2.get(i), bookRank.get(i)));
            }
            mAdapter2.notifyDataSetChanged();
        }
    }
}