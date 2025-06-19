import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Admin from '../Admin/Admin';
import AdminDashboard from '../Admin/Dashboard/AdminDashboard';
import SuperAdmin from '../SuperAdmin/SuperAdmin';
import NotFound from '../customers/pages/NotFound/NotFound';
import { useSelector } from 'react-redux';
import CreateRestaurantForm from '../Admin/AddRestaurants/CreateRestaurantForm';

const AdminRouters = () => {
  const { auth, restaurant } = useSelector((store) => store);

  // Debug logging
  console.log('=== AdminRouters Debug ===');
  console.log('restaurant.usersRestaurant:', restaurant.usersRestaurant);
  console.log('Type of usersRestaurant:', typeof restaurant.usersRestaurant);
  console.log('Is Array?', Array.isArray(restaurant.usersRestaurant));
  console.log(
    'Length if array:',
    Array.isArray(restaurant.usersRestaurant)
      ? restaurant.usersRestaurant.length
      : 'N/A'
  );
  console.log('Boolean check:', !!restaurant.usersRestaurant);
  console.log('Full restaurant state:', restaurant);

  // Kiểm tra điều kiện
  const shouldShowAdmin =
    restaurant.usersRestaurant &&
    (Array.isArray(restaurant.usersRestaurant)
      ? restaurant.usersRestaurant.length > 0
      : true);

  console.log('shouldShowAdmin:', shouldShowAdmin);

  return (
    <div>
      <Routes>
        <Route
          path='/*'
          element={shouldShowAdmin ? <Admin /> : <CreateRestaurantForm />}
        />
      </Routes>
    </div>
  );
};

export default AdminRouters;
