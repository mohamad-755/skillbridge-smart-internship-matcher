import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

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

// Auth
export const registerUser = (data) => api.post('/auth/register', data);
export const loginUser = (data) => api.post('/auth/login', data);

// Saved Opportunities
export const getSavedOpportunities = (userId) => api.get(`/saved-opportunities/${userId}`);

export const saveOpportunity = (userId, opportunityId) =>
  api.post(`/saved-opportunities/${userId}/${opportunityId}`);

export const unsaveOpportunity = (userId, opportunityId) =>
  api.delete(`/saved-opportunities/${userId}/${opportunityId}`);

export default api;