package activi.com.controller;

import activi.com.mapper.TestActiviMapper;
import activi.com.model.TestActivi;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;


@RestController
public class Demo {
	@Resource
	TestActiviMapper testActiviMapper;

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping("/demo")
	public void testStartVacationApplyProcess() {
//取运行时服务
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance pi = null;
		try {
			//指定执行我们部署的vacationApply工作流程
			String processDefiKey = "vacationApply";//bpmn 的 process id属性

			Map<String, Object> variables = new HashMap<>();
			variables.put("key", "value");
			//取得流程实例
			pi = runtimeService.startProcessInstanceByKey(processDefiKey, variables);//通过流程定义的key 来执行流程
			System.out.println("流程VacationApply执行对象id：" + pi.getId()); //Execution 对象
			System.out.println("流程实例的id：" + pi.getProcessInstanceId());//ProcessInstance 对象
			System.out.println("流程VacationApply定义id：" + pi.getProcessDefinitionId());//输出流程定义的id
			//获取启动流程时设入的流程变量

			TestActivi activi = new TestActivi();
			activi.setExamp("ceshi");
			activi.setStatus("ceshistatusDeem");
			int i = 5 / 0;
			testActiviMapper.insert(activi);
			System.out.println("流程VacationApply的流程变量：" + runtimeService.getVariables(pi.getId()));
			System.out.println("工作流" + pi.getId() + "开始");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			runtimeService.deleteProcessInstance(pi.getProcessInstanceId(), "错误");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			System.out.println("这里是回滚");
		}
	}
}
