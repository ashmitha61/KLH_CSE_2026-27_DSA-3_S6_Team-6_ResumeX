# Resume Search and Candidate Screening System

## Problem Statement

Recruiters often receive a large number of resumes for a single job role. Manually reviewing each resume is time-consuming, repetitive, and error-prone. A resume screening system helps reduce this effort by identifying relevant candidates based on required skills and comparing them using algorithmic scoring.

## Objective

This project demonstrates a simple Java-based resume search and candidate screening system. It reads resume files, compares them to job requirements, and ranks candidates based on pattern matching, fuzzy search, and cosine similarity.

## Features

- Resume reading from text files
- Pattern matching for required skills
- Fuzzy search using Levenshtein distance
- TF-IDF based text similarity
- Cosine similarity scoring
- Candidate ranking and final recommendation

## Technologies

- Java
- Java Collections Framework
- Regular Expressions
- File Handling
- Object-oriented programming
- Algorithms and data structures

## Architecture

Resume Files
     ↓
Resume Reader
     ↓
Text Processing
     ↓
Pattern Matching
     ↓
Fuzzy Search
     ↓
TF-IDF + Cosine Similarity
     ↓
Candidate Ranking
     ↓
Final Results

## Algorithms Used

### Pattern Matching
Pattern matching checks whether all required skills are present in each resume. It uses case-insensitive matching and regular expressions to compare skills like "Machine Learning" and "SQL".

### Fuzzy Search
Fuzzy search finds words that are similar but not exactly the same, such as "Pythn" and "Python". The system uses the Levenshtein distance algorithm to calculate edit distance.

### TF-IDF
TF-IDF measures how important a word is within a document compared with other documents. It gives higher value to important terms and lower value to common ones.

### Cosine Similarity
Cosine similarity measures the angle between two vectors in a multi-dimensional space. It is useful for comparing job requirements to resumes.

## How to Run

Open VS Code terminal in the project root and run:

```bash
javac src/*.java
java -cp src Main
```

## Sample Input

Job Title:
Machine Learning Intern

Required Skills:
Python, Machine Learning, SQL, Data Structures

## Sample Output

```text
==================================================
       RESUME SEARCH AND CANDIDATE SCREENING
==================================================
Enter Job Title:
Machine Learning Intern
Enter Required Skills:
Python, Machine Learning, SQL, Data Structures
--------------------------------------------------------------
Candidate: Rahul
Matched Skills:
Python
Machine Learning
SQL
Data Structures
Missing Skills:
None
Pattern Matching Score: 100.00%
Fuzzy Matching Score: 96.00%
Cosine Similarity Score: 88.45%
Final Score: 94.58%
...
Most relevant candidate: Rahul
```

## Future Enhancements

- PDF resume extraction
- GUI or web interface
- Database integration
- NLP-based skill extraction
- Education and experience-based ranking
- Advanced semantic similarity
- Resume upload and recruiter login

## Git Setup Commands

```bash
git init
git add .
git commit -m "Initial implementation of Resume Search and Candidate Screening System"
```

To connect to GitHub, create a new repository on GitHub and then run:

```bash
git remote add origin <your-github-repository-url>
git branch -M main
git push -u origin main
```

Replace `<your-github-repository-url>` with your repository URL.

## Review-2 Demonstration

1. Open the GitHub repository.
2. Show the project folder structure.
3. Run the Java program.
4. Enter the job title.
5. Enter the required skills.
6. Show pattern matching results.
7. Demonstrate fuzzy search with a spelling variation.
8. Show similarity scores.
9. Show final ranking.
10. Explain the algorithms used.

## Review-2 Explanation

This project is a simple system that helps recruiters screen resumes faster. It reads resumes, checks whether they contain required skills, and ranks candidates based on how closely the resume matches the job description. In this Review-2 implementation, we covered pattern matching, fuzzy search, and similarity calculation using TF-IDF and cosine similarity. Pattern matching checks exact required keywords, fuzzy search handles spelling mistakes, and cosine similarity compares the overall text content. Candidates are ranked using weighted scores so the most relevant profile appears first.

## Future Work

In the future, this project can support PDF extraction, database storage, recruiter login, and more advanced machine learning-based resume ranking.
