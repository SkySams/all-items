package org.example.controller.graph;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.graph.GraphExploreRequest;
import org.elasticsearch.client.graph.GraphExploreResponse;
import org.elasticsearch.client.graph.Hop;
import org.elasticsearch.client.graph.VertexRequest;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/12/12
 */
@Api(tags = "GRAPH")
@RestController
@RequestMapping("/grash")
public class GraphController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("勘探")
    @GetMapping()
    public GraphExploreResponse explore() throws Exception {

        GraphExploreRequest request = new GraphExploreRequest();
        request.indices("index1", "index2");
        request.useSignificance(false);
        TermQueryBuilder startingQuery = new TermQueryBuilder("text", "projectx");

        Hop hop1 = request.createNextHop(startingQuery);
        VertexRequest people = hop1.addVertexRequest("participants");
        people.minDocCount(1);
        VertexRequest files = hop1.addVertexRequest("attachment_md5");
        files.minDocCount(1);

        Hop hop2 = request.createNextHop(null);
        VertexRequest vr2 = hop2.addVertexRequest("participants");
        vr2.minDocCount(5);
        GraphExploreResponse exploreResponse = client.graph().explore(request, RequestOptions.DEFAULT);
        return exploreResponse;
    }

}
