import axios from 'axios';

const BASE_URL = 'https://skillbridge-smart-internship-matcher-production.up.railway.app';

const api = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Students
export const createStudent = (studentData) => api.post('/students', studentData);
export const getStudentById = (id) => api.get(`/students/${id}`);
export const getAllStudents = () => api.get('/students');

// Opportunities
export const getAllOpportunities = () => api.get('/opportunities');
export const createOpportunity = (data) => api.post('/opportunities', data);

// Matching
export const getMatchesForStudent = (studentId) => api.get(`/match/${studentId}/all`);
export const matchStudentWithOpportunity = (studentId, opportunityId) => 
  api.get(`/match/${studentId}/${opportunityId}`);

export default api;