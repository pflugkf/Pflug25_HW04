/*
 * Assignment #: HW04
 * File Name: Pflug25_HW04 --- FilterByStateFragment.java
 * Full Name: Kristin Pflug
 */

package com.example.pflug25_hw04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterByStateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilterByStateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FilterByStateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilterByStateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilterByStateFragment newInstance(String param1, String param2) {
        FilterByStateFragment fragment = new FilterByStateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FilterByStateFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mListener = (FilterByStateFragmentListener) context;
    }

    ListView listView;
    ArrayList<String> states = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter_by_state, container, false);
        listView = view.findViewById(R.id.listView);
        getActivity().setTitle(R.string.filter_fragment_title);

        ArrayList<DataServices.User> userArrayList = DataServices.getAllUsers();

        for(int i = 0; i < userArrayList.size(); i++){
            String userState = userArrayList.get(i).state;
            if(!states.contains(userState)){
                states.add(userState);
            }
        }
        Collections.sort(states);
        states.add(0, "All States");

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, states);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String stateToFilterBy = states.get(i);
                mListener.filterUserList(stateToFilterBy);

            }
        });

        return view;
    }

    interface FilterByStateFragmentListener {
        void filterUserList(String filterByState);
    }
}