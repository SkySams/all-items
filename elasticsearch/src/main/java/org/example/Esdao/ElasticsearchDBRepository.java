package org.example.Esdao;

import org.example.entity.es.ElasticsearchDB;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: zyh
 * @date: 2022/12/13
 */
@Component
public interface ElasticsearchDBRepository extends ElasticsearchRepository<ElasticsearchDB,Long>{

    long countByName(String name);

    long deleteByName(String name);

    List<ElasticsearchDB> removeByName(String name);


}
