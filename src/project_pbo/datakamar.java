/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_pbo;

/**
 *
 * @author ACER
 */
public class datakamar {
    
    private final int roomNumber;
    private final String roomType;
    private final String status;
    private final double price;   
    
    public datakamar(int roomNumber, String roomType, String status, double price){
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.status = status;
        this.price = price;      
    }
    public int getRoomNumber(){
        return roomNumber;
    }
    public String getRoomType(){
        return roomType;
    }
    public String getStatus(){
        return status;
    }
    public double getPrice(){
        return price; 
    }

}
