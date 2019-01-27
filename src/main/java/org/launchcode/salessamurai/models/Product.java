package org.launchcode.salessamurai.models;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 100, message = "Please enter the manufacturer of the product.")
    private String manufacturer;

    @NotNull
    @Size(min =1, max=10, message = "Please enter the model.")
    private String model;

    private Wholesalers wholesalers;

    @ManyToOne
    private Users user;

    @NotNull
    @Size(min=1, max = 10, message="Please enter the manufacturer of the product.")
    private String mfgID;

    private String skuNumber;
    private String quantity;
    private String wholesalePrice;
    private String retailPrice;
    private String notes;





    public Product(String manufacturer, String model, String mfgID, String skuNumber, String quantity,String wholesalePrice, String retailPrice, String notes, Wholesalers wholesalers) {

        this.manufacturer = manufacturer;
        this.model = model;
        this.wholesalers = wholesalers;
        this.mfgID = mfgID;
        this.skuNumber = skuNumber;
        this.quantity = quantity;
        this.wholesalePrice = wholesalePrice;
        this.retailPrice = retailPrice;
        this.notes = notes;
    }
    public Product(){ }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Time getWholesalers() {
        return wholesalers;
    }

    public void setWholesalers(Time time) {
        this.wholesalers = wholesalers;
    }

    public String getMfgID() {
        return mfgID;
    }

    public void setMfgID(String mfgID) {
        this.mfgID = mfgID;
    }

    public String getSkuNumber() {
        return skuNumberr;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setUser(Users user) {
        this.user = user;
    }



}
