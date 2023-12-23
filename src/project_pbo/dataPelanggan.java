/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_pbo;

/**
 *
 * @author ACER
 */
public class dataPelanggan {
   
    private int custumer_id;
    private String roomType;
    private String roomNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    
    
   

    public int getCustumer_id(){
        return custumer_id;
    }
    public String getRoomType(){
        return roomType;
    }
    public String getRoomNumber(){
        return roomNumber;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
    return lastName;
    }
    public String getPhoneNumber(){
    return phoneNumber;
    }
    public String getEmail(){
    return email;
    }
    
    public void setCustumer_id(int custumer_id){
           this.custumer_id = custumer_id;
    }
    public void setRoomType(String roomType){
           this.roomType = roomType;
    }
    public void setRoomNumber(String roomNumber){
           this.roomNumber = roomNumber;
    }
    public void setFirstName(String firstName){
           this.firstName = firstName;
    }
    public void setLastName(String lastName){
           this.lastName = lastName;
    }
    public void setPhoneNumber(String phoneNumber){
           this.phoneNumber = phoneNumber;
    }
    public void setemail(String email){
           this.phoneNumber = email;
    }
    
    
    
     public dataPelanggan(int custumer_id, String roomType, String roomNumber, String firstName, String lastName, String phoneNumber,String email){
        this.custumer_id = custumer_id;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.firstName = firstName;   
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;  
    }
    
}

    