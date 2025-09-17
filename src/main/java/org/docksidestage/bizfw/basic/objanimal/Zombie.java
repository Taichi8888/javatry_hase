/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.objanimal;

/**
 * The object for zombie(ゾンビ).
 * @author jflute
 */
public class Zombie extends UndeadMonster {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done hase UndeadMonsterのundeadDiary変数を隠蔽してしまっていますね by jflute (2025/07/15)
    // UndeadMonsterのundeadDiary は protected で継承しているので、ここで再び宣言する必要はないです。

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Zombie() {
    }

// おもいで
//    @Override
//    protected int getInitialHitPoint() {
//        return super.getInitialHitPoint();
//    }

//    public static class ZombieDiary {
//
//        private int breatheInCount;
//
//        public void countBreatheIn() {
//            ++breatheInCount;
//        }
//
//        public int getBreatheInCount() {
//            return breatheInCount;
//        }
//    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
// おもいで：Creatureクラスに移行by hase (2025/07/09)
//    @Override
//    public BarkedSound bark() {
//        // 考え（言い訳）コメント：by hase (2025/07/08)
//        // 理想的には、breathIn()のたびにcountBreathIn()したい。
//        // しかし、ZombieのためだけにAnimalクラスでbreathIn()のカウントをするのは大袈裟
//        // 直感的に、bark()1回につき1度しか吸い込まないので、bark()をオーバーライドして元の機能を実現しました。
//        // done hase 悩んでて素晴らしい(^^。ただ、突き落としてしまいますが... by jflute (2025/07/09)
//        // bark()でBreathが一回とは限らないので、将来そのへんが変わったらcountが狂ってしまいます。
//        // とはいえどうしたらいいんだかって感じではありますよね。
//        // hint1: オブジェクト指向的な階層構造は別にAnimalだけのものじゃない
    //
    // Animalと同じ粒度のUndeadMonsterクラスを作成し、ZombieはUndeadMonsterの1つとしました。by hase (2025/07/09)
    // さらに、AnimalとUndeadMonsterの親クラスCreatureを作成し、共通の処理を移行しました。
    // bark()の時、UndeadMonsterだけDiaryにカウントする責務については、BarkingProcessに任せました。
    // 前回の反省を活かし、bark()のたびにカウントするのではなく、breathIn()のたびにカウントするようにしました。
//        return super.bark();
//    }
    // done hase [いいね] おおぉぉ、すごい。階層構造が変わりましたね。でもこれはこれでUndeadという概念をしっかり表現したと by jflute (2025/07/15)
    // そのために、Creatureという概念も導き出して、より抽象化して扱えるようになったということですね。
    // これはこれでとても良いと思います。
    // BarkingProcess が Zombie に依存するのはベタ感(列挙感)が強いですが、
    // Undead という抽象概念に依存するのであれば、そういう業務ロジックという風に捉えることができますね。
    // done hase 修行++: だがしかし、BarkingProcess に UndeadMonster の if文があるのも少々もったいないですね by jflute (2025/07/15)
    // BarkingProcess から UndeadMonster の分岐を外して、diaryを実現することも可能です。
    // hint2: オブジェクト指向的な階層構造は別にAnimal/Creatureの路線だけのものじゃない
    // hint3: オーバーライドはAnimal/Creatureの路線だけじゃなくどのオブジェクトでも利用できる

    // なるほど、BarkingProcessの階層構造ですか！by hase (2025/07/15)
    // done hase [いいね] おお、良い感じになってきましたね！細かい実装のtodoは別途入れています by jflute (2025/07/15)
    
    // done jflute (質問です) 粒度を揃えるためにAnimalBarkingProcessとUndeadBarkingProcessの両方を作成したのですが、
    // AnimalBarkingProcessは作らなくてもよかったでしょうか。
    // done hase [へんじ] 悩ましいところですね。将来に備えるか備えないかの違いで、多少ギャンブルになります by jflute (2025/07/15)
    // なので正解があるわけではなく、Animal固有の処理が入りそう、確率が高いと考えるのであれば今の状態で良いと思います。
    // 人によっては「必要になったときに作るほうが良い」というセオリーもあります。
    // 事前に作ってても、一生使われなかったり、使われそうなときが来てもちょっと要件が違って使えなかったりするからと。
    // 特に後者はぼくもすごく思います。要件がないときに適した枠組みを用意するって難しくて、いざ要件が来たら「想定とちがーう」ってなりがち。
    // とはいえ用意しなかったらしなかったで、後の人が AnimalBarkingProcess を作ったらいいということを思いつけない可能性も。
    // こういうジレンマです(^^。
    // 追記: この階層構造は綺麗で概念を適切に表現してると思うので、最初から枠組みあってもいいかなと思った。

    // 確かに！！使われそうな時が来ても要件が違ったり、というセオリーは考えつきませんでした。by hase (2025/07/16)
    // とても納得しました。ありがとうございます！

    @Override
    protected String getBarkWord() {
        return "uooo";
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
// おもいで：Creatureクラスに移行by hase (2025/07/09)
//    @Override
//    public void downHitPoint() {
//        // do nothing, infinity hit point
//    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
// おもいで：Creatureクラスに移行by hase (2025/07/09)
//    public ZombieDiary getZombieDiary() {
//        return zombieDiary;
//    }
}
