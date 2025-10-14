package org.docksidestage.bizfw.basic.objanimal.barking;

import java.util.function.IntConsumer;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// done hase javadoc, もうAnimalじゃなくてCreatureですね by jflute (2025/07/15)
// #1on1: 高度な仕事の取りに行き方、人生の仕事に対するイメージ (2025/10/01)
// done hase [読み物課題] 仕事の先にある仕事は明るい by jflute (2025/10/01)
// https://jflute.hatenadiary.jp/entry/20131028/brightness
/**
 * Creatureが鳴くプロセスをここにまとめたよ
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
    protected static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done hase final付けちゃいましょう by jflute (2025/07/15)
    // done hase もはや creature 依存がないので unused になってる by jflute (2025/10/01)
// おもいで：creatureを引数で受け取っていたとき by hase (2025/10/03)
    // protected final Creature creature; // which creature is barking
    protected final IntConsumer downHitPointCallback;
    protected final Supplier<String> getBarkWordCallback;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(IntConsumer downHitPointCallback, Supplier<String> getBarkWordCallback) {
        this.downHitPointCallback = downHitPointCallback;
        this.getBarkWordCallback = getBarkWordCallback;
    }

    // ===================================================================================
    //                                                                                Bark
    //                                                                         ===========
    // done hase animalをひたすら引数で持ち回すのも大変そうなので、Constructorで受け取ってインスタンスに持ってしまったらどうでしょう？ by jflute (2025/07/07)
    // Animalの中で BarkingProcess は、インスタンスのライフサイクルが一致しているので、それで問題ないなることもないですし。
    public BarkedSound bark() {
        // done hase インスタンス変数でcreature持ってますから、引数渡しじゃなくても良いような？ by jflute (2025/07/15)
        // それぞれのprotectedメソッド (インスタンスメソッド) で、インスタンス変数を直接使ってもらえば良いかなと。
        breatheIn();
        prepareAbdominalMuscle();
        String barkWord = getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    // done hase コメントアウトにはコメントを (これは消すと言うよりも、どういう思考があったかのコメントがあると良いなと) by jflute (2025/07/07)
    protected void breatheIn() { // actually depends on barking
        logger.debug("...Breathing in for barking");
// おもいで：UndeadBarkingProcessのbreatheIn()に移行 by hase (2025/07/15)
//        if (creature instanceof UndeadMonster) { // 呼吸のカウントの責務は呼吸メソッドに任せました by hase (2025/07/09)
//            ((UndeadMonster) creature).getUndeadDiary().countBreatheIn();
//        }
        downHitPointCallback.accept(1);
    } // breatheInはAnimalの責務なので、Animalのメソッドに移動した
    // しかし、breathInはBarkingProcess特有の吸い込みとして、再度こちらに移動した by hase (2025/07/08)

    // done hase クラス内で呼び出すだけのメソッドならprivateで by jflute (2025/07/07)
    private void prepareAbdominalMuscle() { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        downHitPointCallback.accept(1);
    }

    // done hase メソッド内に不要な空行がありますので削除で by jflute (2025/07/09)
    public String getBarkWord() {
        return getBarkWordCallback.get(); // 直接コールバックを呼び出し
    }

    private BarkedSound doBark(String barkWord) {
        downHitPointCallback.accept(2); // 吠えるのは体力を使うし、みんなに見られるから心も疲れる。2ダメージ。
        return new BarkedSound(barkWord);
    }
}
