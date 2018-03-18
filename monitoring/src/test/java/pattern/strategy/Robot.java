package pattern.strategy;

/**
 * Created by david100gom on 2018. 3. 18.
 *
 * Github : https://github.com/david100gom
 */
public abstract class Robot {

    private String name;
    private MovingStrategy movingStrategy;
    private AttackStrategy attackStrategy;

    public Robot(String name) {
        this.name = name;
        System.out.println("P-"+getName());
    }

    public String getName() {
        return name;
    }

    public void move() {
        movingStrategy.move();
    }

    public void attack() {
        attackStrategy.attack();
    }

    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
}
