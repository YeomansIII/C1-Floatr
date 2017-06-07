//{
//        "first_name": "Pedro",
//        "last_name": "De Jesus",
//        "customer_id": "aaaa",
//        "bank_accounts": ["aa"],
//        "offers": ["aa"],
//        "requests": ["aa"],
//        "num_ratings": 123,
//        "rating": 123,
//        "avatar_url": "aa",
//        "status": "aa"
//
//        }

        package me.floatr.models;

        import java.util.ArrayList;
        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("bank_accounts")
    @Expose
    private List<String> bankAccounts = new ArrayList<String>();
    @SerializedName("offers")
    @Expose
    private List<String> offers = new ArrayList<String>();
    @SerializedName("requests")
    @Expose
    private List<String> requests = new ArrayList<String>();
    @SerializedName("num_ratings")
    @Expose
    private Integer numRatings;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     * The customer_id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return
     * The bankAccounts
     */
    public List<String> getBankAccounts() {
        return bankAccounts;
    }

    /**
     *
     * @param bankAccounts
     * The bank_accounts
     */
    public void setBankAccounts(List<String> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    /**
     *
     * @return
     * The offers
     */
    public List<String> getOffers() {
        return offers;
    }

    /**
     *
     * @param offers
     * The offers
     */
    public void setOffers(List<String> offers) {
        this.offers = offers;
    }

    /**
     *
     * @return
     * The requests
     */
    public List<String> getRequests() {
        return requests;
    }

    /**
     *
     * @param requests
     * The requests
     */
    public void setRequests(List<String> requests) {
        this.requests = requests;
    }

    /**
     *
     * @return
     * The numRatings
     */
    public Integer getNumRatings() {
        return numRatings;
    }

    /**
     *
     * @param numRatings
     * The num_ratings
     */
    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    /**
     *
     * @return
     * The rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     * The rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     * The avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     *
     * @param avatarUrl
     * The avatar_url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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
