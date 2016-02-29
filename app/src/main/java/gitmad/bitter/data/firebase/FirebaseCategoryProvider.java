package gitmad.bitter.data.firebase;

import com.firebase.client.Firebase;

import java.util.concurrent.CountDownLatch;

import gitmad.bitter.data.CategoryProvider;


/**
 * Created by brian on 2/22/16.
 */
public class FirebaseCategoryProvider implements CategoryProvider {
    private Firebase catproRef;
    public FirebaseCategoryProvider() {
        catproRef = new Firebase("https://bitter-gitmad.firebaseio.com/postCategories");
    }

    private void awaitLatch(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] getCategories() {
        return catproRef;
    }
}
