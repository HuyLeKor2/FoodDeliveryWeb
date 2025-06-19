import logo from './logo.svg';
import './App.css';
import { ThemeProvider } from '@emotion/react';
import darkTheme from './theme/DarkTheme';
import { CssBaseline } from '@mui/material';
import './index.css';
import HomePage from './customers/pages/Home/HomePage';
import Navbar from './customers/components/Navbar/Navbar';
import CustomerRoutes from './Routers/CustomerRoutes';
import {
  getAllRestaurantsAction,
  getRestaurantByUserId,
} from './State/Customers/Restaurant/restaurant.action';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { getUser } from './State/Authentication/Action';
import { findCart } from './State/Customers/Cart/cart.action';
import Routers from './Routers/Routers';

function App() {
  const dispatch = useDispatch();
  const { auth } = useSelector((store) => store);
  const jwt = localStorage.getItem('jwt');

  useEffect(() => {
    if (jwt) {
      dispatch(getUser(jwt));
      dispatch(findCart(jwt));
      // dispatch(getAllRestaurantsAction(jwt));
    }
  }, [auth.jwt]);

  // Load restaurant data for restaurant owners
  useEffect(() => {
    if (auth.user?.role === 'ROLE_RESTAURANT_OWNER') {
      console.log('Loading restaurant data for user:', auth.user.email);
      dispatch(getRestaurantByUserId(auth.jwt || jwt));
    }
  }, [auth.user, auth.jwt, jwt]);

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      {/* <Navbar /> */}
      {/* <HomePage /> */}
      <Routers />
    </ThemeProvider>
  );
}

export default App;
