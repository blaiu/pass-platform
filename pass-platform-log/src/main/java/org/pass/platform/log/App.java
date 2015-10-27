package org.pass.platform.log;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.FilteredQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightField;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gome.pass.platform.log.bean.LogInfo;
import com.gome.pass.platform.log.bean.Message;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Client client;
	
    public static void close() {
    	client.close();
    }
    
    @SuppressWarnings("resource")
	public static void init() {
    	client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("10.58.56.62", 9300));
    } 
    
	
	public static void main( String[] args ) throws ParseException, JsonParseException, JsonMappingException, IOException
    {
		init();
		
//		SearchResponse response = client.prepareSearch()
//		        .setTypes("type", "syslog")
//		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//		        .setQuery(QueryBuilders.termQuery("file", "b7aa4cd854fc8e5d1a4850baa95e2c9557dd00e5a53bcfb12c46fdd0ceab39a5"))             // Query
//		        .setPostFilter(FilterBuilders.rangeFilter("age").from(12).to(18))   // Filter
//		        .setFrom(0).setSize(60).setExplain(true)
//		        .execute()
//		        .actionGet();
		
		
//		QueryBuilder qb = termQuery("multi", "test");
//
//		SearchResponse scrollResp = client.prepareSearch(test)
//		        .setSearchType(SearchType.SCAN)
//		        .setScroll(new TimeValue(60000))
//		        .setQuery(qb)
//		        .setSize(100).execute().actionGet(); //100 hits per shard will be returned for each scroll
//		//Scroll until no hits are returned
//		while (true) {
//
//		    for (SearchHit hit : scrollResp.getHits().getHits()) {
//		        //Handle the hit...
//		    }
//		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
//		    //Break condition: No hits are returned
//		    if (scrollResp.getHits().getHits().length == 0) {
//		        break;
//		    }
//		}
		
		
		
//		SearchResponse response = client.prepareSearch()
//                .setTypes("syslog")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(QueryBuilders.termQuery("file", "b7aa4cd854fc8e5d1a4850baa95e2c9557dd00e5a53bcfb12c46fdd0ceab39a5")) // Query
//                .setScroll(new TimeValue(3000))
////                .setFilter(FilterBuilders.rangeFilter("age").from(20).to(30)) // Filter
//                .setFrom(0).setSize(60).setExplain(true).execute().actionGet();
//        SearchHits hits = response.getHits();
//        System.out.println(hits.getTotalHits());
//        for (int i = 0; i < hits.getHits().length; i++) {
//            System.out.println(hits.getHits()[i].getSourceAsString());
//        }
		
		ss ();
		
//		test ();
    }
	
	
	@SuppressWarnings("unused")
	public static void ss () throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		Date date = sdf.parse(" 2015-8-10 00:00:01 ");
		Date date2 = sdf.parse(" 2015-8-11 00:00:01 ");
		
		
		RangeQueryBuilder queryBuilder = new RangeQueryBuilder("timeRangeQueryBuilder").queryName("time");
		queryBuilder.from(date).to(date2);
		
		
		TermQueryBuilder tqb1 = QueryBuilders.termQuery("containers_id", "b24e7f873a3a33d284bf50adf41b0b8be4e0e1f8d16d5593779b04a5e3a6fa3d");
//		TermQueryBuilder tqb2 = QueryBuilders.termQuery("offset", "2956");
//		TermQueryBuilder tqb3 = QueryBuilders.termQuery("message", "main");
		SearchResponse scrollResp = client.prepareSearch()
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setScroll(new TimeValue(60000))
		        .setQuery(QueryBuilders.termQuery("containers_id", "b24e7f873a3a33d284bf50adf41b0b8be4e0e1f8d16d5593779b04a5e3a6fa3d"))
