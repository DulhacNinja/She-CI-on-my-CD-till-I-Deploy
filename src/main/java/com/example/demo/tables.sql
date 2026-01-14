-- =============================================
-- PostgreSQL Schema: University Course Structure
-- =============================================

-- Drop existing tables (if any) in correct dependency order
DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS packs CASCADE;
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS instructors CASCADE;
DROP TABLE if EXISTS student_preferences CASCADE;

-- =============================================
-- Table: students
-- =============================================
CREATE TABLE students (
                          id SERIAL PRIMARY KEY,
                          code VARCHAR(20) UNIQUE NOT NULL,
                          name VARCHAR(100) NOT NULL,
                          email VARCHAR(150) UNIQUE NOT NULL,
                          year INT CHECK (year >= 1 AND year <= 3)
    );

-- =============================================
-- Table: instructors
-- =============================================
CREATE TABLE instructors (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(100) NOT NULL,
                             email VARCHAR(150) UNIQUE NOT NULL
);

-- =============================================
-- Table: packs
-- =============================================
CREATE TABLE packs (
                       id SERIAL PRIMARY KEY,
                       year INT NOT NULL,
    semester SMALLINT NOT NULL CHECK (semester IN (1, 2)),
    name VARCHAR(100) NOT NULL
);

-- =============================================
-- Table: courses
-- =============================================
CREATE TABLE courses (
                         id SERIAL PRIMARY KEY,
                         type VARCHAR(50) NOT NULL,
                         code VARCHAR(20) UNIQUE NOT NULL,
                         abbr VARCHAR(20),
                         name VARCHAR(150) NOT NULL,
                         instructor_id INT REFERENCES instructors(id) ON DELETE SET NULL,
                         pack_id INT REFERENCES packs(id) ON DELETE CASCADE,
                         group_count INT DEFAULT 1 CHECK (group_count >= 1),
                         description TEXT
);

-- =============================================
-- Table: student_preferences
-- =============================================
CREATE TABLE student_preferences (
                                     id SERIAL PRIMARY KEY,
                                     student_id BIGINT NOT NULL REFERENCES students(id) ON DELETE CASCADE,
                                     course_id BIGINT NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
                                     rank INTEGER NOT NULL
);

-- =============================================
-- Done
-- =============================================
