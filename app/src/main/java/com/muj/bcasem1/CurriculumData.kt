package com.muj.bcasem1

object CurriculumRepository {
    val subjects = listOf(
        Subject("c_prog", "DCA1102", "Programming in C", 14),
        Subject("web_prog", "DCA1106", "Web Programming (HTML/CSS/JS)", 12),
        Subject("fund_it", "DCA1101", "Fundamentals of IT & Computers", 12),
        Subject("math_found", "DCA1103", "Basic Mathematics", 12),
        Subject("dig_logic", "DCA1104", "Digital Logic & Computer Design", 10),
        Subject("comm_skills", "DCA1105", "Business Communication", 10)
    )

    private val detailedUnits: Map<Pair<String, Int>, StudyUnit> = mapOf(
        ("c_prog" to 1) to StudyUnit(
            unitNumber = 1,
            subjectId = "c_prog",
            title = "Introduction to C & Program Structure",
            topics = listOf("History of C", "Compilation Pipeline", "Header Files & main()", "Tokens & Keywords"),
            concepts = listOf(
                ConceptCard(
                    title = "1.1 Anatomy of a C Program",
                    biteSizedSummary = "Every C program begins execution at main(). Preprocessor directives (#include) load library declarations before compilation.",
                    theoryDeepDive = "C is a procedural, compiled language developed by Dennis Ritchie in 1972. Compilation transforms source code (.c) through Preprocessor -> Compiler -> Assembler -> Linker into an executable binary.",
                    codeExample = "#include <stdio.h>\nint main() {\n    printf(\"Hello MUJ BCA!\\n\");\n    return 0;\n}"
                ),
                ConceptCard(
                    title = "1.2 Tokens, Identifiers & Keywords",
                    biteSizedSummary = "C has 32 reserved keywords. Identifiers name variables/functions and cannot start with a digit.",
                    theoryDeepDive = "Tokens are the smallest individual units in a program: keywords (int, return, if), identifiers, constants, string literals, operators, and punctuators."
                )
            ),
            questions = listOf(
                Question("c1_q1", QuestionType.MCQ, "Which function serves as the mandatory entry point of every C program?", listOf("start()", "main()", "init()", "stdio()"), "main()", "The OS loader invokes main() first when starting a compiled C executable."),
                Question("c1_q2", QuestionType.TRUE_FALSE, "In C, variable names can start with a numeric digit such as 1count.", listOf("True", "False"), "False", "Identifiers must begin with an alphabet (A-Z, a-z) or an underscore (_)."),
                Question("c1_q3", QuestionType.FILL_BLANK, "The preprocessor directive used to include standard input/output functions is #include <______>.", listOf("stdio.h", "conio.h", "math.h", "stdlib.h"), "stdio.h", "stdio.h declares printf() and scanf().")
            )
        ),
        ("c_prog" to 2) to StudyUnit(
            unitNumber = 2,
            subjectId = "c_prog",
            title = "Data Types, Variables & Operators",
            topics = listOf("Primitive Data Types", "Format Specifiers", "Arithmetic & Relational Operators", "Operator Precedence"),
            concepts = listOf(
                ConceptCard(
                    title = "2.1 Primitive Data Types & Sizes",
                    biteSizedSummary = "char (1 byte), int (4 bytes), float (4 bytes, 6-digit precision), double (8 bytes, 15-digit precision).",
                    theoryDeepDive = "Data types tell the compiler how much memory to reserve and how to interpret bit patterns. Modifiers like signed, unsigned, short, and long adjust range and storage.",
                    codeExample = "int age = 18;\nfloat gpa = 8.75f;\nchar grade = 'A';"
                ),
                ConceptCard(
                    title = "2.2 Arithmetic, Logical & Bitwise Operators",
                    biteSizedSummary = "Integer division truncates decimals (5 / 2 == 2). Modulo (%) returns the remainder (5 % 2 == 1).",
                    theoryDeepDive = "Logical operators (&&, ||, !) evaluate truthiness with short-circuit behavior, while bitwise operators (&, |, ^, <<, >>) manipulate individual bits directly."
                )
            ),
            questions = listOf(
                Question("c2_q1", QuestionType.MCQ, "What is the output of 7 / 2 in C when both operands are int?", listOf("3.5", "3", "4", "1"), "3", "Integer division discards the fractional remainder."),
                Question("c2_q2", QuestionType.FILL_BLANK, "The format specifier used to read or print a double value in scanf() is ____.", listOf("%lf", "%f", "%d", "%c"), "%lf", "scanf() requires %lf for double precision floating-point numbers.")
            )
        ),
        ("c_prog" to 3) to StudyUnit(
            unitNumber = 3,
            subjectId = "c_prog",
            title = "Control Flow: Branching & Loops",
            topics = listOf("if-else & switch", "for, while, do-while Loops", "break & continue"),
            concepts = listOf(
                ConceptCard(
                    title = "3.1 Entry-Controlled vs Exit-Controlled Loops",
                    biteSizedSummary = "for and while check conditions before running; do-while executes the body at least once before checking.",
                    theoryDeepDive = "Use for loops when iteration counts are known upfront, while loops for sentinel-driven conditions, and do-while loops for menus that must render at least once.",
                    codeExample = "for(int i = 1; i <= 5; i++) {\n    if(i == 3) continue;\n    printf(\"%d \", i);\n}"
                )
            ),
            questions = listOf(
                Question("c3_q1", QuestionType.MCQ, "Which loop is guaranteed to execute its body at least once?", listOf("for", "while", "do-while", "nested if"), "do-while", "do-while tests its condition at the bottom of the loop.")
            )
        ),
        ("c_prog" to 6) to StudyUnit(
            unitNumber = 6,
            subjectId = "c_prog",
            title = "Pointers & Dynamic Memory Allocation",
            topics = listOf("Address-of (&) & Dereference (*)", "Pointer Arithmetic", "malloc, calloc, realloc, free"),
            concepts = listOf(
                ConceptCard(
                    title = "6.1 Pointers Fundamentals",
                    biteSizedSummary = "A pointer stores the memory address of another variable rather than a direct value.",
                    theoryDeepDive = "Using &var gets the address; *ptr dereferences that address to read or modify the underlying value. Incrementing an int* advances by sizeof(int) bytes.",
                    codeExample = "int x = 42;\nint *p = &x;\n*p = 99; // x is now 99"
                )
            ),
            questions = listOf(
                Question("c6_q1", QuestionType.MCQ, "Which function allocates a block of memory on the heap and initializes all bytes to zero?", listOf("malloc()", "calloc()", "free()", "sizeof()"), "calloc()", "calloc(n, size) allocates zero-initialized contiguous memory.")
            )
        ),
        ("web_prog" to 1) to StudyUnit(
            unitNumber = 1,
            subjectId = "web_prog",
            title = "Web Architecture & HTML5 Document Structure",
            topics = listOf("Client-Server HTTP/HTTPS", "DOCTYPE & Semantic Tags", "Headings, Links & Lists"),
            concepts = listOf(
                ConceptCard(
                    title = "1.1 Semantic HTML5 Structure",
                    biteSizedSummary = "Semantic tags (<header>, <nav>, <main>, <article>, <footer>) convey meaning to browsers and screen readers.",
                    theoryDeepDive = "Every valid HTML5 page starts with <!DOCTYPE html>, followed by <html>, <head> (metadata, title, CSS links), and <body> (visible DOM elements).",
                    codeExample = "<!DOCTYPE html>\n<html>\n<body>\n  <header><h1>BCA Portal</h1></header>\n  <main><p>Welcome!</p></main>\n</body>\n</html>"
                )
            ),
            questions = listOf(
                Question("w1_q1", QuestionType.MCQ, "Which HTML5 element defines the primary self-contained content of a document?", listOf("<section>", "<article>", "<aside>", "<div>"), "<article>", "<article> represents independent, self-contained content.")
            )
        ),
        ("web_prog" to 4) to StudyUnit(
            unitNumber = 4,
            subjectId = "web_prog",
            title = "CSS3 Box Model, Flexbox & Grid",
            topics = listOf("Selectors & Specificity", "Content-Padding-Border-Margin", "Flexbox Alignment"),
            concepts = listOf(
                ConceptCard(
                    title = "4.1 The CSS Box Model",
                    biteSizedSummary = "Every HTML element is a rectangular box made of Content -> Padding -> Border -> Margin.",
                    theoryDeepDive = "Setting box-sizing: border-box ensures padding and border are included inside the declared width and height, preventing layout overflow.",
                    codeExample = ".card {\n  box-sizing: border-box;\n  padding: 16px;\n  margin: 12px;\n  border: 1px solid #333;\n}"
                )
            ),
            questions = listOf(
                Question("w4_q1", QuestionType.TRUE_FALSE, "In the CSS Box Model, margin adds space inside the element's border.", listOf("True", "False"), "False", "Padding adds space inside the border; margin adds space outside the border.")
            )
        )
    )

