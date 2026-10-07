package p466.tacocloud.web;

import org.springframework.web.bind.annotation.*;
import p466.tacocloud.data.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.security.core.annotation.
        AuthenticationPrincipal;

import p466.tacocloud.User;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import p466.tacocloud.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
            @Valid TacoOrder order,
            Errors errors,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return " orderForm ";
        }

        order.setUserId(user.getId());

        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}

