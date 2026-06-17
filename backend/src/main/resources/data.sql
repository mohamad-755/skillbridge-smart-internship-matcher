INSERT INTO student (name, major, academic_year, location) VALUES ('Mohamad', 'Computer Science', 'Junior', 'Beirut');
INSERT INTO student_skills (student_id, skills) VALUES (1, 'Java'), (1, 'Git'), (1, 'OOP'), (1, 'Data Structures');
INSERT INTO student_interests (student_id, interests) VALUES (1, 'Backend'), (1, 'AI'), (1, 'Internship');

INSERT INTO opportunity (title, organization, category, location, deadline, description) VALUES ('Backend Internship', 'TechStart', 'Internship', 'Beirut', '2026-07-15', 'A beginner-friendly backend internship for CS students.');
INSERT INTO opportunity_required_skills (opportunity_id, required_skills) VALUES (1, 'Java'), (1, 'Spring Boot'), (1, 'Git');

INSERT INTO opportunity (title, organization, category, location, deadline, description) VALUES ('AI Bootcamp', 'DataLab', 'Bootcamp', 'Remote', '2026-08-01', 'A practical AI bootcamp for students interested in machine learning.');
INSERT INTO opportunity_required_skills (opportunity_id, required_skills) VALUES (2, 'Python'), (2, 'Machine Learning'), (2, 'Git');

INSERT INTO opportunity (title, organization, category, location, deadline, description) VALUES ('Frontend Volunteer Program', 'CodeForGood', 'Volunteering', 'Beirut', '2026-06-20', 'Volunteer with a tech NGO and help build simple websites.');
INSERT INTO opportunity_required_skills (opportunity_id, required_skills) VALUES (3, 'HTML'), (3, 'CSS'), (3, 'JavaScript'), (3, 'React');


