package com.example.selling.data.repository;


import com.example.selling.common.CommonUtil;
import com.example.selling.common.VfData;
import com.example.selling.data.entity.Product;
import com.example.selling.domain.DataTableResults;
import com.example.selling.ultis.bean.CategoryBean;
import com.example.selling.ultis.bean.ProductBean;
import com.example.selling.ultis.form.FormCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public default DataTableResults<ProductBean> getDatatable(VfData vfData, FormCategory form) {
        List<Object> paramList = new ArrayList<>();
        String sql = "SELECT ";
        sql += "    p.id as id, ";
        sql += "    p.name as name  , ";
        sql += "    p.image as image , ";
        sql += "    p.price as price , ";
        sql += "    p.quantity as quantity , ";
        sql += "    p.id_supplier as idSupplier , ";
        sql += "    c.name as nameCategory ";
        sql += "    FROM product as p ";
        sql += "    JOIN category as c ";
        sql += "    ON c.id = p.id_category ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");
        if (!CommonUtil.isEmpty(form.getName())) {
            CommonUtil.filter(form.getName(), strCondition, paramList, "p.name");
        }
        String orderBy = " ORDER BY p.name DESC ";
        return vfData.findPaginationQuery(sql + strCondition.toString(), orderBy, paramList, ProductBean.class);
    }

}