//		        .setQuery(QueryBuilders.boolQuery().must(tqb1))
//		        .setPostFilter(FilterBuilders.rangeFilter("@timestamp").from(date).to(date2))
//		        .setPostFilter(FilterBuilders.boolFilter().must(FilterBuilders.rangeFilter("@timestamp").from(date).to(date2)))
//		        .setPostFilter((FilterBuilders.rangeFilter("@timestamp").from("now-24h")))
		        .setExplain(true)
		        .execute()
		        .actionGet();
		while (true) {
		    for (SearchHit hit : scrollResp.getHits().getHits()) { 
		    	
//		    	Map<String, HighlightField> result = hit.highlightFields();    
//		          HighlightField titleField = result.get("message");    
//		          Text[] titleTexts =  titleField.fragments();    
//		            
//		          String name = "";  
//		            
//		          for(Text text : titleTexts){    
//		              name += text;  
//		          }    
//		    	System.out.println(name);
		    	System.out.println(hit.getSourceAsString());
		    }
		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
		    if (scrollResp.getHits().getHits().length == 0) {
//		    	ss ();
		    	break;
		    }
		}
	}
	
	
	@SuppressWarnings("unused")
	private static TermQueryBuilder getTermQueryBuilder() {
		
		
		TermQueryBuilder termQueryBuilder = new TermQueryBuilder("file", "b7aa4cd854fc8e5d1a4850baa95e2c9557dd00e5a53bcfb12c46fdd0ceab39a5");
		return termQueryBuilder;
	} 
	
	@SuppressWarnings("unused")
	public static void ad() {
		long current=System.currentTimeMillis() / 1000l;
	    Calendar ca = Calendar.getInstance();	//得到一个Calendar的实例  
	    ca.setTime(new Date());   				//设置时间为当前时间  
	    ca.add(Calendar.YEAR, -1); 				//年份减1  
	    Date lastYear = ca.getTime(); 
	    
//	    QueryBuilders.rangeQuery("@").from(from)
	    
	    QueryStringQueryBuilder queryBuilder = new QueryStringQueryBuilder("中国经济");
        queryBuilder.analyzer("ik").field("log");    
 
        FilteredQueryBuilder query = QueryBuilders.filteredQuery(queryBuilder, FilterBuilders.boolFilter().must(FilterBuilders.rangeFilter("updatetime").from(lastYear.getTime()/1000l).to(current)));
        
	}
	
	@SuppressWarnings("unused")
	public static void test () throws ParseException, JsonParseException, JsonMappingException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		Date date = sdf.parse(" 2015-8-1 13:58:49 ");
		Date date2 = sdf.parse(" 2015-8-5 00:00:01 ");
		SearchResponse scrollResp = client.prepareSearch()
			.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			.setTypes("syslog")
			.setQuery(QueryBuilders.termQuery("file", "b7aa4cd854fc8e5d1a4850baa95e2c9557dd00e5a53bcfb12c46fdd0ceab39a5"))
//			.setQuery(QueryBuilders.rangeQuery("@timestamp").from("\"2015-08-05T08:33:27.872Z\"").to("\"2015-08-11T08:33:27.872Z\""))
//			.setPostFilter(FilterBuilders.andFilter(FilterBuilders.termFilter("file", "b7aa4cd854fc8e5d1a4850baa95e2c9557dd00e5a53bcfb12c46fdd0ceab39a5")))
			.setPostFilter(FilterBuilders.rangeFilter("@timestamp").from("2015-08-05T08:33:27.872Z").to("2015-08-11T08:33:27.872Z"))
			.setScroll(new TimeValue(60000))
            .setSize(100)
            .setExplain(true)
			.execute()
			.actionGet();
		
		while (true) {
		    for (SearchHit hit : scrollResp.getHits().getHits()) { 
		    	System.out.println(hit.getSourceAsString());
		    	
		    	ObjectMapper om = new ObjectMapper();
		    	LogInfo li = om.readValue(hit.getSourceAsString(), LogInfo.class);
		    	System.out.println(li.getFile());
		    	Message m = om.readValue(li.getMessage(), Message.class);
//		    	System.out.println(m.getLog());  
		    	System.out.println("-----------------------");
		    	
		    }
		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
		    System.out.println(scrollResp.getScrollId());
		    if (scrollResp.getHits().getHits().length == 0) {
//		    	ss ();
		    	break;
		    }
		}
	}
}
 