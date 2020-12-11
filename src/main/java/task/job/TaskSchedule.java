package task.job;


import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import util.LogUtil;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2017-10-16<br>
 * <br>
 */
public class TaskSchedule {
    private static TaskSchedule taskSchedule=null;
    private Scheduler scheduler;
    private  static Logger logger= LogUtil.getLogger( TaskSchedule.class);

    TaskConfig dealRecTask;//处理单据任务
    TaskConfig cleanTask;//清理数据任务

    public static  TaskSchedule getTaskSchedule()  {
        if(taskSchedule==null){
            synchronized ( TaskSchedule.class) {
                if(taskSchedule==null){
                    taskSchedule=new  TaskSchedule();
                }
            }
        }
        return taskSchedule;
    }
    private TaskSchedule()  {
        initTaskConfig();
    }

    public void start() throws SchedulerException {
        scheduler.start();
    }
    public void stop() throws SchedulerException {
        stopCleanTask();
        stopDealRecTask();
        //scheduler.pauseAll();
    }
    /**
     * 检查调度是否启动
     * @return
     * @throws SchedulerException
     */
    public  boolean isStarted() throws SchedulerException
    {
        return scheduler.isStarted();
    }


    public void init() throws SchedulerException {
        logger.info("--------初始化任务-----------");
        System.setProperty("org.terracotta.quartz.skipUpdateCheck","true");//关闭自动更新
        SchedulerFactory factory=new StdSchedulerFactory();
        scheduler=factory.getScheduler();
       /* for(TaskConfig config : configs){
            JobDetail job= JobBuilder.newJob(TaskScheduleJob.class).withIdentity(config.getTaskName(),"jg").build();
            job.getJobDataMap().put("classname",config.getClassName());
            job.getJobDataMap().put("methodname",config.getMethodName());
            Trigger trigger= TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
                    .withSchedule(CronScheduleBuilder.cronSchedule(config.getTrige()).withMisfireHandlingInstructionDoNothing())
                    .startNow().build();
            System.out.println(trigger.getMisfireInstruction());
            scheduler.scheduleJob(job, trigger);

            logger.info("添加任务："+config);
        }*/
        logger.info("----------初始化任务完成-----------");
    }

    private void startTask(TaskConfig taskConfig)
            throws SchedulerException{
        if(!taskConfig.getIsrunning()){
            JobDetail job= JobBuilder.newJob( TaskScheduleJob.class).
                    withIdentity(taskConfig.getTaskName(), taskConfig.getTrigeGroupName()).build();
            job.getJobDataMap().put("classname",taskConfig.getClassName());
            job.getJobDataMap().put("methodname",taskConfig.getMethodName());
            Trigger trigger= TriggerBuilder.newTrigger().withIdentity(taskConfig.getTrigeName(), taskConfig.getTrigeGroupName())
                    .withSchedule(CronScheduleBuilder.cronSchedule(taskConfig.getTrige()))
                    .startNow().build();
            scheduler.scheduleJob(job, trigger);
            taskConfig.setIsrunning(true);
        }else{
            logger.info("任务:'"+taskConfig.getClassName()+"'已在运行中");
            //stopTask(taskConfig);
            //startTask(taskConfig);
        }

    }
    private  void stopTask(TaskConfig taskConfig)
            throws SchedulerException{
        if(taskConfig.getIsrunning()){
            try {
                scheduler.pauseTrigger(TriggerKey.triggerKey(taskConfig.getTrigeName(), taskConfig.getTrigeGroupName()));
                scheduler.unscheduleJob(TriggerKey.triggerKey(taskConfig.getTrigeName(), taskConfig.getTrigeGroupName()));// 移除触发器
                scheduler.deleteJob(JobKey.jobKey(taskConfig.getTaskName(), taskConfig.getTrigeGroupName()));
                taskConfig.setIsrunning(false);
            } catch (SchedulerException e) {
                logger.error("任务停止异常",e);
                Trigger.TriggerState state = scheduler.getTriggerState(TriggerKey.triggerKey(taskConfig.getTrigeName(), taskConfig.getTrigeGroupName()));
                if(state==Trigger.TriggerState.NONE){
                    //不存在
                    taskConfig.setIsrunning(false);
                }
                throw e;
            }
        }else{
            logger.info("任务:'"+taskConfig.getClassName()+"'已停止");
        }


    }

    /**
     * 启动处理单据任务
     * @throws SchedulerException
     */
    public void startDealRecTask()
            throws SchedulerException{
        startTask(dealRecTask);

    }

    /**
     * 启动清理任务
     * @throws SchedulerException
     */
    public void startCleanTask()
            throws SchedulerException{
        startTask(cleanTask);

    }
    /**
     * 停止调度Job任务
     * @param
     * @return
     * @throws SchedulerException
     */
    public  void stopDealRecTask()
            throws SchedulerException{
        stopTask(dealRecTask);
    }
    /**
     * 停止清理任务
     * @param
     * @return
     * @throws SchedulerException
     */
    public  void stopCleanTask()
            throws SchedulerException{
        stopTask(cleanTask);
    }


  /*  private List<TaskConfig> getTaskConfig(){
        List<TaskConfig> configs=new ArrayList<TaskConfig>();
        //region 查询未推送支付结果的订单，在次查询pos后进行推送到fcloud
        TaskConfig taskPushPayResultService=new TaskConfig("com.fingard.pos.task.TaskPushPayResultService",
                "execute","0/2 * * * * ? ");
        taskPushPayResultService.setDescribe("推送未推送支付结果的订单");
        taskPushPayResultService.setTaskName("TaskPushPayResultService");
        configs.add(taskPushPayResultService);
        //endregion
        return configs;
    }*/
    private void initTaskConfig(){
        dealRecTask=new TaskConfig("com.fingard.pos.task.TaskDealRecmentsService",
                "execute","0/10 * * * * ? ","cecTrige","trigGru",
                "TaskPushPayResultService","jg");
        dealRecTask.setDescribe("推送未推送支付结果的订单");

        //测试使用
        /*cleanTask=new TaskConfig("com.fingard.pos.task.TaskCleanDataService",
                "execute","0/20 * * * * ? ","claenTrige","trigGru",
                "TaskCleanService","jg");*/
        //每天23点清理
        cleanTask=new TaskConfig("com.fingard.pos.task.TaskCleanDataService",
                "execute","0 0 23 * * ? ","claenTrige","trigGru",
                "TaskCleanService","jg");
        cleanTask.setDescribe("清理数据任务");

    }

}
