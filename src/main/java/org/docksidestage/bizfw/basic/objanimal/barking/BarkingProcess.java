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

    // done hase せっかくなので、こういった↓タグコメントを付けていきましょう by jflute (2025/07/07)
    // 参考例: (カテゴライズできる単位でまとめていく感じ)
    // https://github.com/lastaflute/lastaflute-example-harbor/blob/master/src/main/java/org/docksidestage/app/web/signup/SignupAction.java
    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected Animal animal; // which animal is barking

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(Animal animal) {
        this.animal = animal;
    }

    // ===================================================================================
    //                                                                                Bark
    //                                                                         ===========
    // done hase animalをひたすら引数で持ち回すのも大変そうなので、Constructorで受け取ってインスタンスに持ってしまったらどうでしょう？ by jflute (2025/07/07)
    // Animalの中で BarkingProcess は、インスタンスのライフサイクルが一致しているので、それで問題ないなることもないですし。
    public BarkedSound bark() {
        breatheIn(animal); // Zombieは日記にカウントするのでAnimalの責務
        prepareAbdominalMuscle(animal); // BarkingProcessの責務として腹筋を準備する
        String barkWord = getBarkWord(animal); // Animalごとに違う鳴き声なのでAnimalの責務
        BarkedSound barkedSound = doBark(animal, barkWord); // BarkingProcessの責務として鳴く
        return barkedSound;
    }

    // done hase コメントアウトにはコメントを (これは消すと言うよりも、どういう思考があったかのコメントがあると良いなと) by jflute (2025/07/07)
    private void breatheIn(Animal animal) { // actually depends on barking
        logger.debug("...Breathing in for barking"); // dummy implementation
        animal.downHitPoint();
    } // breatheInはAnimalの責務なので、Animalのメソッドに移動した
    // しかし、breathInはBarkingProcess特有の吸い込みとして、再度こちらに移動した by hase (2025/07/08)

    // done hase クラス内で呼び出すだけのメソッドならprivateで by jflute (2025/07/07)
    private void prepareAbdominalMuscle(Animal animal) { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        animal.downHitPoint();
    }

    // TODO hase メソッド内に不要な空行がありますので削除で by jflute (2025/07/09)
    private String getBarkWord(Animal animal) {
        return animal.getBarkWord();

    }

    private BarkedSound doBark(Animal animal, String barkWord) {
        animal.downHitPoint();
        return new BarkedSound(barkWord);
    }
}
