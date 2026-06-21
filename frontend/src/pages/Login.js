import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { loginUser } from '../api/api';
import './Login.css';

function Login({ setUser }) {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    email: '',
    password: '',
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      const response = await loginUser(form);
      localStorage.setItem('skillbridgeUser', JSON.stringify(response.data));
      setUser(response.data);
      navigate('/');
    }catch (err) {
      const backendErrors = err.response?.data;

      if (backendErrors && typeof backendErrors === 'object') {
        setError(Object.values(backendErrors).join(' '));
      } else if (typeof backendErrors === 'string') {
        setError(backendErrors);
      } else {
        setError('We could not log you in. Please check your email and password.');
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-card">
        <h1>Log in</h1>
        <p>Access your SkillBridge account.</p>

        <form onSubmit={handleSubmit} noValidate>
          <div className="form-group">
            <label>Email</label>
            <input
              name="email"
              type="email"
              value={form.email}
              onChange={handleChange}
              placeholder="you@example.com"
              required
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              name="password"
              type="password"
              value={form.password}
              onChange={handleChange}
              placeholder="Enter your password"
              required
            />
          </div>

          {error && <p className="error">{error}</p>}

          <button type="submit" disabled={loading}>
            {loading ? 'Logging in...' : 'Log in'}
          </button>
        </form>

        <p className="auth-switch">
          New to SkillBridge? <Link to="/register">Create an account</Link>
        </p>
      </div>
    </div>
  );
}

export default Login;