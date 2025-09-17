package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* service.*.*(..)) || execution(* controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(" Entering: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(* service.*.*(..)) || execution(* controller.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println(" Exiting: " + joinPoint.getSignature() + " | Returned: " + result);
    }
}
