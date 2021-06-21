package com.example.selling.data.repository;

import com.example.selling.common.CommonUtil;
import com.example.selling.common.VfData;
import com.example.selling.data.entity.Category;

import com.example.selling.domain.DataTableResults;
import com.example.selling.ultis.bean.CategoryBean;
import com.example.selling.ultis.form.FormCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public default DataTableResults<CategoryBean> getDatatable(VfData vfData, FormCategory form) {
        List<Object> paramList = new ArrayList<>();
        String sql = "SELECT ";
        sql += "    c.id as id, ";
        sql += "    c.name as name, ";
        sql += "    c.image as image, ";
        sql += "    c.description as description ";
        sql += "    FROM category c ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");
        if (!CommonUtil.isEmpty(form.getName())) {
            CommonUtil.filter(form.getName(), strCondition, paramList, "c.name");
        }
        String orderBy = " ORDER BY c.name DESC ";
        return vfData.findPaginationQuery(sql + strCondition.toString(), orderBy, paramList, CategoryBean.class);
    }

    ;

}
