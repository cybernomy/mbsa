package com.mg.merp.crm.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * LinkProblemSolution generated by hbm2java
 */
@Entity
@Table(name="CRM_LINK_PROBLEM_SOLUTION"
)
public class LinkProblemSolution extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private LinkProblemSolutionId Id;

    public LinkProblemSolution() {
    }

    public LinkProblemSolution(LinkProblemSolutionId Id) {
       this.Id = Id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="CrmSolution", column=@Column(name="SOLUTION_ID", nullable=false) ), 
        @AttributeOverride(name="CrmProblem", column=@Column(name="PROBLEM_ID", nullable=false) ), 
        @AttributeOverride(name="SysClient", column=@Column(name="CLIENT_ID", nullable=false) ) } )
    public LinkProblemSolutionId getId() {
        return this.Id;
    }
    
    public void setId(LinkProblemSolutionId Id) {
        this.Id = Id;
    }




}


