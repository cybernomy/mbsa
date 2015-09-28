package com.mg.merp.discount.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.security.model.SecUser;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * CardHist generated by hbm2java
 */
@Entity
@Table(name="DIS_CARD_HIST"
)
public class CardHist extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Card Card;
     private SysClient SysClient;
     private SecUser User;
     private Date TimeStamp;
     private BigDecimal Discount;
     private String Comments;

    public CardHist() {
    }

    public CardHist(Card Card, SysClient SysClient, SecUser User, Date TimeStamp, BigDecimal Discount, String Comments) {
       this.Card = Card;
       this.SysClient = SysClient;
       this.User = User;
       this.TimeStamp = TimeStamp;
       this.Discount = Discount;
       this.Comments = Comments;
    }
   
     @SequenceGenerator(name="generator", sequenceName="DIS_CARD_HIST_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CARD_ID")
    public Card getCard() {
        return this.Card;
    }
    
    public void setCard(Card Card) {
        this.Card = Card;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    public SecUser getUser() {
        return this.User;
    }
    
    public void setUser(SecUser User) {
        this.User = User;
    }

    
    @Column(name="TIME_STAMP", columnDefinition="TIMESTAMP")
    public Date getTimeStamp() {
        return this.TimeStamp;
    }
    
    public void setTimeStamp(Date TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

    
    @Column(name="DISCOUNT", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getDiscount() {
        return this.Discount;
    }
    
    public void setDiscount(BigDecimal Discount) {
        this.Discount = Discount;
    }

    
    @Column(name="COMMENTS", columnDefinition="VARCHAR", length=256)
    public String getComments() {
        return this.Comments;
    }
    
    public void setComments(String Comments) {
        this.Comments = Comments;
    }




}


