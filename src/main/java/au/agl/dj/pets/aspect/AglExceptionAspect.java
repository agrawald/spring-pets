package au.agl.dj.pets.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect for handling all the exception thrown in the application and log them
 * This can be a place where we can log the error or send them to Splunk or LogStash
 */
@Slf4j
@Aspect
@Component
public class AglExceptionAspect {
    /**
     * Function to handle all the exceptions in the whole application
     *
     * @param jp        {@link JoinPoint}
     * @param throwable {@link Throwable}
     */
    @AfterThrowing(pointcut = "execution(* au.agl.dj.pets.*.*(..))", throwing = "throwable")
    public void afterThrowing(final JoinPoint jp, final Throwable throwable) {
        log.error("Exception encountered: {}", jp, throwable);
        //TODO can be enhanced to log the error in Splunk or Logstash or send an email or monitoring
    }
}
