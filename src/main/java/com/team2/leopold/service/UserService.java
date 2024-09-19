package com.team2.leopold.service;

import com.team2.leopold.entity.User;
import com.team2.leopold.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /* 회원가입 */
    @Transactional
    public void join(User user) throws DuplicateKeyException {
        Optional<User> optionalUser = repository.findById(user.getId());
        if (optionalUser.isPresent()) throw new DuplicateKeyException(null);

        repository.save(user);
    }

    /* 로그인 */
    public User login(User user) throws AuthenticationException {
        Optional<User> optionalUser = repository.findById(user.getId());

        if (optionalUser.isEmpty()) throw new AuthenticationException();
        User foundUser = optionalUser.get();

        if (!foundUser.getPassword().equals(user.getPassword())) throw new AuthenticationException();
        return foundUser;
    }
}