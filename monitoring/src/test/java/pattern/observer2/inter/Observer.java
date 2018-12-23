package pattern.observer2.inter;

import pattern.observer2.impl.EnemyStatus;

/**
 * Created by david100gom on 2018. 12. 22.
 *
 * Github : https://github.com/david100gom
 */
public interface Observer {

    public void update(EnemyStatus stat);

}
