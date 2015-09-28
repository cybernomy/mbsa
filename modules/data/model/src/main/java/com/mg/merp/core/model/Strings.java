package com.mg.merp.core.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Strings generated by hbm2java
 */
@Entity
@Table(name="STRINGS"
)
public class Strings extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private StringsId id;
     private String Text;

    public Strings() {
    }

	
    public Strings(StringsId id) {
        this.id = id;
    }
    public Strings(StringsId id, String Text) {
       this.id = id;
       this.Text = Text;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="Section", column=@Column(name="SECTION", columnDefinition="INTEGER") ), 
        @AttributeOverride(name="Val", column=@Column(name="VAL", columnDefinition="INTEGER") ) } )
    public StringsId getId() {
        return this.id;
    }
    
    public void setId(StringsId id) {
        this.id = id;
    }

    
    @Column(name="TEXT", columnDefinition="VARCHAR", length=80)
    public String getText() {
        return this.Text;
    }
    
    public void setText(String Text) {
        this.Text = Text;
    }




}


