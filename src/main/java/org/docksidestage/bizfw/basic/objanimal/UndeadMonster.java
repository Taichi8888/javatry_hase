package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.barking.UndeadBarkingProcess;

/**
 * The object for undead monster.
 * @author tahasega
 */
public abstract class UndeadMonster extends Creature {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected UndeadDiary undeadDiary;
    protected final UndeadBarkingProcess undeadBarkingProcess;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public UndeadMonster() {
        this.undeadDiary = new UndeadDiary();        
        this.undeadBarkingProcess = new UndeadBarkingProcess(this);
    }

    @Override // creatureクラス作成時に復活
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }

    public static class UndeadDiary {

        private int breatheInCount;

        public void countBreatheIn() {
            ++breatheInCount;
        }

        public int getBreatheInCount() {
            return breatheInCount;
        }
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        return this.undeadBarkingProcess.bark();
    }

    @Override
    public abstract String getBarkWord();

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    public void downHitPoint() {
        // do nothing, infinity hit point
    }

    // ===================================================================================
    //                                                                               Fight
    //                                                                              ======
    @Override
    public void fight(Creature creature) {
        creature.downHitPoint();
        creature.downHitPoint(); // アンデッドは2回攻撃
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public UndeadDiary getUndeadDiary() {
        return undeadDiary;
    }
}

