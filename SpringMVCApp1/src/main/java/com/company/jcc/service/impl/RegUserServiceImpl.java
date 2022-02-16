//package com.company.jcc.service.impl;
//
//import com.company.jcc.model.RegUser;
//import com.company.jcc.repository.RegUserRepository;
//import com.company.jcc.service.RegUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//@Transactional
//public class RegUserServiceImpl implements RegUserService {
//
//    @Autowired
//    private RegUserRepository regUserRepository;
//
//    @Override
//    public void create(RegUser regUser) {
//        regUserRepository.create(regUser);
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return regUserRepository.findByUserName(username);
//    }
//}
