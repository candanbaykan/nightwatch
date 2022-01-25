package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.common.utility.trimmer.annotation.Trim;
import com.ct.nightwatch.webapi.repository.UserRepository;
import com.ct.nightwatch.webapi.repository.entity.User;
import com.ct.nightwatch.webapi.service.UserService;
import com.ct.nightwatch.webapi.service.dto.UserDetails;
import com.ct.nightwatch.webapi.service.dto.UserRequest;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.UserMapper;
import com.ct.nightwatch.webapi.service.specification.UserSpecification;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;

    public DefaultUserService(UserRepository userRepository, UserMapper userMapper) {
        this.encoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @PreAuthorize("@authService.isAdmin() || " +
            "(#parameters['username'] != null && " +
            "#parameters['username'].equals(authentication.name))")
    public List<UserDetails> findAll(Map<String, String> parameters) {
        UserSpecification specification = new UserSpecification(parameters);
        return userRepository.findAll(specification).stream()
                .map(userMapper::toDetails)
                .collect(Collectors.toList());
    }

    @Override
    @PostAuthorize("@authService.isAdmin() || @authService.isEqualUser(#id)")
    public UserDetails findById(Long id) {
        return userRepository.findDetailsById(id)
                .map(userMapper::toDetails)
                .orElseThrow(() -> new EntityNotFoundException(id, User.class));
    }

    @Override
    @PreAuthorize("@authService.isAdmin()")
    public Long save(@Trim UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }


    @Override
    @PreAuthorize("@authService.isAdmin()")
    public void updateById(Long id, @Trim UserRequest userRequest) {
        if (!userRepository.existsById(id))
            throw new EntityNotFoundException(id, User.class);

        userRequest.setPassword(
                Optional.ofNullable(userRequest.getPassword())
                        .orElse(userRepository.findById(id)
                                .map(User::getPassword)
                                .orElseThrow(() -> new EntityNotFoundException(id, User.class)))
        );

        User user = userMapper.toEntity(id, userRequest);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @PreAuthorize("@authService.isAdmin()")
    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(id, User.class);
        }
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(), List.of(
                        new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())
                )))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
    }
}
