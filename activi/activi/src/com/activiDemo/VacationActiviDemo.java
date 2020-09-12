package com.activiDemo;


import org.activiti.engine.*;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import java.util.List;

/**
 * @author deduce
 */
public class VacationActiviDemo {


	public static void main(String[] args)  {
		// 创建流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		System.out.println(engine);
		testQueryTaskByAssigneeCheckOne(engine);
		engine.close();


//		// 得到流程存储服务组件
//		RepositoryService repositoryService = engine.getRepositoryService();
//		// 得到运行时服务组件
//		RuntimeService runtimeService = engine.getRuntimeService();
//		// 获取流程任务组件
//		TaskService taskService = engine.getTaskService();
//
//		// 部署流程文件
//		repositoryService.createDeployment()
//				.addClasspathResource("bpmn/First.bpmn").deploy();
//		// 启动流程
//		runtimeService.startProcessInstanceByKey("process1");
//		// 查询第一个任务
//		Task task = taskService.createTaskQuery().singleResult();
//		System.out.println("第一个任务完成前，当前任务名称：" + task.getName());
//		// 完成第一个任务
//		taskService.complete(task.getId());
//		// 查询第二个任务
//		task = taskService.createTaskQuery().singleResult();
//		System.out.println("第二个任务完成前，当前任务名称：" + task.getName());
//		// 完成第二个任务（流程结束）
//		taskService.complete(task.getId());
//		task = taskService.createTaskQuery().singleResult();
//		System.out.println("流程结束后，查找任务：" + task);
//
//		engine.close();
		// 退出
//		System.exit(0);
	}


	private static void testQueryTaskByAssigneeCheckOne(ProcessEngine engine) {
		//任务的办理人
		String assignee = "第一审批人";
		//取得任务服务
		TaskService taskService = engine.getTaskService();
		//创建一个任务查询对象
		TaskQuery taskQuery = taskService.createTaskQuery();
		//指定办理人的任务列表
		List<Task> list = taskQuery.taskAssignee(assignee)
				.list();
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
}
