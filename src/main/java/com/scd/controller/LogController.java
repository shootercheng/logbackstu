package com.scd.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;


@RestController
public class LogController {
	
	private Logger LOGGER = LoggerFactory.getLogger(LogController.class);
	
	@RequestMapping(value="/showloglevel",method = RequestMethod.GET)
	public String logTest(){
		LOGGER.info("info level log");
		LOGGER.debug("debug level log");
		LOGGER.warn("warn level log");
		LOGGER.error("error level log");
		return "ok";
	}
	
	  // debug 级别设置， 输出 debug、info、warn、error 日志
	  // info 级别设置， 输出 info、warn、error 日志
	  // warn 级别设置, 输出 warn、error 日志
	  // error 级别设置， 输出 error 日志
	 /**
	 * 
     * logback动态修改包名的日志级别
     * @param level 日志级别
     * @param packageName 包名
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/level")
    public String updateLogbackLevel( @RequestParam(value="level") String level,
                                      @RequestParam(value="packageName",defaultValue = "-1") String packageName) throws Exception {
        ch.qos.logback.classic.LoggerContext loggerContext =(ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();
        if(packageName.equals("-1")) {
            // 默认值-1，更改全局日志级别；否则按传递的包名或类名修改日志级别。
            loggerContext.getLogger("root").setLevel(ch.qos.logback.classic.Level.toLevel(level));
        } else {
            loggerContext.getLogger(packageName).setLevel(ch.qos.logback.classic.Level.valueOf(level));
        }
        return "success";
    }
    
    @RequestMapping(value="/getloglevel", method=RequestMethod.GET)
    public Map<String, String> getLogLvel(@RequestParam(value="packageName",defaultValue = "-1") String packageName){
    	Map<String, String> map = new HashMap<>();
    	ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
    	if(iLoggerFactory instanceof LoggerContext){
    		LoggerContext loggerContext = (LoggerContext) iLoggerFactory;
    		if("-1".equals(packageName)){
    			packageName = "root";
    		}
    		Level loggerLevel = loggerContext.getLogger(packageName).getLevel();
    		map.put("packageName", packageName);
    		map.put("level", String.valueOf(loggerLevel));
    	}
    	return map;
    }
}
