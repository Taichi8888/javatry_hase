package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;

/**
 * Animalが鳴くプロセスをここにまとめたよ
 * @author jflute
 * @author tahasega
 */
public class BarkingProcess {

    public BarkedSound bark(Animal animal) {
        animal.breatheIn();
        animal.prepareAbdominalMuscle();
        String barkWord = animal.getBarkWord();
        BarkedSound barkedSound = animal.doBark(barkWord);
        return barkedSound;
    }
}
