import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;

public class RecommendationSystem {

    public static void main(String[] args) {
        try {
            // Load the data
            DataModel model = new FileDataModel(new File("data.csv"));

            // Create a user similarity measure
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Create a user neighborhood
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

            // Build the recommender
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Generate recommendations for a user
            int userID = 1;
            List<RecommendedItem> recommendations = recommender.recommend(userID, 3);

            // Print the recommendations
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Recommended Item: " + recommendation.getItemID() + " with value: " + recommendation.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
