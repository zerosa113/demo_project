package com.example.demo_project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.el.WebApplicationContextFacesELResolver;

import com.example.demo_project.entity.Register;
import com.example.demo_project.repository.RegisterDao;
import com.example.demo_project.service.ifs.RegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.Result;

@WebAppConfiguration
@SpringBootTest(classes = DemoProjectApplication.class) // �M�ת�Application.class
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterTest {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private RegisterDao registerDao;

	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

	// mockMvc�O���WebApplicationContext�ҫإߪ���A�i�H�ϥΨӽs�gweb���Ϊ���X����
	@Autowired
	private WebApplicationContext wac;
	// ��{��http�ШD�������A�D�n�O�ΨӴ���controller
	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

	}
	// �n�[@BeforeAll�N�n�[@TestInstance(Lifecycle.PER_CLASS)

	@AfterAll
	public void afterAll() {
		System.out.println("After ALL!");
	}

	@BeforeEach // �Φb�إ߰����
	public void beforeEach() {
		System.out.println("Before Each!");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void registerControllerTest() throws Exception {
		// �p�GHeaders�n�[���ѼƦ��h�ӥi�ϥΦ��覡
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// set register_body
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("account", "A99");
		map.put("pwd", "A123456");
		map.put("name", "David");
		map.put("age", 22);
		map.put("city", "Tainan");
		// map to string
		ObjectMapper objectMapper = new ObjectMapper();
		String mapString = objectMapper.writeValueAsString(map);
		// �L�Xpostman�榡
		System.out.println(map.toString());
		// {account=A99, pwd=A123456, name=David, age=22, city=Tainan}
		System.out.println(mapString);
		// postman�榡
		// {"account":"A99","pwd":"A123456","name":"David","age":22,"city":"Tainan"}
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/regiser").contentType(CONTENT_TYPE) // Headers�n�ѥ[���Ѽƥu��content_type�ɡA�i�����ϥΡA�����z�LHeaders�[�J
//				.headers(headers)
				.content(mapString)); // .content�u���\�r��A�ҥH�n�ഫ���r��
		// get response && �Nresponse�����e�ন�r��
//		MockHttpServletResponse httpResponse = result.andReturn().getResponse();
//		String resString = httpResponse.getContentAsString();	//�X�֦��U��
		String resString = result.andReturn().getResponse().getContentAsString();
		// �N�o�쪺response�r���নJSON(map)
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		Map<String, Object> resData = jsonParser.parseMap(resString);
		// map���^�Ӫ�key�ȬOString,value�OObject
		String rtnMessage = (String) resData.get("message"); // get key�Ȥ~����omap��value/�ݩʭn�নString
		System.out.println(rtnMessage);
		Assert.isTrue(rtnMessage.equals("Successful"), "Message error!");
		Map<String, Object> rtnInfo = (Map<String, Object>) resData.get("register_info");
		Assert.isTrue(((String) rtnInfo.get("account")).equals("A99"), "Account error!");
		// �[�W�ݩ�(String)��n()�X�d�����q�����D(String)�n����q
		System.out.println(rtnInfo);
		System.out.println(resData);

	}

	@Test // jupiter.api.Test
	public void registerTest() {
		Register reg = registerService.register("A99", "123456", "Alice", 18, "Tainan");
		// Optional<Register> result = registerDao.findById("A01");
		Assert.notNull(reg, "Result is null!");
		Assert.isTrue(reg.getAccount().equalsIgnoreCase("A99"), "Account error!");
		registerDao.delete(reg);
		Assert.isTrue(!registerDao.findById("A99").isPresent(), "reg is not null!");
		Assert.isTrue(!registerDao.existsById("A99"), "reg is not null!");
		System.out.println("Register Test");
	}

	@Test
	public void activeAccountTest() {
		// register new account
		Register reg = registerService.register("A99", "123456", "Alice", 18, "Tainan");
		Assert.isTrue(!reg.isActive(), "Account is active!"); // �P�_�Ĥ@�ӰѼƬO�_��true,�Y��falsec�|�X�{�᭱�r��
		// reg.isActive() == false
		// active registered account //boolean�w�]�Ofalse�[�W!,�w�����G��false�F��true����
		registerService.activeAccount("A99");
		Register newReg = registerDao.findById("A99").get();
		Assert.isTrue(newReg.isActive(), "Account is active!");
		// newReg.isActive() == true
		registerDao.delete(newReg);

		System.out.println("Active Account!");
	}

	@Test
	public void addRoleTest() {
		List<String> roleList = new ArrayList<>();
		roleList.add("SA");
		roleList.add("SD");
		roleList.add("SA");
		roleList.add("SD");
		roleList.add("PM");
		registerService.addRole("A02", roleList);
		System.out.println("Add role!");

	}

	@SuppressWarnings("unchecked")
	@Test
	public void registerListControllerTest() throws Exception {
		// �p�G�n�[���ѼƦ��h�ӥi�ϥ�Headers���覡
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// Headers�������r��n��r��
		// set register_body
		Map<String, Object> map = new LinkedHashMap<>();
		List<String> roleList = new ArrayList<>();
		roleList.add("AAA");
		roleList.add("BBB");
		map.put("account", "A02");
//		map.put("pwd", "A123456");
//		map.put("name", "David");
//		map.put("age", 22);
//		map.put("city", "Tainan");
		map.put("role_list", roleList);
		// map to string
		ObjectMapper objectMapper = new ObjectMapper();
		String mapString = objectMapper.writeValueAsString(map);
		// �L�Xpostman�榡
		System.out.println(map.toString());
		// {account=A99, pwd=A123456, name=David, age=22, city=Tainan}
		System.out.println(mapString);
		// postman�榡
		// {"account":"A99","pwd":"A123456","name":"David","age":22,"city":"Tainan"}
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/add_rolelist")// �J�f�W�٭n�@�P
				.contentType(CONTENT_TYPE) // Headers�n�ѥ[���Ѽƥu��content_type�ɡA�i�����ϥΡA�����z�LHeaders�[�J
//				.headers(headers)
				.content(mapString)); // .content�u���\�r��A�ҥH�n�ഫ���r��
		// get response && �Nresponse�����e�ন�r��
