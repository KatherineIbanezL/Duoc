/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sistema_teatro_moro_V2;

/**
 *
 * @author Katherine
 */
public class Asiento {
    int id;
    String zona;
    boolean disponible;

    public Asiento(int id, String zona) {
        this.id = id;
        this.zona = zona;
        this.disponible = true;
    }
}
