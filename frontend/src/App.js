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
                <Home />
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
                <Opportunities />
              </ProtectedRoute>
            }
          />

          <Route
            path="/saved"
            element={
              <ProtectedRoute user={user}>
                <SavedOpportunities />
              </ProtectedRoute>
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;