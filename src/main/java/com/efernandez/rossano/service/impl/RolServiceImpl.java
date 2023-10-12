package com.efernandez.rossano.service.impl;

import com.efernandez.rossano.dao.Rol;
import com.efernandez.rossano.repository.RolRepository;
import com.efernandez.rossano.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

}
