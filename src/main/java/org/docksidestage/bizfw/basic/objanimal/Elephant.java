package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.vehicle.Ridable;

/**
 * 象のオブジェクト
 * @author tahasega
 */
public class Elephant extends Animal implements Ridable {

    protected boolean bien = false;

    public boolean isBien() {
        return bien;
    }

    public Elephant() {
    }

    public String getBarkWord() {
        return "paon"; // what in English
    }
//    @Override
    public void fight() {
        downHitPoint();
        this.bien = true;
    }

    @Override
    public void downHitPoint() {
        super.downHitPoint();
        if (isBien()) {
            super.downHitPoint();
            super.downHitPoint();
        }
    }

    public void ride() {
        this.bien = false; // medicine given ...
        downHitPoint();
    }
}
