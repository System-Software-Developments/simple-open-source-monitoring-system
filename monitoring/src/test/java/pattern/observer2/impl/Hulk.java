package pattern.observer2.impl;

import pattern.observer2.inter.Heros;
import pattern.observer2.inter.Observer;
import pattern.observer2.inter.Subject;

/**
 * Created by david100gom on 2018. 12. 22.
 *
 * Github : https://github.com/david100gom
 */
public class Hulk implements Observer,Heros {

    public Hulk(){};
    public Hulk(Subject team){
        System.out.println("헐크 합류");
        team.add(this);
    }

    @Override
    public void update(EnemyStatus stat) {
        this.behavior(stat);
    }

    @Override
    public void behavior(EnemyStatus stat) {
        switch(stat){
            case NONE: System.out.println("헐크 : 브루스 배너 상태"); break;
            case APPEAR: System.out.println("헐크 : 헐크로 변신"); break;
            case ATTACK: System.out.println("헐크 : 무시함"); break;
            case DISAPPEAR: System.out.println("헐크 : 변신 해제"); break;
        }
    }
}
