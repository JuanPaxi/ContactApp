package com.panfilosoft.contactapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.panfilosoft.contactapp.data.local.database.ContactDatabase
import com.panfilosoft.contactapp.data.repository.ContactRepository
import com.panfilosoft.contactapp.ui.navigation.NavManager
import com.panfilosoft.contactapp.ui.screens.ContactViewModel
import com.panfilosoft.contactapp.ui.screens.HomeScreen
import com.panfilosoft.contactapp.ui.theme.ContactAppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ContactAppTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//
//                    val database = Room.databaseBuilder(
//                        this,
//                        NoteDatabase::class.java,
//                        "note_database"
//                    ).build()
//                    val dao = database.noteDao()
//                    val viewModel = NoteViewModel(dao)
//
//                    NoteSearchScreen(noteViewModel = viewModel)
//
//                }
//            }
//        }
//    }
//}

// =====================>

//@Entity(tableName = "notes")
//data class Note(
//    @PrimaryKey(autoGenerate = true) val id: Long = 0,
//    val title: String,
//    val description: String
//)
//
//@Dao
//interface NoteDao {
//    @Query("SELECT * FROM notes WHERE title LIKE :query OR description LIKE :query")
//    fun searchNotes(query: String): Flow<List<Note>>
//
//    @Insert
//    suspend fun addData(note: Note)
//}
//
//@Database(
//    entities = [Note::class],
//    version = 1,
//    exportSchema = false
//)
//abstract class NoteDatabase : RoomDatabase() {
//    abstract fun noteDao(): NoteDao
//}
//
//
//
//
//
//class NoteViewModel(private val noteDao: NoteDao) : ViewModel() {
//    private val _query = MutableStateFlow("")
//    val query: StateFlow<String> get() = _query
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    val filteredNotes: StateFlow<List<Note>> = _query
//        .flatMapLatest { query ->
//            noteDao.searchNotes("%$query%")
//        }
//        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
//
//    fun onSearchQueryChanged(query: String) {
//        _query.value = query
//    }
//
//    fun addNote(note: Note) = viewModelScope.launch {
//        noteDao.addData(note)
//    }
//}
//
//
//
//
//@Composable
//fun NoteSearchScreen(noteViewModel: NoteViewModel) {
//    val query by noteViewModel.query.collectAsState()
//    val filteredNotes by noteViewModel.filteredNotes.collectAsState()
//
//    var title by remember {
//        mutableStateOf("")
//    }
//    var description by remember {
//        mutableStateOf("")
//    }
//
//    Column {
//        Row {
//            TextField(
//                modifier = Modifier.weight(0.4f),
//                value = title,
//                onValueChange = { title = it },
//                label = { Text(text = "title") })
//            TextField(
//                modifier = Modifier.weight(0.4f),
//                value = description,
//                onValueChange = { description = it },
//                label = { Text(text = "Description") })
//            Button(
//                modifier = Modifier.weight(0.2f),
//                onClick = {
//                    noteViewModel.addNote(
//                        Note(title = title, description = description)
//                    )
//                    title = ""
//                    description = ""
//                }) { Text(text = "Add") }
//
//        }
//        TextField(
//            value = query,
//            onValueChange = { noteViewModel.onSearchQueryChanged(it) },
//            label = { Text("Search Notes") }
//        )
//
//        LazyColumn {
//            items(filteredNotes) { note ->
//                Column {
//                    Text(text = note.title)
//                    Text(text = note.description)
//                }
//            }
//        }
//    }
//}

// =====================>


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val database = Room.databaseBuilder(
                        this,
                        ContactDatabase::class.java,
                        "contact_database"
                    ).build()
                    val dao = database.contactDao()
                    val repository = ContactRepository(dao)
                    val viewModel = ContactViewModel(repository)

                    NavManager(viewModel = viewModel)

                }
            }
        }
    }
}


//

//
//
//
//@Composable
//fun HomeScreen(
////    viewModel: ContactViewModel
//) {
//    var name by remember {
//        mutableStateOf("")
//    }
//    var number by remember {
//        mutableStateOf("")
//    }
//    var email by remember {
//        mutableStateOf("")
//    }
//
////    val notes by viewModel.contacts.collectAsState()
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        TextField(value = name, onValueChange = { name = it })
//        TextField(value = number, onValueChange = { number = it })
//        TextField(value = email, onValueChange = { email = it })
//        Button(onClick = {
////            viewModel.insertContact(
////                Contact(
////                    name = name,
////                    number = number,
////                    email = email
////                )
////            )
////            name = ""
////            number = ""
////            email = ""
//        }) { Text(text = "Add Contact") }
//        LazyColumn(
//            modifier = Modifier.fillMaxWidth()
//        ) {
////            items(notes) { contact ->
//                Column {
////                    Text(text = contact.name)
//                }
//            }
//        }
//    }
//}
