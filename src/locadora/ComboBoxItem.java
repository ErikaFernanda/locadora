/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package locadora;

/**
 *
 * @author fernandaerika
 */
public class ComboBoxItem {
    
    String nome;
    String coluna;

    public ComboBoxItem(String nome, String coluna) {
        this.nome = nome;
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
