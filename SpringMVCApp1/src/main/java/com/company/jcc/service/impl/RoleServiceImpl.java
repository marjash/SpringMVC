//package com.company.jcc.service.impl;
//
//import com.company.jcc.model.Role;
//import com.company.jcc.repository.RoleRepository;
//import com.company.jcc.service.RoleService;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//
//@Service
//public class RoleServiceImpl implements RoleService {
//
//    private final RoleRepository roleRepository;
//
//    public RoleServiceImpl(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public Role create(Role role) {
//            return roleRepository.create(role);
//    }
//
//    @Override
//    public Role readById(int id) {
//        return roleRepository.readById(id);
//    }
//
//    @Override
//    public Role update(Role role) {
//        return roleRepository.update(role);
//    }
//
//    @Override
//    public void delete(int id) {
//        roleRepository.delete(id);
//    }
//
//    @Override
//    public List<Role> getAll() {
//        return roleRepository.findAll();
//    }
//
//
//}