    private val unitTitlesBySubject: Map<String, List<String>> = mapOf(
        "c_prog" to listOf(
            "Introduction to C & Program Structure",
            "Data Types, Variables & Operators",
            "Control Flow: Branching & Loops",
            "Functions, Scope & Recursion",
            "Arrays & String Manipulation",
            "Pointers & Dynamic Memory Allocation",
            "Structures & Unions",
            "File Handling in C",
            "Preprocessor Directives & Macros",
            "Command Line Arguments",
            "Searching & Sorting Algorithms in C",
            "Linked Lists Implementation",
            "Stacks & Queues using C",
            "Error Handling & Best Practices"
        ),
        "web_prog" to listOf(
            "Web Architecture & HTML5 Document Structure",
            "HTML Forms, Tables & Multimedia",
            "CSS3 Selectors, Colors & Typography",
            "CSS3 Box Model, Flexbox & Grid",
            "Responsive Web Design & Media Queries",
            "JavaScript Basics: Variables & Data Types",
            "JS Functions, Objects & Arrays",
            "DOM Manipulation & Event Handling",
            "Client-Side Form Validation",
            "Asynchronous JS: Promises & Fetch API",
            "Web Storage (LocalStorage & SessionStorage)",
            "Hosting & Website Deployment Basics"
        ),
        "fund_it" to listOf(
            "Evolution & Generations of Computers",
            "Computer Organization: CPU, ALU & Control Unit",
            "Primary & Secondary Memory Hierarchy",
            "Input & Output Devices",
            "System Software vs Application Software",
            "Operating System Concepts & Functions",
            "Windows & Linux Command Essentials",
            "Computer Networks: LAN, MAN, WAN",
            "OSI & TCP/IP Reference Models",
            "Internet Protocols: HTTP, DNS, FTP, SMTP",
            "Database & Cloud Computing Fundamentals",
            "Cyber Security & Safe Computing"
        ),
        "math_found" to listOf(
            "Set Theory & Venn Diagrams",
            "Relations & Functions",
            "Matrices: Types & Operations",
            "Determinants & Cramer's Rule",
            "Inverse of a Matrix & Linear Equations",
            "Permutations & Combinations",
            "Binomial Theorem",
            "Arithmetic & Geometric Progressions",
            "Limits & Continuity",
            "Differentiation Basics",
            "Integration Fundamentals",
            "Elementary Statistics & Probability"
        ),
        "dig_logic" to listOf(
            "Number Systems: Binary, Octal, Hexadecimal",
            "Binary Arithmetic & Complements (1's & 2's)",
            "Boolean Algebra Laws & De Morgan's Theorems",
            "Logic Gates: AND, OR, NOT, NAND, NOR, XOR",
            "Karnaugh Maps (K-Map) Simplification",
            "Combinational Circuits: Adders & Subtractors",
            "Multiplexers, Demultiplexers & Encoders",
            "Sequential Circuits: Latches & Flip-Flops",
            "Registers & Counters",
            "Memory Devices: RAM, ROM, PROM, EPROM"
        ),
        "comm_skills" to listOf(
            "Process & Models of Communication",
            "Verbal vs Non-Verbal Communication",
            "Barriers to Effective Communication",
            "Active Listening & Empathy",
            "Business Letter & Email Writing",
            "Report & Proposal Drafting",
            "Resume Writing & Cover Letters",
            "Group Discussions & Interview Skills",
            "Presentation Skills & Body Language",
            "Cross-Cultural & Workplace Etiquette"
        )
    )

