package gitmad.bitter.data.firebase;

import android.graphics.Bitmap;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import gitmad.bitter.data.CategoryProvider;


/**
 * Created by brian on 2/22/16.
 */
public class FirebaseCategoryProvider implements CategoryProvider {
    private Firebase categoryProviderRef;
    private String[] pie;
    public FirebaseCategoryProvider() {
        categoryProviderRef = new Firebase("https://bitter-gitmad.firebaseio.com/postCategories");
    }
    public String[] getCategories() {
        Firebase categoryrRef = categoryProviderRef;


        final CountDownLatch latch = new CountDownLatch(1);
       final AtomicReference<Map> categoryAtomicRef = new AtomicReference<>();
        categoryrRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getKey();
                dataSnapshot.getValue(Map.class);
                categoryAtomicRef.set(dataSnapshot.getValue(Map.class));
                latch.countDown();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                throw firebaseError.toException();
            }
        });

        awaitLatch(latch);

        return categoryAtomicRef.get().keySet().toArray(new String[]);
    }



    private void awaitLatch(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   // @Override
   // public String[] getCategories() {
        //return categoryProviderRef;
    //}
}
