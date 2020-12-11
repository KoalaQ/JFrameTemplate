package task.job;


import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import util.LogUtil;

/**
 * ����˵��: <br>
 * ϵͳ�汾: 1.0.0 <br>
 * ������Ա: lyd
 * ����ʱ��: 2017-10-16<br>
 * <br>
 */
public class TaskSchedule {
    private static TaskSchedule taskSchedule=null;
    private Scheduler scheduler;
    private  static Logger logger= LogUtil.getLogger( TaskSchedule.class);

    TaskConfig dealRecTask;//����������
    TaskConfig cleanTask;//������������

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
     * �������Ƿ�����
     * @return
     * @throws SchedulerException
     */
    public  boolean isStarted() throws SchedulerException
    {
        return scheduler.isStarted();
    }


    public void init() throws SchedulerException {
        logger.info("--------��ʼ������-----------");
        System.setProperty("org.terracotta.quartz.skipUpdateCheck","true");//�ر��Զ�����
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

            logger.info("�������"+config);
        }*/
        logger.info("----------��ʼ���������-----------");
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
            logger.info("����:'"+taskConfig.getClassName()+"'����������");
            //stopTask(taskConfig);
            //startTask(taskConfig);
        }

    }
    private  void stopTask(TaskConfig taskConfig)
            throws SchedulerException{
        if(taskConfig.getIsrunning()){
            try {
                scheduler.pauseTrigger(TriggerKey.triggerKey(taskConfig.getTrigeName(), taskConfig.getTrigeGroupName()));
                scheduler.unscheduleJob(TriggerKey.triggerKey(taskConfig.getTrigeName(), taskConfig.getTrigeGroupName()));// �Ƴ�������
                scheduler.deleteJob(JobKey.jobKey(taskConfig.getTaskName(), taskConfig.getTrigeGroupName()));
                taskConfig.setIsrunning(false);
            } catch (SchedulerException e) {
                logger.error("����ֹͣ�쳣",e);
                Trigger.TriggerState state = scheduler.getTriggerState(TriggerKey.triggerKey(taskConfig.getTrigeName(), taskConfig.getTrigeGroupName()));
                if(state==Trigger.TriggerState.NONE){
                    //������
                    taskConfig.setIsrunning(false);
                }
                throw e;
            }
        }else{
            logger.info("����:'"+taskConfig.getClassName()+"'��ֹͣ");
        }


    }

    /**
     * ��������������
     * @throws SchedulerException
     */
    public void startDealRecTask()
            throws SchedulerException{
        startTask(dealRecTask);

    }

    /**
     * ������������
     * @throws SchedulerException
     */
    public void startCleanTask()
            throws SchedulerException{
        startTask(cleanTask);

    }
    /**
     * ֹͣ����Job����
     * @param
     * @return
     * @throws SchedulerException
     */
    public  void stopDealRecTask()
            throws SchedulerException{
        stopTask(dealRecTask);
    }
    /**
     * ֹͣ��������
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
        //region ��ѯδ����֧������Ķ������ڴβ�ѯpos��������͵�fcloud
        TaskConfig taskPushPayResultService=new TaskConfig("com.fingard.pos.task.TaskPushPayResultService",
                "execute","0/2 * * * * ? ");
        taskPushPayResultService.setDescribe("����δ����֧������Ķ���");
        taskPushPayResultService.setTaskName("TaskPushPayResultService");
        configs.add(taskPushPayResultService);
        //endregion
        return configs;
    }*/
    private void initTaskConfig(){
        dealRecTask=new TaskConfig("com.fingard.pos.task.TaskDealRecmentsService",
                "execute","0/10 * * * * ? ","cecTrige","trigGru",
                "TaskPushPayResultService","jg");
        dealRecTask.setDescribe("����δ����֧������Ķ���");

        //����ʹ��
        /*cleanTask=new TaskConfig("com.fingard.pos.task.TaskCleanDataService",
                "execute","0/20 * * * * ? ","claenTrige","trigGru",
                "TaskCleanService","jg");*/
        //ÿ��23������
        cleanTask=new TaskConfig("com.fingard.pos.task.TaskCleanDataService",
                "execute","0 0 23 * * ? ","claenTrige","trigGru",
                "TaskCleanService","jg");
        cleanTask.setDescribe("������������");

    }

}
