package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Animalが鳴くプロセスをここにまとめたよ
 * @author jflute
 * @author tahasega
 */
public class BarkingProcess {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    public BarkedSound bark(Animal animal) {
        animal.breatheIn(); // Zombieは日記にカウントするのでAnimalの責務
        prepareAbdominalMuscle(animal); // BarkingProcessの責務として腹筋を準備する
        String barkWord = getBarkWord(animal); // Animalごとに違う鳴き声なのでAnimalの責務
        BarkedSound barkedSound = doBark(animal, barkWord); // BarkingProcessの責務として鳴く
        return barkedSound;
    }

//    public void breatheIn(Animal animal) { // actually depends on barking
//        logger.debug("...Breathing in for barking"); // dummy implementation
//        animal.downHitPoint();
//    }

    public void prepareAbdominalMuscle(Animal animal) { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        animal.downHitPoint();
    }

    public String getBarkWord(Animal animal) {
        return animal.getBarkWord();

    }

    public BarkedSound doBark(Animal animal, String barkWord) {
        animal.downHitPoint();
        return new BarkedSound(barkWord);
    }
}
