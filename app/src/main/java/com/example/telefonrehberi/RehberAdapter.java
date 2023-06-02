package com.example.telefonrehberi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RehberAdapter extends BaseAdapter {

    ArrayList<Rehber> rehberler;
    Context context;

    public RehberAdapter(ArrayList<Rehber> rehberler, Context context) {
        this.rehberler = rehberler;
        this.context = context;
    }

    @Override
    public int getCount() {
        return rehberler.size();
    }

    @Override
    public Object getItem(int i) {
        return rehberler.get(i);
    }

    @Override
    public long getItemId(int i) {
        return rehberler.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) view = LayoutInflater.from(context).inflate(R.layout.listview_satir,viewGroup,false);
        Rehber rehber = rehberler.get(i);
        TextView ad = view.findViewById(R.id.textViewAd),
                 telno = view.findViewById(R.id.textViewTelefon);
        ad.setText(rehber.getAd());
        telno.setText(rehber.getTelefon());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, RehberDetayActivity.class);
                i.putExtra("id",rehber.getId());
                i.putExtra("ad",rehber.getAd());
                i.putExtra("telefon",rehber.getTelefon());
                i.putExtra("eposta",rehber.getEposta());
                context.startActivity(i);
            }
        });
        return view;
    }
}
