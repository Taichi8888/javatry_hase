package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Creature;
import org.docksidestage.bizfw.basic.objanimal.UndeadMonster;
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
    protected Creature creature; // which animal is barking

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(Creature creature) {
        this.creature = creature;
    }

    // ===================================================================================
    //                                                                                Bark
    //                                                                         ===========
    // done hase animalをひたすら引数で持ち回すのも大変そうなので、Constructorで受け取ってインスタンスに持ってしまったらどうでしょう？ by jflute (2025/07/07)
    // Animalの中で BarkingProcess は、インスタンスのライフサイクルが一致しているので、それで問題ないなることもないですし。
    public BarkedSound bark() {
        breatheIn(creature); // Zombieは日記にカウントするのでAnimalの責務
        prepareAbdominalMuscle(creature); // BarkingProcessの責務として腹筋を準備する
        String barkWord = getBarkWord(creature); // Animalごとに違う鳴き声なのでAnimalの責務
        BarkedSound barkedSound = doBark(creature, barkWord); // BarkingProcessの責務として鳴く
        return barkedSound;
    }

    // done hase コメントアウトにはコメントを (これは消すと言うよりも、どういう思考があったかのコメントがあると良いなと) by jflute (2025/07/07)
    private void breatheIn(Creature creature) { // actually depends on barking
        logger.debug("...Breathing in for barking");
        if (creature instanceof UndeadMonster) { // 呼吸のカウントの責務は呼吸メソッドに任せました by hase (2025/07/09)
            ((UndeadMonster) creature).getUndeadDiary().countBreatheIn();
        }
        creature.downHitPoint();
    } // breatheInはAnimalの責務なので、Animalのメソッドに移動した
    // しかし、breathInはBarkingProcess特有の吸い込みとして、再度こちらに移動した by hase (2025/07/08)

    // done hase クラス内で呼び出すだけのメソッドならprivateで by jflute (2025/07/07)
    private void prepareAbdominalMuscle(Creature creature) { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        creature.downHitPoint();
    }

    // done hase メソッド内に不要な空行がありますので削除で by jflute (2025/07/09)
    private String getBarkWord(Creature creature) {
        return creature.getBarkWord();
    }

    private BarkedSound doBark(Creature creature, String barkWord) {
        creature.downHitPoint();
        return new BarkedSound(barkWord);
    }
}
