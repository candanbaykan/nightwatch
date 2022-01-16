package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.UserDetails;
import com.ct.nightwatch.webapi.service.dto.UserRequest;
import com.ct.nightwatch.webapi.service.dto.UserSummary;

import java.util.List;

public interface UserService {

    List<UserDetails> findAll();

    UserDetails findById(Long id);

    Long save(UserRequest userRequest);

    void updateById(Long id, UserRequest userRequest);

    void deleteById(Long id);

}
