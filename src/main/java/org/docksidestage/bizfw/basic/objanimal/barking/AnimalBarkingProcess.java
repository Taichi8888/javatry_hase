package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;

/**
 * Animalが鳴くプロセス
 * @author tahasega
 */
public class AnimalBarkingProcess extends BarkingProcess {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AnimalBarkingProcess(Animal animal) {
        super(animal);
    }
    
    // done hase [ふぉろー] こういうコメントあるといいかも by jflute (2025/07/16)
    // e.g. いま空っぽだけど、何かanimalで固有処理ができたら追加していくこと
}
