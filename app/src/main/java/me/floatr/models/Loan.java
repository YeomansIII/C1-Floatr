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


public class Loan {

    @SerializedName("loaner")
    @Expose
    private User loaner;
    @SerializedName("loaner_bank_account")
    @Expose
    private String loanerBankAccount;
    @SerializedName("loanee")
    @Expose
    private User loanee;
    @SerializedName("principle")
    @Expose
    private Integer principle;
    @SerializedName("interest_rate")
    @Expose
    private Integer interestRate;
    @SerializedName("minimum_payment")
    @Expose
    private Integer minimumPayment;
    @SerializedName("next_payment")
    @Expose
    private Integer nextPayment;
    @SerializedName("next_payment_due_date")
    @Expose
    private String nextPaymentDueDate;
    @SerializedName("remaining_amount")
    @Expose
    private Integer remainingAmount;
    @SerializedName("period")
    @Expose
    private Integer period;
    @SerializedName("period_unit")
    @Expose
    private String periodUnit;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("payments")
    @Expose
    private Integer payments;

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
     * The loanerBankAccount
     */
    public String getLoanerBankAccount() {
        return loanerBankAccount;
    }

    /**
     *
     * @param loanerBankAccount
     * The loaner_bank_account
     */
    public void setLoanerBankAccount(String loanerBankAccount) {
        this.loanerBankAccount = loanerBankAccount;
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
     * The principle
     */
    public Integer getPrinciple() {
        return principle;
    }

    /**
     *
     * @param principle
     * The principle
     */
    public void setPrinciple(Integer principle) {
        this.principle = principle;
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
     * The minimumPayment
     */
    public Integer getMinimumPayment() {
        return minimumPayment;
    }

    /**
     *
     * @param minimumPayment
     * The minimum_payment
     */
    public void setMinimumPayment(Integer minimumPayment) {
        this.minimumPayment = minimumPayment;
    }

    /**
     *
     * @return
     * The nextPayment
     */
    public Integer getNextPayment() {
        return nextPayment;
    }

    /**
     *
     * @param nextPayment
     * The next_payment
     */
    public void setNextPayment(Integer nextPayment) {
        this.nextPayment = nextPayment;
    }

    /**
     *
     * @return
     * The nextPaymentDueDate
     */
    public String getNextPaymentDueDate() {
        return nextPaymentDueDate;
    }

    /**
     *
     * @param nextPaymentDueDate
     * The next_payment_due_date
     */
    public void setNextPaymentDueDate(String nextPaymentDueDate) {
        this.nextPaymentDueDate = nextPaymentDueDate;
    }

    /**
     *
     * @return
     * The remainingAmount
     */
    public Integer getRemainingAmount() {
        return remainingAmount;
    }

    /**
     *
     * @param remainingAmount
     * The remaining_amount
     */
    public void setRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
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

    /**
     *
     * @return
     * The payments
     */
    public Integer getPayments() {
        return payments;
    }

    /**
     *
     * @param payments
     * The payments
     */
    public void setPayments(Integer payments) {
        this.payments = payments;
    }

}
