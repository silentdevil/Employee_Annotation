package com.exist.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Cacheable;
import javax.persistence.Embeddable;

import org.hibernate.annotations.*;

@Embeddable
public class Address {
	
   @Column(name="street_no")
   private int streetNo;
   private String street; 
   private String brgy; 

   private String city;     
   private String zipcode; 

   

   public int getStreetNo() {
      return streetNo;
   }

   public void setStreetNo(int streetNo) {
      this.streetNo = streetNo;
   }

   public String getStreet() {
      return street;
   }
   public void setStreet( String street ) {
      this.street = street;
   }
   public String getCity() {
      return city;
   }
   public void setCity( String city ) {
      this.city = city;
   }
   public String getBrgy() {
      return brgy;
   }
   public void setBrgy(String brgy) {
      this.brgy = brgy;
   }
   public String getZipcode() {
      return zipcode;
   }
   public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
   }
   
   public String toString() {
	   StringBuffer sb = new StringBuffer();
		sb.append(streetNo).append(" ");
		sb.append(street).append(", ");
		sb.append(brgy).append(", ");
		sb.append(city).append(" " + zipcode);
		return sb.toString();
   }

   @Override
   public boolean equals(Object obj) {
       if(obj == null || getClass() != obj.getClass())
         return false;

         Address add2 = (Address) obj;

         return this.streetNo == add2.getStreetNo() && this.street.equals(add2.getStreet()) && this.brgy.equals(add2.getBrgy())
            && this.city.equals(add2.getCity());
         //return this.toString().equals(obj.toString());
   }

   @Override
   public int hashCode() {
        return java.util.Objects.hash(streetNo,street,brgy,city);
    }
   

   
}
