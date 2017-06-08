

package me.floatr.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoanRequest {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("loanee")
    @Expose
    private String loanee;
    @SerializedName("min_request")
    @Expose
    private Integer minRequest;
    @SerializedName("max_request")
    @Expose
    private Integer maxRequest;
    @SerializedName("interest_rate")
    @Expose
    private double interestRate;
    @SerializedName("period")
    @Expose
    private Integer period;
    @SerializedName("period_unit")
    @Expose
    private String periodUnit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The loanee
     */
    public String getLoaner() {
        return loanee;
    }

    /**
     * @param loanee The loanee
     */
    public void setLoaner(String loanee) {
        this.loanee = loanee;
    }

    /**
     * @return The minRequest
     */
    public Integer getMinRequest() {
        return minRequest;
    }

    /**
     * @param minRequest The min_request
     */
    public void setMinRequest(Integer minRequest) {
        this.minRequest = minRequest;
    }

    /**
     * @return The maxRequest
     */
    public Integer getMaxRequest() {
        return maxRequest;
    }

    /**
     * @param maxRequest The max_request
     */
    public void setMaxRequest(Integer maxRequest) {
        this.maxRequest = maxRequest;
    }

    /**
     * @return The interestRate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * @param interestRate The interest_rate
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * @return The period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * @param period The period
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * @return The periodUnit
     */
    public String getPeriodUnit() {
        return periodUnit;
    }

    /**
     * @param periodUnit The period_unit
     */
    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }

}
