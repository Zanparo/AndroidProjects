package com.epitech.carbomap.model;

import android.util.Log;

/**
 * Created by samue on 18/04/2017.
 */

public class ScenarResult {

    public boolean isComplete() {
        if (_scenar == eScenario.UNSELECT || _area_distrib == eAreaDistrib.UNSELECT || _frequency == eFrequency.UNSELECT || _quick_access == eQuickAccess.UNSELECT || _need_data == eNeedData.UNSELECT || _past_data == ePastData.UNSELECT || _spatial == eSpatial.UNSELECT)
            return false;
        return true;
    }

    public eScenario getScenar() {
        return _scenar;
    }

    public void setScenar(eScenario _scenar) {
        this._scenar = _scenar;
    }

    public void setScenar(int _scenar) {
        this._scenar = eScenario.values()[_scenar];
    }

    public double getBudget() {
        return _budget;
    }

    public void setBudget(double _budget) {
        this._budget = _budget;
    }

    public ePriority getBudgetPriority() {
        return _budget_priority;
    }

    public void setBudgetPriority(int priority) {
        if (priority > ePriority.values().length - 1)
            priority = 0;
        this._budget_priority = ePriority.values()[priority];
    }

    public eFlexibility getBudgetFlexibility() {
        return _budget_flexibility;
    }

    public void setBudgetFlexibility(int flexibility) {
        if (flexibility > eFlexibility.values().length - 1)
            flexibility = 0;
        this._budget_flexibility = eFlexibility.values()[flexibility];
    }

    public eBudgetUnit getBudgetUnit() {
        return _budget_unit;
    }

    public void setBudgetUnit(int budgetUnit) {
        this._budget_unit = eBudgetUnit.values()[budgetUnit];
    }

    public eAreaDistrib getAreaDistrib() {
        return _area_distrib;
    }

    public void setAreaDistrib(int _area_distrib) {
        this._area_distrib = eAreaDistrib.values()[_area_distrib];
    }

    public int getAreaAmount() {
        return _area_amount;
    }

    public void setAreaAmount(int area_amount) {
        this._area_amount = area_amount;
    }

    public eAreaUnit getAreaUnit() {
        return _area_unit;
    }

    public void setAreaUnit(int area_unit) {
        this._area_unit = eAreaUnit.values()[area_unit];
    }

    public int getTotalAreas() {
        return _total_areas;
    }

    public void setTotalAreas(int totalAreas) {
        this._total_areas = totalAreas;
    }

    public eAreaUnit getTotalAreasUnit() {
        return _total_areas_unit;
    }

    public void setTotalAreasUnit(int totalAreasUnit) {
        this._total_areas_unit = eAreaUnit.values()[totalAreasUnit];
    }

    public ePriority getAreaPriority() {
        return _area_priority;
    }

    public void setAreaPriority(int priority) {
        if (priority > ePriority.values().length - 1)
            priority = 0;
        this._area_priority = ePriority.values()[priority];
    }

    public eFlexibility getAreaFlexibility() {
        return _area_flexibility;
    }

    public void setAreaFlexibility(int flexibility) {
        if (flexibility > eFlexibility.values().length - 1)
            flexibility = 0;
        this._area_flexibility = eFlexibility.values()[flexibility];
    }

    public eSpatial getSpatial() {
        return _spatial;
    }

    public void setSpatial(int spatial) {
        this._spatial = eSpatial.values()[spatial];
    }

    public ePriority getSpatialPriority() {
        return _spatial_priority;
    }

    public void setSpatialPriority(int priority) {
        if (priority > ePriority.values().length - 1)
            priority = 0;
        this._spatial_priority = ePriority.values()[priority];
    }

    public eFlexibility getSpatialFlexibility() {
        return _spatial_flexibility;
    }

    public void setSpatialFlexibility(int flexibility) {
        if (flexibility > eFlexibility.values().length - 1)
            flexibility = 0;
        this._spatial_flexibility = eFlexibility.values()[flexibility];
    }

    public ePastData getPastData() {
        return _past_data;
    }

    public void setPastData(int pastData) {
        this._past_data = ePastData.values()[pastData];
    }

    public ePriority getPastDataPriority() {
        return _past_data_priority;
    }

