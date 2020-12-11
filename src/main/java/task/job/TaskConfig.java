package task.job;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2017-10-20<br>
 * <br>
 */
public class TaskConfig {
    private String className;
    private String methodName;
    private String trige;
    private String trigeName;
    private String trigeGroupName;
    private String taskName;
    private String describe;
    private String group;//任务
    private Boolean isrunning=false;

    public TaskConfig() {
    }

    public TaskConfig(String className, String methodName, String trige) {
        this.className = className;
        this.methodName = methodName;
        this.trige = trige;
    }

    public TaskConfig(String className, String methodName, String trige, String trigeName, String trigeGroupName, String taskName, String taskGroup) {
        this.className = className;
        this.methodName = methodName;
        this.trige = trige;
        this.trigeName = trigeName;
        this.trigeGroupName = trigeGroupName;
        this.taskName = taskName;
        this.group = group;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getTrige() {
        return trige;
    }

    public void setTrige(String trige) {
        this.trige = trige;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTrigeName() {
        return trigeName;
    }

    public void setTrigeName(String trigeName) {
        this.trigeName = trigeName;
    }

    public String getTrigeGroupName() {
        return trigeGroupName;
    }

    public void setTrigeGroupName(String trigeGroupName) {
        this.trigeGroupName = trigeGroupName;
    }

    public Boolean getIsrunning() {
        return isrunning;
    }

    public void setIsrunning(Boolean isrunning) {
        this.isrunning = isrunning;
    }

    @Override
    public String toString() {
        return "TaskConfig{" +
                "taskName='" + taskName + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}