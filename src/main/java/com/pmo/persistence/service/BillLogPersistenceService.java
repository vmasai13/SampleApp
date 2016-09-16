package com.pmo.persistence.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.pmo.bean.BillLogBean;
import com.pmo.persistence.MongoDBPersistenceService;

/**
 * The persistence service class for the <code>InvoiceBean</code> domain object.
 * @author X075668
 *
 */
@Component
@Repository
public class BillLogPersistenceService extends MongoDBPersistenceService{

	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<BillLogBean> findAllInBetween(Date startDate, Date endDate) {
		Query query = new Query(Criteria.where("dateOfReport").gte(startDate).lte(endDate));
		query.with(new Sort(Sort.Direction.ASC, "dateOfReport"));
		return Collections.unmodifiableList(mongoTemplate.find(query, BillLogBean.class, "billLog"));
	}

	public List<BillLogBean> findAll() {
		Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "orderOfDisplay"));
        return Collections.unmodifiableList(mongoTemplate.find(query, BillLogBean.class, "billLog"));
	}

}
