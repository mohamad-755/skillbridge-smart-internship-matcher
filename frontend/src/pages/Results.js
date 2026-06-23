import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getMyMatches } from '../api/api';
import './Results.css';

function Results() {
  const navigate = useNavigate();

  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [expanded, setExpanded] = useState(null);

  uuseEffect(() => {
  const fetchMatches = async () => {
    try {
      const response = await getMyMatches();
      setResults(response.data);
    } catch (err) {
      setError('We could not load your matches right now. Please complete your profile and try again.');
    } finally {
      setLoading(false);
    }
  };

  fetchMatches();
}, []);

  const getScoreColor = (score) => {
    if (score >= 70) return '#4ecca3';
    if (score >= 40) return '#f39c12';
    return '#e74c3c';
  };

  const toggleExpand = (id) => {
    setExpanded(expanded === id ? null : id);
  };

  if (loading) return <div className="loading">Finding your best matches...</div>;
  if (error) return <div className="error">{error}</div>;

  return (
    <div className="results">
      <div className="results-header">
        <h1>Your Matches</h1>
        <p>{results.length} opportunities ranked by fit</p>
        <button className="back-btn" onClick={() => navigate('/')}>
          ← Back to Profile
        </button>
      </div>

      <div className="results-list">
        {results.map((result) => (
          <div key={result.opportunityId} className="result-card">
            <div className="result-top">
              <div className="result-info">
                <h2>{result.opportunityTitle}</h2>
                <p className="organization">{result.organization}</p>
                <div className="tags">
                  <span className="tag">{result.category}</span>
                  <span className="tag">{result.location}</span>
                  <span className="tag">Deadline: {result.deadline}</span>
                </div>
              </div>
              <div className="score-circle" style={{ borderColor: getScoreColor(result.matchScore) }}>
                <span style={{ color: getScoreColor(result.matchScore) }}>
                  {result.matchScore}%
                </span>
              </div>
            </div>

            <p className="reason">{result.reason}</p>

            <div className="skills-row">
              <div className="skills-group">
                <h4>Matched Skills</h4>
                <div className="skill-tags">
                  {result.matchedSkills.length > 0
                    ? result.matchedSkills.map((skill) => (
                        <span key={skill} className="skill-tag matched">{skill}</span>
                      ))
                    : <span className="no-skills">None</span>}
                </div>
              </div>
              <div className="skills-group">
                <h4>Missing Skills</h4>
                <div className="skill-tags">
                  {result.missingSkills.length > 0
                    ? result.missingSkills.map((skill) => (
                        <span key={skill} className="skill-tag missing">{skill}</span>
                      ))
                    : <span className="no-skills">None — perfect match!</span>}
                </div>
              </div>
            </div>

            {result.learningRoadmap.length > 0 && (
              <div className="roadmap-section">
                <button
                  className="roadmap-toggle"
                  onClick={() => toggleExpand(result.opportunityId)}
                >
                  {expanded === result.opportunityId ? '▲ Hide Learning Roadmap' : '▼ Show Learning Roadmap'}
                </button>
                {expanded === result.opportunityId && (
                  <ol className="roadmap-list">
                    {result.learningRoadmap.map((step, index) => (
                      <li key={index}>{step}</li>
                    ))}
                  </ol>
                )}
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}

export default Results;