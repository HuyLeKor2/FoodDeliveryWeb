import React from 'react';
import { Route, Routes } from 'react-router-dom';
import HomePage from '../customers/pages/Home/HomePage';
import Navbar from '../customers/components/Navbar/Navbar';
import Profile from '../customers/pages/Profile/Profile';
import Restaurant from '../customers/pages/Restaurant/Restaurant';
import Cart from '../customers/pages/Cart/Cart';
import PaymentSuccess from '../customers/pages/PaymentSuccess/PaymentSuccess';
const CustomerRoutes = () => {
  return (
    <div className='relative'>
      <nav className='sticky top-0 z-50'>
        <Navbar />
      </nav>
      <Routes>
        <Route exact path='/' element={<HomePage />} />
        <Route exact path='/account/:register' element={<HomePage />} />
        <Route
          exact
          path='/restaurant/:city/:title/:id'
          element={<Restaurant />}
        />
        <Route path='/cart' element={<Cart />} />
        <Route path='/payment/success/:id' element={<PaymentSuccess />} />
        <Route path='/my-profile/*' element={<Profile />} />
        {/* <Route path='/search' element={<Search />} />
        <Route
          path='/admin/add-restaurant'
          element={<CreateRestaurantForm />}
        />
        <Route
          exact
          path='/password_change_success'
          element={<PasswordChangeSuccess />}
        />
        <Route exact path='/*' element={<NotFound />} /> */}
      </Routes>
    </div>
  );
};

export default CustomerRoutes;
