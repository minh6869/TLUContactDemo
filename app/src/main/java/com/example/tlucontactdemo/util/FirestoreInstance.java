package com.example.tlucontactdemo.util;




import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreInstance {

    private static FirebaseFirestore instance;

    private FirestoreInstance() {}

    public static FirebaseFirestore getInstance() {
        if (instance == null) {
            instance = FirebaseFirestore.getInstance();
        }
        return instance;
    }
}