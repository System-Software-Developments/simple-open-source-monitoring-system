package pattern.observer2.impl;

import pattern.observer2.inter.Heros;
import pattern.observer2.inter.Observer;
import pattern.observer2.inter.Subject;

/**
 * Created by david100gom on 2018. 12. 22.
 *
 * Github : https://github.com/david100gom
 */
public class Vision implements Observer,Heros {

    public Vision(){};
    public Vision(Subject team){
        System.out.println("비전 합류");
        team.add(this);
    }

    @Override
    public void update(EnemyStatus stat) {
        this.behavior(stat);
    }

    @Override
    public void behavior(EnemyStatus stat) {
        switch(stat){
            case NONE: System.out.println("비전 : 대기중"); break;
            case APPEAR: System.out.println("비전 : 전투준비"); break;
            case ATTACK: System.out.println("비전 : 효과없음"); break;
            case DISAPPEAR: System.out.println("비전 : 전투상태 해제"); break;
        }
    }
}
