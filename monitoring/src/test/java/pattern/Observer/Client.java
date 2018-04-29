package pattern.Observer;

/**
 * Created by david100gom on 2018. 4. 22.
 *
 * Github : https://github.com/david100gom
 */
public class Client {

    public static void main(String[] args) {
        ScoreRecord scoreRecord = new ScoreRecord();


        DataSheetView dataSheetView3 = new DataSheetView(scoreRecord, 3);
        DataSheetView dataSheetView5 = new DataSheetView(scoreRecord, 5);

        scoreRecord.attach(dataSheetView3);


        scoreRecord.attach(dataSheetView5);

        for(int i = 1; i <= 5; i++) {

            int score = i * 10;
            System.out.println("Adding : "+score);
            scoreRecord.addScore(score);

        }

        scoreRecord.detach(dataSheetView3);

        for(int i = 1; i <= 5; i++) {

            int score = i * 10;
            System.out.println("Adding : "+score);
            scoreRecord.addScore(score);

        }


    }
}
