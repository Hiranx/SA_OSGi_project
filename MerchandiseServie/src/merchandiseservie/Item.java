package merchandiseservie;

public class Item {
    private String number;
    private String name;
    private int price;
    private int qty;

    public Item(String number, String name, int price, int qty) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
