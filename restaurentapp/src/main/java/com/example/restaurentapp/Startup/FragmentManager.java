package com.example.restaurentapp.Startup;

import android.os.Bundle;

import android.net.Uri;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.restaurentapp.Startup.DishesActivities.DailyOffer;
import com.example.restaurentapp.Startup.HomeActivities.Home;
import com.example.restaurentapp.Startup.HomeActivities.HomeStats;
import com.example.restaurentapp.Startup.OrderActivities.Order;
import com.example.restaurentapp.Startup.OrderActivities.PagerAdapterOrder;
import com.example.restaurentapp.Startup.OrderActivities.Reservation;
import com.example.restaurentapp.Startup.ProfileActivities.PagerAdapterProfile;
import com.example.restaurentapp.Startup.ProfileActivities.Profile;
import com.example.restaurentapp.Startup.ProfileActivities.Rating;
import com.example.restaurentapp.R;

import static android.view.View.VISIBLE;
import static android.view.View.INVISIBLE;
import static com.example.mylibrary.SharedClass.RESERVATION_PATH;
import static com.example.mylibrary.SharedClass.RESTAURATEUR_INFO;
import static com.example.mylibrary.SharedClass.ROOT_UID;

public class FragmentManager extends AppCompatActivity implements DailyOffer.OnFragmentInteractionListener,
        Reservation.OnFragmentInteractionListener, Order.OnFragmentInteractionListener, Home.OnFragmentInteractionListener,
        Profile.OnFragmentInteractionListener, HomeStats.OnFragmentInteractionListener,
        PagerAdapterOrder.OnFragmentInteractionListener, PagerAdapterProfile.OnFragmentInteractionListener,
        Rating.OnFragmentInteractionListener {

    private View notificationBadge;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item ->  {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                if(!(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof Home)){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
                }
                return true;
            case R.id.navigation_profile:
                if(!(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof PagerAdapterProfile)){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PagerAdapterProfile()).commit();
                }
                return true;
            case R.id.navigation_dailyoffer:
                if(!(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof DailyOffer)){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DailyOffer()).commit();
                }
                return true;
            case R.id.navigation_reservation:
                if(!(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof PagerAdapterOrder)){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PagerAdapterOrder()).commit();
                }
                return true;
        }

        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Home()).commit();
        }

        addBadgeView();
        hideBadgeView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkBadge();
    }

    private void checkBadge(){
        Query query = FirebaseDatabase.getInstance().getReference(RESTAURATEUR_INFO + "/" + ROOT_UID
                + "/" + RESERVATION_PATH);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    long count = dataSnapshot.getChildrenCount();

                    ((TextView)notificationBadge.findViewById(R.id.count_badge)).setText(Long.toString(count));
                    refreshBadgeView();
                }
                else{
                    hideBadgeView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addBadgeView() {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(3);

        notificationBadge = LayoutInflater.from(this).inflate(R.layout.notification_badge, menuView, false);

        itemView.addView(notificationBadge);
    }

    private void refreshBadgeView() {
        notificationBadge.setVisibility(VISIBLE);
    }

    private void hideBadgeView(){
        notificationBadge.setVisibility(INVISIBLE);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}