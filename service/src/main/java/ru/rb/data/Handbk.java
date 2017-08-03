package ru.rb.data;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="CT")
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn (name="CTH_ID", discriminatorType = DiscriminatorType.INTEGER )
//@DiscriminatorValue("0")
public abstract class Handbk {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CT")
  @SequenceGenerator(sequenceName = "SEQ_CT", allocationSize = 1, name = "SEQ_CT") 
  @Column(name = "CT_ID")
  private int ctId;
  public int getCtId() {
    return ctId;
  }
  public void setCtId( int value ) {
    ctId = value;
  }
  
  @Column(name = "PRN_CT_ID")
  private int prnCtId;
  public int getPrnCtId() {
    return prnCtId;
  }
  public void setPrnCtId( int value ) {
    prnCtId = value;
  }
  
  @Column(name = "CTH_SEQ_NUM")
  private int cthSeqNum;
  public int getCthSeqNum() {
    return cthSeqNum;
  }
  public void setCthSeqNum( int value ) {
    cthSeqNum = value;
  }
  
  @Column(name = "RT_CTH_ID")
  private int rtCthId;
  public int getRtCthId() {
    return rtCthId;
  }
  public void setRtCthId( int value ) {
    rtCthId = value;
  }
  
  @Column(name = "F_DT")
  @Convert(converter = LocalDateAttributeConverter.class)
  private LocalDate fDt;
  public LocalDate getFDt() {
    return fDt;
  }
  public void setFDt( LocalDate value ) {
    fDt = value;
  }  
  
  @Column(name = "L_DT")
  @Convert(converter = LocalDateAttributeConverter.class)
  private LocalDate lDt;
  public LocalDate getLDt() {
    return lDt;
  }
  public void setLDt( LocalDate value ) {
    lDt = value;
  }    
  
  @Column(name = "CD")
  private String cd;
  public String getCd() {
    return cd;
  }
  public void setCd( String value ) {
    cd = value;
  }
  
  @Column(name = "NM")
  private String nm;
  public String getNm() {
    return nm;
  }
  public void setNm( String value ) {
    nm = value;
  }  
  
  @Column(name = "LNM")
  private String lnm;
  public String getLnm() {
    return lnm;
  }
  public void setLnm( String value ) {
    lnm = value;
  }  
  
  @Column(name = "DSCR")
  private String dscr;
  public String getDscr() {
    return dscr;
  }
  public void setDscr( String value ) {
    dscr = value;
  }   
  
}