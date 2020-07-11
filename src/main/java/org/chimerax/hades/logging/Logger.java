package org.chimerax.hades.logging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 04-Jun-20
 * Time: 6:34 PM
 */

@Aspect
@Component
@AllArgsConstructor
public class Logger {

    private LogRepository logRepository;

    @Async
    @After("@annotation(Logged)")
    public void createLog(JoinPoint joinPoint) {

        final Log log = new Log()
                .setText(Arrays.toString(joinPoint.getArgs()))
                .setPath(joinPoint.getSignature().toShortString())
                .setType("INFO");


        logRepository.save(log);

    }
}
