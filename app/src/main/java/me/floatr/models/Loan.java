//{
//
//        "loaner": {},
//        "loaner_bank_account": "aa",
//        "loanee":{},
//        "principle": 123,
//        "interest_rate": 123,
//        "minimum_payment": 123,
//        "next_payment": 123,
//        "next_payment_due_date": "Date",
//        "remaining_amount": 123,
//        "period": 123,
//        "period_unit": "aaa",
//        "status": "aa",
//        "payments": 123
//
//        }


        package me.floatr.models;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import butterknife.Optional;


public class Loan {

    @SerializedName("initiate_value")
    @Expose
    private int initiateValue;

    @SerializedName("loaner")
    @Expose
    private User loaner;

    public int getInitiateValue() {
        return initiateValue;
    }

    public String getId() {
        return id;
    }

    public Integer getMinOffer() {
        return minOffer;
    }

    public Integer getMaxOffer() {
        return maxOffer;
    }

    @SerializedName("loanee")

    @Expose
    private User loanee;

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("interest_rate")
    @Expose
    private Integer interestRate;

    @SerializedName("min_offer")
    @Expose
    private Integer minOffer;

    @SerializedName("max_offer")
    @Expose
    private Integer maxOffer;

    @Expose
    private Integer period;
    @SerializedName("period_unit")
    @Expose
    private String periodUnit;

    @SerializedName("status")
    @Expose
    private String status;


    /**
     *
     * @return
     * The loaner
     */
    public User getLoaner() {
        return loaner;
    }

    /**
     *
     * @param loaner
     * The loaner
     */
    public void setLoaner(User loaner) {
        this.loaner = loaner;
    }

    /**
     *
     * @return
     * The loanee
     */
    public User getLoanee() {
        return loanee;
    }

    /**
     *
     * @param loanee
     * The loanee
     */
    public void setLoanee(User loanee) {
        this.loanee = loanee;
    }


    /**
     *
     * @return
     * The interestRate
     */
    public Integer getInterestRate() {
        return interestRate;
    }

    /**
     *
     * @param interestRate
     * The interest_rate
     */
    public void setInterestRate(Integer interestRate) {
        this.interestRate = interestRate;
    }


    /**
     *
     * @return
     * The period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     *
     * @param period
     * The period
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     *
     * @return
     * The periodUnit
     */
    public String getPeriodUnit() {
        return periodUnit;
    }

    /**
     *
     * @param periodUnit
     * The period_unit
     */
    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