    public void setPastDataPriority(int priority) {
        if (priority > ePriority.values().length - 1)
            priority = 0;
        this._past_data_priority = ePriority.values()[priority];
    }

    public eFlexibility getPastDataFlexibility() {
        return _past_data_flexibility;
    }

    public void setPastDataFlexibility(int flexibility) {
        if (flexibility > eFlexibility.values().length - 1)
            flexibility = 0;
        this._past_data_flexibility = eFlexibility.values()[flexibility];
    }

    public eNeedData getNeedData() {
        return _need_data;
    }

    public void setNeedData(int needData) {
        this._need_data = eNeedData.values()[needData];
    }

    public ePriority getNeedDataPriority() {
        return _need_data_priority;
    }

    public void setNeedDataPriority(int priority) {
        if (priority > ePriority.values().length - 1)
            priority = 0;
        this._need_data_priority = ePriority.values()[priority];
    }

    public eFlexibility getNeedDataFlexibility() {
        return _need_data_flexibility;
    }

    public void setNeedDataFlexibility(int flexibility) {
        if (flexibility > eFlexibility.values().length - 1)
            flexibility = 0;
        this._need_data_flexibility = eFlexibility.values()[flexibility];
    }

    public eQuickAccess getQuickAccess() {
        return _quick_access;
    }

    public void setQuickAccess(int quickAccess) {
        this._quick_access = eQuickAccess.values()[quickAccess];
    }

    public ePriority getQuickAccessPriority() {
        return _quick_access_priority;
    }

    public void setQuickAccessPriority(int priority) {
        if (priority > ePriority.values().length - 1)
            priority = 0;
        this._quick_access_priority = ePriority.values()[priority];
    }

    public eFlexibility getQuickAccessFlexibility() {
        return _quick_access_flexibility;
    }

    public void setQuickAccessFlexiblity(int flexiblity) {
        if (flexiblity > eFlexibility.values().length - 1)
            flexiblity = 0;
        this._quick_access_flexibility = eFlexibility.values()[flexiblity];
    }

    public eFrequency getFrequency() {
        return _frequency;
    }

    public void setFrequency(int frequency) {
        this._frequency = eFrequency.values()[frequency];
    }

    public ePriority getFrequencyPriority() {
        return _frequency_priority;
    }

    public void setFrequencyPriority(int priority) {
        if (priority > ePriority.values().length - 1)
            priority = 0;
        this._frequency_priority = ePriority.values()[priority];
    }

    public eFlexibility getFrequencyFlexibility() {
        return _frequency_flexibility;
    }

    public void setFrequencyFlexibility(int flexibility) {
        if (flexibility > eFlexibility.values().length - 1)
            flexibility = 0;
        this._frequency_flexibility = eFlexibility.values()[flexibility];
    }

    public String expose() {
        if (isComplete())
            return "Scenario:" + _scenar.toString() + " |budget_priority:" + _budget_priority + " |budget_flexibility:" + _budget_flexibility.toString() + " |budgetValue:" + _budget + " |budgetUnit:" + _budget_unit.toString() + " |areaDistrib:" + _area_distrib.toString() + " |areaAmount:" + _area_amount + " |areaUnit:" + _area_unit.toString() + " |totalAreas:" + _total_areas + " |totalAreasUnit:" + _total_areas_unit.toString() + " |areaPriority:" + _area_priority.toString() + " |areaFlexibility:" + _area_flexibility.toString() + " |spatial:" + _spatial.toString() + " |spatialPriority:" + _spatial_priority.toString() + " |spatialFlexibility:" + _spatial_flexibility.toString() + " |quickAccess:" + _quick_access + " |quickAccessPriority:" + _quick_access_priority + " |quickAccessFlexibility:" + _quick_access_flexibility + " |pastData:" + _past_data.toString() + " |pastDataPriority:" + _past_data_priority.toString() + " |pastDataFlexibility:" + _past_data_flexibility.toString() + "  |frequency:" + _frequency.toString() + "  |frequencyPriority:" + _frequency_priority + "  |frequencyFlexibility:" + _frequency_flexibility;
        else {
            Log.i("Info=", "Scenario:" + _scenar.toString() + " |budget_priority:" + _budget_priority + " |budget_flexibility:" + _budget_flexibility.toString() + " |budgetValue:" + _budget + " |budgetUnit:" + _budget_unit.toString() + " |areaDistrib:" + _area_distrib.toString() + " |areaAmount:" + _area_amount + " |areaUnit:" + _area_unit.toString() + " |totalAreas:" + _total_areas + " |totalAreasUnit:" + _total_areas_unit.toString() + " |areaPriority:" + _area_priority.toString() + " |areaFlexibility:" + _area_flexibility.toString() + " |spatial:" + _spatial.toString() + " |spatialPriority:" + _spatial_priority.toString() + " |spatialFlexibility:" + _spatial_flexibility.toString() + " |quickAccess:" + _quick_access + " |quickAccessPriority:" + _quick_access_priority + " |quickAccessFlexibility:" + _quick_access_flexibility + " |pastData:" + _past_data.toString() + " |pastDataPriority:" + _past_data_priority.toString() + " |pastDataFlexibility:" + _past_data_flexibility.toString() + "  |frequency:" + _frequency.toString() + "  |frequencyPriority:" + _frequency_priority + "  |frequencyFlexibility:" + _frequency_flexibility);
            return "Error : Fields missing";}
    }

