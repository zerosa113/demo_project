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
@SpringBootTest(classes = DemoProjectApplication.class) // 專案的Application.class
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterTest {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private RegisterDao registerDao;

	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

	// mockMvc是基於WebApplicationContext所建立物件，可以使用來編寫web應用的整合測試
	@Autowired
	private WebApplicationContext wac;
	// 實現對http請求的模擬，主要是用來測試controller
	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

	}
	// 要加@BeforeAll就要加@TestInstance(Lifecycle.PER_CLASS)

	@AfterAll
	public void afterAll() {
		System.out.println("After ALL!");
	}

	@BeforeEach // 用在建立假資料
	public void beforeEach() {
		System.out.println("Before Each!");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void registerControllerTest() throws Exception {
		// 如果Headers要加的參數有多個可使用此方式
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
		// 印出postman格式
		System.out.println(map.toString());
		// {account=A99, pwd=A123456, name=David, age=22, city=Tainan}
		System.out.println(mapString);
		// postman格式
		// {"account":"A99","pwd":"A123456","name":"David","age":22,"city":"Tainan"}
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/regiser").contentType(CONTENT_TYPE) // Headers要參加的參數只有content_type時，可直接使用，不須透過Headers加入
//				.headers(headers)
				.content(mapString)); // .content只允許字串，所以要轉換成字串
		// get response && 將response的內容轉成字串
//		MockHttpServletResponse httpResponse = result.andReturn().getResponse();
//		String resString = httpResponse.getContentAsString();	//合併成下↓
		String resString = result.andReturn().getResponse().getContentAsString();
		// 將得到的response字串轉成JSON(map)
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		Map<String, Object> resData = jsonParser.parseMap(resString);
		// map接回來的key值是String,value是Object
		String rtnMessage = (String) resData.get("message"); // get key值才能取得map的value/屬性要轉成String
		System.out.println(rtnMessage);
		Assert.isTrue(rtnMessage.equals("Successful"), "Message error!");
		Map<String, Object> rtnInfo = (Map<String, Object>) resData.get("register_info");
		Assert.isTrue(((String) rtnInfo.get("account")).equals("A99"), "Account error!");
		// 加上屬性(String)後要()出範圍讓電腦知道(String)要到哪段
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
		Assert.isTrue(!reg.isActive(), "Account is active!"); // 判斷第一個參數是否為true,若為falsec會出現後面字串
		// reg.isActive() == false
		// active registered account //boolean預設是false加上!,預期結果為false達成true條件
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
		// 如果要加的參數有多個可使用Headers此方式
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// Headers指接受字串要轉字串
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
		// 印出postman格式
		System.out.println(map.toString());
		// {account=A99, pwd=A123456, name=David, age=22, city=Tainan}
		System.out.println(mapString);
		// postman格式
		// {"account":"A99","pwd":"A123456","name":"David","age":22,"city":"Tainan"}
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/add_rolelist")// 入口名稱要一致
				.contentType(CONTENT_TYPE) // Headers要參加的參數只有content_type時，可直接使用，不須透過Headers加入
//				.headers(headers)
				.content(mapString)); // .content只允許字串，所以要轉換成字串
		// get response && 將response的內容轉成字串
//		MockHttpServletResponse httpResponse = result.andReturn().getResponse();
//		String resString = httpResponse.getContentAsString();	//合併成下↓
		String resString = result.andReturn().getResponse().getContentAsString();
		// 將得到的response字串轉成JSON(map)
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		Map<String, Object> resData = jsonParser.parseMap(resString);
		// map接回來的key值是String,value是Object
		String rtnMessage = (String) resData.get("message"); // get key值才能取得map的value/屬性要轉成String
		System.out.println(rtnMessage);
		Assert.isTrue(rtnMessage.equals("Successful"), "Message error!");
		Map<String, Object> rtnInfo = (Map<String, Object>) resData.get("register_info");
		Assert.isTrue(((String) rtnInfo.get("account")).equals("A02"), "Account error!");
		// 加上屬性(String)後要()出範圍讓電腦知道(String)要到哪段
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
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); // 日期月份是MM，mm是分鐘
		List<Register> result = registerDao.doQueryByExiredRegTime(date);
		System.out.println(result.size());
	}

	@Test
	public void doQueryWithPageSizeTest() throws ParseException {
		String dateStr = "2022-11-10";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); // 日期月份是MM，mm是分鐘
		List<Register> result = registerDao.doQueryByExiredRegTime(date, 2);
		System.out.println(result.size());
		for (Register item : result) {
			System.out.println(item.getAccount());
		}
	}

	@Test
	public void doQueryWithStartPostitionTest() throws ParseException {
		String dateStr = "2022-11-10";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); // 日期月份是MM，mm是分鐘
		List<Register> result = registerDao.doQueryByExiredRegTime(date, -1, 2);
		System.out.println(result.size());
		for (Register item : result) {
			System.out.println(item.getAccount());
		}
	}

	@Test
	public void doNativeQueryTest() throws ParseException {
		String dateStr = "2022-11-10";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); // 日期月份是MM，mm是分鐘
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
		// 日期轉換為字串
		SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		System.out.println(sdff.format(new Date())); // 當前日期時間 //a:上午或下午
		// 解析日期_字串解析成日期
		String dateStr = "2022-11-25";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date newDate = sdf.parse(dateStr);
		System.out.println(newDate);
	}

	@Test
	public void dateTimeFormatTest() {
		// 日期轉換為字串
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm a");
		String nowStr = now.format(format);
		System.out.println(nowStr);
		// 解析日期(LocalDate)
		String dateStr = "2022年11月25日";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		LocalDate date = LocalDate.parse(dateStr, formatter);
		System.out.println(date);
		// 解析日期(LocalDateTime)
		String dateTimeStr = "2022年11月25日 12時30分10秒";
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh時mm分ss秒");
		LocalDate dateTime = LocalDate.parse(dateTimeStr, formatter1);
		System.out.println(dateTime);
	}

	@Test
	public void dateToLocalDateTest() {
		Date date = new Date();
		Instant instant = date.toInstant();
		// 透過ZoneId指定時區
		ZoneId zoneId = ZoneId.systemDefault();
		System.out.println("ZoneId(指定時區:)" + zoneId);
		// atZone():返回從指定時區該Instant 生成的ZonedDateTime
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
		// atStarOfDate(ZoneId):創建指定時區的日期開始時間
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
