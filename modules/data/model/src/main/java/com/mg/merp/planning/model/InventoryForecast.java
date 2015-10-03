package com.mg.merp.planning.model;

import com.mg.merp.core.model.SysClient;
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
 * InventoryForecast generated by hbm2java
 */
@Entity
@Table(name = "PP_INVENTORY_FORECAST")
public class InventoryForecast extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private String InventoryForecastCode;

    private String Description;

    public InventoryForecast() {
    }

    public InventoryForecast(SysClient SysClient, String InventoryForecastCode, String Description) {
        this.SysClient = SysClient;
        this.InventoryForecastCode = InventoryForecastCode;
        this.Description = Description;
    }

    @SequenceGenerator(name = "generator", sequenceName = "PP_INVENTORY_FORECAST_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, columnDefinition = "INTEGER")
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @Column(name = "INVENTORY_FORECAST_CODE", columnDefinition = "CHAR", length = 20)
    public String getInventoryForecastCode() {
        return this.InventoryForecastCode;
    }

    public void setInventoryForecastCode(String InventoryForecastCode) {
        this.InventoryForecastCode = InventoryForecastCode;
    }

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR", length = 80)
    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}

