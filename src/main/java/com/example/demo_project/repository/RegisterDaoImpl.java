package com.example.demo_project.repository;
//protected權限要與繼承的class放在同個package
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.example.demo_project.entity.Register;

public class RegisterDaoImpl extends BaseDao {
	
	public List<Register> doQueryByExiredRegTime(Date date){
		//StringBuffer字串緩衝區
		StringBuffer sb = new StringBuffer();
		sb.append(" select R from Register R")	//第一個R是*的意思
		  .append(" where R.regTime > :expiredDate");
		Map<String,Object>params = new HashMap<>();
		params.put("expiredDate", date);
		return doQuery(sb.toString(),params,Register.class);
	}
	
	public List<Register> doQueryByExiredRegTime(Date date,int pageSize){
		//StringBuffer字串緩衝區
		StringBuffer sb = new StringBuffer();
		sb.append(" select R from Register R")	//第一個R是*的意思
		  .append(" where R.regTime > :expiredDate");
		Map<String,Object>params = new HashMap<>();
		params.put("expiredDate", date);
		return doQuery(sb.toString(),params,Register.class,pageSize);
	}

	public List<Register> doQueryByExiredRegTime(Date date,int pageSize,int startPosition){
		//StringBuffer字串緩衝區
		StringBuffer sb = new StringBuffer();
		sb.append(" select R from Register R")	//第一個R是*的意思
		  .append(" where R.regTime > :expiredDate");
		Map<String,Object>params = new HashMap<>();
		params.put("expiredDate", date);
		return doQuery(sb.toString(),params,Register.class,pageSize,startPosition);
	}

	public List<Register> doNativeQueryByExiredRegTime(Date date,int pageSize,int startPosition){
		//StringBuffer字串緩衝區
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from register r")	//Sql語法
		  .append(" where r.reg_time > :expiredDate");
		Map<String,Object>params = new HashMap<>();
		params.put("expiredDate", date);
		return doNativeQuery(sb.toString(),params,Register.class,pageSize,startPosition);
	}
	
	//====update
	public int doUpdateAgeByName(String name,int newAge) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update Register set age = :age");
		sb.append(" where name = :newName");
		Map<String,Object>params = new HashMap<>();
		params.put("age", newAge);
		params.put("newName", name);
		return doUpdate(sb.toString(), params) ;
	}
	
	public int doUpdateAgeByAccount(String account,int newAge) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update Register set age = :age");
		sb.append(" where account = :newAccount");
		Map<String,Object>params = new HashMap<>();
		params.put("age", newAge);
		params.put("newAccount", account);
		return doUpdate(sb.toString(),params);
	}
	
	public int nativeUpdateAgeByName(String name,int newAge) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update register set age = :age");
		sb.append(" where name = :newName");
		Map<String,Object>params = new HashMap<>();
		params.put("age", newAge);
		params.put("newName", name);
		return doNativeUpdate(sb.toString(), params) ;
	}
	
	public int nativeUpdateAgeByAccount(String account,int newAge) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update register set age = :age");
		sb.append(" where account = :account");
		Map<String,Object>params = new HashMap<>();
		params.put("age", newAge);
		params.put("account", account);
		return doNativeUpdate(sb.toString(),params);
	}
	
	
	/*final sql: select * from register r where  role like %General% or role like %SA% or role like %SD%
	*===============================
	*Map: 相同的 key其對應的值會後蓋前
	*params = {role0=%General%, role1=%SA%,role2=%SD%}
	*StringBuffer sql: select * from register r where role like :role0 or role like :role1 or role like :role2
	*上面的 StringBuffer sql 中的 0,1,2 可以取自參數列 roleList 的 index
	*要判斷參數列是否為最後一個，因為會決定 StringBuffer sql 是否需要加 or
	*/
	public List<Register>doQueryRoleContains(List<String>roleList){	//General,SA,SD
		Map<String,Object>params = new HashMap<>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from register r where ");
		for(int i = 0; i < roleList.size(); i++) {
			String item = roleList.get(i);
			//select * from register r where role like :role0 or role like :role1 or role like :role2
			//params = {role0=%General%, role1=%SA%,role2=%SD%}	
			//key值都是role 放入map會被覆蓋
			if(i<roleList.size()-1) {
				sb.append("role like :role" + i + " or ");
			}else {
				sb.append("role like :role" + i );
			}
			params.put("role" + i , "%"+ item + "%");
		}
		System.out.println(sb.toString());
		return doNativeQuery(sb.toString(), params, Register.class, -1, 0);
	}
	
	
}
