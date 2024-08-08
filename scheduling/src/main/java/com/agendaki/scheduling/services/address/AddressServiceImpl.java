package com.agendaki.scheduling.services.address;

import com.agendaki.scheduling.repositories.InstanceRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final InstanceRepository instanceRepository;

    public AddressServiceImpl(InstanceRepository instanceRepository) {
        this.instanceRepository = instanceRepository;
    }

    @Override
    public void insertAddress() {

    }

    @Override
    public void updateAddress() {

    }

    @Override
    public void getAddress() {

    }
}
