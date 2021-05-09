package com.example.fooddelivery.UI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.example.fooddelivery.Items.OrderCustomerItem;
import com.example.fooddelivery.R;
import com.example.fooddelivery.ViewHolders.OrderViewHolder;

import static com.example.mylibrary.SharedClass.fooddelivery_PATH;
import static com.example.mylibrary.SharedClass.ROOT_UID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *  interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */


public class Order extends Fragment {
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<OrderCustomerItem, OrderViewHolder> exampleapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView_ordered;
    private static FirebaseRecyclerOptions<OrderCustomerItem> options =
            new FirebaseRecyclerOptions.Builder<OrderCustomerItem>()
                    .setQuery(FirebaseDatabase.getInstance().getReference(fooddelivery_PATH).child(ROOT_UID).child("orders").orderByChild("sort"),
                            OrderCustomerItem.class).build();

    private com.example.fooddelivery.UI.Order.OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = view.findViewById(R.id.ordered_list);
        exampleapter = new FirebaseRecyclerAdapter<OrderCustomerItem, OrderViewHolder>(options) {
            @NonNull
            @Override
            public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_item, viewGroup, false);
                return new OrderViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull OrderViewHolder holder, int position, @NonNull OrderCustomerItem model) {
                String orderkey = getRef(position).getKey();
                holder.setData(model, position, orderkey);
                holder.getView().findViewById(R.id.order_details_button).setOnClickListener(a->{
                    Intent intent = new Intent(getContext(), OrderDetails.class);
                    intent.putExtra("order_item", model);
                    startActivity(intent);
                });
            }
        };

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(exampleapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        exampleapter.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
        exampleapter.stopListening();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}