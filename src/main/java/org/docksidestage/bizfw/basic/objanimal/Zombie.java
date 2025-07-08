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

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;

/**
 * The object for zombie(ゾンビ).
 * @author jflute
 */
public class Zombie extends Animal {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final ZombieDiary zombieDiary = new ZombieDiary();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Zombie() {
    }

    @Override
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }

    public static class ZombieDiary {

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
    @Override
    public BarkedSound bark() {
        // 考え（言い訳）コメント：by hase (2025/07/08)
        // 理想的には、breathIn()のたびにcountBreathIn()したい。
        // しかし、ZombieのためだけにAnimalクラスでbreathIn()のカウントをするのは大袈裟
        // 直感的に、bark()1回につき1度しか吸い込まないので、bark()をオーバーライドして元の機能を実現しました。
        // TODO hase 悩んでて素晴らしい(^^。ただ、突き落としてしまいますが... by jflute (2025/07/09)
        // bark()でBreathが一回とは限らないので、将来そのへんが変わったらcountが狂ってしまいます。
        // とはいえどうしたらいいんだかって感じではありますよね。
        // hint1: オブジェクト指向的な階層構造は別にAnimalだけのものじゃない
        zombieDiary.countBreatheIn();
        return super.bark();
    }

    @Override
    public String getBarkWord() {
        return "uooo"; // what in English?
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    public void downHitPoint() {
        // do nothing, infinity hit point
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public ZombieDiary getZombieDiary() {
        return zombieDiary;
    }
}
