package com.example.elastic.service;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.BootAppLoader;
import com.example.elastic.document.Creator;
import com.example.elastic.document.Request;
import com.example.elastic.repository.RequestDocumentRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootAppLoader.class)
public class RequestDocumentRepositoryTest {

	@Value("${spring.data.elasticsearch.properties.path.home}")
	private String localElasticSearchDirectory;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private RequestDocumentRepository classUnderTest;
	
	@Test
	public void testDocumentCombiningInAndORQueryCriterionThroughElasticSearchQuery() {
		
		Request request1 = new Request();
		request1.setId(1L);
		request1.setRequestName("requestname1");
		request1.setRequestDesc("desc2");				
		Creator creator1 = new Creator();
		creator1.setCode("code1");
		creator1.setName("name1");
		request1.setCreator(creator1);
		
		Request request2 = new Request();
		request2.setId(2L);
		request2.setRequestName("requestname2");
		request2.setRequestDesc("desc2");
		
		Creator creator2 = new Creator();
		creator2.setCode("code2");
		creator2.setName("name2");
		request2.setCreator(creator2);
		
		long id1 = classUnderTest.save(request1).getId();
		long id2 = classUnderTest.save(request2).getId();
		
		Assert.assertEquals(id1, classUnderTest.findOne(1L).getId());
		Assert.assertEquals(id2, classUnderTest.findOne(2L).getId());
			
		List<String> codes = new ArrayList<>();
		codes.add("code9");
		codes.add("code1");
				
		BoolQueryBuilder boolQueryBuilder  =  QueryBuilders.boolQuery();				
		for (String code : codes) {
			boolQueryBuilder .should(matchQuery("creator.code", code));
		}		
		QueryBuilder multiBuilder = multiMatchQuery("desc2").field("requestName").field("requestDesc").type(MultiMatchQueryBuilder.Type.BEST_FIELDS);						
		QueryBuilder builder = QueryBuilders.boolQuery().must(boolQueryBuilder).should(multiBuilder);        
        final SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
        List<Request> result =   elasticsearchTemplate.queryForList(searchQuery, Request.class);
		Assert.assertEquals(result.size(), 1);
	}
	
	@After
	public void after() {
		classUnderTest.deleteAll();
	}

}
