package com.amazingenergy.saitamaappservice.seedwork;

import com.amazingenergy.saitamadomain.common.Address;

public interface GeoLocationService {
    Address getAddress(String ipAddress);
}
