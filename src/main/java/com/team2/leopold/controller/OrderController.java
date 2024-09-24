package com.team2.leopold.controller;

import com.team2.leopold.dto.RequestOrderDto;
import com.team2.leopold.entity.Order;
import com.team2.leopold.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /* 주문 정보 등록과 연계 */
    @PostMapping("order")
    public ResponseEntity<?> insertOrder(@RequestBody RequestOrderDto dto, HttpServletRequest request) {
        Order newOrder = new Order();
        newOrder.setReceiver(dto.getReceiver());
        newOrder.setReceiveMethod(dto.getReceiveMethod());
        newOrder.setZipcode(dto.getZipcode());
        newOrder.setAddress(dto.getAddress());
        newOrder.setAddressDetail(dto.getAddressDetail());
        newOrder.setPhone(dto.getPhone());
        newOrder.setEmail(dto.getEmail());
        newOrder.setMessage(dto.getMessage());
        newOrder.setDeliverPrice(dto.getDeliverPrice());
        newOrder.setStatus(dto.getStatus());
        newOrder.setFinalPrice(dto.getFinalPrice());
        newOrder.setPaymentMethod(dto.getPaymentMethod());
        newOrder.setAccount(dto.getAccount());
        newOrder.setHolder(dto.getHolder());

        orderService.insertOrder(newOrder);
        Integer newOrderUid = newOrder.getUid();

        HttpSession session = request.getSession(false);
        if (session == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않음.");

        try {
            orderService.connectOrderWithWish((Integer) session.getAttribute("userUid"), newOrderUid);
            return ResponseEntity.status(HttpStatus.OK).body("주문 정보 등록과 연계 완료!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(newOrderUid);
        }
    }

    /* 주문 정보 삭제 */
    @DeleteMapping("order")
    public ResponseEntity<?> deleteOrder(@RequestParam(name = "orderUid") Integer orderUid) {
        orderService.deleteOrder(orderUid);
        return ResponseEntity.status(HttpStatus.OK).body("주문 정보 삭제 완료!");
    }
}
