package com.example.logging.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@RestController
public class LoggingController {
  Logger logger = LoggerFactory.getLogger(LoggingController.class);

  @RequestMapping("/")
  public String index() {
    logger.trace("A TRACE Message");
    logger.debug("A DEBUG Message");
    logger.info("An INFO Message");
    logger.warn("A WARN Message");
    logger.error("An ERROR Message");

    return "Howdy! Check out the Logs to see the output...";
  }

  @RequestMapping("/ref")
  public String ref() throws ClassNotFoundException {

    StringBuilder sb = new StringBuilder();
    Class c = Class.forName("com.example.logging.model.Emp");
//        Constructor array
    Constructor[] constructors = c.getDeclaredConstructors();
    sb.append("constructors :" + constructors.length);
    for(Constructor constructor : constructors) {
      System.out.println("Name of Constructor : "+constructor);

      System.out.println("Count of constructor parameter : "+constructor.getParameterCount());

      Parameter[] parameters = constructor.getParameters();
      for(Parameter parameter : parameters) {
        System.out.println("Constructor's parameter : "+parameter);
      }
      System.out.println(System.lineSeparator());
    }
    System.out.println(System.lineSeparator());
//          Method Array
    Method[] methods = c.getDeclaredMethods();
    System.out.println("Length of method : "+methods.length);
    sb.append("Length of method : "+methods.length);
    for(Method method : methods){
      System.out.println("Method name: \t"+method);
      System.out.println("Method return type : \t"+method.getReturnType());
      System.out.println("Method parameter count: \t"+method.getParameterCount());
      System.out.println(System.lineSeparator());
      Parameter[] parameters = method.getParameters();
      for(Parameter parameter : parameters) {
        System.out.println("Method's Parameter : "+parameter);
      }
      System.out.println(System.lineSeparator());
    }
    System.out.println(System.lineSeparator());
//        Annotations
    Class[] classes = c.getDeclaredClasses();
    sb.append("Length of classes : "+classes.length);
    for(Class class1 : classes) {
      System.out.println("class: "+class1);
      System.out.println("Name of class: "+class1.getName());
    }
//        Annotations
    Annotation[] anno = c.getDeclaredAnnotations();
    sb.append("Length of Annotations : "+anno.length);
    for(Annotation annotation : anno) {
      System.out.println("Annotation: "+annotation);
    }


    return sb.toString();
  }
}