    public enum eScenario {
        UNSELECT,
        STORM,
        DISEASE,
        CARBON
    }

    public enum ePriority {
        LOW,
        MEDIUM,
        HIGH
    }

    public enum eFlexibility {
        NONE,
        LOW,
        MEDIUM,
        HIGH
    }

    public enum eAreaDistrib {
        UNSELECT,
        CONTINUOUS,
        CLUSTERED,
        DISPARATE
    }

    public enum eBudgetUnit {
        HECTAR,
        TOTAL
    }

    public enum eAreaUnit {
        HECTARES,
        KM
    }

    public enum eSpatial {
        UNSELECT,
        SUBMETRE,
        METRE,
        HIGH,
        MEDIUM,
        LOW
    }

    public enum ePastData {
        UNSELECT,
        NONE,
        PASTMONTH,
        PASTSEASON,
        PASTSIXMONTHS
    }

    public enum eNeedData {
        UNSELECT,
        NONE,
        ONEWEEK,
        TWOWEEKS,
        ONEMONTH,
        TWOMONTHS,
        SEASON,
        SIXMONTHS
    }

    public enum eQuickAccess {
        UNSELECT,
        DAYS,
        ONETWOWEEK,
        ONEMONTH,
        TWOMONTHS,
        TWOSIXMONTHS
    }

    public enum eFrequency {
        UNSELECT,
        TIMEPERIOD
    }


    private eScenario _scenar = eScenario.UNSELECT;
    private ePriority _budget_priority = ePriority.LOW;
    private eFlexibility _budget_flexibility = eFlexibility.NONE;
    private double _budget = -1;
    private eBudgetUnit _budget_unit = eBudgetUnit.HECTAR;
    private eAreaDistrib _area_distrib = eAreaDistrib.UNSELECT;
    private int _area_amount = -1;
    private eAreaUnit _area_unit = eAreaUnit.HECTARES;
    private ePriority _area_priority = ePriority.LOW;
    private eFlexibility _area_flexibility = eFlexibility.NONE;
    private int _total_areas = -1 ;
    private eAreaUnit _total_areas_unit = eAreaUnit.HECTARES;
    private eSpatial _spatial = eSpatial.UNSELECT;
    private ePriority _spatial_priority = ePriority.LOW;
    private eFlexibility _spatial_flexibility = eFlexibility.NONE;
    private ePastData _past_data = ePastData.UNSELECT;
    private ePriority _past_data_priority = ePriority.LOW;
    private eFlexibility _past_data_flexibility = eFlexibility.NONE;
    private eNeedData _need_data = eNeedData.UNSELECT;
    private ePriority _need_data_priority = ePriority.LOW;
    private eFlexibility _need_data_flexibility = eFlexibility.NONE;
    private eQuickAccess _quick_access = eQuickAccess.UNSELECT;
    private ePriority _quick_access_priority = ePriority.LOW;
    private eFlexibility _quick_access_flexibility = eFlexibility.NONE;
    private eFrequency _frequency = eFrequency.UNSELECT;
    private ePriority _frequency_priority = ePriority.LOW;
    private eFlexibility _frequency_flexibility = eFlexibility.NONE;
}
