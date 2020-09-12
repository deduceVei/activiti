package unnet.hzfc.webconsole;

import activi.com.mapper.TestActiviMapper;
import activi.com.model.TestActivi;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebConsoleApplicationTests {

	@Resource
	TestActiviMapper testActiviMapper;

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	//执行工作流
	@Test
	@Transactional(rollbackFor = Exception.class)
	public void testStartVacationApplyProcess() {

		try {
			//指定执行我们部署的vacationApply工作流程
			String processDefiKey = "vacationApply";//bpmn 的 process id属性
			//取运行时服务
			RuntimeService runtimeService = processEngine.getRuntimeService();
			Map<String, Object> variables = new HashMap<>();
			variables.put("key", "value");
			//取得流程实例
			ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefiKey, variables);//通过流程定义的key 来执行流程
			System.out.println("流程VacationApply执行对象id：" + pi.getId()); //Execution 对象
			System.out.println("流程实例的id：" + pi.getProcessInstanceId());//ProcessInstance 对象
			System.out.println("流程VacationApply定义id：" + pi.getProcessDefinitionId());//输出流程定义的id
			//获取启动流程时设入的流程变量

			TestActivi activi = new TestActivi();
			activi.setExamp("ceshi");
			activi.setStatus("ceshistatus");
			testActiviMapper.insert(activi);
			System.out.println("流程VacationApply的流程变量：" + runtimeService.getVariables(pi.getId()));
			System.out.println("工作流" + pi.getId() + "开始");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("这里是回滚");
		}
	}


	@Test
	public void contextLoads() throws Exception {
		List<String> warnings = new ArrayList<String>();
		String path = this.getClass().getClassLoader().getResource("generatorConfig.xml").getPath();
		File configFile = new File(path);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
//
////
////	@Test
////	public void contextLoads() throws Exception {
////		User test = new User();
////		test.setRealname("jijixiao");
////		test.setLoginname("测试组");
////
////		redisTemplate.opsForValue().set("test",JSONObject.toJSONString(test));
////	}
////
////	@Test
////	public void test() throws Exception {
////		User test = new User();
////		test.setRealname("jijixiao");
////		test.setLoginname("测试组");
////		String testString = JSONObject.toJSONString(test);
////
////
////		redisTemplate.opsForValue().;
////	}
////
//
//
//	@Test
//	public void contextLoads() throws Exception {
//		Map<String,String> check = new HashMap<>();
//        createHandMadeTableByMonthly.createHandMadeTable();
//
//	}
//
//
//
//}
