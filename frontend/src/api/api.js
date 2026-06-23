import axios from 'axios';

const BASE_URL =
  process.env.REACT_APP_API_URL ||
  'https://skillbridge-smart-internship-matcher-production.up.railway.app';

const api = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

api.interceptors.request.use((config) => {
  const savedUser = localStorage.getItem('skillbridgeUser');

  if (savedUser) {
    const user = JSON.parse(savedUser);

    if (user.token) {
      config.headers.Authorization = `Bearer ${user.token}`;
    }
  }

  return config;
});

// Students
export const createStudent = (studentData) => api.post('/students/me', studentData);
export const getMyProfile = () => api.get('/students/me');
export const getStudentById = (id) => api.get(`/students/${id}`);
export const getAllStudents = () => api.get('/students');

// Opportunities
export const getAllOpportunities = () => api.get('/opportunities');
export const createOpportunity = (data) => api.post('/opportunities', data);

// Matching
export const getMatchesForStudent = (studentId) => api.get(`/match/${studentId}/all`);
export const getMyMatches = () => api.get('/match/me');
export const matchStudentWithOpportunity = (studentId, opportunityId) => 
  api.get(`/match/${studentId}/${opportunityId}`);

// Auth
export const registerUser = (data) => api.post('/auth/register', data);
export const loginUser = (data) => api.post('/auth/login', data);

export const getSavedOpportunities = () => api.get('/saved-opportunities/me');

export const saveOpportunity = (opportunityId) =>
  api.post(`/saved-opportunities/me/${opportunityId}`);

export const unsaveOpportunity = (opportunityId) =>
  api.delete(`/saved-opportunities/me/${opportunityId}`);

// Applications
export const createApplication = (opportunityId) =>
  api.post(`/applications/me/${opportunityId}`);

export const getApplications = () =>
  api.get('/applications/me');

export const updateApplicationStatus = (applicationId, status) =>
  api.put(`/applications/${applicationId}/status?status=${status}`);

export const deleteApplication = (applicationId) =>
  api.delete(`/applications/${applicationId}`);

export default api;