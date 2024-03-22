package de.pdbm.starter.view;

import de.pdbm.starter.business.messages.entity.Familiensatnd;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped

public class FamilienstandView {
    private Familiensatnd familiensatnd;
public String save(){
    System.out.println("ausgew'ht wurde"+familiensatnd);
    return null;
}
    public Familiensatnd getFamiliensatnd() {
        return familiensatnd;
    }

    public void setFamiliensatnd(Familiensatnd familiensatnd) {
        this.familiensatnd = familiensatnd;
    }
}
