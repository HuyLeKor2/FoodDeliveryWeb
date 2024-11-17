import logo from './logo.svg';
import './App.css';
import { ThemeProvider } from '@emotion/react';
import darkTheme from './theme/DarkTheme';
import { CssBaseline } from '@mui/material';
import './index.css';
import { Navbar } from './customers/component/Navbar/Navbar';
import { Home } from './customers/component/Home/Home';
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
