package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel {
    private Object[][]donnees;
    private String entetes[];

    public Tableau(Object[][]donnees, String entetes[]){
        this.donnees = donnees;
        this.entetes = entetes;
    }

    @Override
    public int getRowCount() {
        return this.donnees.length;
    }

    @Override
    public int getColumnCount() {
        return this.entetes.length;
    }

    @Override
    public Object getValueAt(int ligne, int colonne) {
        return this.donnees[ligne][colonne];
    }

    @Override
    public String getColumnName(int colonne) {
        return this.entetes[colonne];
    }
    //ajouter une ligne à la matrice des données
    public void insertLigne(Object[]ligne){
        Object[][]temp = new Object[this.donnees.length+1][this.entetes.length];
        for(int i=0;i<this.donnees.length;i++){
            temp[i] = this.donnees[i];
        }
        temp[this.donnees.length] = ligne;
        this.donnees = temp;
        this.fireTableDataChanged(); //mise à jour des données
    }

    //supprimer une ligne de la matrice des données
    public void deleteLigne(int ligne){
        Object[][]temp = new Object[this.donnees.length-1][this.entetes.length];
        int j = 0;
        for(int i=0;i<this.donnees.length;i++){
           if(i!=ligne){
               temp[j] = this.donnees[i];
               j++;
           }
        }
        this.donnees = temp;
        this.fireTableDataChanged();
    }

    public void updateLigne (int ligne, Object[]Ligne){
        Object[][]temp = new Object[this.donnees.length][this.entetes.length];
        for(int i=0;i<this.donnees.length;i++){
            if(i!=ligne){
                temp[i] = this.donnees[i];
            }else{
                temp[i] = Ligne;
            }
        }
        this.donnees = temp;
        this.fireTableDataChanged();
    }

    public void setDonnees(Object[][]donnees){
        this.donnees = donnees;
        this.fireTableDataChanged();
    }
}
