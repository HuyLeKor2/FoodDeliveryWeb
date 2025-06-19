import axios from 'axios';

export const API_URL = 'http://localhost:5454';

export const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Utility function to decode JWT token
export const decodeJWT = (token) => {
  try {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split('')
        .map(function (c) {
          return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        })
        .join('')
    );
    return JSON.parse(jsonPayload);
  } catch (error) {
    console.error('Error decoding JWT:', error);
    return null;
  }
};

// Check if user has restaurant owner role
export const hasRestaurantOwnerRole = (token) => {
  const decoded = decodeJWT(token);
  if (!decoded) return false;

  // Check different possible field names for authorities
  const authorities =
    decoded.authorities || decoded.roles || decoded.role || '';
  return authorities.includes('ROLE_RESTAURANT_OWNER');
};

// Check if token is expired
export const isTokenExpired = (token) => {
  const decoded = decodeJWT(token);
  if (!decoded) return true;

  const currentTime = Date.now() / 1000;
  return decoded.exp < currentTime;
};
