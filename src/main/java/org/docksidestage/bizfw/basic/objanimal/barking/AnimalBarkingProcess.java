package org.docksidestage.bizfw.basic.objanimal.barking;

import java.util.function.IntConsumer;
import java.util.function.Supplier;

/**
 * Animalが鳴くプロセス
 * @author tahasega
 */
public class AnimalBarkingProcess extends BarkingProcess {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AnimalBarkingProcess(IntConsumer downHitPointCallback, Supplier<String> getBarkWordCallback) {
        super(downHitPointCallback, getBarkWordCallback);
    }
    
    // done hase [ふぉろー] こういうコメントあるといいかも by jflute (2025/07/16)
    // e.g. いま空っぽだけど、何かanimalで固有処理ができたら追加していくこと
}
