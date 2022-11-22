package com.zrapp.warehouse.Fragment.FragOrder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zrapp.warehouse.R;


public class FragOrderDetail extends Fragment {

    public FragOrderDetail() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_order_detail, container, false);
    }
}