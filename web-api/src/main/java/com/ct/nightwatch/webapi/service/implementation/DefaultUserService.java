package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.repository.UserRepository;
import com.ct.nightwatch.webapi.repository.entity.User;
import com.ct.nightwatch.webapi.service.UserService;
import com.ct.nightwatch.webapi.service.dto.UserDetails;
import com.ct.nightwatch.webapi.service.dto.UserRequest;
import com.ct.nightwatch.webapi.service.dto.UserSummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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
        User user = userMapper.toEntity(userRequest);
        return userRepository.save(user).getId();
    }


    @Override
    public void updateById(Long id, UserRequest userRequest) {
        if (!userRepository.existsById(id))
            throw new EntityNotFoundException(id, User.class);

        userRequest.setPassword(
                Optional.ofNullable(userRequest.getPassword())
                        .orElse(userRepository.findById(id)
                                .map(User::getPassword)
                                .orElseThrow(() -> new EntityNotFoundException(id, User.class)))
        );

        User user = userMapper.toEntity(id, userRequest);
        userRepository.save(user);
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
