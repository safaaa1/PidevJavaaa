/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

/**
 *
 * @author safa
 */
public class Rating {
   private int id;
  private double note;

    public Rating(int id, double note) {
        this.id = id;
        this.note = note;
    }

    public Rating() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", note=" + note + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.note) ^ (Double.doubleToLongBits(this.note) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rating other = (Rating) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.note) != Double.doubleToLongBits(other.note)) {
            return false;
        }
        return true;
    }
  
  
  
  
  
  
}
