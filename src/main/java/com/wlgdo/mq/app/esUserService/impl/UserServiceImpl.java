package com.wlgdo.mq.app.esUserService.impl;

import com.wlgdo.mq.app.esUserService.DocumentSearchRepository;
import com.wlgdo.mq.app.esUserService.IUserService;
import com.wlgdo.mq.app.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private DocumentSearchRepository documentSearchRepository;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public Users save(Users user) {
        Users save = elasticsearchRestTemplate.save(user);

//        Users user1 = documentSearchRepository.save(user);
        return save;
    }

    @Override
    public Users query(String userId) {
        Users users = documentSearchRepository.findById(userId).get();
        return users;
    }

    @Override
    public boolean update(Users user) {
        return false;
    }
}
