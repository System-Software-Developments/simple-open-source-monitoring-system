package pattern.dip;

/**
 * Created by david100gom on 2018. 3. 17.
 *
 * Github : https://github.com/david100gom
 */
public class Kid {

    private Toy toy;

    public void setToy(Toy toy) {
        this.toy = toy;
    }

    public void play() {
        System.out.print(toy.toString());
    }
}
