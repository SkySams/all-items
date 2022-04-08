package org.example.Esdao;

import org.example.Elasticsearch;
import org.example.entity.es.ProductEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: zyh
 * @date: 2022/4/8
 */
@Component
public interface ProductRepository extends ElasticsearchRepository<ProductEs, String> {

    List<ProductEs> findByName(String name);

}
