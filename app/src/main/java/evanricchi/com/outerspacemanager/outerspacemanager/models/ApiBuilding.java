package evanricchi.com.outerspacemanager.outerspacemanager.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Array;

public class ApiBuilding {
    private Integer level;

    private Integer amountOfEffectByLevel;

    private Integer amountOfEffectLevel0;

    private Integer buildingId;

    private Boolean building;

    private String effect;

    private Integer gasCostByLevel;

    private Integer gasCostLevel0;

    private String imageUrl;

    private Integer mineralCostByLevel;

    private Integer mineralCostLevel0;

    private String name;

    private Integer timeToBuildByLevel;

    private Integer timeToBuildLevel0;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAmountOfEffectByLevel() {
        return amountOfEffectByLevel;
    }

    public void setAmountOfEffectByLevel(Integer amountOfEffectByLevel) {
        this.amountOfEffectByLevel = amountOfEffectByLevel;
    }

    public Integer getAmountOfEffectLevel0() {
        return amountOfEffectLevel0;
    }

    public void setAmountOfEffectLevel0(Integer amountOfEffectLevel0) {
        this.amountOfEffectLevel0 = amountOfEffectLevel0;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Boolean getBuilding() {
        return building;
    }

    public void setBuilding(Boolean building) {
        this.building = building;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public Integer getGasCostByLevel() {
        return gasCostByLevel;
    }

    public void setGasCostByLevel(Integer gasCostByLevel) {
        this.gasCostByLevel = gasCostByLevel;
    }

    public Integer getGasCostLevel0() {
        return gasCostLevel0;
    }

    public void setGasCostLevel0(Integer gasCostLevel0) {
        this.gasCostLevel0 = gasCostLevel0;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMineralCostByLevel() {
        return mineralCostByLevel;
    }

    public void setMineralCostByLevel(Integer mineralCostByLevel) {
        this.mineralCostByLevel = mineralCostByLevel;
    }

    public Integer getMineralCostLevel0() {
        return mineralCostLevel0;
    }

    public void setMineralCostLevel0(Integer mineralCostLevel0) {
        this.mineralCostLevel0 = mineralCostLevel0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimeToBuildByLevel() {
        return timeToBuildByLevel;
    }

    public void setTimeToBuildByLevel(Integer timeToBuildByLevel) {
        this.timeToBuildByLevel = timeToBuildByLevel;
    }

    public Integer getTimeToBuildLevel0() {
        return timeToBuildLevel0;
    }

    public void setTimeToBuildLevel0(Integer timeToBuildLevel0) {
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }
}
