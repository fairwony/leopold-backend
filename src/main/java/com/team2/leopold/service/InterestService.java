package com.team2.leopold.service;

import com.team2.leopold.entity.Interest;
import com.team2.leopold.entity.Product;
import com.team2.leopold.entity.User;
import com.team2.leopold.repository.InterestRepository;
import com.team2.leopold.repository.ProductRepository;
import com.team2.leopold.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class InterestService {
    private InterestRepository interestRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Autowired
    public InterestService(InterestRepository interestRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.interestRepository = interestRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /* 관심 상품 등록 */
    @Transactional
    public void insertInterest(Integer userUid, Integer productUid) throws EntityNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userUid);
        if(optionalUser.isEmpty()) throw new EntityNotFoundException();
        User foundUser = optionalUser.get();

        Optional<Product> optionalProduct = productRepository.findById(productUid);
        if(optionalProduct.isEmpty()) throw new EntityNotFoundException();
        Product foundProduct = optionalProduct.get();

        Interest interest = new Interest();
        interest.setUser(foundUser);
        interest.setProduct(foundProduct);
        interestRepository.save(interest);
    }

    /* 관심 상품 삭제 */
    @Transactional
    public void deleteInterest(Integer interestUid) throws EntityNotFoundException {
        Optional<Interest> optionalInterest = interestRepository.findById(interestUid);
        if(optionalInterest.isEmpty()) throw new EntityNotFoundException();

        interestRepository.deleteById(interestUid);
    }

    /* 관심 상품 목록 */

}