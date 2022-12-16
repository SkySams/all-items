package org.example.esdao;

/**
 * @author: zyh
 * @date: 2022/12/5
 */
import org.example.entity.es.ShoppingEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends ElasticsearchRepository<ShoppingEs,Long> {
}
