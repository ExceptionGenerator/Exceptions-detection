package com.shiv.exception.random;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnlineTest {
    public static void main(String[] args) throws Throwable {
//        System.out.println("Discounted price = " + discountedPrice(74001795));
//        Math.round()
//        System.out.println("Discounted price = " + discountedPrice(74000548));
        //807.5 -> 808  9218868437227405312L
//        System.out.println(9218868437227405312L);
//        System.out.println(Math.round(5.5d));
        System.out.println(isIsomorphic("badc","baba"));
    }
    public static boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length())
            return false;
        Map<Character,Character> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(map.get(s.charAt(i))==null){
                int finalI = i;
                Character character1= map.values().parallelStream()
                                .filter(character -> character==t.charAt(finalI)).findFirst()
                        .orElse(null);
                if(character1!=null)
                    return false;
                map.put(s.charAt(i),t.charAt(i));
            }
            if(t.charAt(i)!=map.get(s.charAt(i)))
                return false;
        }
        return true;
    }

    /**
     * Call an API using simple java net
     * And gson library to convert response into object
     * @param barcode
     * @return
     * @throws Throwable
     */
    public static int discountedPrice(int barcode) throws Throwable {
        URL url = new URL("https://jsonmock.hackerrank.com/api/inventory?barcode=" + barcode);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("accept", "application/json");
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode != 200)
            return -1;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        Gson gson = new Gson();
        DataList dataList = gson.fromJson(stringBuilder.toString(), DataList.class);
        System.out.println(dataList);
        if (dataList.getData().isEmpty())
            return -1;
        Data data = dataList.getData().get(0);
        return (int) Math.round(data.getPrice() - ((data.getDiscount() / 100) * data.getPrice()));
    }

}

class DataList {
    private double page;
    private double per_page;
    private double total;
    private double total_pages;
    private List<Data> data;

    public double getPage() {
        return page;
    }

    public void setPage(double page) {
        this.page = page;
    }

    public double getPer_page() {
        return per_page;
    }

    public void setPer_page(double per_page) {
        this.per_page = per_page;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(double total_pages) {
        this.total_pages = total_pages;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataList{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + data +
                '}';
    }
}

class Data {
    private String barcode;
    private String item;
    private String category;
    private double price;
    private double discount;
    private double available;

    @Override
    public String toString() {
        return "Data{" +
                "barcode='" + barcode + '\'' +
                ", item='" + item + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", available=" + available +
                '}';
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }
}