import React, { useEffect, useState } from 'react';
import {
  getAllOpportunities,
  createOpportunity,
  getSavedOpportunities,
  saveOpportunity,
  unsaveOpportunity,
} from '../api/api';
import './Opportunities.css';

function Opportunities() {
  const [opportunities, setOpportunities] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState({
    title: '',
    organization: '',
    category: '',
    location: '',
    deadline: '',
    description: '',
    requiredSkills: '',
  });
  const [savedIds, setSavedIds] = useState([]);
  const user = JSON.parse(localStorage.getItem('skillbridgeUser'));

  useEffect(() => {
    fetchOpportunities();
    fetchSavedOpportunities();
  }, []);

  const fetchOpportunities = async () => {
    try {
      const response = await getAllOpportunities();
      setOpportunities(response.data);
    } catch (err) {
      setError('We could not load opportunities right now. Please refresh the page or try again later.');
    } finally {
      setLoading(false);
    }
  };

  const fetchSavedOpportunities = async () => {
    if (!user?.id) return;

    try {
      const response = await getSavedOpportunities(user.id);
      setSavedIds(response.data.map((item) => item.opportunityId));
    } catch (err) {
      setError('We could not load your saved opportunities right now.');
    }
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = {
        ...form,
        requiredSkills: form.requiredSkills.split(',').map((s) => s.trim()).filter(Boolean),
      };
      await createOpportunity(data);
      setShowForm(false);
      setForm({
        title: '',
        organization: '',
        category: '',
        location: '',
        deadline: '',
        description: '',
        requiredSkills: '',
      });
      fetchOpportunities();
    } catch (err) {
      setError('We could not add this opportunity. Please check the details and try again.');
    }
  };

  const handleToggleSave = async (opportunityId) => {
    if (!user?.id) {
      setError('Please log in to save opportunities.');
      return;
    }

    try {
      if (savedIds.includes(opportunityId)) {
        await unsaveOpportunity(user.id, opportunityId);
        setSavedIds(savedIds.filter((id) => id !== opportunityId));
      } else {
        await saveOpportunity(user.id, opportunityId);
        setSavedIds([...savedIds, opportunityId]);
      }
    } catch (err) {
      setError('We could not update your saved opportunities. Please try again.');
    }
  };

  if (loading) return <div className="loading">Loading opportunities...</div>;

  return (
    <div className="opportunities">
      <div className="opportunities-header">
        <div>
          <h1>Opportunities</h1>
          <p>{opportunities.length} opportunities available</p>
        </div>
        <button className="add-btn" onClick={() => setShowForm(!showForm)}>
          {showForm ? '✕ Cancel' : '+ Add Opportunity'}
        </button>
      </div>

      {error && <p className="error">{error}</p>}

      {showForm && (
        <form className="opportunity-form" onSubmit={handleSubmit}>
          <h3>Add New Opportunity</h3>
          <div className="form-grid">
            <div className="form-group">
              <label>Title</label>
              <input name="title" value={form.title} onChange={handleChange} required placeholder="e.g. Backend Internship" />
            </div>
            <div className="form-group">
              <label>Organization</label>
              <input name="organization" value={form.organization} onChange={handleChange} required placeholder="e.g. TechStart" />
            </div>
            <div className="form-group">
              <label>Category</label>
              <input name="category" value={form.category} onChange={handleChange} required placeholder="e.g. Internship" />
            </div>
            <div className="form-group">
              <label>Location</label>
              <input name="location" value={form.location} onChange={handleChange} required placeholder="e.g. Beirut" />
            </div>
            <div className="form-group">
              <label>Deadline</label>
              <input name="deadline" type="date" value={form.deadline} onChange={handleChange} required />
            </div>
            <div className="form-group">
              <label>Required Skills <span>(comma separated)</span></label>
              <input name="requiredSkills" value={form.requiredSkills} onChange={handleChange} required placeholder="e.g. Java, Spring Boot, Git" />
            </div>
          </div>
          <div className="form-group full">
            <label>Description</label>
            <textarea name="description" value={form.description} onChange={handleChange} required placeholder="Describe the opportunity..." rows={3} />
          </div>
          <button type="submit" className="submit-btn">Add Opportunity</button>
        </form>
      )}

      <div className="opportunities-list">
        {opportunities.map((opp) => (
          <div key={opp.id} className="opportunity-card">
            <div className="opp-top">
              <div>
                <h2>{opp.title}</h2>
                <p className="opp-org">{opp.organization}</p>
              </div>

              <div className="opp-actions">
                <span className="opp-category">{opp.category}</span>
                <button
                  className={savedIds.includes(opp.id) ? 'save-btn saved' : 'save-btn'}
                  onClick={() => handleToggleSave(opp.id)}
                >
                  {savedIds.includes(opp.id) ? 'Saved' : 'Save'}
                </button>
              </div>
            </div>
            <p className="opp-description">{opp.description}</p>
            <div className="opp-footer">
              <div className="opp-skills">
                {opp.requiredSkills.map((skill) => (
                  <span key={skill} className="skill-tag">{skill}</span>
                ))}
              </div>
              <div className="opp-meta">
                <span>📍 {opp.location}</span>
                <span>📅 {opp.deadline}</span>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Opportunities;