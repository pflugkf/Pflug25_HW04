/*
 * Assignment #: HW04
 * File Name: Pflug25_HW04 --- UsersFragment.java
 * Full Name: Kristin Pflug
 */

package com.example.pflug25_hw04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USERS_LIST = "ARG_USERS_LIST";

    // TODO: Rename and change types of parameters
    private ArrayList<DataServices.User> users;

    public UsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param users Parameter 1.
     * //@param param2 Parameter 2.
     * @return A new instance of fragment UsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsersFragment newInstance(ArrayList<DataServices.User> users) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USERS_LIST, users);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.users = (ArrayList<DataServices.User>) getArguments().getSerializable(ARG_USERS_LIST);
        }
    }

    UsersFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mListener = (UsersFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        return view;
    }

    ListView listView;
    UserListAdapter adapter;
    Button filterButton;
    Button sortButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        getActivity().setTitle(R.string.users_fragment_title);
        users = mListener.getUserList();


        adapter = new UserListAdapter(getActivity(), R.layout.users_list_item, users);
        listView.setAdapter(adapter);

        filterButton = view.findViewById(R.id.button_filter_users);
        sortButton = view.findViewById(R.id.button_sort_users);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToFilterUsers();
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToSortUsers();
            }
        });

    }

    class UserListAdapter extends ArrayAdapter<DataServices.User> {

        public UserListAdapter(@NonNull Context context, int resource, @NonNull List<DataServices.User> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.users_list_item, parent, false);
            }

            DataServices.User user = getItem(position);

            ImageView profilePicture = convertView.findViewById(R.id.profile_image);
            TextView userName = convertView.findViewById(R.id.user_name);
            TextView userState = convertView.findViewById(R.id.user_state);
            TextView userAge = convertView.findViewById(R.id.user_age);
            TextView userGroup = convertView.findViewById(R.id.user_group);

            if(user.gender.equals("Female")) {
                profilePicture.setImageResource(R.drawable.avatar_female);
            } else if (user.gender.equals("Male")) {
                profilePicture.setImageResource(R.drawable.avatar_male);
            }

            userName.setText(user.name);
            userState.setText(user.state);
            userAge.setText(user.age + " Years Old");
            userGroup.setText(user.group);

            return convertView;
        }
    }

    interface UsersFragmentListener {
        ArrayList<DataServices.User> getUserList();
        void goToFilterUsers();
        void goToSortUsers();
    }
}