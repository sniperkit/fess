/*
 * Copyright 2012-2018 CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.codelibs.fess.es.config.cbean.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractConditionBean;
import org.codelibs.fess.es.config.bsentity.dbmeta.DataConfigToRoleDbm;
import org.codelibs.fess.es.config.cbean.DataConfigToRoleCB;
import org.codelibs.fess.es.config.cbean.ca.DataConfigToRoleCA;
import org.codelibs.fess.es.config.cbean.ca.bs.BsDataConfigToRoleCA;
import org.codelibs.fess.es.config.cbean.cq.DataConfigToRoleCQ;
import org.codelibs.fess.es.config.cbean.cq.bs.BsDataConfigToRoleCQ;
import org.dbflute.cbean.ConditionQuery;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author ESFlute (using FreeGen)
 */
public class BsDataConfigToRoleCB extends EsAbstractConditionBean {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected BsDataConfigToRoleCQ _conditionQuery;
    protected BsDataConfigToRoleCA _conditionAggregation;
    protected HpSpecification _specification;

    // ===================================================================================
    //                                                                             Control
    //                                                                             =======
    @Override
    public DataConfigToRoleDbm asDBMeta() {
        return DataConfigToRoleDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "data_config_to_role";
    }

    @Override
    public boolean hasSpecifiedColumn() {
        return _specification != null;
    }

    @Override
    public ConditionQuery localCQ() {
        return doGetConditionQuery();
    }

    // ===================================================================================
    //                                                                         Primary Key
    //                                                                         ===========
    public DataConfigToRoleCB acceptPK(String id) {
        assertObjectNotNull("id", id);
        BsDataConfigToRoleCB cb = this;
        cb.query().docMeta().setId_Equal(id);
        return (DataConfigToRoleCB) this;
    }

    @Override
    public void acceptPrimaryKeyMap(Map<String, ? extends Object> primaryKeyMap) {
        acceptPK((String) primaryKeyMap.get("_id"));
    }

    // ===================================================================================
    //                                                                               Build
    //                                                                               =====

    @Override
    public SearchRequestBuilder build(SearchRequestBuilder builder) {
        if (_conditionQuery != null) {
            QueryBuilder queryBuilder = _conditionQuery.getQuery();
            if (queryBuilder != null) {
                builder.setQuery(queryBuilder);
            }
            _conditionQuery.getFieldSortBuilderList().forEach(sort -> {
                builder.addSort(sort);
            });
        }

        if (_conditionAggregation != null) {
            _conditionAggregation.getAggregationBuilderList().forEach(builder::addAggregation);
        }

        if (_specification != null) {
            builder.setFetchSource(_specification.columnList.toArray(new String[_specification.columnList.size()]), null);
        }

        return builder;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    public BsDataConfigToRoleCQ query() {
        assertQueryPurpose();
        return doGetConditionQuery();
    }

    protected BsDataConfigToRoleCQ doGetConditionQuery() {
        if (_conditionQuery == null) {
            _conditionQuery = createLocalCQ();
        }
        return _conditionQuery;
    }

    protected BsDataConfigToRoleCQ createLocalCQ() {
        return new DataConfigToRoleCQ();
    }

    // ===================================================================================
    //                                                                         Aggregation
    //                                                                         ===========
    public BsDataConfigToRoleCA aggregation() {
        assertAggregationPurpose();
        return doGetConditionAggregation();
    }

    protected BsDataConfigToRoleCA doGetConditionAggregation() {
        if (_conditionAggregation == null) {
            _conditionAggregation = createLocalCA();
        }
        return _conditionAggregation;
    }

    protected BsDataConfigToRoleCA createLocalCA() {
        return new DataConfigToRoleCA();
    }

    // ===================================================================================
    //                                                                             Specify
    //                                                                             =======
    public HpSpecification specify() {
        assertSpecifyPurpose();
        if (_specification == null) {
            _specification = new HpSpecification();
        }
        return _specification;
    }

    protected void assertQueryPurpose() {
    }

    protected void assertAggregationPurpose() {
    }

    protected void assertSpecifyPurpose() {
    }

    public static class HpSpecification {
        private List<String> columnList = new ArrayList<>();

        private void doColumn(String name) {
            columnList.add(name);
        }

        public void columnId() {
            doColumn("_id");
        }

        public void columnDataConfigId() {
            doColumn("dataConfigId");
        }

        public void columnRoleTypeId() {
            doColumn("roleTypeId");
        }
    }
}
