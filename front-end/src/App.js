import logo from './logo.svg';
import './App.css';
import { ThemeProvider } from '@emotion/react';
import darkTheme from './theme/DarkTheme';
import { CssBaseline } from '@mui/material';
import './index.css';
import HomePage from './customers/pages/Home/HomePage';
import Navbar from './customers/components/Navbar/Navbar';
import CustomerRoutes from './Routers/CustomerRoutes';
import { getAllRestaurantsAction, getRestaurantByUserId } from './State/Customers/Restaurant/restaurant.action';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { getUser } from './State/Authentication/Action';
function App() {
  const dispatch = useDispatch();
  const { auth } = useSelector((store) => store);
  const jwt = localStorage.getItem("jwt");

  useEffect(() => {

    if (jwt) {
      dispatch(getUser(jwt));
      // dispatch(findCart(jwt));
      // dispatch(getAllRestaurantsAction(jwt));
    }
  }, [auth.jwt]);

  // useEffect(() => {
  //   if (auth.user?.role == "ROLE_RESTAURANT_OWNER") {
  //     dispatch(getRestaurantByUserId(auth.jwt || jwt));
  //   }
  // }, [auth.user]);
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      {/* <Navbar /> */}
      {/* <HomePage /> */}
      <CustomerRoutes />
    </ThemeProvider>
  );
}

export default App;