    val allUnits: List<StudyUnit> by lazy {
        val list = mutableListOf<StudyUnit>()
        var globalCounter = 1
        for (subject in subjects) {
            val titles = unitTitlesBySubject[subject.id].orEmpty()
            titles.forEachIndexed { idx, unitTitle ->
                val unitNum = idx + 1
                val custom = detailedUnits[subject.id to unitNum]
                if (custom != null) {
                    list.add(custom.copy(unitNumber = globalCounter))
                } else {
                    list.add(
                        StudyUnit(
                            unitNumber = globalCounter,
                            subjectId = subject.id,
                            title = unitTitle,
                            topics = listOf(
                                "$unitTitle - Core Principles",
                                "$unitTitle - Practical Applications",
                                "$unitTitle - Exam Revision Checklist"
                            ),
                            concepts = listOf(
                                ConceptCard(
                                    title = "Concept 1: Foundations of $unitTitle",
                                    biteSizedSummary = "Key definitions, rules, and core principles required for MUJ BCA Semester 1 Unit $unitNum of ${subject.name}.",
                                    theoryDeepDive = "In ${subject.name} (${subject.code}), $unitTitle establishes essential analytical and problem-solving building blocks. Focus on syntax/rules, edge cases, and standard problem patterns tested in 30-mark internal assessments and 70-mark term-end exams."
                                ),
                                ConceptCard(
                                    title = "Concept 2: Applied Problem Solving",
                                    biteSizedSummary = "Step-by-step approach to solving exam questions and lab exercises on $unitTitle.",
                                    theoryDeepDive = "Break complex problems into input, transformation logic, and expected output. Verify boundary conditions before finalizing answers."
                                )
                            ),
                            questions = listOf(
                                Question(
                                    id = "${subject.id}_u${unitNum}_q1",
                                    type = QuestionType.MCQ,
                                    prompt = "Which statement best describes the primary objective of '$unitTitle' in ${subject.name}?",
                                    options = listOf(
                                        "To apply structured rules and core principles of $unitTitle",
                                        "To bypass hardware memory management completely",
                                        "To replace all high-level logic with unformatted text",
                                        "None of the above"
                                    ),
                                    answer = "To apply structured rules and core principles of $unitTitle",
                                    explanation = "Mastering the rules and structured workflow of $unitTitle is foundational in ${subject.code}."
                                ),
                                Question(
                                    id = "${subject.id}_u${unitNum}_q2",
                                    type = QuestionType.TRUE_FALSE,
                                    prompt = "Understanding boundary conditions and core rules is essential when solving problems in $unitTitle.",
                                    options = listOf("True", "False"),
                                    answer = "True",
                                    explanation = "Boundary cases prevent logical and runtime errors in exams and lab assignments."
                                )
                            )
                        )
                    )
                }
                globalCounter++
            }
        }
        list
    }
}
