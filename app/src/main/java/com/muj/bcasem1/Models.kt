package com.muj.bcasem1

enum class QuestionType { MCQ, TRUE_FALSE, FILL_BLANK }

data class Question(
    val id: String,
    val type: QuestionType,
    val prompt: String,
    val options: List<String>,
    val answer: String,
    val explanation: String
)

data class ConceptCard(
    val title: String,
    val biteSizedSummary: String,
    val theoryDeepDive: String,
    val codeExample: String? = null
)

data class StudyUnit(
    val unitNumber: Int,
    val subjectId: String,
    val title: String,
    val topics: List<String>,
    val concepts: List<ConceptCard>,
    val questions: List<Question>
)

data class Subject(
    val id: String,
    val code: String,
    val name: String,
    val unitCount: Int
)
