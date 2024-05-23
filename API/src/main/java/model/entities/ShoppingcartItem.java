package model.entities;

import java.util.ArrayList;

public class ShoppingcartItem {
    int shoppingcart;
    int item;
    int ammount;

    public ShoppingcartItem(int shoppingcart, int item, int ammount) {
        this.shoppingcart = shoppingcart;
        this.item = item;
        this.ammount = ammount;
    }

    public ShoppingcartItem(){

    }

    public int getShoppingcart() {
        return shoppingcart;
    }

    public int getItem() {
        return item;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setShoppingcart(int shoppingcart) {
        this.shoppingcart = shoppingcart;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public static String toJSON(ArrayList<ShoppingcartItem> arrayList){
        String json = "[\n";
        for (ShoppingcartItem a: arrayList) {
            if(json.charAt(json.length() - 1) == '}'){
                json += ",\n";
            }
            json += "{\"shoppingcart\": \"" + a.getShoppingcart() + "\",\n" +
                    "\"item\": \"" + a.getItem() + "\",\n" +
                    "\"ammount\": \"" + a.getAmmount() + "\"\n" +
                    "}";
        }
        json += "]";
        System.out.println(json);
        return json;
    }
}
