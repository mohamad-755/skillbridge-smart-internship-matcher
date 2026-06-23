export const getErrorMessage = (err, fallback) => {
  const backendErrors = err.response?.data;

  if (typeof backendErrors === 'string') {
    return backendErrors;
  }

  if (backendErrors?.message) {
    return backendErrors.message;
  }

  if (backendErrors && typeof backendErrors === 'object') {
    return Object.values(backendErrors).join(' ');
  }

  return fallback;
};