package com.aptech.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aptech.model.Product;
import com.aptech.model.User;
import com.aptech.util.FileUtil;

@Repository
public class ProductDAO {
	final static String ELASTICSEARCH_INDEX = "product";
	final static String ELASTICSEARCH_TYPE = "book";
	final static String ELASTICSEARCH_CLUSTER = "tritue";
	final static String ELASTICSEARCH_NODE = "bookstore";
	@Autowired
	private SessionFactory sessionFactory;

	public ArrayList<Product> getAllProduct() {
		Session session = this.sessionFactory.getCurrentSession();
		return new ArrayList<Product>(session.createQuery("from Product").list());
		/*TransportClient client = null;
		ArrayList<Product> lstProducts = new ArrayList<Product>();
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			client = connectElastic(ELASTICSEARCH_CLUSTER, ELASTICSEARCH_NODE);
			SearchResponse response = client.prepareSearch(ELASTICSEARCH_INDEX).setTypes(ELASTICSEARCH_TYPE).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(QueryBuilders.matchAllQuery()).setFrom(0).setSize(10000).setExplain(true).execute().actionGet();
			SearchHit[] results = response.getHits().getHits();
			for (SearchHit item : results) {
				Map<String, Object> result = item.getSource();
				Product p = new Product();
				p.setProId(Long.valueOf(result.get("proId").toString()));
				p.setPrice(Long.valueOf(result.get("price").toString()));
				p.setProName(result.get("proName").toString());
				p.setImg(result.get("img").toString());
				p.setDescription(result.get("description").toString());
				p.setUserName(result.get("userName").toString());
				p.setCateId(Integer.valueOf(result.get("cateId").toString()));
				p.setQuantity(Integer.valueOf(result.get("quantity").toString()));
				p.setCreateDate(sourceFormat.parse(result.get("createDate").toString()));
				p.setModifyDate(sourceFormat.parse(result.get("modifyDate").toString()));
				lstProducts.add(p);
			}
		} catch (UnknownHostException | ParseException e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
			}
		}
		return lstProducts;*/
	}

	public Product getProduct(long proId) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Product) session.get(Product.class, new Long(proId));
	}

	public Product addProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(product);
		createData(product);
		return product;
	}

	public boolean updateProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(product);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteProduct(long proId) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, new Long(proId));
		try {
			if (product != null) {
				session.delete(product);
				return true;
			} else {
				return false;
			}

		} catch (Exception exception) {
			return false;
		}
	}

	public boolean isEnoughQuantity(long proId, int quantity) {
		Product product = this.getProduct(proId);
		if (product == null || product.getQuantity() > quantity) {
			return false;
		}
		return true;
	}

	public static TransportClient connectElastic(String cluster, String node) throws UnknownHostException {
		Settings settings = Settings.builder().put("cluster.name", cluster).put("node.name", node).put("http.enabled", false).put("node.master", false).put("network.host", "127.0.0.1").put("transport.tcp.port", "9300").build();
		TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		return client;
	}

	public static Map<String, Object> putJsonProduct(Product product) {
		Map<String, Object> jsonDocument = new HashMap<String, Object>();
		String proNameEn = FileUtil.removeAccent(product.getProName());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		jsonDocument.put("proId", product.getProId());
		jsonDocument.put("proName", product.getProName());
		jsonDocument.put("proNameEn", proNameEn);
		jsonDocument.put("cateId", product.getCateId());
		jsonDocument.put("quantity", product.getQuantity());
		jsonDocument.put("img", product.getImg());
		jsonDocument.put("price", product.getPrice());
		jsonDocument.put("description", product.getDescription());
		jsonDocument.put("createDate", sdf.format(product.getCreateDate()));
		jsonDocument.put("modifyDate", sdf.format(product.getModifyDate()));
		jsonDocument.put("userName", product.getUserName());
		return jsonDocument;
	}

	public static void createData(Product item) {
		TransportClient client = null;
		try {
			client = connectElastic(ELASTICSEARCH_CLUSTER, ELASTICSEARCH_NODE);
			client.prepareIndex(ELASTICSEARCH_INDEX, ELASTICSEARCH_TYPE, String.valueOf(item.getProId())).setSource(putJsonProduct(item)).execute().actionGet();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}
}

