import React from 'react';
import SearchIcon from '@mui/icons-material/Search';
import { IconButton, Badge } from '@mui/material';
import PersonIcon from '@mui/icons-material/Person';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import './Navbar.css';
export const Navbar = () => {
  return (
    <div className='px-5 z-50 py-[.8rem] bg-[#e91e63]  lg:px-20 flex justify-between'>
      <div
        // onClick={navigateToHome}
        className='lg:mr-10 cursor-pointer flex items-center space-x-4'
      >
        <li className='logo font-semibold text-gray-300 text-2xl'>HuyLeFood</li>
      </div>
      {/* <li className="font font-semibold">Home</li> */}

      <div className='flex items-center space-x-2 1g:space-x-10'>
        <div className=''>
          <IconButton>
            <SearchIcon sx={{ fontSize: '1.5rem' }} />
          </IconButton>
        </div>

        <div className=''>
          <IconButton>
            <PersonIcon sx={{ fontSize: '2rem' }} />
          </IconButton>
        </div>

        <div className=''>
          <Badge color='primary' badgeContent={3}>
            <ShoppingCartIcon sx={{ fontSize: '1.5rem' }} />
          </Badge>
        </div>
      </div>
    </div>
  );
};
