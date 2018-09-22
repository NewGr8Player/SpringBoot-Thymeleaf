package com.xavier.config.log.aop;

import com.xavier.bean.OperateLog;
import com.xavier.bean.User;
import com.xavier.config.log.annotation.SystemLog;
import com.xavier.service.OperateLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 系统日志切面，针对使用了SystemLog注释的方法进行拦截
 * 之后将日志数据写入数据库
 *
 * @author NewGr8Player
 */
@Aspect
@Component
public class SystemLogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(SystemLogAspect.class);

    /**
     * 记录每个用户刚开始访问方法的时间
     */
    private static final ThreadLocal<Date> BEGIN_TIME_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal beginTime");

    private static final ThreadLocal<OperateLog> LOG_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal log");


    @Autowired(required = false)
    private HttpServletRequest request;

    /**
     * spring框架自带的线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private OperateLogService operateLogService;

    public SystemLogAspect() {
    }

    /**
     * 对使用SystemLog注释的方法进行拦截
     */
    @Pointcut("@annotation(com.xavier.config.log.annotation.SystemLog)")
    public void systemLogAspectCtrl() {
    }


    /**
     * 前置通知 用于拦截记录用户的操作的开始时间
     *
     * @param joinPoint 切点
     * @throws InterruptedException
     */
    @Before("systemLogAspectCtrl()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {

        Date beginTime = new Date();

        BEGIN_TIME_THREAD_LOCAL.set(beginTime);

        if (LOG.isDebugEnabled()) {
            LOG.debug("开始计时: {}，URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }
    }

    /**
     * 后置通知 用于拦截用户操作
     *
     * @param joinPoint 切点
     */
    @After("systemLogAspectCtrl()")
    public void doAfter(JoinPoint joinPoint) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (null != user) {
            OperateLog operateLog = new OperateLog();

            //TODO 操作信息
            /*
            operateLog.setUid(user.getId());
            operateLog.setUname(user.getName());
            operateLog.setStatus(true);
            operateLog.setFromIp(request.getRemoteAddr());
            operateLog.setMethod(request.getMethod());
            operateLog.setUri(request.getRequestURI());
            operateLog.setLogType("info");
            operateLog.setOperateFunc(getMethodDescription(joinPoint));
            operateLog.setVisitMethod(getMethod(joinPoint));
            operateLog.setMethodCostTime(new Date().getTime() - BEGIN_TIME_THREAD_LOCAL.get().getTime() + "");
            */

            // 开启新线程进行日志记录
            taskExecutor.execute(new SaveLogThread(operateLog, operateLogService));

            LOG_THREAD_LOCAL.set(operateLog);

        }

    }


    /**
     * 获取注解中对方法的描述信息
     *
     * @param joinPoint 切点
     * @return description
     */
    private String getMethodDescription(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLog systemLog = method.getAnnotation(SystemLog.class);
        return systemLog.description();
    }

    private String getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getName();
    }


    /**
     * 异常通知 记录操作报错日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "systemLogAspectCtrl()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        OperateLog operateLog = LOG_THREAD_LOCAL.get();
        // TODO 操作信息
        //operateLog.setLogType("error");
        //operateLog.setVisitMethodErrorInfo(e.getMessage());
        taskExecutor.execute(new UpdateLogThread(operateLog, operateLogService));
    }

    /**
     * 保存日志线程
     */
    private static class SaveLogThread implements Runnable {

        private OperateLog operateLog;
        private OperateLogService operateLogService;


        public SaveLogThread(OperateLog operateLog, OperateLogService operateLogService) {
            this.operateLog = operateLog;
            this.operateLogService = operateLogService;
        }

        @Override
        public void run() {
            operateLogService.insert(operateLog);
        }
    }

    /**
     * 日志更新线程
     */
    private static class UpdateLogThread extends Thread {

        private OperateLog operateLog;
        private OperateLogService operateLogService;

        public UpdateLogThread(OperateLog operateLog, OperateLogService operateLogService) {
            super(UpdateLogThread.class.getSimpleName());
            this.operateLog = operateLog;
            this.operateLogService = operateLogService;
        }

        @Override
        public void run() {
            operateLogService.updateById(operateLog);
        }
    }
}