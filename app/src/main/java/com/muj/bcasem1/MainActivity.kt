package com.muj.bcasem1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF2D5A27),
                    secondary = Color(0xFF8C4A27),
                    background = Color(0xFFF7F5F0),
                    surface = Color.White
                )
            ) {
                BcaMasteryApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BcaMasteryApp() {
    var selectedSubject by remember { mutableStateOf<Subject?>(null) }
    var selectedUnit by remember { mutableStateOf<StudyUnit?>(null) }
    var practiceModeSubject by remember { mutableStateOf<String?>(null) } // "ALL" or subjectId
    val completedUnits = remember { mutableStateListOf<Int>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when {
                            practiceModeSubject != null -> "Practice Arena (Streak Safe)"
                            selectedUnit != null -> "Unit ${selectedUnit!!.unitNumber}: ${selectedUnit!!.title}"
                            selectedSubject != null -> selectedSubject!!.name
                            else -> "BCA Sem 1 • 70-Unit Mastery"
                        },
                        maxLines = 1,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    if (selectedSubject != null || selectedUnit != null || practiceModeSubject != null) {
                        IconButton(onClick = {
                            when {
                                practiceModeSubject != null -> practiceModeSubject = null
                                selectedUnit != null -> selectedUnit = null
                                selectedSubject != null -> selectedSubject = null
                            }
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            when {
                practiceModeSubject != null -> {
                    PracticeModeScreen(
                        filterSubjectId = practiceModeSubject!!,
                        onExit = { practiceModeSubject = null }
                    )
                }
                selectedUnit != null -> {
                    UnitDetailScreen(
                        unit = selectedUnit!!,
                        isCompleted = completedUnits.contains(selectedUnit!!.unitNumber),
                        onMarkCompleted = {
                            if (!completedUnits.contains(selectedUnit!!.unitNumber)) {
                                completedUnits.add(selectedUnit!!.unitNumber)
                            }
                        }
                    )
                }
                selectedSubject != null -> {
                    val units = CurriculumRepository.allUnits.filter { it.subjectId == selectedSubject!!.id }
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item {
                            Button(
                                onClick = { practiceModeSubject = selectedSubject!!.id },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                            ) {
                                Icon(Icons.Default.PlayArrow, contentDescription = null)
                                Spacer(Modifier.width(8.dp))
                                Text("Practice ${selectedSubject!!.name} MCQs")
                            }
                        }
                        items(units) { unit ->
                            Card(
                                modifier = Modifier.fillMaxWidth().clickable { selectedUnit = unit },
                                colors = CardDefaults.cardColors(containerColor = Color.White)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("UNIT ${unit.unitNumber}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
                                        Text(unit.title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                                        Text("${unit.topics.size} Topics • ${unit.concepts.size} Concepts • ${unit.questions.size} Drills", fontSize = 12.sp, color = Color.Gray)
                                    }
                                    if (completedUnits.contains(unit.unitNumber)) {
                                        Icon(Icons.Default.CheckCircle, contentDescription = "Done", tint = MaterialTheme.colorScheme.primary)
                                    }
                                }
                            }
                        }
                    }
                }
                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("MUJ BCA Semester 1 Syllabus", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                    Spacer(Modifier.height(4.dp))
                                    Text("Completed: ${completedUnits.size} / ${CurriculumRepository.allUnits.size} Units", color = Color(0xFFDCE8D8))
                                    Spacer(Modifier.height(12.dp))
                                    Button(
                                        onClick = { practiceModeSubject = "ALL" },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = MaterialTheme.colorScheme.primary)
                                    ) {
                                        Icon(Icons.Default.School, contentDescription = null)
                                        Spacer(Modifier.width(8.dp))
                                        Text("Launch Full 70-Unit Practice Shuffle", fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                        items(CurriculumRepository.subjects) { subject ->
                            Card(
                                modifier = Modifier.fillMaxWidth().clickable { selectedSubject = subject },
                                colors = CardDefaults.cardColors(containerColor = Color.White)
                            ) {
                                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(subject.code, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
                                        Text(subject.name, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                                        Text("${subject.unitCount} Units • Bite-Sized Theory & MCQs", fontSize = 13.sp, color = Color.Gray)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UnitDetailScreen(unit: StudyUnit, isCompleted: Boolean, onMarkCompleted: () -> Unit) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Topics & Bite-Sized", "Deep Theory", "MCQ & Drills")

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index, onClick = { tabIndex = index }, text = { Text(title, fontSize = 13.sp) })
            }
        }
        when (tabIndex) {
            0 -> LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                item {
                    Text("Unit Topics", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(Modifier.height(6.dp))
                    unit.topics.forEach { topic ->
                        Text("• $topic", fontSize = 14.sp, modifier = Modifier.padding(vertical = 2.dp))
                    }
                }
                items(unit.concepts) { concept ->
                    Card(colors = CardDefaults.cardColors(containerColor = Color.White), modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(concept.title, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            Spacer(Modifier.height(6.dp))
                            Text(concept.biteSizedSummary, fontSize = 14.sp)
                        }
                    }
                }
            }
            1 -> LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(unit.concepts) { concept ->
                    Card(colors = CardDefaults.cardColors(containerColor = Color.White), modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(concept.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Spacer(Modifier.height(8.dp))
                            Text(concept.theoryDeepDive, fontSize = 14.sp, lineHeight = 20.sp)
                            if (concept.codeExample != null) {
                                Spacer(Modifier.height(10.dp))
                                Box(
                                    modifier = Modifier.fillMaxWidth().background(Color(0xFF1E1E1E), RoundedCornerShape(8.dp)).padding(12.dp)
                                ) {
                                    Text(concept.codeExample, color = Color(0xFFA9DC76), fontFamily = FontFamily.Monospace, fontSize = 13.sp)
                                }
                            }
                        }
                    }
                }
            }
            2 -> QuizSection(questions = unit.questions, isCompleted = isCompleted, onMarkCompleted = onMarkCompleted)
        }
    }
}

@Composable
fun QuizSection(questions: List<Question>, isCompleted: Boolean, onMarkCompleted: () -> Unit) {
    val selectedAnswers = remember { mutableStateMapOf<String, String>() }
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        items(questions) { q ->
            Card(colors = CardDefaults.cardColors(containerColor = Color.White), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("[${q.type.name}]", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
                    Spacer(Modifier.height(4.dp))
                    Text(q.prompt, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                    Spacer(Modifier.height(10.dp))
                    q.options.forEach { option ->
                        val chosen = selectedAnswers[q.id]
                        val bgColor = when {
                            chosen == null -> Color(0xFFF2F2F2)
                            option == q.answer -> Color(0xFFD4EDDA)
                            option == chosen -> Color(0xFFF8D7DA)
                            else -> Color(0xFFF2F2F2)
                        }
                        Surface(
                            color = bgColor,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { selectedAnswers[q.id] = option }
                        ) {
                            Text(option, modifier = Modifier.padding(12.dp), fontSize = 14.sp)
                        }
                    }
                    if (selectedAnswers[q.id] != null) {
                        Spacer(Modifier.height(8.dp))
                        Text("Explanation: ${q.explanation}", fontSize = 13.sp, color = Color(0xFF2D5A27))
                    }
                }
            }
        }
        item {
            Button(
                onClick = onMarkCompleted,
                modifier = Modifier.fillMaxWidth(),
                enabled = !isCompleted
            ) {
                Text(if (isCompleted) "Unit Completed ✓" else "Complete Unit & Save Progress")
            }
        }
    }
}

@Composable
fun PracticeModeScreen(filterSubjectId: String, onExit: () -> Unit) {
    val pool = remember(filterSubjectId) {
        val units = if (filterSubjectId == "ALL") CurriculumRepository.allUnits
        else CurriculumRepository.allUnits.filter { it.subjectId == filterSubjectId }
        units.flatMap { it.questions }.shuffled()
    }
    var index by remember { mutableIntStateOf(0) }
    var selected by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableIntStateOf(0) }

    if (pool.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("No practice questions available.") }
        return
    }

    val current = pool[index % pool.size]
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Question ${index + 1} of ${pool.size} • Score: $score", fontWeight = FontWeight.Bold)
        Card(colors = CardDefaults.cardColors(containerColor = Color.White), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(current.prompt, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(12.dp))
                current.options.forEach { opt ->
                    val color = when {
                        selected == null -> Color(0xFFF2F2F2)
                        opt == current.answer -> Color(0xFFD4EDDA)
                        opt == selected -> Color(0xFFF8D7DA)
                        else -> Color(0xFFF2F2F2)
                    }
                    Surface(
                        color = color,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable {
                            if (selected == null) {
                                selected = opt
                                if (opt == current.answer) score++
                            }
                        }
                    ) {
                        Text(opt, modifier = Modifier.padding(12.dp))
                    }
                }
                if (selected != null) {
                    Spacer(Modifier.height(8.dp))
                    Text(current.explanation, fontSize = 13.sp, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { selected = null; index++ }, modifier = Modifier.weight(1f)) {
                Text("Next Shuffled Question")
            }
            OutlinedButton(onClick = onExit) { Text("Exit") }
        }
    }
}
