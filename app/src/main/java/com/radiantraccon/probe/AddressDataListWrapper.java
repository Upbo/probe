package com.radiantraccon.probe;

import java.util.ArrayList;

public class AddressDataListWrapper {

    private ArrayList<AddressData> addressDataList;
    private AddressAdapter addressAdapter;
    public AddressDataListWrapper(int initialCapacity) {
        addressDataList = new ArrayList<>(initialCapacity);
    }

}
