package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;

/**
 * The object for creature.
 * @author tahasega
 */
public abstract class Creature implements Loudable {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint; // is HP
    // TODO hase おもいでにしちゃったけど、FactoryMethodパターンというのをちょっと調べてみてください by jflute (2025/07/15)
    // せっかく BarkingProcess も Creature レベルで抽象化できているのに、
    // Creature のところに BarkingProcess を登場させずに Animal と Undead にそれぞれ定義してるってのはもったいないです。
    // オブジェクト指向にはオーバーライドという技があるので、newするところだけ差し替ガガガガ

// おもいで：UndeadBarkingProcess作成前はCreatureで共通だった by hase (2025/07/15)
//    protected final BarkingProcess barkingProcess;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Creature() {
        hitPoint = getInitialHitPoint();
// おもいで：UndeadBarkingProcess作成前はCreatureで共通だった by hase (2025/07/15)
//        barkingProcess = new BarkingProcess(this);
    }

    protected abstract int getInitialHitPoint();

    // ===================================================================================
    //                                                                                Bark
    //                                                                              ======
// おもいで：UndeadBarkingProcess作成前はCreatureで共通だった by hase (2025/07/15)
//    public BarkedSound bark() {
//        return this.barkingProcess.bark();
//    }
    public abstract BarkedSound bark();

    public abstract String getBarkWord();

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    public abstract void downHitPoint();

    // ===================================================================================
    //                                                                               Fight
    //                                                                              ======
    public abstract void fight(Creature creature);

    // ===================================================================================
    //                                                                                Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                           =========
    public int getHitPoint() {
        return hitPoint;
    }
}
