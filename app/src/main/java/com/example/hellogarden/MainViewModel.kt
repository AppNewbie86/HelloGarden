package com.example.hellogarden

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hellogarden.data.Repository
import com.example.hellogarden.data.models.Member
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage


const val TAG = "MainViewModel"

/**
 * Das MainViewModel kümmert sich um die Kommunikation mit der Firebase Authentication
 * um einen SHA-1 Key zu generieren einfach folgene Zeilen ins Terminal kopieren
 * >>keytool -alias androiddebugkey -keystore ~/.android/debug.keystore -list -v -storepass android<<
 *  * ACHTUNG: in der Firestore Datenbank folgende Regel festlegen
 * >> allow read, write: if request.auth != null; <<
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository()


    /**
     *  Kommunikationspunkt mit der Firestore Datenbank
     */

    private val db = FirebaseFirestore.getInstance()

    /**
     *  Kommunikationspunkt mit der FirebaseAuth
     */

    private val firebaseAuth = FirebaseAuth.getInstance()

    /**
     *  Kommunikationspunkt mit Firebase Storage
     */

    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    /**
     *   currentuser ist null wenn niemand eingeloggt ist
     */

    private val _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser


    /**
     *  Member enthält alle relevanten Daten aus dem Firestore
     */

    private val _member = MutableLiveData<Member>()
    val member: LiveData<Member>
        get() = _member

    /**
     * Verschachtelte Value zum Speichern der Daten Toast
     */

    private val _toast = MutableLiveData<String?>()
    val toast: LiveData<String?>
        get() = _toast


    /**
     * hier wird versucht einen User zu erstellen um diesen anschließend auch gleich
     * einzuloggen
     */

    fun signUp(email: String, password: String, member: Member) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        _currentUser.value = firebaseAuth.currentUser
                        setNameAndLevel(member)
                        _toast.value = "welcome"
                        _toast.value = null
                    } else {
                        Log.e(TAG, "Login failed: ${it.exception}")
                        _toast.value = "login failed\n${it.exception?.localizedMessage}"
                        _toast.value = null
                    }
                }
            } else {
                Log.e(TAG, "SignUp failed: ${it.exception}")
                _toast.value = "signup failed\n${it.exception?.localizedMessage}"
                _toast.value = null
            }
        }
    }

    /**
     * hier wird UserId, nickname und Level in die Firestore Datenbank gespeichert
     *
     */

    private fun setNameAndLevel(member: Member) {
        db.collection("user").document(currentUser.value!!.uid)
            .set(member)
            .addOnFailureListener {
                Log.w(TAG, "Error writing document: $it")
                _toast.value = "error creating player\n${it.localizedMessage}"
                _toast.value = null
            }
    }


    /**
     * Funktion zum Einloggen in die App
     * Übergeben ihm Email und Password und wenn beides erfolgreich geprüft wurde werden wir eingeloggt
     */

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _currentUser.value = firebaseAuth.currentUser
                _toast.value = "welcome"
                _toast.value = null
            } else {
                Log.e(TAG, "Login failed: ${it.exception}")
                _toast.value = "login failed\n${it.exception?.localizedMessage}"
                _toast.value = null
            }
        }
    }

    /**
     * ********************************************************************************************
     * Funktion zum uploaden eines Images in den Speicher
     */

    fun uploadImage(uri: Uri) {
        val imageRef = storageRef.child("images/${currentUser.value?.uid}/profilePic")
        val uploadTask = imageRef.putFile(uri)

        uploadTask.addOnFailureListener {
            Log.e("MainViewModel", "upload failed: $it")
        }

        uploadTask.addOnSuccessListener {
            Log.e("MainViewModel", "upload worked")
        }

        uploadTask.addOnCompleteListener {
            imageRef.downloadUrl.addOnCompleteListener {
                if (it.isSuccessful) {
                    setImage(it.result)
                }
            }
        }
    }


    /**
     * Funktion zum setzen des Bildes in den GallerieSpeicher
     */

    private fun setImage(uri: Uri) {
        db.collection("user").document(currentUser.value!!.uid)
            .update("image", uri.toString())
            .addOnFailureListener {
                Log.w(TAG, "Error writing document: $it")
                _toast.value = "error creating player\n${it.localizedMessage}"
                _toast.value = null
            }
            .addOnCompleteListener {
                getMemberData()
            }
    }

    /**
     * Funktion für den Logout aus der App
     */

    fun logout() {
        firebaseAuth.signOut()
        _currentUser.value = firebaseAuth.currentUser
    }

    /**
     *  Hier werden Spielerdaten mittles UserId aus dem Firestore geladen
     */

    fun getMemberData() {
        db.collection("user").document(currentUser.value!!.uid)
            .get().addOnSuccessListener {
                _member.value = it.toObject(Member::class.java)
            }
            .addOnFailureListener {
                Log.e(TAG, "Error reading document: $it")
            }
    }

    /**
     *  Hier wird die Funktion dafür verwendet das man im EditProfilFragment
     *  noch weitere Parameter seinem Profil hinzufügen kann
     */

/*
    fun updateMember(member: Member) {

        db.collection("user")
            .document(currentUser.value!!.uid) // Zugriff auf db Collection --> User dokument und dort in currentUser

            .update(
                "myDogName", member.myDogName,
                "job", member.job
            )
            .addOnSuccessListener { getMemberData() }
    }

 */


}




