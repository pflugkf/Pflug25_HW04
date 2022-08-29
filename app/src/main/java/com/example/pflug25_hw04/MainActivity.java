/*
 * Assignment #: HW04
 * File Name: Pflug25_HW04 --- MainActivity.java
 * Full Name: Kristin Pflug
 */

package com.example.pflug25_hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements UsersFragment.UsersFragmentListener, FilterByStateFragment.FilterByStateFragmentListener, SortFragment.SortFragmentListener {

    ArrayList<DataServices.User> users = new ArrayList<>();
    boolean isSorted = false;
    String sortBy = "";
    boolean sortDescending = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = DataServices.getAllUsers();
        getSupportFragmentManager().beginTransaction().add(R.id.rootView, UsersFragment.newInstance(users), "users").commit();
    }

    @Override
    public ArrayList<DataServices.User> getUserList() {
        return users;
    }

    @Override
    public void goToFilterUsers() {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, new FilterByStateFragment()).addToBackStack(null).commit();
    }

    @Override
    public void goToSortUsers() {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, new SortFragment()).addToBackStack(null).commit();
    }

    @Override
    public void filterUserList(String filterByState) {

        if(filterByState == "All States"){
            users = DataServices.getAllUsers();

            if(isSorted){
                if(sortBy.equals("Age")){
                    Collections.sort(users, new Comparator<DataServices.User>() {
                        @Override
                        public int compare(DataServices.User u1, DataServices.User u2) {
                            return String.valueOf(u1.age).compareTo(String.valueOf(u2.age));
                        }
                    });
                } else if (sortBy.equals("Name")) {
                    Collections.sort(users, new Comparator<DataServices.User>() {
                        @Override
                        public int compare(DataServices.User u1, DataServices.User u2) {
                            return u1.name.compareTo(u2.name);
                        }
                    });
                } else if (sortBy.equals("State")) {
                    Collections.sort(users, new Comparator<DataServices.User>() {
                        @Override
                        public int compare(DataServices.User u1, DataServices.User u2) {
                            return u1.state.compareTo(u2.state);
                        }
                    });
                }

                if(sortDescending == true){
                    Collections.reverse(users);
                }
            }
        } else {
            ArrayList<DataServices.User> usersFiltered = new ArrayList<>();
            for(int i = 0; i < users.size(); i++){
                if(users.get(i).state.equals(filterByState)) {
                    usersFiltered.add(users.get(i));
                }
            }
            users = usersFiltered;
        }

        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sortUserList(String sortAttribute, String sortDirection) {
        if(sortAttribute.equals("Age")){
            Collections.sort(users, new Comparator<DataServices.User>() {
                @Override
                public int compare(DataServices.User u1, DataServices.User u2) {
                    return String.valueOf(u1.age).compareTo(String.valueOf(u2.age));
                }
            });
        } else if (sortAttribute.equals("Name")) {
            Collections.sort(users, new Comparator<DataServices.User>() {
                @Override
                public int compare(DataServices.User u1, DataServices.User u2) {
                    return u1.name.compareTo(u2.name);
                }
            });
        } else if (sortAttribute.equals("State")) {
            Collections.sort(users, new Comparator<DataServices.User>() {
                @Override
                public int compare(DataServices.User u1, DataServices.User u2) {
                    return u1.state.compareTo(u2.state);
                }
            });
        }

        sortBy = sortAttribute;
        isSorted = true;

        if(sortDirection.equals("Descending")){
            Collections.reverse(users);
            sortDescending = true;
        } else {
            sortDescending = false;
        }

        getSupportFragmentManager().popBackStack();
    }

}