//		MockHttpServletResponse httpResponse = result.andReturn().getResponse();
//		String resString = httpResponse.getContentAsString();	//�X�֦��U��
		String resString = result.andReturn().getResponse().getContentAsString();
		// �N�o�쪺response�r���নJSON(map)
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		Map<String, Object> resData = jsonParser.parseMap(resString);
		// map���^�Ӫ�key�ȬOString,value�OObject
		String rtnMessage = (String) resData.get("message"); // get key�Ȥ~����omap��value/�ݩʭn�নString
		System.out.println(rtnMessage);
		Assert.isTrue(rtnMessage.equals("Successful"), "Message error!");
		Map<String, Object> rtnInfo = (Map<String, Object>) resData.get("register_info");
		Assert.isTrue(((String) rtnInfo.get("account")).equals("A02"), "Account error!");
		// �[�W�ݩ�(String)��n()�X�d�����q�����D(String)�n����q
		System.out.println(rtnInfo);
		System.out.println(resData);

	}

	// 1125_LeetCode_1. Two Sum
	@Test
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> numMap = new HashMap<>();
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			int x = target - nums[i];
			if (numMap.containsKey(x)) {
				result[1] = i;
				result[0] = numMap.get(x);
				return result;
			}
			numMap.put(nums[i], i);
		}
