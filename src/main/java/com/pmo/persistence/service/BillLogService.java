package com.pmo.persistence.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.pmo.pdfextract.bean.BillLogBean;

@Component
public class BillLogService {
	
    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "billLog";

    /**
     * Save the record in database
     * @param billLogBean
     * @return
     */
    public BillLogBean save(BillLogBean billLogBean) {
        if (!mongoTemplate.collectionExists(COLLECTION_NAME)) {
            mongoTemplate.createCollection(COLLECTION_NAME);
        }
        if (billLogBean.getId() == null) {
        	billLogBean.setId(UUID.randomUUID().toString());
            mongoTemplate.insert(billLogBean, COLLECTION_NAME);
        }
        else {
            mongoTemplate.save(billLogBean, COLLECTION_NAME);
        }

        Query query = new Query(Criteria.where("_id").is(billLogBean.getId()));
        BillLogBean settingsBean = (BillLogBean) mongoTemplate.findOne(query, BillLogBean.class, COLLECTION_NAME);

        return settingsBean;
    }

    /**
     * Get the list of items with Indexing for Pagination. 
     * @param jtStartIndex
     * @param jtPageSize
     * @param jtSorting
     * @return
     */
    public List<BillLogBean> list(int jtStartIndex, int jtPageSize, String jtSorting) {
    	Query query = new Query();
    	query.skip(jtStartIndex);
    	query.limit(jtPageSize);
    	String stringArray[] = jtSorting.split(" ");
    	Sort sort = new Sort(Direction.fromString(stringArray[stringArray.length - 1]), stringArray[0]);
    	query.with(sort);
    	
    	List<BillLogBean> settingss = mongoTemplate.find(query, BillLogBean.class);
        return settingss;
    }

    /**
     * Deleting the particular record in the table.
     * @param id
     */
    public void delete(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, COLLECTION_NAME);
    }
    
    // Just for testing purpose
    public List<BillLogBean> listGet() {
        List<BillLogBean> settingss = mongoTemplate.findAll(BillLogBean.class);
        mongoTemplate.findAll(BillLogBean.class);
        return settingss;
    }
}
