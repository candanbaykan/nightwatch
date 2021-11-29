package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.repository.UserRepository;
import com.ct.nightwatch.webapi.repository.entity.User;
import com.ct.nightwatch.webapi.service.dto.UserDetails;
import com.ct.nightwatch.webapi.service.dto.UserRequest;
import com.ct.nightwatch.webapi.service.dto.UserSummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public DefaultUserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserSummary> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDetails> findById(Long id) {
        return userRepository.findDetailsById(id)
                .map(userMapper::toDetails);
    }

    @Override
    public Long save(UserRequest userRequest) {
        return userRepository.save(userMapper.toEntity(userRequest)).getId();
    }


    @Override
    @Transactional
    public Optional<UserDetails> updateById(Long id, UserRequest userRequest) {
        if (!userRepository.existsById(id))
            throw new EntityNotFoundException(id, User.class);

        userRepository.save(userMapper.toEntity(id, userRequest));
        return findById(id);
    }

    @Override
    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(id, User.class);
        }
    }

}
