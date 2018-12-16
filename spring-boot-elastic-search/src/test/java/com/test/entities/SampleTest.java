package com.test.entities;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.BootAppLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootAppLoader.class)
public class SampleTest {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	
	@Before
	public void before() {
			
		elasticsearchTemplate.deleteIndex(Person.class);
		elasticsearchTemplate.createIndex(Person.class);
		elasticsearchTemplate.putMapping(Person.class);
		elasticsearchTemplate.refresh(Person.class);		
	
	}
	
	@Test
	public void test() {

		final List<Car> cars = new ArrayList<Car>();
		final Car saturn = new Car();
		saturn.setName("Saturn");
		saturn.setModel("SL");
		final Car subaru = new Car();
		subaru.setName("Subaru");
		subaru.setModel("Imprezza");	
		cars.add(saturn);
		cars.add(subaru);		
		
		final Person foo = new Person();
		foo.setName("Foo");
		foo.setId("1");
		foo.setCar(cars);
		
		

		
	}
	
	@Test
	public void shouldIndexInitialLevelNestedObject() {

		final List<Car> cars = new ArrayList<Car>();

		final Car saturn = new Car();
		saturn.setName("Saturn");
		saturn.setModel("SL");

		final Car subaru = new Car();
		subaru.setName("Subaru");
		subaru.setModel("Imprezza");

		final Car ford = new Car();
		ford.setName("Ford");
		ford.setModel("Focus");

		cars.add(saturn);
		cars.add(subaru);
		cars.add(ford);

		final Person foo = new Person();
		foo.setName("Foo");
		foo.setId("1");
		foo.setCar(cars);

		final Car car = new Car();
		car.setName("Saturn");
		car.setModel("Imprezza");

		final Person bar = new Person();
		bar.setId("2");
		bar.setName("Bar");
		bar.setCar(Arrays.asList(car));

		final List<IndexQuery> indexQueries = new ArrayList<IndexQuery>();
		final IndexQuery indexQuery1 = new IndexQuery();
		indexQuery1.setId(foo.getId());
		indexQuery1.setObject(foo);

		final IndexQuery indexQuery2 = new IndexQuery();
		indexQuery2.setId(bar.getId());
		indexQuery2.setObject(bar);

		indexQueries.add(indexQuery1);
		indexQueries.add(indexQuery2);

		elasticsearchTemplate.putMapping(Person.class);
		elasticsearchTemplate.bulkIndex(indexQueries);
		elasticsearchTemplate.refresh(Person.class);
		
		final QueryBuilder builder = nestedQuery("car", boolQuery().must(termQuery("car.name", "saturn")).must(termQuery("car.model", "sl")));
		
		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
		final List<Person> persons = elasticsearchTemplate.queryForList(searchQuery, Person.class);
		
		System.out.println(persons);
	}
}