//		nums[0] + nums[1] == target
		return result;
	}

	@Test
	public void updateRegisterInfoDao() {
		int result = registerDao.updateReisterInfo("Davidd", 23, "Taipai", new Date(), "A05");
		System.out.println("---->>" + result);
	}

	@Test
	public void doQueryTest() throws ParseException {
		String dateStr = "2022-11-10";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); // �������OMM�Amm�O����
		List<Register> result = registerDao.doQueryByExiredRegTime(date);
		System.out.println(result.size());
	}

	@Test
	public void doQueryWithPageSizeTest() throws ParseException {
		String dateStr = "2022-11-10";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); // �������OMM�Amm�O����
		List<Register> result = registerDao.doQueryByExiredRegTime(date, 2);
		System.out.println(result.size());
		for (Register item : result) {
			System.out.println(item.getAccount());
		}
	}

	@Test
	public void doQueryWithStartPostitionTest() throws ParseException {
		String dateStr = "2022-11-10";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); // �������OMM�Amm�O����
		List<Register> result = registerDao.doQueryByExiredRegTime(date, -1, 2);
		System.out.println(result.size());
		for (Register item : result) {
			System.out.println(item.getAccount());
		}
	}

	@Test
	public void doNativeQueryTest() throws ParseException {
		String dateStr = "2022-11-10";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); // �������OMM�Amm�O����
		List<Register> result = registerDao.doNativeQueryByExiredRegTime(date, -1, 2);
		System.out.println(result.size());
		for (Register item : result) {
			System.out.println(item.getAccount());
		}
	}

	@Test
	public void doUpdateTest() {
//		int result = registerDao.doUpdateAgeByName("Alice", 18);
		int result = registerDao.doUpdateAgeByAccount("A01", 33);
		System.out.println(result);
	}

	@Test
	public void nativeUpdateTest() {
		int result = registerDao.nativeUpdateAgeByName("Alice", 77);
		System.out.println(result);
	}

	@Test
	public void doQueryRoleContains() {
		List<String> strList = Arrays.asList("General");
		List<Register> result = registerDao.doQueryRoleContains(strList);
		System.out.println(result.size());
	}

	@Test
	public void simpleDateFormatTest() throws ParseException {
		// ����ഫ���r��
		SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		System.out.println(sdff.format(new Date())); // ��e����ɶ� //a:�W�ȩΤU��
		// �ѪR���_�r��ѪR�����
		String dateStr = "2022-11-25";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date newDate = sdf.parse(dateStr);
		System.out.println(newDate);
	}

	@Test
	public void dateTimeFormatTest() {
		// ����ഫ���r��
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy�~MM��dd�� hh:mm a");
		String nowStr = now.format(format);
		System.out.println(nowStr);
		// �ѪR���(LocalDate)
		String dateStr = "2022�~11��25��";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy�~MM��dd��");
		LocalDate date = LocalDate.parse(dateStr, formatter);
		System.out.println(date);
		// �ѪR���(LocalDateTime)
		String dateTimeStr = "2022�~11��25�� 12��30��10��";
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy�~MM��dd�� hh��mm��ss��");
		LocalDate dateTime = LocalDate.parse(dateTimeStr, formatter1);
		System.out.println(dateTime);
	}

	@Test
	public void dateToLocalDateTest() {
		Date date = new Date();
		Instant instant = date.toInstant();
		// �z�LZoneId���w�ɰ�
		ZoneId zoneId = ZoneId.systemDefault();
		System.out.println("ZoneId(���w�ɰ�:)" + zoneId);
		// atZone():��^�q���w�ɰϸ�Instant �ͦ���ZonedDateTime
		LocalDate localDate = instant.atZone(zoneId).toLocalDate();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		System.out.println("Date: " + date);
		System.out.println("LocalDate: " + localDate);
		System.out.println("LocalDateTime: " + localDateTime);
	}

	@Test
	public void localDateToDateTest() {
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDate localDate = LocalDate.now();
		// atStarOfDate(ZoneId):�Ыث��w�ɰϪ�����}�l�ɶ�
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
		System.out.println("ZonedDateTime: " + zonedDateTime);
		Date date = Date.from(zonedDateTime.toInstant());
		System.out.println("Date: " + date);
		System.out.println("LocalDate: " + localDate);
	}
	
	@Test
	public void controllerTest() {
		double random = Math.random()*10000;
		int verifyCode = (int) Math.round(random);
		System.out.println(random);
		System.out.println(verifyCode);
	}
	
	@Test
	public void findAll() {
		//before
		List<Register> result = registerService.findAll();
		//after
		System.out.println(result.size());
	}

}
