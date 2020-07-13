package cn.jorian.jorianframework.core.elsearch.service.impl;

import cn.jorian.jorianframework.core.elsearch.mapper.SearchSysUserMapper;
import cn.jorian.jorianframework.core.elsearch.service.SearchUserService;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: jorian
 * @date: 2019/11/20 14:47
 * @description: this is  description for the class
 */
@Service
public class SearchUserServiceImpl implements SearchUserService {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    SearchSysUserMapper searchSysUserMapper;

    @Autowired
    private JestClient jestClient;

    /**
     * ElasticsearchRepository 方式实现
     * @param q
     * @return
     */
    @Override
    public List<SysUser> searchOne(String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<SysUser> searchResult = searchSysUserMapper.search(builder);
        Iterator<SysUser> iterator = searchResult.iterator();
        List<SysUser> list = new ArrayList<SysUser>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * elasticsearchTemplate 方式实现
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> searchAll() {
        // 这一步是最关键的
        Client client = elasticsearchTemplate.getClient();

        SearchRequestBuilder srb = client.prepareSearch("blog")
                .setTypes("sysUser");
        // 查询所有
        SearchResponse sr = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
        SearchHits hits = sr.getHits();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSource();
            list.add(source);
            System.out.println(hit.getSourceAsString());
        }
        return list;
    }

    /**
     * 通过jest实现查询
     * @return
     * @throws IOException
     */
    @Override
    public String searchByJest() throws IOException {

        String searchJson = "{\n" +
                "    \"query\": {\n" +
                "        \"match\": {\n" +
                "            \"title\": \"的\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建一个搜索
        Search search = new Search.Builder(searchJson).addIndex("blog").addType("sysUser").build();
        //执行
        SearchResult result = null;
        result = jestClient.execute(search);
        return result.getJsonString();
    }

    @Override
    public String add(SysUser sysUser) throws IOException {

        //构建一个索引
        Index index = new Index.Builder(sysUser).index("blog").type("sysUser").build();
        //执行
        DocumentResult result =jestClient.execute(index);
        return result.getJsonString();
    }
}
