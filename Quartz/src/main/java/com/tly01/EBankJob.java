package com.tly01;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class EBankJob extends QuartzJobBean {
    private EBankJobBean eBankJobBean;
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
            	eBankJobBean.printAnotherMessage();	
	}
	public void seteBankJobBean(EBankJobBean eBankJobBean) {
		this.eBankJobBean = eBankJobBean;
	}
}
