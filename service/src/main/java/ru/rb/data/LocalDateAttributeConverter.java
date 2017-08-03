package ru.rb.data;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;

import java.time.LocalDate;
import java.sql.Date;

@Converter
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date>
{

  @Override
  public Date convertToDatabaseColumn(LocalDate localDate)
  {
    return localDate == null? null: Date.valueOf(localDate);
  }

  @Override
  public LocalDate convertToEntityAttribute(Date date)
  {
    return date == null? null: date.toLocalDate();
  }
}