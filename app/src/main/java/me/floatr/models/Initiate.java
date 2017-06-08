package me.floatr.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by clq678 on 6/8/17.
 */

public class Initiate {
    @SerializedName("initiate_value")
    @Expose
    private Integer initiateValue;

    @SerializedName("loanee_bank_account")
    @Expose
    private String loaneeBankAccount;

    public Initiate(Integer initiateValue, String loaneeBankAccount) {
        this.initiateValue = initiateValue;
        this.loaneeBankAccount = loaneeBankAccount;
    }
}
