package com.example.selling.data.repository;

import com.example.selling.common.CommonUtil;
import com.example.selling.common.VfData;
import com.example.selling.data.dto.BillOrderDTO;
import com.example.selling.data.entity.BillOrder;
import com.example.selling.domain.DataTableResults;
import com.example.selling.ultis.bean.BillOrderBean;
import com.example.selling.ultis.bean.CategoryBean;
import com.example.selling.ultis.form.FormCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BillOrderRepository extends JpaRepository<BillOrder, Integer> {
    public default DataTableResults<BillOrderBean> getDatatable(VfData vfData, BillOrderDTO form) {
        List<Object> paramList = new ArrayList<>();
        String sql = "SELECT ";
        sql += "    c.id as id, ";
        sql += "    c.price as price, ";
        sql += "    c.created_date as createdDate, ";
        sql += "    c.quantity as quantity, ";
        sql += "    c.description as description ";
        sql += "    FROM bill_order c ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");
        if (!CommonUtil.isEmpty(form.getPrice())) {
            CommonUtil.filter(form.getPrice(), strCondition, paramList, "c.price");
        }
        String orderBy = " ORDER BY c.price DESC ";
        return vfData.findPaginationQuery(sql + strCondition.toString(), orderBy, paramList, BillOrderBean.class);
    }
}
