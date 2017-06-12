package com.example.ericbell.appnaorynet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ericbell.appnaorynet.R;
import com.example.ericbell.appnaorynet.YnetDataSource;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecylcerShow extends Fragment implements YnetDataSource.OnYnetArivvedListener{

RecyclerView rcView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment_recylcer_show, container, false);
        rcView = (RecyclerView) v.findViewById(R.id.rcView);
        YnetDataSource.getYnet(this);
         return v;

    }

    @Override
    public void onYnetArvvied(List<YnetDataSource.Ynet> data) {
        rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        rcView.setAdapter(new RecyclerViewAdapter(getContext(),data));
    }
}
