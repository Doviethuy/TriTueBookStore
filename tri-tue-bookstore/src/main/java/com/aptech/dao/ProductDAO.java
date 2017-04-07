package com.aptech.dao;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aptech.model.Category;
import com.aptech.model.Product;
import com.aptech.util.Constant;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Repository
public class ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ArrayList<Product> getAllProduct() {
		/*ArrayList<Product> products = new ArrayList<Product>();
		JestClient client = connectElastic();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			SearchSourceBuilder builder = new SearchSourceBuilder();
			builder.query(QueryBuilders.matchAllQuery()).from(0).size(10000);
			Search search = new Search.Builder(builder.toString()).addIndex(Constant.ELASTICSEARCH_INDEX).addType(Constant.ELASTICSEARCH_TYPE).build();
			JestResult result = client.execute(search);
			JsonObject object = result.getJsonObject();
			JsonArray arr = object.get("hits").getAsJsonObject().get("hits").getAsJsonArray();
			for (JsonElement item : arr) {
				JsonObject temp = item.getAsJsonObject().get("_source").getAsJsonObject();
				Product product = new Product();
				product.setProId(Long.valueOf(temp.get("proId").toString()));
				product.setPrice(Long.valueOf(temp.get("price").toString()));
				product.setProName(temp.get("proName").toString());
				product.setImg(temp.get("img").toString());
				product.setDescription(temp.get("description").toString());
				product.setUserName(temp.get("userName").toString());
				product.setCateId(Integer.valueOf(temp.get("cateId").toString()));
				product.setQuantity(Integer.valueOf(temp.get("quantity").toString()));
				product.setCreateDate(sdf.parse(temp.get("createDate").toString()));
				product.setModifyDate(sdf.parse(temp.get("modifyDate").toString()));
				products.add(product);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (client != null) {
				client.shutdownClient();
			}
		}
		return products;*/
		Session session = this.sessionFactory.getCurrentSession();
		return new ArrayList<Product>(session.createQuery("from Product").list());
	}

	public Product getProduct(long proId) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Product) session.get(Product.class, new Long(proId));
	}

	public Product addProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(product);
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

	public static JestClient connectElastic() {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(Constant.ELASTICSEARCH_HOME).multiThreaded(true).build());
		return factory.getObject();
	}

}
