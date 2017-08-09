package ru.rb.data.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateXmlConverter extends XmlAdapter<String, LocalDate> {

  public static final DateTimeFormatter FMT = DateTimeFormatter.ISO_DATE;
  
  @Override
  public LocalDate unmarshal(String value) throws Exception {
    return LocalDate.parse(value, FMT);
  }

  @Override
  public String marshal(LocalDate date) throws Exception {
    return date.format(FMT);
  }
}