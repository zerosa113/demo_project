package com.example.demo_project.repository;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

public class BaseDao {

	@PersistenceContext // JPA專有的註釋
	private EntityManager entityManger;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz) { // 無分頁筆數
		Query query = entityManger.createQuery(sql, clazz); // clazz撈出來的資料要放到相應的位置
		if (!CollectionUtils.isEmpty(params)) {
			// 原本使用entrySet遍歷取值
//			for(Entry<String, Object> item:params.entrySet()) {
//				query.setParameter(item.getKey(),item.getValue());
//			}
			// 上下方法皆可使用
			for (Parameter p : query.getParameters()) {
				System.out.println("p---->>" + p);
				System.out.println("p.getName()---->>" + p.getName());
				query.setParameter(p, params.get(p.getName()));
			}
		}

		return query.getResultList();
	}

	// @params pageSize:每次要返回的筆數，同limit
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int pageSize) {
		Query query = entityManger.createQuery(sql, clazz); // clazz撈出來的資料要放到相應的位置
		if (!CollectionUtils.isEmpty(params)) {
			// 原本使用entrySet遍歷取值
//			for(Entry<String, Object> item:params.entrySet()) {
//				query.setParameter(item.getKey(),item.getValue());
//			}
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}

		if (pageSize > 0) {
			query.setMaxResults(pageSize);
		}
		return query.getResultList();
	}

	// startPosition:返回結果的起始筆數
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int pageSize, int startPosition) {
		Query query = entityManger.createQuery(sql, clazz); // clazz撈出來的資料要放到相應的位置
		if (!CollectionUtils.isEmpty(params)) {
			// 原本使用entrySet遍歷取值
//			for(Entry<String, Object> item:params.entrySet()) {
//				query.setParameter(item.getKey(),item.getValue());
//			}
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		if (pageSize > 0) {
			// 每次要返回的結果筆數
			query.setMaxResults(pageSize);
		}
		if (startPosition >= 0) {
			// 要從第幾個位置開始取
			query.setFirstResult(startPosition);
		}
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doNativeQuery(String nativeSql, Map<String, Object> params,
			Class<EntityType> clazz, int pageSize, int startPosition) {
		Query query = null;
		if (clazz == null) {
			query = entityManger.createNativeQuery(nativeSql);
		} else {
			query = entityManger.createNativeQuery(nativeSql, clazz);
		}
		if (!CollectionUtils.isEmpty(params)) {
			// 原本使用entrySet遍歷取值
//			for(Entry<String, Object> item:params.entrySet()) {
//				query.setParameter(item.getKey(),item.getValue());
//			}
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		if (pageSize > 0) {
			// 每次要返回的結果比數
			query.setMaxResults(pageSize);
		}
		if (startPosition >= 0) {
			// 要從第幾個位置開始取
			query.setFirstResult(startPosition);
		}
		return query.getResultList();
	}

	// execute update
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected int doUpdate(String sql, Map<String, Object> params) {
		Query query = entityManger.createQuery(sql);
		if (!CollectionUtils.isEmpty(params)) {
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		return query.executeUpdate();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected int doNativeUpdate(String nativeSql, Map<String, Object> params) {
		Query query = entityManger.createNativeQuery(nativeSql);
		if (!CollectionUtils.isEmpty(params)) {
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		return query.executeUpdate();
	}
}
