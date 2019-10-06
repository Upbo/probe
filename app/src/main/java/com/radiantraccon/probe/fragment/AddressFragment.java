package com.radiantraccon.probe.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.radiantraccon.probe.MainActivity;
import com.radiantraccon.probe.R;
import com.radiantraccon.probe.data.AddressAdapter;
import com.radiantraccon.probe.data.AddressDataListWrapper;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {

    public AddressDataListWrapper addresses;
    public AddressFragment() {
        // Required empty public constructor
        addresses = new AddressDataListWrapper();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        addresses.initAdapter();
        final AddressAdapter adapter = addresses.getAddressAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                ((MainActivity)getActivity()).onAddressFragmentSubmit(adapter.getItem(pos));
            }
        });

        return view;
    }
}
