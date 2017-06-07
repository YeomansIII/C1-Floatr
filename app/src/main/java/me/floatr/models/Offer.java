package me.floatr.models;

/**
 * Created by jason on 10/29/16.
 */

/*
{
  "id": 1,
  "user": {
    "first_name": "Pedro",
    "last_name": "DeJesus",
    "avatar_url": "http://google.com",
    "rating": 5
  },
  "starting_range": 400,
  "ending_range": 500,
  "interest_rate": 0.5,
  "period": 3,
  "period_unit": "month"
}
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private UserMinimal user;
    @SerializedName("starting_range")
    @Expose
    private Integer startingRange;
    @SerializedName("ending_range")
    @Expose
    private Integer endingRange;
    @SerializedName("interest_rate")
    @Expose
    private Double interestRate;
    @SerializedName("period")
    @Expose
    private Integer period;
    @SerializedName("period_unit")
    @Expose
    private String periodUnit;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The user
     */
    public UserMinimal getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(UserMinimal user) {
        this.user = user;
    }

    /**
     * @return The startingRange
     */
    public Integer getStartingRange() {
        return startingRange;
    }

    /**
     * @param startingRange The starting_range
     */
    public void setStartingRange(Integer startingRange) {
        this.startingRange = startingRange;
    }

    /**
     * @return The endingRange
     */
    public Integer getEndingRange() {
        return endingRange;
    }

    /**
     * @param endingRange The ending_range
     */
    public void setEndingRange(Integer endingRange) {
        this.endingRange = endingRange;
    }

    /**
     * @return The interestRate
     */
    public Double getInterestRate() {
        return interestRate;
    }

    /**
     * @param interestRate The interest_rate
     */
    public void setInterestRate(Double interestRate) {
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