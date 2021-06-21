package com.example.selling.common;

import com.example.selling.domain.DataTableResults;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.List;

public interface VfData {
    public <T> DataTableResults<T>
    findPaginationQuery(String nativeQuery, String nativeQueryCount,
                        String orderBy, List<Object> paramList, Class obj);

    /**
     * @param findPaginationQuery
     * @param paramList
     * @param obj
     * @return
     */
    public <T> DataTableResults<T> findPaginationQuery(String nativeQuery, String orderBy, List<Object> paramList, Class obj);
//    public <T> DataTableResults<T> findPaginationQuery(String nativeQuery, String orderBy, Map<String, String> paramList, Class obj);


    /**
     * Get session.
     */
    public Session getSession();

    public SQLQuery createSQLQuery(String sql);

    /**
     * ham set result transformer cua cau query
     *
     * @param query cau query
     * @param obj   doi tuong
     */
    public void setResultTransformer(SQLQuery query, Class obj);

    /**
     * Get list alias column.
     *
     * @param query
     * @return
     */
    public List<String> getReturnAliasColumns(SQLQuery query);
}

