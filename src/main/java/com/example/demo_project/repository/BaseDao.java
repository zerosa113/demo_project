package com.example.demo_project.repository;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

public class BaseDao {

	@PersistenceContext // JPA�M��������
	private EntityManager entityManger;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz) { // �L��������
		Query query = entityManger.createQuery(sql, clazz); // clazz���X�Ӫ���ƭn����������m
		if (!CollectionUtils.isEmpty(params)) {
			// �쥻�ϥ�entrySet�M������
//			for(Entry<String, Object> item:params.entrySet()) {
//				query.setParameter(item.getKey(),item.getValue());
//			}
			// �W�U��k�ҥi�ϥ�
			for (Parameter p : query.getParameters()) {
				System.out.println("p---->>" + p);
				System.out.println("p.getName()---->>" + p.getName());
				query.setParameter(p, params.get(p.getName()));
			}
		}

		return query.getResultList();
	}

	// @params pageSize:�C���n��^�����ơA�Plimit
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int pageSize) {
		Query query = entityManger.createQuery(sql, clazz); // clazz���X�Ӫ���ƭn����������m
		if (!CollectionUtils.isEmpty(params)) {
			// �쥻�ϥ�entrySet�M������
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

	// startPosition:��^���G���_�l����
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int pageSize, int startPosition) {
		Query query = entityManger.createQuery(sql, clazz); // clazz���X�Ӫ���ƭn����������m
		if (!CollectionUtils.isEmpty(params)) {
			// �쥻�ϥ�entrySet�M������
//			for(Entry<String, Object> item:params.entrySet()) {
//				query.setParameter(item.getKey(),item.getValue());
//			}
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		if (pageSize > 0) {
			// �C���n��^�����G����
			query.setMaxResults(pageSize);
		}
		if (startPosition >= 0) {
			// �n�q�ĴX�Ӧ�m�}�l��
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
			// �쥻�ϥ�entrySet�M������
//			for(Entry<String, Object> item:params.entrySet()) {
//				query.setParameter(item.getKey(),item.getValue());
//			}
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		if (pageSize > 0) {
			// �C���n��^�����G���
			query.setMaxResults(pageSize);
		}
		if (startPosition >= 0) {
			// �n�q�ĴX�Ӧ�m�}�l��
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
