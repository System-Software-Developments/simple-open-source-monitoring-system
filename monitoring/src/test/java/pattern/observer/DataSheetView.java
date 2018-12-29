package pattern.observer;


import java.util.List;

/**
 * Created by david100gom on 2018. 4. 22.
 *
 * Github : https://github.com/david100gom
 */
public class DataSheetView implements Observer {

    private ScoreRecord scoreRecord;
    private int viewCount;

    public DataSheetView(ScoreRecord scoreRecord, int viewCount) {
        this.scoreRecord = scoreRecord;
        this.viewCount = viewCount;
    }

    @Override
    public void update() {
        List<Integer> record = scoreRecord.getScoreRecord();
        displayScores(record, viewCount);
    }

    private void displayScores(List<Integer> record, int viewCount) {
        System.out.print("List of " + viewCount + " entries: ");

        for(int i=0; i < viewCount && i < record.size(); i++) {
            System.out.print(record.get(i)+ " ");
        }

        System.out.println();
    }

}