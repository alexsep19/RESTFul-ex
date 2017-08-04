package ru.rb.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Currency.class)
public class Currencies extends ArrayList<Currency> {
  
  public Currencies() {
    super();
  }
  
  public Currencies(Collection<? extends Currency> coll) {
    super(coll);
  }
  
  @XmlElement(name="currency")
  public List<Currency> getCurrencies () {
    return this;
  }
  
  public void setCurrencies ( List<Currency> values ) {
    super.addAll(values);
  }
  
}