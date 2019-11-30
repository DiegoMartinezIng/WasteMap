package com.example.wastecontrol.persistencia.respositorio;

import android.util.Log;

import com.example.wastecontrol.persistencia.model.Model;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Repository<T extends Model> {

    private static final String TAG = Repository.class.getSimpleName();
    private static FirebaseFirestore databaseReference;
    protected static Repository intance;

    public static FirebaseFirestore getDatabaseReference() {
        if (databaseReference == null) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(Boolean.TRUE);
            databaseReference = FirebaseFirestore.getInstance();
        }

        return databaseReference;
    }

    public T crear(T model, Consumer<T> successHandler, Consumer<Exception> errorHandler) {
        if (model == null) throw new NullPointerException("Modelo es NULL");
        if (model.getId() == null) {
            DocumentReference document = getDatabaseReference().collection(model.NODE_NAME()).document();
            model.setId(document.getId());
            document.set(model.toMap()).addOnSuccessListener(documentReference -> {
                        successHandler.accept(model);
                    })
                    .addOnFailureListener(e -> errorHandler.accept(e));
        } else {
            return actualizar(model, successHandler, errorHandler);
        }

        return model;
    }

    //
//
//
    public T actualizar(T model, Consumer<T> successHandler, Consumer<Exception> errorHandler) {
        if (!(model != null && model.getId() != null)) return null;
        CollectionReference collection = getDatabaseReference().collection(model.NODE_NAME());
        collection.
                add(model.toMap())
                .addOnSuccessListener(documentReference -> {
                    model.setId(documentReference.getId());
                    successHandler.accept(model);
                })
                .addOnFailureListener(e -> errorHandler.accept(e));

        return model;
    }

    public T buscarReferencia(T model, Consumer<T> successHandler, Consumer<Exception> errorHandler){
        getDatabaseReference().collection(model.NODE_NAME()).document(model.getId()).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                T resul = (T) task.getResult().toObject(getClassModel());
                successHandler.accept(resul);
            }
        }).addOnFailureListener(e -> errorHandler.accept(e));
        return model;
    }


    public void buscar(T model, Consumer<List<T>> successHandler, Consumer<Exception> errorHandler){
        Query query;
        query = queryFilter(model, getDatabaseReference().collection(model.NODE_NAME()));
        query.get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        List<T> list = new ArrayList<>();
                        List<DocumentSnapshot> result = task.getResult().getDocuments();
                        for (DocumentSnapshot doc: result) {
                            Log.d(Repository.TAG, "buscar: "+doc.toString());
                            list.add((T) doc.toObject(getClassModel()));
                        }
                        successHandler.accept(list);
                    }

                })
                .addOnFailureListener(e -> errorHandler.accept(e));
    }


    protected abstract Query queryFilter(T filtro, CollectionReference collection);

    protected abstract Class getClassModel();
}
