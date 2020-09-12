package activi.com.customers;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * @author deduce
 */
public class CustomExcutionListener implements ExecutionListener {
	//当监听事件发生时执行此方法
	@Override
	public void notify(DelegateExecution delegateExecution) {
		System.out.println("自定义的监听器执行。。当监听事件发生时执行此方法。");
	}
}

