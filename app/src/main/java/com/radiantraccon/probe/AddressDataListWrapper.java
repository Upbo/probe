package com.radiantraccon.probe;

import java.util.ArrayList;

public class AddressDataListWrapper {

    private ArrayList<AddressData> addressDataList;
    private AddressAdapter addressAdapter;
    public AddressDataListWrapper(ArrayList<AddressData> list) {
        addressDataList = list;
    }

    public ArrayList<AddressData> getAddressDataList() {
        return addressDataList;
    }

}
