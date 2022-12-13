package org.example.Esdao;

import org.example.entity.es.Entity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author: zyh
 * @date: 2022/12/13
 */
@Component
public interface EntityRespository extends ElasticsearchRepository<Entity,Long> {
}
