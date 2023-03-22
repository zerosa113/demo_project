package com.example.demo_project.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo_project.annotation.ConditionalOnB;
import com.example.demo_project.constants.RegisterRtnCode;
import com.example.demo_project.entity.Register;
import com.example.demo_project.repository.RegisterDao;
import com.example.demo_project.service.ifs.RegisterService;
import com.example.demo_project.vo.RegisterRes;

@ConditionalOnB
@EnableScheduling
@Service
public class RegisterServiceImpl2 implements RegisterService{
	
	@Autowired
	private RegisterDao registerDao;
	
	@Override
	public Register register(String account, String pwd, String name, int age, String city) {
		Register reg = new Register(account, pwd, name, age, city); 
		//������U�w�s�b�b��
		if(registerDao.existsById(account)) {
			return null;
		}
		reg.setRegTime(new Date());		//new Date()�t�η�e�ɶ�	*�nimport util
		//reg.setActive(false);		//Active��date_type�O���L�ȡAfalse default
		reg.setRole("General");
		return registerDao.save(reg);
	}

	@Override
	public RegisterRes activeAccount(String account) {
		Optional<Register> regOp = registerDao.findById(account);	//�q��Ʈw���ƥX��
		if(regOp.isPresent()) {
			Register reg = regOp.get();
			reg.setActive(true);
			registerDao.save(reg);		//�^��enum�T��
			return new RegisterRes(null, RegisterRtnCode.SUCCESSFUL.getMessage());
		}
		return new RegisterRes(null, RegisterRtnCode.FAILURE.getMessage());
	}

	@Override
	public RegisterRes addRole(String account, List<String> roleList) {
		Optional<Register> regOp = registerDao.findById(account);
		if(regOp.isPresent()) {
			Set<String>roleSet = new HashSet<>(); //Set�����\���ƪ��ȥX�{
			//�h���Ѽƭ��ơA�w�q�bDB���ݩʪ��Ȥ��ݭn����
			for(String str:roleList) {
				roleSet.add(str);
			}
			//�h��DB�̤w�s�b���ȩM�ѼƱo�ȡB��̪����Ƴ���
			Register reg = regOp.get();		//���X��
			String role = reg.getRole();	
			//�}�C���T�w�榡�O[��, ��, ��, ]
			//�i��|���h�ӭȡA�n�γr��(,)���Ϲj����
			String[] roleArray = role.split(",");	//�������x�s�N�|�[�W�Ů�
			for(String item : roleArray) {
				String str = item.trim();		//�h���ť�	 trim():�h���r�ꪺ�e��ť�
				roleSet.add(str);
			}	
//			System.out.println(roleSet.toString());		//���հ}�C���G
			//�}�C�e��t��[]�A�ϥ�substring�h��[]				//toString��}�C�ন�r��
			System.out.println(roleSet.toString().substring(1,roleSet.toString().length()-1));		
			//index�q0�}�l��,substring�]�t�}�Y���]�t����	//ex.substring(4,8)���m4~7
			//roleSet.toString().length()-1 --> �}�C�r�����-1
			String newStr = roleSet.toString().substring(1,roleSet.toString().length()-1);
//			StringBuffer newStrBuf = new StringBuffer();
//			for(String item:roleSet) {
//				newStrBuf.append(item.trim());
//			}
			reg.setRole(newStr);	
			registerDao.save(reg);
			return new RegisterRes(reg, RegisterRtnCode.SUCCESSFUL.getMessage());
		}
		
		return new RegisterRes(null, RegisterRtnCode.ADD_ROLE_FAILURE.getMessage());
	}

	@Override
	public RegisterRes addRoleSet(String account, Set<String> roleSet) {
		Optional<Register> regOp = registerDao.findById(account);
		if(regOp.isPresent()) {
			//�h��DB�̤w�s�b���ȩM�ѼƱo�ȡB��̪����Ƴ���
			Register reg = regOp.get();		//���X��
			String role = reg.getRole();	
			//�}�C���T�w�榡�O[��, ��, ��, ]
			//�i��|���h�ӭȡA�n�γr��(,)���Ϲj����
			String[] roleArray = role.split(",");	//�������x�s�N�|�[�W�Ů�
			for(String item : roleArray) {
				String str = item.trim();		//�h���ť�	 trim():�h���r�ꪺ�e��ť�
				roleSet.add(str);
			}	
			//�}�C�e��t��[]�A�ϥ�substring�h��[]				//toString��}�C�ন�r��
			System.out.println(roleSet.toString().substring(1,roleSet.toString().length()-1));		
			//index�q0�}�l��,substring�]�t�}�Y���]�t����	//ex.substring(4,8)���m4~7
			//roleSet.toString().length()-1 --> �}�C�r�����-1
			String newStr = roleSet.toString().substring(1,roleSet.toString().length()-1);
			reg.setRole(newStr);	
			registerDao.save(reg);
			return new RegisterRes(reg, RegisterRtnCode.SUCCESSFUL.getMessage());
		}
		return new RegisterRes(null, RegisterRtnCode.ADD_ROLE_FAILURE.getMessage());
	}
	

//	@Scheduled(fixedRateString = "${heartbeat.ms}")
//	public void scheduledPrintDate() {
//		System.out.println("=======================");
//		System.out.println(new Date());
//	}

//	@Scheduled(cron = "0/5 * * * * *")
//	public void scheduledPrintDate1() {
//		System.out.println(new Date());
//	}
	
	@Override
	public Register findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Register> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
