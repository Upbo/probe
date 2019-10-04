package com.radiantraccon.probe;

import java.util.ArrayList;

public class AddressDataListWrapper {
    // data to populate the RecyclerView with
    private ArrayList<AddressData> addressDataList;
    private AddressAdapter addressAdapter;

    // constructor
    public AddressDataListWrapper() { }
    // getter and setter for list
    public ArrayList<AddressData> getAddressDataList() {
        return addressDataList;
    }
    public void setAddressDataList(ArrayList<AddressData> list) {
        addressDataList = list;
    }
    // getter and init for adapter
    public AddressAdapter getAddressAdapter() {
        return addressAdapter;
    }
    public void initAdapter() {
        addressAdapter = new AddressAdapter(addressDataList);
    }

}
