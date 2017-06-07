//{
//        "loan": {
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
//        },
//        "loanee_bank_account": "aa",
//        "loaner": {},
//        "amount": 123,
//        "date": "Date",
//        "success": true
//        }



        package me.floatr.models;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class Payment {

    @SerializedName("loan")
    @Expose
    private Loan loan;
    @SerializedName("loanee_bank_account")
    @Expose
    private String loaneeBankAccount;
    @SerializedName("loaner")
    @Expose
    private User loaner;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("success")
    @Expose
    private Boolean success;

    /**
     *
     * @return
     * The loan
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     *
     * @param loan
     * The loan
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    /**
     *
     * @return
     * The loaneeBankAccount
     */
    public String getLoaneeBankAccount() {
        return loaneeBankAccount;
    }

    /**
     *
     * @param loaneeBankAccount
     * The loanee_bank_account
     */
    public void setLoaneeBankAccount(String loaneeBankAccount) {
        this.loaneeBankAccount = loaneeBankAccount;
    }

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
     * The amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     * The amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     * The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

}