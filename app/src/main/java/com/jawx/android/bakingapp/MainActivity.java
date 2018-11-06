package com.jawx.android.bakingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jawx.android.bakingapp.Utils.JsonUtils;
import com.jawx.android.bakingapp.Utils.MainAdapter;
import com.jawx.android.bakingapp.Utils.MyConnection;
import org.json.JSONException;
import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements MainAdapter.MainAdapterClickHandler {
    JsonUtils jsonArray;
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    GridLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_deserts_Main_view);
        layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
       new FitchData().execute();
    }

    @Override
    public void onClick(int position) {

    }

    public class FitchData extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params) {
            String data = null;
            try {
                data = MyConnection.getData(MyConnection.getURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        @Override
        protected void onPostExecute(String data) {

            if (data != null && !data.equals("")) {
                try {
                    jsonArray = new JsonUtils(data);
                    mMainAdapter = new MainAdapter(MainActivity.this, jsonArray);
                    mRecyclerView.setAdapter(mMainAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
