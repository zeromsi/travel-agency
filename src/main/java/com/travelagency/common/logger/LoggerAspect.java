package com.travelagency.common.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.travelagency.data.User;

@Aspect
@Component
public class LoggerAspect {
	final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	
	private final String SERVICE = "execution(* com.travelagency.business.service.*.*.*(..))";

	private final String CONTROLLER = "execution(* com.travelagency.business.web.*.*(..))";
	
	
	@AfterThrowing(pointcut = SERVICE, throwing = "ex")
	public void auditException_ServiceLayer(JoinPoint joinPoint, Exception ex) throws Throwable {
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		StringBuffer parameters = new StringBuffer();
		getParametersAndValuesAsStringBuffer(joinPoint, codeSignature, parameters);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName;
		try {
			UserDetails userDetails  = (UserDetails)authentication.getPrincipal();
			userName = userDetails.getUsername();
		}catch(Exception e) {
			userName = "anonymous";
		}
		LogFormat log=new LogFormat( joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(), ex.toString(), parameters.toString(), userName);
		
		LOGGER.log(Level.WARNING, log.toString());
	}
	
	
	@Around(CONTROLLER)
	public Object auditTrail_ControllerLayer(ProceedingJoinPoint joinPoint) throws Throwable {

		final long start = System.currentTimeMillis();
		final long executionTime = System.currentTimeMillis() - start;
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		StringBuffer parameters = new StringBuffer();
		getParametersAndValuesAsStringBuffer(joinPoint, codeSignature, parameters);
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName;
		try {
			UserDetails userDetails  = (UserDetails)authentication.getPrincipal();
			userName = userDetails.getUsername();
		}catch(Exception e) {
			userName = "anonymous";
		}
		LogFormat log=new LogFormat( joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(), "n/a", parameters.toString(), userName);
	
		LOGGER.log(Level.INFO, log.toString());
		
		final Object proceed = joinPoint.proceed();
		return proceed;
	}
	
	
	@Around(SERVICE)
	public Object auditTrail_ServiceLayer(ProceedingJoinPoint joinPoint) throws Throwable {

		final long start = System.currentTimeMillis();
		final long executionTime = System.currentTimeMillis() - start;
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		StringBuffer parameters = new StringBuffer();
		getParametersAndValuesAsStringBuffer(joinPoint, codeSignature, parameters);
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName;
		try {
			UserDetails userDetails  = (UserDetails)authentication.getPrincipal();
			userName = userDetails.getUsername();
		}catch(Exception e) {
			userName = "anonymous";
		}
		LogFormat log=new LogFormat( joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(), "n/a", parameters.toString(), userName);
	
		LOGGER.log(Level.INFO, log.toString());
		
		final Object proceed = joinPoint.proceed();
		return proceed;
	}
	
	
	
	private void getParametersAndValuesAsStringBuffer(JoinPoint joinPoint, CodeSignature codeSignature,
			StringBuffer parameters) {
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			String[] argNames = codeSignature.getParameterNames();
			for (int i = 0; i < argNames.length; i++) {
				if (i < argNames.length) {
					parameters.append(argNames[i] + ":" + joinPoint.getArgs()[i] + ", ");
				} else {
					parameters.append(argNames[i] + ":" + joinPoint.getArgs()[i]);
				}
			}
		}
	}
}
