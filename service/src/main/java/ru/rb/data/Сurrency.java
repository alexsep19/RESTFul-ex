package ru.rb.data;

import javax.persistence.Access;
import javax.persistence.AccessType;
//import javax.persistence.AttributeOverride;
//import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="CCY")
@DiscriminatorValue("9")
@Access(AccessType.FIELD)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="CT_ID")
public class Сurrency extends Handbk {
  public Сurrency() {}
  
  public Сurrency( String cd, String nm, String lnm, String dscr
    , LocalDate fDt, LocalDate lDt, byte grnlt, byte prcs_mtl_flg, String spcl_cd ) {
      setCd(cd); setNm(nm); setLnm(lnm); setDscr(dscr); setFDt(fDt); setLDt(lDt);
      setGrnlt(grnlt); setPrcsMtlFlg(prcs_mtl_flg); setSpclCd(spcl_cd);
      setPrnCtId(3); setRtCthId(6);
  }
  
  @Column(name = "GRNLT")
  private byte grnlt;
  public byte getGrnlt() {
    return grnlt;
  }
  public void setGrnlt( byte value ) {
    grnlt = value;
  }  
  
  @Column(name = "PRCS_MTL_FLG")
  private byte prcsMtlFlg;
  public byte getPrcsMtlFlg() {
    return prcsMtlFlg;
  }
  public void setPrcsMtlFlg( byte value ) {
    prcsMtlFlg = value;
  }
  
  @Column(name = "SPCL_CD")
  private String spclCd;
  public String getSpclCd() {
    return spclCd;
  }
  public void setSpclCd( String value ) {
    spclCd = value;
  }
  
}