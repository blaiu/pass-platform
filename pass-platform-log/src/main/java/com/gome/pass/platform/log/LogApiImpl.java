/**
 * 
 */
package com.gome.pass.platform.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.lang3.StringUtils;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gome.pass.platform.log.bean.LogInfo;
import com.gome.pass.platform.log.bean.Message;

/*
 * @author bailu-ds
 *
 */
public class LogApiImpl implements LogApi {

	private String address;
	
	private Integer port;
	
	private static final ThreadLocal<Client> tl = new ThreadLocal<Client>();
	
	public Client getClient() {
		Client client = tl.get();
		if (null == client) {
			client = initClient ();
			tl.set(client);
		}
		return client;
	}
	
	@SuppressWarnings("resource")
	private Client initClient () {
		if (null == address || "".equals(address.trim())) {
			address = LogConstant.address;
		}
		if (null == port) {
			port = LogConstant.port;
		}
		Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(address, port));
		return client;
	}
	
	@Override
	public List<Message> getLog(String file, String scrollId, Date startDate, String keyword) throws JsonParseException, JsonMappingException, IOException {
		Client client = initClient();
		if (null != scrollId && !"".equals(scrollId)) {
			return getLog(client, scrollId, keyword);
		}
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch();
		searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		setFiledQuery(searchRequestBuilder, file, keyword);
		setRangeFilterTime(searchRequestBuilder, startDate, LogTimeType.M);
		sethighlighter(searchRequestBuilder, "message");
		setScroll(searchRequestBuilder);
		setSize(searchRequestBuilder, 100);
		setExplain(searchRequestBuilder);
		SearchResponse scrollResp = searchRequestBuilder.execute().actionGet();
		return getRecords(scrollResp, keyword);
	}
	
	private List<Message> getLog(Client client, String scrollId, String title) throws JsonParseException, JsonMappingException, IOException {
		SearchResponse scrollResp = client.prepareSearchScroll(scrollId).setScroll(new TimeValue(600000)).execute().actionGet();
		return getRecords(scrollResp, title);
	}
	
	private List<Message> getRecords(SearchResponse scrollResp, String title) throws JsonParseException, JsonMappingException, IOException {
		List<Message> lists = new ArrayList<Message>();
		for (SearchHit hit : scrollResp.getHits().getHits()) { 
			ObjectMapper om = new ObjectMapper();
			LogInfo li = om.readValue(hit.getSourceAsString(), LogInfo.class);
//			Map<String, HighlightField> result = hit.highlightFields(); 
//			String log = "";
//			if (result.size() > 0) {
//				HighlightField titleField = result.get(title);    
//				Text[] titleTexts =  titleField.fragments();    
//				for(Text text : titleTexts){    
//					log += text;  
//				} 
//			} else {
//				log = li.getMessage();
//			}
	    	Message m = om.readValue(li.getMessage(), Message.class);
	    	if (!StringUtils.isEmpty(title)) {
	    		m.setLog(m.getLog().replaceAll(title, "<span style=\"color:red\">" + title + "</span>"));
	    	}
	    	m.setContainers_id(m.getContainers_id());
	    	m.setScrollId(scrollResp.getScrollId());
	    	lists.add(m);
	    }
		return lists;
	}

	/**
	 * 设置滚动查询
	 * @param searchRequestBuilder
	 */
	private void setScroll(SearchRequestBuilder searchRequestBuilder) {
		searchRequestBuilder.setScroll(new TimeValue(60000));
	}
	
	/**
	 * 设置结果大小
	 * @param searchRequestBuilder
	 */
	private void setSize(SearchRequestBuilder searchRequestBuilder, int size) {
		searchRequestBuilder.setSize(size);
	}

	/**
	 * 按时间范围搜素
	 * @param searchRequestBuilder
	 * @param starDate
	 * @param endDate
	 */
	@SuppressWarnings("static-access")
	private void setRangeFilterTime(SearchRequestBuilder searchRequestBuilder, Date startDate, LogTimeType logTimeType) {
		searchRequestBuilder.setPostFilter(FilterBuilders.rangeFilter("@timestamp").from("now-" + calculation(startDate) + logTimeType.M.getValue()));
	}
	
	/**
	 * 是否按查询匹配度排序
	 * @param searchRequestBuilder
	 */
	private void setExplain (SearchRequestBuilder searchRequestBuilder) {
		searchRequestBuilder.setExplain(true);
	}
	
	/**
	 * 设置搜索关键字
	 * @param searchRequestBuilder
	 * @param keyword
	 */
	private void setFiledQuery(SearchRequestBuilder searchRequestBuilder, String file, String keyword) {
		TermQueryBuilder tqb1 = QueryBuilders.termQuery("containers_id", file);
		BoolQueryBuilder bqb = QueryBuilders.boolQuery();
		bqb.must(tqb1);
		if (null != keyword && !"".equals(keyword)) {
			TermQueryBuilder tqb2 = QueryBuilders.termQuery("message", keyword);
			bqb.should(tqb2);
		}
		searchRequestBuilder.setQuery(bqb);
	}
	
	/**
	 * 高亮显示
	 * @param searchRequestBuilder
	 */ 
	private void sethighlighter (SearchRequestBuilder searchRequestBuilder, String title) {
//		searchRequestBuilder.addHighlightedField(title);
//		searchRequestBuilder.setHighlighterPreTags("<span style=\"color:red\">");
//		searchRequestBuilder.setHighlighterPostTags("</span>");
	}
	
	private static long calculation(Date startDate) {
		Date now = new Date();
		long temp = now.getTime() - startDate.getTime();
		long hours = temp / 1000 / 3600;
		long temp2 = temp % (1000 * 3600);
		long mins = temp2 / 1000 / 60;
		if (hours > 0) {
			mins += (hours * 60);
		}
		return mins;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
//	public static void main(String[] args) throws ParseException, JsonParseException, JsonMappingException, IOException {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
//		Date date = sdf.parse(" 2015-8-11 13:58:49 ");
//		LogApiImpl logApiImpl = new LogApiImpl();
//		List<Message> lists = logApiImpl.getLog("ccee1e4bd102bb9f445829863003c041037350fdfebac5eb3c4d2953330b3c69", null, date, "POST");
//		for (Message m : lists) {
//			System.out.println(m.getLog());
//		}
//		
//		if (lists.size() > 0) {
//			logApiImpl.show(logApiImpl, lists, date);
//		}
//	}
	
//	private void show(LogApiImpl logApiImpl, List<Message> list, Date date) throws JsonParseException, JsonMappingException, IOException {
//		List<Message> list2 = logApiImpl.getLog("b7aa4cd854fc8e5d1a4850baa95e2c9557dd00e5a53bcfb12c46fdd0ceab39a5", list.get(list.size()-1).getScrollId(), date, "POST");
//		if (list2.size() == 0) {
//			return;
//		}
//		for (Message m : list2) {
//			System.out.println(m.getLog());
//		}
//		logApiImpl.show(logApiImpl, list2, date);
//	}
	
}
