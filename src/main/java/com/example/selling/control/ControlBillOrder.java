package com.example.selling.control;

import com.example.selling.common.Response;
import com.example.selling.constants.Constants;
import com.example.selling.data.dto.BillOrderDTO;
import com.example.selling.data.entity.BillOrder;
import com.example.selling.data.repository.BillOrderRepository;
import com.example.selling.data.service.BillOrderService;
import com.example.selling.domain.DataTableResults;
import com.example.selling.ultis.bean.BillOrderBean;
import com.example.selling.ultis.bean.CategoryBean;
import com.example.selling.ultis.form.FormCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/billOrder")
@CrossOrigin("'http://localhost:4200")
public class ControlBillOrder {
    @Autowired
    private BillOrderService billOrderService;
    @Autowired
    private BillOrderRepository billOrderRepository;

    @GetMapping("/getListBillOrder")
    public @ResponseBody
    Response getList() {
        List<BillOrder> lst = billOrderRepository.findAll();
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(lst);
    }

    @PostMapping("/createBillOrder")
    public @ResponseBody
    Response saveOrUpdate(@RequestBody BillOrderDTO billOrderDTO) {
        BillOrder entity;
        if ((billOrderDTO.getId() <= 0)) {
            entity = billOrderRepository.findById(billOrderDTO.getId()).orElse(null);
        } else {
            entity = new BillOrder();
        }
        entity.setId(billOrderDTO.getId());
        entity.setPrice(billOrderDTO.getPrice());
        entity.setDescription(billOrderDTO.getDescription());
        entity.setQuantity(billOrderDTO.getQuantity());
        entity.setCreatedDate(new Date());
        billOrderRepository.save(entity);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

    @DeleteMapping("deleteBillOrder/{id}")
    public @ResponseBody
    Response deleteCategory(@PathVariable int id) {
        BillOrder billOrder = billOrderRepository.findById(id).orElse(null);
        if (Objects.isNull(billOrder)) {
            return new Response("Ban ghi da bi xoa");
        }
        billOrderRepository.deleteById(id);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

    @GetMapping("/search")
    public @ResponseBody
    DataTableResults<BillOrderBean> processSearch(BillOrderDTO form) {
        return billOrderService.getDataTables(form);
    }
}
