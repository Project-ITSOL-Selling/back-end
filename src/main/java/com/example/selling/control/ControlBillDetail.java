package com.example.selling.control;

import com.example.selling.common.Response;
import com.example.selling.constants.Constants;
import com.example.selling.data.dto.BillDetailDTO;
import com.example.selling.data.entity.BillDetail;
import com.example.selling.data.repository.BillDetailRepository;
import com.example.selling.data.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/billDetail")
@CrossOrigin("*")
public class ControlBillDetail {
    @Autowired
    private BillDetailService billDetailService;
    @Autowired
    private BillDetailRepository billDetailRepository;

    @GetMapping("/getListBillDetail")
    public @ResponseBody
    Response getList() {
        List<BillDetail> lst = billDetailRepository.findAll();
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(lst);
    }

    @PostMapping("/createBillDetail")
    public @ResponseBody
    Response saveOrUpdate(@RequestBody BillDetailDTO billDetailDTO) {
        BillDetail entity;
        if ((billDetailDTO.getId() <= 0)) {
            entity = billDetailRepository.findById(billDetailDTO.getId()).orElse(null);
        } else {
            entity = new BillDetail();
        }
        entity.setId(billDetailDTO.getId());
        entity.setIdBillOrder(billDetailDTO.getIdBillOrder());
        entity.setIdProduct(billDetailDTO.getIdProduct());
        entity.setCreatedDate(billDetailDTO.getCreatedDate());
        entity.setTotalMoney(billDetailDTO.getTotalMoney());
        billDetailRepository.save(entity);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

    @DeleteMapping("deleteBillDetail/{id}")
    public @ResponseBody
    Response deleteCategory(@PathVariable int id) {
        BillDetail billDetail = billDetailRepository.findById(id).orElse(null);
        if (Objects.isNull(billDetail)) {
            return new Response("Ban ghi da bi xoa");
        }
        billDetailRepository.deleteById(id);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }
}
