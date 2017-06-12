package com.example.ericbell.appnaorynet;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by eric.bell on 6/12/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<YnetDataSource.Ynet> data;

    public RecyclerViewAdapter(Context context, List<YnetDataSource.Ynet> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.ynet_item, parent, false);
        return new RecyclerViewHolder(v);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        YnetDataSource.Ynet ynet = data.get(position);
        holder.tvTitle.setText(ynet.getTitle());
        holder.description.setText(ynet.getDescription());
        Picasso.with(context).
                load(ynet.getThumble()).
                placeholder(R.drawable.placeholder).
                error(R.drawable.error).
                into(holder.ivthumble);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivthumble;
        TextView description;
        TextView tvTitle;
        private WebView webView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ivthumble = (ImageView) itemView.findViewById(R.id.ivthumble);
            description = (TextView) itemView.findViewById(R.id.tvDescription);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            String link = data.get(position).getLink();

            if (context instanceof FragmentActivity){
                FragmentActivity activity = (FragmentActivity) context;

                activity.getSupportFragmentManager().
                        beginTransaction().
                        addToBackStack("ynet_list").
                        replace(R.id.container, FragmentWebView.newInstance(link)).
                        commit();
            }

        }
    }
}
