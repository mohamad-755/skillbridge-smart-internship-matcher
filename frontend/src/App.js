import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Results from './pages/Results';
import Opportunities from './pages/Opportunities';
import Login from './pages/Login';
import Register from './pages/Register';
import Navbar from './components/Navbar';
import './App.css';
import ProtectedRoute from './components/ProtectedRoute';
import SavedOpportunities from './pages/SavedOpportunities';
import Applications from './pages/Applications';
import AdminDashboard from './pages/AdminDashboard';

function App() {
  const [user, setUser] = useState(() => {
    const savedUser = localStorage.getItem('skillbridgeUser');
    return savedUser ? JSON.parse(savedUser) : null;
  });

  return (
    <Router>
      <Navbar user={user} setUser={setUser} />
      <div className="container">
        <Routes>
          <Route path="/login" element={<Login setUser={setUser} />} />
          <Route path="/register" element={<Register setUser={setUser} />} />
          <Route
            path="/"
            element={
              <ProtectedRoute user={user}>
                {user?.role === 'ADMIN' ? <AdminDashboard user={user} /> : <Home />}
              </ProtectedRoute>
            }
          />

          <Route
            path="/results"
            element={
              <ProtectedRoute user={user}>
                <Results />
              </ProtectedRoute>
            }
          />

          <Route
            path="/opportunities"
            element={
              <ProtectedRoute user={user}>
                <Opportunities user={user} />
              </ProtectedRoute>
            }
          />

          <Route
            path="/admin"
            element={
              <ProtectedRoute user={user}>
                {user?.role === 'ADMIN' ? (
                  <AdminDashboard user={user} />
                ) : (
                  <Opportunities user={user} />
                )}
              </ProtectedRoute>
            }
          />

          <Route
            path="/saved"
            element={
              <ProtectedRoute user={user}>
                {user?.role === 'STUDENT' ? (
                  <SavedOpportunities user={user} />
                ) : (
                  <Opportunities user={user} />
                )}
              </ProtectedRoute>
            }
          />

          <Route
            path="/applications"
            element={
              <ProtectedRoute user={user}>
                {user?.role === 'STUDENT' ? (
                  <Applications user={user} />
                ) : (
                  <Opportunities user={user} />
                )}
              </ProtectedRoute>
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;