package com.example.selling.control;

import com.example.selling.common.Response;
import com.example.selling.constants.Constants;
import com.example.selling.data.dto.OrderDTO;
import com.example.selling.data.entity.DBOOrder;
import com.example.selling.data.repository.OrderRepository;
import com.example.selling.data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/order")
@CrossOrigin("'http://localhost:4200")
public class ControlOrder {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/getListOrder")
    public @ResponseBody
    Response getList() {
        List<DBOOrder> lst = orderRepository.findAll();
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(lst);
    }

    @PostMapping("/createOrder")
    public @ResponseBody
    Response saveOrUpdate(@RequestBody OrderDTO orderDTO) {
        DBOOrder entity;
        if ((orderDTO.getId() > 0)) {
            entity = orderRepository.findById(orderDTO.getId()).orElse(null);
        } else {
            entity = new DBOOrder();
        }
        entity.setId(orderDTO.getId());
        entity.setTotalMoney(orderDTO.getTotalMoney());
        entity.setIdCustomer(orderDTO.getIdCustomer());
        entity.setCreatedDate(orderDTO.getCreatedDate());
        entity.setDescription(orderDTO.getDescription());
        orderRepository.save(entity);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

    @DeleteMapping("deleteOrder/{id}")
    public @ResponseBody
    Response deleteCategory(@PathVariable int id) {
        DBOOrder DBOOrder = orderRepository.findById(id).orElse(null);
        if (Objects.isNull(DBOOrder)) {
            return new Response("Ban ghi da bi xoa");
        }
        orderRepository.deleteById(id);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }
}
