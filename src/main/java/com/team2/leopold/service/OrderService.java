package com.team2.leopold.service;

import com.team2.leopold.entity.Cart;
import com.team2.leopold.entity.Order;
import com.team2.leopold.entity.User;
import com.team2.leopold.entity.Wish;
import com.team2.leopold.repository.CartRepository;
import com.team2.leopold.repository.OrderRepository;
import com.team2.leopold.repository.WishRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private OrderRepository orderRepository;
    private WishRepository wishRepository;
    private CartRepository cartRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, WishRepository wishRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.wishRepository = wishRepository;
        this.cartRepository = cartRepository;
    }

    /* 주문 정보 등록 */
    @Transactional
    public void insertOrder(Order order) {
        orderRepository.save(order);
    }


    /* 주문 정보와 주문 상품 연계 */
    @Transactional
    public void connectOrderWithWish(Integer userUid, Integer orderUid) throws EntityNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(orderUid);
        if (optionalOrder.isEmpty()) throw new EntityNotFoundException();
        Order foundOrder = optionalOrder.get();

        List<Wish> wishList = wishRepository.findWishList(userUid);
        for (Wish w : wishList) {
            w.setOrder(foundOrder);
            w.setPrice(w.getCart().getProduct().getPrice());
            w.setDiscountRate(w.getCart().getProduct().getDiscountRate());
            w.setOrderedYn("y");
            w.getCart().setUsedYn("y");
            w.getCart().setOrder(foundOrder);
            wishRepository.save(w);
        }
    }

    /* 주문 정보 삭제 */
    @Transactional
    public void deleteOrder(Integer orderUid) {
        orderRepository.deleteById(orderUid);
    }
}
