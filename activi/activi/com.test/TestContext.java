import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestContext {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	// 创建流程引擎
	@Test
	public void testCreatEngine() {

		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		processEngine.close();
		System.out.println("通过ProcessEngines 来获取流程引擎");
		return;
	}

	//部署工作流
	@Test
	public void testDeploy() {
		//获取仓库服务 ：管理流程定义
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deploy = repositoryService.createDeployment()//创建一个部署的构建器
				.addClasspathResource("bpmn/vacationApply.bpmn")//从类路径中添加资源,一次只能添加一个资源
				.name("请假流程")//设置部署的名称
				.category("请假审批流程类型")//设置部署的类别
				.deploy();

		System.out.println("部署的-请假流程-id：" + deploy.getId());
		System.out.println("部署的-请假流程-name：" + deploy.getName());
		return;
	}

	//删除流程定义
	@Test
	public void testDeleteProcessDef() {
		//通过部署id来删除流程定义
		String deploymentId = "ID";
		boolean cascade = false;  // 级联删除, 设置为true的话, 有正在跑的流程实例及任务也会被删除
		processEngine.getRepositoryService().deleteDeployment(deploymentId, cascade);
	}

	// 删除流程实例
	@Test
	public void testDeleteProcessInstance() {
		String processInstanceId = "processInstanceId";
		String deleteReason = "不请假了";  // 可以添加删除原因
		processEngine.getRuntimeService().deleteProcessInstance(processInstanceId, deleteReason);
	}

	//执行工作流
	@Test
	public void testStartVacationApplyProcess() {

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
		System.out.println("流程VacationApply的流程变量：" + runtimeService.getVariables(pi.getId()));
		System.out.println("工作流" + pi.getId() + "开始");
	}

	//根据任务的办理人来查询他所有待办理的任务
	@Test
	public void testQueryTaskByAssignee() {
		//任务的办理人
		String assignee = "请假人";
		//取得任务服务
		TaskService taskService = processEngine.getTaskService();
		//创建一个任务查询对象
		TaskQuery taskQuery = taskService.createTaskQuery();
		//指定办理人的任务列表
		List<Task> list = taskQuery.taskAssignee(assignee)
				.list();
		//遍历任务列表
		System.out.println("任务办理人-" + assignee);
		if (list != null && list.size() > 0) {
			for (Task task : list) {
				System.out.println("任务的办理人：" + task.getAssignee());
				System.out.println("任务的id：" + task.getId());
				System.out.println("任务的名称：" + task.getName());
			}
		}
	}

	//根据任务的办理人(第一审批人)来查询他所有待办理的任务
	@Test
	public void testQueryTaskByAssigneeCheckOne() {
		//任务的办理人
		String assignee = "第一审批人";
		//取得任务服务
		TaskService taskService = processEngine.getTaskService();
		//创建一个任务查询对象
		TaskQuery taskQuery = taskService.createTaskQuery();
		//指定办理人的任务列表
		List<Task> list = taskQuery.taskAssignee(assignee).list();
		//遍历任务列表
		System.out.println("任务办理人-" + assignee);
		//ASSIGNEE指的是 当前任务在ASSIGN的待处理中
		if (list != null && list.size() > 0) {
			for (Task task : list) {
				System.out.println("任务的办理人：" + task.getAssignee());
				System.out.println("任务的id：" + task.getId());
				System.out.println("任务的名称：" + task.getName());
			}
		}
	}

	//根据任务的候选办理CandidateUser来查询他所有待办理的任务
	@Test
	public void testQueryTaskByCandidateUser() {
		//任务的办理人
		String candidateUser = "张三";
		//取得任务服务
		TaskService taskService = processEngine.getTaskService();
		//创建一个任务查询对象
		TaskQuery taskQuery = taskService.createTaskQuery();
		//指定办理人的任务列表
		List<Task> candidateUserList = taskQuery.taskCandidateUser(candidateUser).list();
		//遍历任务列表
		System.out.println("任务办理人-" + candidateUser);
		//ASSIGNEE指的是 当前任务在ASSIGN的待处理中
		if (candidateUserList != null && candidateUserList.size() > 0) {
			for (Task task : candidateUserList) {
				System.out.println("任务的名称：" + task.getName());
				System.out.println("任务的id：" + task.getId());
				System.out.println("任务的办理人：" + task.getAssignee()); //为null 因为candidateUser，自然Assignee为null

			}
		}

		List<Task> taskCandidateGroupList = taskQuery.taskCandidateGroup("一级审批组").list();
		System.out.println("------任务办理--组------");
		//ASSIGNEE指的是 当前任务在ASSIGN的待处理中
		if (taskCandidateGroupList != null && taskCandidateGroupList.size() > 0) {
			for (Task task : taskCandidateGroupList) {
				System.out.println("组任务的名称：" + task.getName());
				System.out.println("组任务的id：" + task.getId());
				System.out.println("组任务的办理人：" + task.getAssignee());//为null 因为taskCandidateGroup，自然Assignee为null
			}
		}
	}

	//拾取公共任务（将公共任务变为个人任务）
	@Test
	public void testGroupAssigneeToPerson() {
		//使用默认配置文件创建流程引擎
		String taskId = "102505";
		String userId = "王五";
		processEngine.getTaskService().claim(taskId, userId);
	}

	//退回任务（将个人任务重新变为公共任务）将使任务归还给拿任务的地方
	@Test
	public void testPersonToGroup() {
		//使用默认配置文件创建流程引擎
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		String taskId = "102505";
		processEngine.getTaskService().setAssignee(taskId, null);
	}

	//查看bpmn 资源图片
	@Test
	public void viewImage() throws Exception {
		String deploymentId = "1";
		String imageName = null;
		//取得某个部署的资源的名称  deploymentId
		List<String> resourceNames = processEngine.getRepositoryService().getDeploymentResourceNames(deploymentId);
		// vacationApply.png
		if (resourceNames != null && resourceNames.size() > 0) {
			for (String temp : resourceNames) {
				if (temp.indexOf(".png") > 0) {
					imageName = temp;
				}
			}
		}

		/**
		 * 读取资源
		 * deploymentId:部署的id
		 * resourceName：资源的文件名
		 */
		InputStream resourceAsStream = processEngine.getRepositoryService()
				.getResourceAsStream(deploymentId, imageName);

		//把文件输入流写入到文件中
		File file = new File("d:/" + imageName);
		FileUtils.copyInputStreamToFile(resourceAsStream, file);
	}

	@Test
	public void testQueryProcessDefination() {
		String processDefiKey = "vacationApply";//流程定义key
		//获取流程定义列表   act_re_procdef
		List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery()
				//查询 ，好比where
//        .processDefinitionId(proDefiId) //流程定义id
				// 流程定义id  ： vacationApply:1:4   组成 ： proDefikey（流程定义key）+version(版本)+自动生成id
				.processDefinitionKey(processDefiKey)//流程定义key 由bpmn 的 process 的  id属性决定
//        .processDefinitionName(name)//流程定义名称  由bpmn 的 process 的  name属性决定
//        .processDefinitionVersion(version)//流程定义的版本
				.latestVersion()//最新版本
				//排序
				.orderByProcessDefinitionVersion().desc()//按版本的降序排序
				//结果
//        .count()//统计结果
//        .listPage(arg0, arg1)//分页查询
				.list();

		//遍历结果
		if (list != null && list.size() > 0) {
			for (ProcessDefinition temp : list) {
				System.out.println("流程定义的id: " + temp.getId());
				System.out.println("流程定义的key: " + temp.getKey());
				System.out.println("流程定义的版本: " + temp.getVersion());
				System.out.println("流程定义部署的id: " + temp.getDeploymentId());
				System.out.println("流程定义的名称: " + temp.getName());
			}
		}
	}

	//查询正在运行任务
	@Test
	public void testQueryRunningTask() {
		//取得任务服务
		TaskService taskService = processEngine.getTaskService();
		//创建一个任务查询对象
		TaskQuery taskQuery = taskService.createTaskQuery();
		//所有办理人的任务列表
		List<Task> list = taskQuery.list();
		//遍历任务列表
		if (list != null && list.size() > 0) {
			int order = 1;
			for (Task task : list) {
				System.out.println(order + " 任务的办理人：" + task.getAssignee());
				System.out.println("  任务的id：" + task.getId());
				System.out.println("  任务的名称：" + task.getName());
				System.out.println("-----");
				order++;
			}
		}
	}

	//获取流程实例的状态
	@Test
	public void testGetProcessInstanceState() {
		String processInstanceId = "112501";
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance pi = processEngine.getRuntimeService()
				.createProcessInstanceQuery()
				.processInstanceId(processInstanceId)
				.singleResult();//返回的数据要么是单行，要么是空，其他情况报错
		//判断流程实例的状态
		if (pi != null) {
			//TODO  不知为何pi.getActivityId()为null
			System.out.println("该流程实例 " + processInstanceId + " 正在运行...  " + "当前活动的任务id:" + pi.getActivityId());
			// TODO 如下解决了 pi.getActivityId()为null的问题
			System.out.println("getActiveActivityIds" + runtimeService.getActiveActivityIds(pi.getId()));
		} else {
			System.out.println("当前的流程实例 " + processInstanceId + " 已经结束！");
		}

	}

	/**
	 * 获取历史实例的运行过程
	 */
	@Test
	public void testQueryHistoryTask() {
		String processInstanceId = "65001";
		List<HistoricTaskInstance> list = processEngine.getHistoryService()
				.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId)
				.list();
		if (list != null && list.size() > 0) {
			int index = 1;
			for (HistoricTaskInstance temp : list) {
				System.out.println(index++ + "  历史流程实例任务id：" + temp.getId());
				System.out.println("   历史流程定义的id：" + temp.getProcessDefinitionId());
				System.out.println("   历史流程实例任务名称：" + temp.getName());
				System.out.println("   历史流程实例任务处理人：" + temp.getAssignee());
				System.out.println("   历史流程实例流程变量：" + temp.getProcessVariables());

				System.out.println("--------------");
			}
		}
	}

	/**
	 * 添加用户和组测试
	 * act_id_group表与act_id_user表
	 * 要对应起来，这是组成员和组的对应，当出现组权限时候，要在此添加
	 */
	@Test
	public void testOneCheckGroup() {
		/** 添加用户 */
		IdentityService identityService = processEngine.getIdentityService();
		User user1 = identityService.newUser("张三1");
		user1.setPassword("123");
		User user2 = identityService.newUser("李四1");
		user2.setPassword("456");
		User user3 = identityService.newUser("王五1");
		user3.setPassword("789");

		identityService.saveUser(user1);
		identityService.saveUser(user2);
		identityService.saveUser(user3);

		/** 添加角色组 */
		Group group = identityService.newGroup("一级审批组1");// 创建用户
		group.setName("一级审批组1");
		identityService.saveGroup(group);
		System.out.println(group.getId() + "---" + group.getName());

		// 建立用户和角色的关联关系
		identityService.createMembership(user1.getId(), group.getId());
		identityService.createMembership(user2.getId(), group.getId());
		identityService.createMembership(user3.getId(), group.getId());
		System.out.println("添加一级审批组1成功");
	}


	//完成任务-带有流程变量 决定网关去向
	@Test
	public void testCompleteTaskByTaskId() {
		String taskId = "130002";
		Map<String, Object> params = new HashMap<>();
		params.put("apply", "normal");
		params.put("bxje", "250");
		//taskId：任务id
		processEngine.getTaskService().complete(taskId, params);
		//processEngine.getTaskService().complete(taskId);
		System.out.println(taskId + "-任务执行完毕");
	}

	/**
	 * 测试设置流程变量--获取流程变量
	 */
	@Test
	public void TestVariables() {

		// 得到流程存储服务组件
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 得到运行时服务组件
		RuntimeService runtimeService = processEngine.getRuntimeService();
		// 获取流程任务组件
		TaskService taskService = processEngine.getTaskService();

		Map<String, Object> variables = new HashMap<>(20);
		variables.put("key1", "value1");
		variables.put("key2", "value2");
		taskService.setVariables("110005", variables);

		System.out.println("taskService获取task的流程变量");
		Map<String, Object> taskVariableMap = taskService.getVariables("110005");
		for (String exp : taskVariableMap.keySet()) {
			System.out.println(exp + "--" + taskVariableMap.get(exp));
		}

		System.out.println("runtimeService获取task的流程变量");
		Map<String, Object> runtimeVariableMap = runtimeService.getVariables("65002");
		for (String exp : runtimeVariableMap.keySet()) {
			System.out.println(exp + "--" + runtimeVariableMap.get(exp));
		}

		// 退出
		System.exit(0);

	}

	/**
	 * 跳过任务
	 */
	@Test
	public void signal() {
		processEngine.getRuntimeService().trigger("65002");
		System.out.println("执行tRuntimeService().trigger()");
	}
}
