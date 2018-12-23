package pattern.observer2.impl;

import pattern.observer2.inter.Observer;
import pattern.observer2.inter.Subject;

/**
 * Created by david100gom on 2018. 12. 22.
 *
 * Github : https://github.com/david100gom
 */
public class Watcher implements Observer {

    Subject subject;

    public Watcher(Subject subject){
        this.subject = subject;
        this.subject.add(this);
    }

    public void checkStat(){
        System.out.println("======= 상태체크 ========");
        subject.notifyObserver(this);
    }

    @Override
    public void update(EnemyStatus stat) {
        switch(stat){
            case NONE: System.out.println("(와처 : 대기)"); break;
            case APPEAR: System.out.println("(와처 : 빌런등장 기록)"); break;
            case ATTACK: System.out.println("(와처 : 빌런공격 기록)"); break;
            case DISAPPEAR: System.out.println("(와처 : 빌런제거 기록)"); break;
        }

    }

}
