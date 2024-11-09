import logo from './logo.svg';
import './App.css';
import { ThemeProvider } from '@emotion/react';
import { Navbar } from './component/Navbar/Navbar';
import darkTheme from './theme/DarkTheme';
import { CssBaseline } from '@mui/material';
import { Home } from './component/Home/Home';
import './index.css';
function App() {
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <Navbar />
      <Home />
    </ThemeProvider>
  );
}

export default App;
