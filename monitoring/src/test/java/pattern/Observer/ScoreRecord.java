package pattern.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david100gom on 2018. 4. 22.
 *
 * Github : https://github.com/david100gom
 */
public class ScoreRecord extends Subject {

    private List<Integer> scores = new ArrayList<Integer>();

    public void addScore(int score) {
        scores.add(score);

        notifyObservers();
    }

    public List<Integer> getScoreRecord() {
        return scores;
    }

}