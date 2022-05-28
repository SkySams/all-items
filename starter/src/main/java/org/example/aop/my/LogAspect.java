package org.example.aop.my;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author: zyh
 * @date: 2022/5/28
 */
@Component
@Aspect
public class LogAspect {

    /**

     执行前后顺序是这样

     : @Around环绕通知
     : @Before通知执行
     : @Before通知执行结束
     : @Around环绕通知执行结束
     : @After后置通知执行了!
     : @AfterReturning第一个后置返回通知的返回值：success


     */

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final String POINT_CUT = "execution(public * org.example.controller.*.*(..))";

    @Pointcut(POINT_CUT)
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        logger.info("@Before通知执行");

        // 目标参数
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(arg->{
            try {
                logger.info(OBJECT_MAPPER.writeValueAsString(arg));
            } catch (JsonProcessingException e) {
                logger.info(arg.toString());
            }
        });

        //aop代理对象
        Object aThis = joinPoint.getThis();
        logger.info(aThis.toString());

        //aop代理对象
        Object bThis = joinPoint.getThis();
        logger.info(bThis.toString());


        //获取连接点的方法签名对象
        Signature signature = joinPoint.getSignature();
        logger.info(signature.toLongString());
        logger.info(signature.toShortString());
        logger.info(signature.toString());

        //获取方法名
        logger.info(signature.getName());
        //获取声明类型名
        logger.info(signature.getDeclaringTypeName());
        //获取声明类型  方法所在类的class对象
        logger.info(signature.getDeclaringType().toString());
        //和getDeclaringTypeName()一样
        logger.info(signature.getDeclaringType().getName());

        //连接点类型
        String kind = joinPoint.getKind();
        logger.info(kind);

        //返回连接点方法所在类文件中的位置  打印报异常
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        logger.info(sourceLocation.toString());

        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
        logger.info(staticPart.toLongString());

        //attributes可以获取request信息 session信息等
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info(request.getRequestURL().toString());
        // 127.0.0.1
        logger.info(request.getRemoteAddr());
        logger.info(request.getMethod());
        logger.info(request.getRequestURI());

    }

    /**
     * 后置返回
     *      如果第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法参数类型匹配时才能执行后置返回通知，否则不执行，
     *            参数为Object类型将匹配任何目标返回值
     */
    @AfterReturning(value = POINT_CUT,returning = "result")
    public void doAfterReturningAdvice1(JoinPoint joinPoint,Object result) {
        if (result instanceof String){
            System.out.println("字符串");
        }
        logger.info("第一个后置返回通知的返回值：" + result);
    }

    @AfterReturning(value = POINT_CUT,returning = "result",argNames = "result")
    public void doAfterReturningAdvice2(String result) {
        logger.info("第二个后置返回通知的返回值：" + result);
    }

    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *  对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = POINT_CUT,throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        logger.info(joinPoint.getSignature().getName());
        if(exception instanceof NullPointerException){
            logger.info("发生了空指针异常!!!!!");
        }
    }

    @After(value = POINT_CUT)
    public void doAfterAdvice(JoinPoint joinPoint){
        logger.info("后置通知执行了!");
    }

    /**
     * 环绕通知：
     *   注意:Spring AOP的环绕通知会影响到AfterThrowing通知的运行,不要同时使用
     *
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around(value = POINT_CUT)
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        logger.info("@Around环绕通知："+proceedingJoinPoint.getSignature().toString());
        Object obj = null;
        try {
            obj = proceedingJoinPoint.proceed(); //可以加参数
            logger.info(obj.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info("@Around环绕通知执行结束");
        return obj;
    }



}