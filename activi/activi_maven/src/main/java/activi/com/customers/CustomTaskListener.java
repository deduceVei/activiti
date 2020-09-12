package activi.com.customers;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.Set;

/**
 * @author Hiseico
 * @create 2018-06-07 17:10
 * @desc 自定义任务监听器
 **/
public class CustomTaskListener implements TaskListener {
	/**
	 * 监听任务事件
	 *
	 * @param delegateTask
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		//任务办理人
		String assignee = delegateTask.getAssignee();
		//任务名称
		String eventName = delegateTask.getEventName();
		//事件名称
		String name = delegateTask.getName();
		//流程实例Id
		String processInstanceId = delegateTask.getProcessInstanceId();
		//获取当前流程实例范围内的所有流程变量的名字
		Set<String> variableNames = delegateTask.getVariableNames();
		for (String key : variableNames) {
			Object value = delegateTask.getVariable(key);
			System.out.println(key + " = " + value);
		}
		System.out.println("一个["+name+"]任务被创建了，由["+assignee+"]负责办理");
	}
}
