package ru.rb.rest;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//TODO: Добавить реализацию MessageBodyReader/Writer для JSON
@ApplicationPath("/rs")
public class ApplicationConfig extends Application {
  private final Set<Class<?>> classes;
  
  public ApplicationConfig() {
    HashSet<Class<?>> set = new HashSet<>();
    set.add(CurrencyService.class);
    classes = Collections.unmodifiableSet(set);
  }
  
  @Override
  public Set<Class<?>> getClasses() {
    return classes;
  }
  
  /**
   * Для работы в GlassFish
   */
  @Override
  public Map<String, Object> getProperties() {
      Map<String, Object> map = new HashMap<>();
      map.put("jersey.config.server.disableMoxyJson", true);
      return map;
  }  
  
}