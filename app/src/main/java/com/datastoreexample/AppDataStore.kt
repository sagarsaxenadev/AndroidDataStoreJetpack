package com.datastoreexample

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(val context: Context) {
    private val dataStore = context.createDataStore(name = "my_app_data")

    companion object {

        val USER_NAME = preferencesKey<String>("user_name")
        val USER_EMAIL = preferencesKey<String>("user_email")

    }

    suspend fun saveName(name: String) {
        dataStore.edit {
            it[USER_NAME] = name
        }
    }


    suspend fun saveEmail(email: String) {

        dataStore.edit {
            it[USER_EMAIL] = email
        }

    }


    val userNameFlow: Flow<String> = dataStore.data.map {
        it[USER_NAME] ?: ""
    }

    val userEmailFlow: Flow<String> = dataStore.data.map {
        it[USER_EMAIL] ?: ""
    }


}