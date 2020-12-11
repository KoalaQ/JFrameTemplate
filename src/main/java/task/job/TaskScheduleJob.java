package task.job;


import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import util.BeanUtils;
import util.LogUtil;
import util.StringUtils;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2017-10-16<br>
 * <br>
 */
public  class TaskScheduleJob implements Job {
    private  static Logger logger= LogUtil.getLogger(TaskScheduleJob.class);
    //private PosService posService= ServiceFactory.getPosService();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info(Thread.currentThread().getName());
        String className= StringUtils.valueOf(jobExecutionContext.getJobDetail().getJobDataMap().get("classname"));
        String method= StringUtils.valueOf(jobExecutionContext.getJobDetail().getJobDataMap().get("methodname"));
        try {
            BeanUtils.invoke(className,method);
        } catch (Exception e) {
            logger.error("调用任务异常,类名："+className+",方法："+method,e);
            //posService.throwErrorMsg(EnActionEvent.RUN_TASK_WARNING,"调用任务异常"+e.getMessage());
            throw new JobExecutionException(e);
        }
    }
}
