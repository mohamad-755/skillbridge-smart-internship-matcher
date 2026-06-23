import React, { useEffect, useState } from 'react';
import { createStudent, getMyProfile } from '../api/api';
import { useNavigate } from 'react-router-dom';
import './Home.css';

function Home() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const [form, setForm] = useState({
    name: '',
    major: '',
    academicYear: '',
    location: '',
    skills: '',
    interests: '',
  });

  useEffect(() => {
    const loadProfile = async () => {
      try {
        const response = await getMyProfile();
        const profile = response.data;

        setForm({
          name: profile.name || '',
          major: profile.major || '',
          academicYear: profile.academicYear || '',
          location: profile.location || '',
          skills: profile.skills ? profile.skills.join(', ') : '',
          interests: profile.interests ? profile.interests.join(', ') : '',
        });
      } catch (err) {
        // No profile yet, keep the form empty.
      }
    };

    loadProfile();
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      const studentData = {
        name: form.name,
        major: form.major,
        academicYear: form.academicYear,
        location: form.location,
        skills: form.skills.split(',').map((s) => s.trim()).filter(Boolean),
        interests: form.interests.split(',').map((s) => s.trim()).filter(Boolean),
      };

      await createStudent(studentData);
      navigate('/results');
    } catch (err) {
      setError(
        err.response?.data?.message ||
          'We could not submit your profile right now. Please check your connection and try again.'
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="home">
      <div className="home-header">
        <h1>Find Your Perfect Internship</h1>
        <p>Enter your profile and we will match you with the best opportunities based on your skills and interests.</p>
      </div>

      <form className="profile-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Full Name</label>
          <input
            type="text"
            name="name"
            placeholder="e.g. Mohamad"
            value={form.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Major</label>
          <input
            type="text"
            name="major"
            placeholder="e.g. Computer Science"
            value={form.major}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Academic Year</label>
          <input
            type="text"
            name="academicYear"
            placeholder="e.g. Junior"
            value={form.academicYear}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Location</label>
          <input
            type="text"
            name="location"
            placeholder="e.g. Beirut"
            value={form.location}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Skills <span>(comma separated)</span></label>
          <input
            type="text"
            name="skills"
            placeholder="e.g. Java, Git, OOP, Data Structures"
            value={form.skills}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Interests <span>(comma separated)</span></label>
          <input
            type="text"
            name="interests"
            placeholder="e.g. Backend, AI, Internship"
            value={form.interests}
            onChange={handleChange}
            required
          />
        </div>

        {error && <p className="error">{error}</p>}

        <button type="submit" disabled={loading}>
          {loading ? 'Finding Matches...' : 'Find My Matches'}
        </button>
      </form>
    </div>
  );
}

export default Home;