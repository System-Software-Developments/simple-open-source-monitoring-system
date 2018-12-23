package pattern.observer2.impl;

import pattern.observer2.inter.Heros;
import pattern.observer2.inter.Observer;
import pattern.observer2.inter.Subject;

/**
 * Created by david100gom on 2018. 12. 22.
 *
 * Github : https://github.com/david100gom
 */
public class Ironman implements Observer,Heros {

    public Ironman(){};
    public Ironman(Subject team){
        System.out.println("아이언맨 합류");
        team.add(this);
    }

    @Override
    public void update(EnemyStatus stat) {
        this.behavior(stat);
    }

    //
    @Override
    public void behavior(EnemyStatus stat) {
        switch(stat){
            case NONE: System.out.println("아이언맨 : 대기중"); break;
            case APPEAR: System.out.println("아이언맨 : 수트 착용"); break;
            case ATTACK: System.out.println("아이언맨 : 해킹당함. 전투불가능 "); break;
            case DISAPPEAR: System.out.println("아이언맨 : 수트 해제"); break;
        }
    }


}
