package ru.gil.tacocloud.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.gil.tacocloud.cofiguration.OrderProps;
import ru.gil.tacocloud.model.TacoOrder;
import ru.gil.tacocloud.model.Users;
import ru.gil.tacocloud.service.TacoOrderService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@RequiredArgsConstructor
public class OrderController {

    private final TacoOrderService tacoOrderService;

    private final OrderProps props;


    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder,
                               Errors errors,
                               @AuthenticationPrincipal Users user,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        tacoOrder.setUsers(user);

        tacoOrderService.createTacoOrder(tacoOrder);
        log.info("Order submitted {}", tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal Users user, Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        List<TacoOrder> tacoOrders = tacoOrderService.getTacoOrdersForUser(user, pageable);
        model.addAttribute("orders", tacoOrders);
        return "orderList";
    }

}
