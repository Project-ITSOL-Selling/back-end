package com.example.selling.common;

import com.example.selling.constants.SearchParams;
import com.example.selling.domain.DataTableResults;
import com.google.gson.Gson;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class VfDatalml implements VfData {
    private static final Logger LOGGER = LoggerFactory.getLogger(VfDatalml.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private HttpServletRequest req;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public SQLQuery createSQLQuery(String sql) {
        return getSession().createSQLQuery(sql);
    }

    @Override
    public void setResultTransformer(SQLQuery query, Class obj) {
        Field[] fileds = obj.getDeclaredFields();
        Map<String, String> mapFileds = new HashMap();
        for (Field filed : fileds) {
            mapFileds.put(filed.getName(), filed.getGenericType().toString());
        }
        List<String> aliasColumns = getReturnAliasColumns(query);
        for (String aliasColumn : aliasColumns) {
            String dataType = mapFileds.get(aliasColumn);
            if (dataType == null) {
                LOGGER.debug(aliasColumn + " is not defined");
            } else {
                Type hbmType = null;
                if ("class java.lang.Long".equals(dataType)) {
                    hbmType = LongType.INSTANCE;
                } else if ("class java.lang.Integer".equals(dataType)) {
                    hbmType = IntegerType.INSTANCE;
                } else if ("class java.lang.Double".equals(dataType)) {
                    hbmType = DoubleType.INSTANCE;
                } else if ("class java.lang.String".equals(dataType)) {
                    hbmType = StringType.INSTANCE;
                } else if ("class java.lang.Boolean".equals(dataType)) {
                    hbmType = BooleanType.INSTANCE;
                } else if ("class java.util.Date".equals(dataType)) {
                    hbmType = TimestampType.INSTANCE;
                }
                if (hbmType == null) {
                    LOGGER.debug(dataType + " is not supported");
                } else {
                    query.addScalar(aliasColumn, hbmType);
                }
            }
        }
        query.setResultTransformer(Transformers.aliasToBean(obj));

    }

    @Override
    public List<String> getReturnAliasColumns(SQLQuery query) {
        List<String> aliasColumns = new ArrayList();
        String sqlQuery = query.getQueryString();
        sqlQuery = sqlQuery.replace("\n", " ");
        sqlQuery = sqlQuery.replace("\t", " ");
        int numOfRightPythis = 0;
        int startPythis = -1;
        int endPythis = 0;
        boolean hasRightPythis = true;
        while (hasRightPythis) {
            char[] arrStr = sqlQuery.toCharArray();
            hasRightPythis = false;
            int idx = 0;
            for (char c : arrStr) {
                if (idx > startPythis) {
                    if ("(".equalsIgnoreCase(String.valueOf(c))) {
                        if (numOfRightPythis == 0) {
                            startPythis = idx;
                        }
                        numOfRightPythis++;
                    } else if (")".equalsIgnoreCase(String.valueOf(c))) {
                        if (numOfRightPythis > 0) {
                            numOfRightPythis--;
                            if (numOfRightPythis == 0) {
                                endPythis = idx;
                                break;
                            }
                        }
                    }
                }
                idx++;
            }
            if (endPythis > 0) {
                sqlQuery = sqlQuery.substring(0, startPythis) + " # " + sqlQuery.substring(endPythis + 1);
                hasRightPythis = true;
                endPythis = 0;
            }
        }
        String arrStr[] = sqlQuery.substring(0, sqlQuery.toUpperCase().indexOf(" FROM ")).split(",");
        for (String str : arrStr) {
            String[] temp = str.trim().split(" ");
            String alias = temp[temp.length - 1].trim();
            if (alias.contains(".")) {
                alias = alias.substring(alias.lastIndexOf(".") + 1).trim();
            }
            if (alias.contains(",")) {
                alias = alias.substring(alias.lastIndexOf(",") + 1).trim();
            }
            if (alias.contains("`")) {
                alias = alias.replace("`", "");
            }
            if (!aliasColumns.contains(alias)) {
                aliasColumns.add(alias);
            }
        }
        return aliasColumns;
    }

    @Override
    public <T> DataTableResults<T> findPaginationQuery(String nativeQuery, String nativeQueryCount, String orderBy, List<Object> paramList, Class obj) {
        return null;
    }

    @Override
    public <T> DataTableResults<T> findPaginationQuery(String nativeQuery, String orderBy,
                                                       List<Object> paramList, Class obj) {
        return findPagination(nativeQuery, orderBy, paramList, obj, 5);
    }

    private <T> DataTableResults<T> findPagination(String nativeQuery, String orderBy,
                                                   List<Object> paramList, Class obj, int limit) {
        String _search = req.getParameter("_search");
        SearchParams searchParams = new SearchParams();
        if (!CommonUtil.isNullOrEmpty(_search)) {
            searchParams = new Gson().fromJson(_search, SearchParams.class);
        }
        String paginatedQuery = CommonUtil.buildPaginatedQuery(nativeQuery, orderBy, searchParams);
        String countStrQuery = CommonUtil.buildCountQuery(nativeQuery);
        SQLQuery query = createSQLQuery(paginatedQuery);
        setResultTransformer(query, obj);
        // pagination
        query.setFirstResult(CommonUtil.NVL(searchParams.getFirst()));
        query.setMaxResults(CommonUtil.NVL(searchParams.getRows(), limit));
        SQLQuery countQuery = createSQLQuery(countStrQuery);
        if (!CommonUtil.isNullOrEmpty(paramList)) {
            int paramSize = paramList.size();
            for (int i = 0; i < paramSize; i++) {
                countQuery.setParameter(i + 1, paramList.get(i));
                query.setParameter(i + 1, paramList.get(i));
            }
        }
        List<T> userList = query.list();
        Object totalRecords = countQuery.uniqueResult();

        DataTableResults<T> dataTableResult = new DataTableResults<T>();
        dataTableResult.setData(userList);
        if (!CommonUtil.isEmpty(userList)) {
            dataTableResult.setRecordsTotal(String.valueOf(totalRecords));
            dataTableResult.setRecordsFiltered(String.valueOf(totalRecords));
            dataTableResult.setFirst(String.valueOf(CommonUtil.NVL(searchParams.getFirst())));
        } else {
            dataTableResult.setRecordsFiltered("0");
            dataTableResult.setRecordsTotal("0");
        }

        return dataTableResult;
    }


}
