package pattern.observer2.impl;

import java.util.ArrayList;
import java.util.List;

import pattern.observer2.inter.Observer;
import pattern.observer2.inter.Subject;

/**
 * Created by david100gom on 2018. 12. 22.
 *
 * Github : https://github.com/david100gom
 */
public class Avengers implements Subject {

    private List<Observer> heros;
    private EnemyStatus stat;

    public Avengers() {
        System.out.println("어벤져스 창설");

        heros = new ArrayList<Observer>();
    }

    @Override
    public void add(Observer observer) {
        heros.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        if(heros.contains(observer)) {
            heros.remove(observer);
        }
    }

    @Override
    public void nodifyObservers() {
        for(Observer ob: heros) {
            ob.update(stat);
        }
    }

    @Override
    public void notifyObserver(Observer observer) {
        observer.update(stat);
    }

    public void setStat(EnemyStatus stat) {
        this.stat = stat;
        nodifyObservers();
    }

    public List<Observer> getMembers() {
        return this.heros;
    }
}
