package com.jawx.android.bakingapp.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jawx.android.bakingapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements View.OnClickListener   {
    private JsonUtils jsonArray;
    private final MainAdapterClickHandler mHandler;

    public interface MainAdapterClickHandler {
        void onClick(int position);
    }
    public MainAdapter(MainAdapterClickHandler clickHandler, JsonUtils jsonArray1) {
        jsonArray = jsonArray1;
        mHandler = clickHandler;
    }
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int itemId = R.layout.main_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(itemId, parent, false);
        return new MainViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MainAdapter.MainViewHolder holder, int position) {
            holder.bind(position);
    }
    @Override
    public int getItemCount() {
        if (jsonArray != null) return jsonArray.getDataCount();
        else return 0;
    }
    @Override
    public void onClick(View view) {
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textView;
    View view;

    public MainViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_desertName);
        view = itemView;
        itemView.setOnClickListener(this);
    }

    public View getView() {
        return view;
    }

    @Override
    public void onClick(View view) {
        int adapterPosition = getAdapterPosition();
        mHandler.onClick(adapterPosition);
    }

    public void bind(int position) {
        try {
            textView.setText(jsonArray.getName(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

    }
