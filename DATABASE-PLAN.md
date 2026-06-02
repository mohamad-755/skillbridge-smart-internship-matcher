# Database Plan

## Tables for Version 1

### students

Stores student information.

Fields:
- id
- name
- major
- academic_year
- location

### skills

Stores all possible skills.

Fields:
- id
- name

### student_skills

Connects students with skills.

Fields:
- student_id
- skill_id

### opportunities

Stores opportunities.

Fields:
- id
- title
- organization
- category
- location
- deadline
- description

### opportunity_skills

Connects opportunities with required skills.

Fields:
- opportunity_id
- skill_id

## Why We Need These Tables

A student can have many skills.

An opportunity can require many skills.

The matching algorithm compares student_skills with opportunity_skills.