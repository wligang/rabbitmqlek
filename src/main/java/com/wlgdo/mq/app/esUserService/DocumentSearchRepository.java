package com.wlgdo.mq.app.esUserService;

import com.wlgdo.mq.app.model.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface  DocumentSearchRepository extends ElasticsearchRepository<Users,String> {
}
