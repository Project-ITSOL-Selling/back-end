package com.example.selling.control;

import com.example.selling.common.Response;
import com.example.selling.constants.Constants;
import com.example.selling.data.dto.OrderDetailDTO;
import com.example.selling.data.entity.OrderDetail;
import com.example.selling.data.repository.OrderDetailRepository;
import com.example.selling.data.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/orderDetail")
@CrossOrigin("'http://localhost:4200")
public class ControlOrderDetail {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("/getListOrderDetail")
    public @ResponseBody
    Response getList() {
        List<OrderDetail> lst = orderDetailRepository.findAll();
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(lst);
    }

    @PostMapping("/createOrderDetail")
    public @ResponseBody
    Response saveOrUpdate(@RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetail entity;
        if ((orderDetailDTO.getId() <= 0)) {
            entity = orderDetailRepository.findById(orderDetailDTO.getId()).orElse(null);
        } else {
            entity = new OrderDetail();
        }
        entity.setId(orderDetailDTO.getId());
        entity.setQuantity(orderDetailDTO.getQuantity());
        entity.setIdProduct(orderDetailDTO.getIdProduct());
        entity.setTotalMoney(orderDetailDTO.getTotalMoney());
        entity.setIdOrder(orderDetailDTO.getIdOrder());
        orderDetailRepository.save(entity);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

    @DeleteMapping("deleteOrderDetail/{id}")
    public @ResponseBody
    Response deleteCategory(@PathVariable int id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElse(null);
        if (Objects.isNull(orderDetail)) {
            return new Response("Ban ghi da bi xoa");
        }
        orderDetailRepository.deleteById(id);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

}
