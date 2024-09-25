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

    /* 포인트 차감 */
    @Transactional
    public void reducePoint(Integer amount, Integer userUid) {
        User foundUser = repository.findById(userUid).get();
        foundUser.setPoint(foundUser.getPoint() - amount);
        repository.save(foundUser);
    }

    /* 포인트 증가 */
    @Transactional
    public void increasePoint(Integer amount, Integer userUid) {
        User foundUser = repository.findById(userUid).get();
        foundUser.setPoint(foundUser.getPoint() + amount);
        repository.save(foundUser);
    }

    /* 유저 정보 반환 */
    public User findUser(Integer userUid) {
        return repository.findById(userUid).get();
    }
}