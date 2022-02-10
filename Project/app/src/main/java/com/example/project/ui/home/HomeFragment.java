package com.example.project.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private String htmlPageUrl;
    EditText searchBook;
    ArrayList<String> bookName = new ArrayList<String>();
    ArrayList<String> booking = new ArrayList<String>();
    ArrayList<String> bookAuthor = new ArrayList<String>();


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<PaintTitle> myDataset = new ArrayList<PaintTitle>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        searchBook = (EditText) root.findViewById(R.id.searchBook);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.searchList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);


        Button htmlTitleButton = (Button)root.findViewById(R.id.button);
        htmlTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    htmlPageUrl = "https://lib.hanbat.ac.kr/search/tot/result?st=KWRD&si=TOTAL&q="+ searchBook.getText() +"&oi=&os=&cpp=10";
                    myDataset.clear();
                    bookName.clear();
                    booking.clear();
                    mAdapter.notifyDataSetChanged();
                    HomeFragment.JsoupAsyncTask jsoupAsyncTask = new HomeFragment.JsoupAsyncTask();
                    jsoupAsyncTask.execute();
                }catch (Exception e){
                    Log.w("HomaFragment", "검색 실패");
                }

            }
        });
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
                Elements titles = doc.select(".searchTitle");
                Elements Reservation = doc.select(".book_state");
                Elements author = doc.select(".bookline");

                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    bookName.add(e.text());
                }
                for(Element e: Reservation){
                    booking.add(e.text());
                }
                for(Element e: author){
                    bookAuthor.add(e.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            for(int i = 0; i < bookName.size(); i++){
                if(booking.get(i) != "대출가능" || booking.get(i) != "대출중"){
                    booking.add("");
                }
                myDataset.add(new PaintTitle(bookName.get(i), booking.get(i), bookAuthor.get(i*6), bookAuthor.get(i*6+2), bookAuthor.get(i*6+1)));
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}